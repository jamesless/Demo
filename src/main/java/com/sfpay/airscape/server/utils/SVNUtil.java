package com.sfpay.airscape.server.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import ch.qos.logback.classic.Logger;

import com.sfpay.airscape.server.exception.FindPomException;
import com.sfpay.airscape.server.task.ScheduleParseXml;
import com.sfpay.airscape.server.vo.auxiliary.SVN;

/**
 * @author sfhq593 2015年12月28日16:39:20
 **/
public class SVNUtil {
	private static Logger logger = (Logger) LoggerFactory.getLogger(SVNUtil.class);
	
	private static SVNUtil svnUtil = null;
	/**
	 * TODO SVN登录用户名
	 */
	private String userName;
	/**
	 * TODO SVN登录密码
	 */
	private String passWord;
	/**
	 * TODO SVN连接路径
	 */
	private String svnRoot;

	private List<SVN> poms;
	private SVNRepository repository = null;

	private SVNUtil() {
		super();
	}

	/**
	 * 
	 * @param userName
	 *            userName
	 * @param passWord
	 *            passWord
	 * @param svnRoot
	 *            svnRoot
	 * @return SVNUtil SVNUtil
	 */
	public static SVNUtil init(String userName, String passWord, String svnRoot) {
		logger.info("SVNUtil初始化开始");
		synchronized (SVNUtil.class) {
			if (svnUtil == null) {
				svnUtil = new SVNUtil();
				svnUtil.userName = userName;
				svnUtil.passWord = passWord;
				// 激活访问协议
				registerProtocol();
			}
		}
		svnUtil.svnRoot = svnRoot;
		svnUtil.poms = new ArrayList<SVN>();
		svnUtil.login();
		return svnUtil;
	}

	/**
	 * 
	 * @param svnRoot
	 *            svnRoot
	 * @return SVNUtil SVNUtil
	 */
	public static SVNUtil init(String svnRoot) {
		logger.info("SVNUtil初始化开始");
		svnUtil.svnRoot = svnRoot;
		return svnUtil;
	}

	/**
	 * 注册协议
	 */
	public static void registerProtocol() {
		/**
		 * TODO 定义访问SVN使用的协议 http https svn：
		 */
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();
	}

	/**
	 * 登录验证
	 * 
	 * @return boolean
	 */
	private boolean login() {
		try {
			logger.info("登录验证");
			SVNURL repositoryURL = SVNURL.parseURIEncoded(this.svnRoot);
			repository = SVNRepositoryFactory.create(repositoryURL);
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(this.userName,
					this.passWord);
			repository.setAuthenticationManager(authManager);
			return true;
		} catch (SVNException svne) {
			logger.error(svne.toString());
			ScheduleParseXml.TASK.setIsErr("1");
			return false;
		}
	}

	/**
	 * 
	 * @return List
	 * @throws Exception Exception
	 */
	public List<SVN> getSVNDir() throws Exception{
		listEntries("");
		return poms;
	}

	/**
	 * 
	 * @param path svnPath
	 * @throws Exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public void listEntries(String path) throws Exception{
//		try {
			Collection entries = repository.getDir(path, -1, null, (Collection) null);
			Iterator iterator = entries.iterator();
			ArrayList<SVN> svns = new ArrayList<SVN>();

			/**
			 * 取了对应SVN url 所有的目录
			 */
			while (iterator.hasNext()) {
				SVNDirEntry entry = (SVNDirEntry) iterator.next();
				SVN svn = new SVN();
				svn.setKind(entry.getKind().toString());
				svn.setName(entry.getName());
				svn.setRepositoryRoot(entry.getRepositoryRoot().toString());
				/**
				 * 相对于仓库的根路径，否则取不出文件
				 **/
				svn.setUrl(this.svnRoot.equals("") ? "/" + entry.getName() : "/" + path + "/" + entry.getName());
				svn.setState("file".equals(svn.getKind()) ? null : "closed");
				svn.setVersion(this.repository.getLatestRevision());
				svns.add(svn);
			}

			/**
			 * 迭代所有的目录
			 */
			boolean exist = false;
			boolean isPkg = false;
			for (SVN svn : svns) {
				String pom = svn.getUrl().substring(svn.getUrl().lastIndexOf("/") + 1, svn.getUrl().length());
				if (svn.getUrl().matches(".*\\/trunk.*") && !svn.getUrl().matches(".*\\/database.*")
						&& !svn.getUrl().matches(".*\\/META-INF.*") && "file".equals(svn.getKind()) && !"".equals(pom)
						&& "pom.xml".equals(pom.trim())) {
					poms.add(svn);
					
					/**
					 * 解析pom.xml文件
					 * 
					 */
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					svnUtil.getFileFromSVN(svn.getUrl(), outputStream, svn.getVersion());
					ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
					SAXReader reader = new SAXReader();
					Document document = reader.read(byteArrayInputStream);
					Element pElement = document.getRootElement().element("packaging");
					String packaging = pElement==null?"":pElement.getText();
					
					//假如pom是的packaging是pom类型的，则要递归目录
					if("pom".equals(packaging)){
						isPkg = true;
					}else{
						exist = true;
					}
					continue;
				}
			}
			/**
			 * 迭代所有目录及文件，未发现pom.xml 则进行子目录进行重新迭代 查找
			 * 
			 */

			if (!exist || isPkg) {
				for (SVN svn : svns) {
					if (svn.getUrl().matches(".*\\/trunk.*") && !svn.getUrl().matches(".*\\/database.*")
							&& !svn.getUrl().matches(".*\\/META-INF.*") && "dir".equals(svn.getKind())) {
						listEntries("".equals(path) ? svn.getName() : path + "/" + svn.getName());
					}
				}
			}
//		}catch (Exception e) {
//			svnRepositoryService.queryAll();
//			String urlPath = repository.getLocation().getPath();
//			String[] pathArr = urlPath.split("/");
//			String projectName = pathArr[pathArr.length-1];
//			SvnRepository svnRepository = new SvnRepository();
//			svnRepository.setRepositoryName(projectName);
//			svnRepository.setRepositoryUrl(urlPath);
//			svnRepository.setStatus("0");
//			svnRepositoryService.add(svnRepository);
//			System.out.println(repository);
//		}
	}

	/**
	 * 
	 * @param filePath
	 *            filePath
	 * @param outputStream
	 *            outputStream
	 * @param version
	 *            version
	 * @throws Exception
	 *             Exception
	 */
	public void getFileFromSVN(String filePath, OutputStream outputStream, long version) throws Exception {
		try {
			SVNProperties properties = new SVNProperties();
			repository.getFile(filePath, version, properties, outputStream);
		} catch (Error e) {
			logger.error(e.toString());
			// 当获取文件失败时,则抛出查找不到相应的pom文件错误，并进行记录
			throw new FindPomException(e.getLocalizedMessage());
		}

	}

}