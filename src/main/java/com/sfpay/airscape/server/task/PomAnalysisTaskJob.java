package com.sfpay.airscape.server.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.sfpay.airscape.server.service.ErrorMessageService;
import com.sfpay.airscape.server.service.IAnalysisService;
import com.sfpay.airscape.server.service.TaskWorkingRecService;
import com.sfpay.airscape.server.vo.ErrorMessage;
import com.sfpay.airscape.server.vo.TaskWorkingRec;

/**
 * 定时分析pom.xml，生存结果文件，并解析入库
 * @author sfhq917 2016-01-04
 *
 */
public class PomAnalysisTaskJob {
	
	// maven 目录
	@Value("${classworldsConf}")
	private String classworldsConf;
	
	// m2.conf的位置
	@Value("${mavenHome}")
	private String mavenHome;
	
	// 所有pom文件根目录
	@Value("${pomRootPath}")
	private String pomRootPath;
	
	// 分析结果文件的存储目录
	@Value("${resultFilePath}")
	private String resultFilePath;
	
	// 运行的jar包
	@Value("${executeJarPath}")
	private String executeJarPath;
	
	// 执行的jar包
	@Value("${executeClass}")
	private String executeClass;
	
	// 执行的命令
	@Value("${executeCommand}")
	private String executeCommand;
	
	@Resource
	private IAnalysisService analysisService;
	
	@Resource
	private ErrorMessageService errorMessageService;
	
	@Resource
	private TaskWorkingRecService taskWorkingRecService;

	
	/**日志对象*/
	private static final Logger LOGGER =LoggerFactory.getLogger(PomAnalysisTaskJob.class);
	
	/**文件类型*/
	private static final String FILENAME =  "pom.xml";
	
	/**任务运行记录*/
	public static final TaskWorkingRec TASK = new TaskWorkingRec();
	
	private String order = "";
	
	/**运行状态标识*/
	private static final String NORMAL_STATUS = "0";
	private static final String ERR_STATUS = "1";
	
	
	/**
	 * 分析深层依赖关系
	 * @throws IOException IO异常
	 * @throws InterruptedException 流程中断异常
	 * @throws URISyntaxException 路径语法异常
	 */
	public void analysisDependency() throws IOException, InterruptedException,URISyntaxException {
		TASK.setTaskType("PomAnalysisTaskJob");
		TASK.setStartTime(new Date());
		TASK.setIsErr(NORMAL_STATUS);
		TASK.setStatus(ERR_STATUS);
		taskWorkingRecService.add(TASK);

		LOGGER.info("PomAnalysisTaskJob, pomRootPath:"+pomRootPath);

		// 预删除m_depen_analysis表的所有关系依赖数据
		analysisService.deleteRelations();

		// 获取目录下的所有pom.xml
		File file = new File(pomRootPath);
		List<File> fileList = getFiles(file, FILENAME);
		
		LOGGER.info("PomAnalysisTaskJob,fileList.size():"+fileList.size());
		
		// 循环解析入库
		for (int i = 0; i < fileList.size(); i++) {
			order = "";
			
			File f = fileList.get(i);

			// 根据pom.xml使用分析，并生成结果文件
			String pomPath = f.getParentFile().getPath();
			
			// 根据提供的path，执行java命令，调用maven分析命令
			callMavenToAnalysis(pomPath);

			// 获取pom.xml的上一级目录
			String fileName = f.getParentFile().getName();

			// 读取文件
			String str = analysisFile(resultFilePath + File.separator + fileName, pomPath);

			// 存储依赖关系
			analysisService.addRelations(str);
		}
		
		analysisService.mergeRelations();
		
		TASK.setEndTime(new Date());
		TASK.setStatus(NORMAL_STATUS);
		
		LOGGER.info("运行记录进库");
		taskWorkingRecService.update(TASK);
	}
	
	
	/**
	 * 递归列出所有的pom.xml
	 * @param file 文件对象
	 * @param filename 文件名
	 * @return fileList
	 */
	public static List<File> getFiles(File file, String filename) {
        List<File> fileList = new ArrayList<File>();
        File[] fs = file.listFiles();

		for (int i=0; i<fs.length; i++) {
			File f = fs[i];
			if (f.isFile()) {
				if (null != filename && !"".equals(filename) && filename.equals(f.getName())) {
					fileList.add(f);
				}
			} else {
				List<File> fileResultList = getFiles(f, filename);
				fileList.addAll(fileResultList);
			}
		}
        return fileList;
    }
	
