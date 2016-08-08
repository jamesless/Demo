package com.sfpay.airscape.server.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.sfpay.airscape.server.exception.FindPomException;
import com.sfpay.airscape.server.task.ScheduleParseXml;
import com.sfpay.airscape.server.vo.AppDependences;
import com.sfpay.airscape.server.vo.Application;
import com.sfpay.airscape.server.vo.ErrorMessage;
import com.sfpay.airscape.server.vo.SvnRepository;
import com.sfpay.airscape.server.vo.auxiliary.SVN;

/**
 * @author sfhq593 2015年12月28日16:01:23 
 */
public class ParseXml {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ParseXml.class);
	private static Properties properties = null;
	private static List<SvnRepository> svnRepositories = null;
	private static String userName = null;
	private static String passWord = null;
	private static ParseXml parseXml = null;
	private static final int SIZE = 1025;
	private static String path;
	
	private static final String VERSION = "version";
	private static final String GROUPID = "groupId";
	
	private static final String ERR_STATUS = "1";
	private static final String NORMAL_STATUS = "0";

	private ParseXml() {
		super();
	}

	/**
	 * 
	 * @param svnRepositories
	 *            svnRepositories
	 * @param path
	 *            path
	 * @return ParseXml ParseXml
	 * @throws IOException
	 *             IOException
	 */
	public static ParseXml init(List<SvnRepository> svnRepositories, String path) throws IOException {
		synchronized (ParseXml.class) {
			if (parseXml == null) {
				parseXml = new ParseXml();
				properties = new Properties();
				InputStream inStream = ParseXml.class.getClassLoader()
						.getResourceAsStream("properties/svn-account.properties");
				properties.load(inStream);
				ParseXml.userName = properties.getProperty("userName");
				ParseXml.passWord = properties.getProperty("passWord");
			}
			ParseXml.svnRepositories = svnRepositories;
			ParseXml.path = path;
		}
		return parseXml;
	}
	
	/**
	 * 获取pom文件中引用变量的值，没有引用值则返回本文件版本号
	 * @param str	需要解析变量名的字符串
	 * @param propertiesNode	pom文件中的properties节点
	 * @return
	 */
	private static String getCiteValue(String str, Element propertiesNode,Element parent){
		String result = "";
		try {
			if(str.matches("\\$\\{.*")){
				String citeName = str.substring(2, str.length()-1);
				if(null != propertiesNode && null != propertiesNode.element(citeName)){
					result =  propertiesNode.element(citeName).getText();
				}else if(null != parent) {
					result = parent.element(VERSION).getText();
				}
			}else{
				result = str;
			}
		} catch (Exception e) {
			ScheduleParseXml.TASK.setIsErr(ERR_STATUS);
			LOGGER.info("getCiteValue异常,参数1："+str+"参数2:"+propertiesNode);
		}
		return result;
	}
	
	private static String getVersion(Element parent){
		if(null != parent){
			return parent.element(VERSION).getText();
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * @return map 结果集
	 * @throws Exception Exception
	 */
	public static Map<String, List<?>> parseSvnDirectory() throws Exception {
		ErrorMessage errorMessage = new ErrorMessage();
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		/**
		 * 关键词Key为Application 存放模块原始集合信息 --关键词Key为AppDependences
		 * 存放模块原始依赖集合信息--关键字为Error表示所有出错信息
		 */
		List<Application> applications = new ArrayList<Application>();
		List<AppDependences> dppDependences = new ArrayList<AppDependences>();
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		map.put("Application", applications);
		map.put("AppDependences", dppDependences);
		map.put("Error", errorMessages);
		for (SvnRepository svnRepository : svnRepositories) {
			if(NORMAL_STATUS.equals(svnRepository.getStatus())){
				continue;
			}
			String repositoryUrl = svnRepository.getRepositoryUrl();
			
			SVNUtil svnUtil = SVNUtil.init(ParseXml.userName, ParseXml.passWord, repositoryUrl);
			List<SVN> svnDir = null;
			try{
				svnDir = svnUtil.getSVNDir();
			}
			catch(Exception e)
			{
				LOGGER.error("获取SVN地址异常:"+e.toString());
				ScheduleParseXml.TASK.setIsErr(ERR_STATUS);
				continue;
			}
			for (SVN svn : svnDir) {
				try {
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					svnUtil.getFileFromSVN(svn.getUrl(), outputStream, svn.getVersion());
					ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
					ByteArrayInputStream bufferInputStream = new ByteArrayInputStream(outputStream.toByteArray());
					/**
					 * 解析pom.xml文件
					 * 
					 */

					SAXReader reader = new SAXReader();
					Document document = reader.read(byteArrayInputStream);

					/**
					 * 根据所关注的字段进行解析
					 * 
					 */

					Element rootElement = document.getRootElement();
					Element parent = rootElement.element("parent");
					Element artifactId = rootElement.element("artifactId");
					Element packaging = rootElement.element("packaging");
					Element groupId = rootElement.element(GROUPID);
					Element version = rootElement.element(VERSION);
					Element description = rootElement.element("description");
					Element propertiesNode = rootElement.element("properties");
					
					
					//实现packaging其他目录的扫描
					
					Application application = new Application();
					try {
						application.setApplicationName(null != artifactId ? artifactId.getText() : "");
						application.setArtifactId(null != artifactId ? artifactId.getText() : "");
						application.setPackaging(null != packaging ? packaging.getText() : "jar");
						application.setCreateTime(new Date());
						//判断pom文件当中是否存在引用关系的变量，存在则取properties标签中的值
						application.setVersion(
								null != version ? getCiteValue(version.getText(),propertiesNode,parent) 
										: getVersion(parent));
						application.setPomUrl(repositoryUrl+svn.getUrl());
						application.setPomDescription(null != description ? description.getText() : "");
						application.setGroupId(null != groupId ? groupId.getText() : "");
						application.setStatus(NORMAL_STATUS);
						
						if("".equals(application.getGroupId()) && null != parent){
								application.setGroupId(parent.element(GROUPID).getText());
						}
						
						
						if(null != rootElement.element("parent")){
							Element parentGroupId = parent.element(GROUPID);
							Element parentArtifactId = parent.element("artifactId");
							Element parentVersion = parent.element(VERSION);
							application.setParentGroupId(null != parentGroupId? parentGroupId.getText() : "");
							application.setParentArtifactId(null != parentArtifactId ? parentArtifactId.getText() : "");
							application.setParentVersion(null != parentVersion ? getCiteValue(parentVersion.getText(),propertiesNode,parent) : "");
						}
						
						//丢弃version中存在变量的数据
						if(null != application.getParentVersion() && application.getParentVersion().matches("\\$\\{.*")){
								continue;
						}
						
						if(StringUtils.isNotEmpty(application.getVersion()))
						{
							/**
							 * 加入集合
							 */
							applications.add(application);
						}
					}catch (Exception e) {
						ScheduleParseXml.TASK.setIsErr(ERR_STATUS);
						errorMessages.add(traceErrorMessage(errorMessage, svn, e));
						LOGGER.error("application中发生异常:"+e.toString());
					}

					/**
					 * 解析依赖关系
					 */

					try {
						/**
						 * 取到所有的dependency节点
						 */
						if (null != rootElement.element("dependencies")) {
							@SuppressWarnings("unchecked")
							List<Element> dependency = rootElement.element("dependencies").elements("dependency");
							if (null != dependency) {
								for (Element ele : dependency) {
									AppDependences dependences = new AppDependences();
									dependences.setArtifactId(application.getArtifactId());
									dependences.setGroupId(application.getGroupId());
									dependences.setVersion(application.getVersion());
									dependences.setPackaging(application.getPackaging());
									dependences.setStatus(NORMAL_STATUS);
									dependences.setCreateTime(new Date());
									dependences.setDepenGroupId(
											null != ele.element(GROUPID)? ele.element(GROUPID).getText() : "");
									dependences.setDepenArtifactId(
											null != ele.element("artifactId") ? ele.element("artifactId").getText() : "");
									dependences.setDepenVersion(
											null != ele.element(VERSION) ? 
													getCiteValue(ele.element(VERSION).getText(),propertiesNode,parent) : "");
									
									/**
									 * 当被依赖模块名称 = 依赖模块名称 + “-skeleton” 时，ROLE值取“P"
									 */
									if (dependences.getDepenArtifactId()
											.matches(application.getArtifactId() + "-skeleton")) {
										dependences.setRole("P");
									}else{
										dependences.setRole("C");
									}
									if(StringUtils.isNotEmpty(dependences.getDepenVersion()) 
											&& StringUtils.isNotEmpty(dependences.getVersion()))
									{
										dppDependences.add(dependences);
									}
								}
							}
						}
					} catch (Exception e) {
						ScheduleParseXml.TASK.setIsErr(ERR_STATUS);
						errorMessages.add(traceErrorMessage(errorMessage, svn, e));
						LOGGER.error("dependences中发生异常:"+e.toString());
					}

					/**
					 * 将pom文件checkout至本地
					 * 
					 */

					String pomVersion = "";
					String pomGroupId = "";
					String pomArtifactId = "";
					try {
						if (!"".equals(application.getVersion().trim())) {
							pomVersion = "." + application.getVersion().trim();
						} else {
							if (version == null && parent != null) {
								pomVersion = "." + parent.element(VERSION).getText();
							}
						}

						if (!"".equals(application.getGroupId().trim())) {
							pomGroupId = application.getGroupId().trim();
						} else {
							if (groupId == null && parent != null) {
								pomGroupId = parent.element(GROUPID).getText();
							}
						}

						if (!"".equals(application.getArtifactId().trim())) {
							pomArtifactId = "." + application.getArtifactId().trim();
						}

						String fileDir = pomGroupId + pomArtifactId + pomVersion + "/" + "pom.xml";
						File pom = new File(path + "/" + fileDir);
						if (!pom.getParentFile().exists()) {
							// 如果目标文件所在的目录不存在，则创建父目录
							pom.getParentFile().mkdirs();
							// 创建文件
							pom.createNewFile();
						}
						if (pom.exists()) {
							FileOutputStream pomStream = new FileOutputStream(pom);
							byte[] b = new byte[ParseXml.SIZE];
							int i = 0;
							while ((i = bufferInputStream.read(b)) != -1) {
								pomStream.write(b, 0, i);
							}
							pomStream.flush();
							pomStream.close();
						}
						bufferInputStream.close();
					} catch (Exception e) {
						ScheduleParseXml.TASK.setIsErr(ERR_STATUS);
						errorMessages.add(traceErrorMessage(errorMessage, svn, e));
						LOGGER.error("svn checkout本地发生异常:"+e.toString());
					}
				} catch (FindPomException e) {
					ScheduleParseXml.TASK.setIsErr(ERR_STATUS);
					errorMessages.add(traceErrorMessage(errorMessage, svn, e));
					LOGGER.error(e.toString());
				} catch (Exception e) {
					ScheduleParseXml.TASK.setIsErr(ERR_STATUS);
					errorMessages.add(traceErrorMessage(errorMessage, svn, e));
					LOGGER.error(e.toString());
				}
			}
		}
		return map;
	}

	/**
	 * 跟踪错误信息
	 * @param errorMessage errorMessage对象
	 * @param svn svn对象
	 * @param e 异常对象
	 * @return ErrorMessage errorMessage
	 */
	private static ErrorMessage traceErrorMessage(ErrorMessage errorMessage,SVN svn, Exception e) {
		errorMessage.setAppId(svn.getUrl().substring(
				svn.getUrl().substring(0, svn.getUrl().lastIndexOf("/")).lastIndexOf("/") + 1,
				svn.getUrl().lastIndexOf("/")));
		errorMessage.setCreateTime(new Date());
		errorMessage.setErrType(NORMAL_STATUS);
		errorMessage.setErrMsg(e.toString());
		errorMessage.setSvnPath(svn.getRepositoryRoot() + svn.getUrl());
		errorMessage.setUpdateTime(new Date());
		ScheduleParseXml.TASK.setIsErr(ERR_STATUS);
		return errorMessage;
	}
	
}
