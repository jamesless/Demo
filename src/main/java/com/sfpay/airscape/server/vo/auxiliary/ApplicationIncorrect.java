package com.sfpay.airscape.server.vo.auxiliary;

/**
 * 
 * 类说明：<br>
 * 
 * 
 * <p>
 * 详细描述：<br>
 * 
 * 
 * </p>
 * 
 * @author sfhq
 * 
 * CreateDate: 2016-1-13
 */
public class ApplicationIncorrect {
	private String applicationName;
	private String subSystemName;
	private String packaging;
	private String groupId;
	private String artifactId;
	private String pomUrl;
	private String pomDescription;
	private String version;
	private String level;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getSubSystemName() {
		return subSystemName;
	}

	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getPackaging() {
		return packaging;
	}


	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public void setPomUrl(String pomUrl) {
		this.pomUrl = pomUrl;
	}
	
	public String getPomUrl() {
		return pomUrl;
	}

	public void setPomDescription(String pomDescription) {
		this.pomDescription = pomDescription;
	}

	public String getPomDescription() {
		return pomDescription;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return "ApplicationIncorrect [applicationName=" + applicationName + ", subSystemName=" + subSystemName
				+ ", packaging=" + packaging + ", groupId=" + groupId + ", artifactId=" + artifactId + ", pomUrl="
				+ pomUrl + ", pomDescription=" + pomDescription + ", version=" + version + ", level=" + level + "]";
	}

}