	/**
	 * 根据提供的path，执行java命令，调用maven分析
	 * @param path pom文件的路径
	 * @throws IOException IO异常
	 */
	public void callMavenToAnalysis(String path) throws IOException {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("java ");
		strBuffer.append("-Dclassworlds.conf=");
		strBuffer.append(classworldsConf);
		strBuffer.append(" -Dmaven.home=");
		strBuffer.append(mavenHome);
		strBuffer.append(" -Duser.dir=");
		strBuffer.append(path);
		strBuffer.append(" -DresultFilePath=");
		strBuffer.append(resultFilePath);
		strBuffer.append(" -classpath ");
		strBuffer.append(executeJarPath);
		strBuffer.append(" ");
		strBuffer.append(executeClass);
		strBuffer.append(" ");
		strBuffer.append(executeCommand);

		InputStream  inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader  = null;
		// 执行java命令，调用maven分析
		try {
			order = strBuffer.toString();
			Process process = Runtime.getRuntime().exec(order);
			inputStream = process.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			reader = new BufferedReader(inputStreamReader);
			
			String str = reader.readLine();
			while (null != str) {
				str = reader.readLine();
				if (null == str) {
					process.destroy();
				}
			}
		} catch (Exception e) {
			LOGGER.error("error callsPomAnalysisTaskJob user java command to call maven: "
					+ strBuffer.toString());
			TASK.setIsErr(ERR_STATUS);
		} finally {
			if (null != reader) {
				reader.close();
			}
			if (null != inputStreamReader) {
				inputStreamReader.close();
			}
			if (null != inputStream) {
				inputStream.close();
			}
		}

		LOGGER.info("PomAnalysisTaskJob user java command to call maven: "
				+ strBuffer.toString());

	}
	
	
	/**
	 * 解析文件获取文件内容
	 * @param filePath 结果文件根目录
	 * @param pomPath pom文件路径
	 * @return 字符串
	 * @throws IOException IO异常
	 */
	public String analysisFile(String filePath, String pomPath) throws IOException {
		String path=filePath;
		File f = new File(resultFilePath);
		File [] fs = null;
		if(f.isDirectory())
		{
			fs = f.listFiles();
		}
		if(null != fs)
		{
			for(File fe:fs)
			{
				if(fe.getPath().contains(filePath))
				{
					path = fe.getPath();
					break;
				}
			}
		}
		StringBuffer stringBuffer = new StringBuffer();
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader =  null;
		String str = null;
		
		File file = new File(path);
		
		// if file can not exist, generate one fail record
		if(!file.isFile()){
			ErrorMessage  errorMessage = new ErrorMessage();
			errorMessage.setErrMsg(pomPath+"/pom.xml could not be analysis....");
			errorMessage.setErrType(ERR_STATUS);
			errorMessage.setCreateTime(new Date());
			errorMessage.setAppId(path);
			errorMessage.setSvnPath(pomPath);
			errorMessage.setErrOrder(order);
			List<ErrorMessage> errList = errorMessageService.query(errorMessage);
			if(errList.size() == 0){
				errorMessageService.add(errorMessage);
			}else{
				errorMessageService.update(errorMessage);
			}
			TASK.setIsErr(ERR_STATUS);
			return null;
		}
		
		try {
			fileInputStream = new FileInputStream(path);	
			inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			while ((str = bufferedReader.readLine()) != null) {
				stringBuffer.append(str);
			}
		} catch (FileNotFoundException e) {
			TASK.setIsErr(ERR_STATUS);
			LOGGER.error("PomAnalysisTaskJob  can not found this file:"+ path);
		} catch (IOException e) {
			TASK.setIsErr(ERR_STATUS);
			LOGGER.error("PomAnalysisTaskJob IOException:"+ path);
		} finally {
			if(null != bufferedReader){
				bufferedReader.close();	
			}
			if(null != inputStreamReader){
				inputStreamReader.close();
			}
			if(null != fileInputStream){
				fileInputStream.close();
			}
		}
		return stringBuffer.toString();
	}
	

}
