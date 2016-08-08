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
 * CreateDate: 2016-1-12
 */
public class ApplicationRelation {

	private String applicationName;
	private String subSystemName;
	private String  description;
	private String packaging;
	private String groupId;
	private String artifactId;
	private String version;
	private String pomUrl;
	private String pomDescription;
	private String depApplicationName;
	private String depSubSystemName;
	private String  depDescription;
	private String depPackaging;
	private String depGroupId;
	private String depArtifactId;
	private String depVersion;
	private String depPomUrl;
	private String depPomDescription;
	private String level;
	private String depLevel;
	private String depenOnGroupId;
	private String depenOnArtifactId;
	private String depenOnVersion;
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}
	public String getSubSystemName() {
		return subSystemName;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	public String getPackaging() {
		return packaging;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	public String getPomDescription() {
		return pomDescription;
	}
	public void setPomDescription(String pomDescription) {
		this.pomDescription = pomDescription;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDepApplicationName() {
		return depApplicationName;
	}
	public void setDepApplicationName(String depApplicationName) {
		this.depApplicationName = depApplicationName;
	}
	public String getDepSubSystemName() {
		return depSubSystemName;
	}
	public void setDepSubSystemName(String depSubSystemName) {
		this.depSubSystemName = depSubSystemName;
	}
	public String getDepPackaging() {
		return depPackaging;
	}
	public void setDepPackaging(String depPackaging) {
		this.depPackaging = depPackaging;
	}
	public String getDepGroupId() {
		return depGroupId;
	}
	public void setDepGroupId(String depGroupId) {
		this.depGroupId = depGroupId;
	}
	public String getDepArtifactId() {
		return depArtifactId;
	}
	public void setDepArtifactId(String depArtifactId) {
		this.depArtifactId = depArtifactId;
	}
	public String getDepPomUrl() {
		return depPomUrl;
	}
	public void setDepPomUrl(String depPomUrl) {
		this.depPomUrl = depPomUrl;
	}
	public String getDepPomDescription() {
		return depPomDescription;
	}
	public void setDepPomDescription(String depPomDescription) {
		this.depPomDescription = depPomDescription;
	}
	public String getDepVersion() {
		return depVersion;
	}
	public void setDepVersion(String depVersion) {
		this.depVersion = depVersion;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDepLevel() {
		return depLevel;
	}
	public void setDepLevel(String depLevel) {
		this.depLevel = depLevel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDepDescription() {
		return depDescription;
	}
	public void setDepDescription(String depDescription) {
		this.depDescription = depDescription;
	}
	@Override
	public String toString() {
		return "ApplicationRelation [applicationName=" + applicationName + ", subSystemName=" + subSystemName
				+ ", description=" + description + ", packaging=" + packaging + ", groupId=" + groupId + ", artifactId="
				+ artifactId + ", pomUrl=" + pomUrl + ", pomDescription=" + pomDescription + ", version=" + version
				+ ", depApplicationName=" + depApplicationName + ", depSubSystemName=" + depSubSystemName
				+ ", depDescription=" + depDescription + ", depPackaging=" + depPackaging + ", depGroupId=" + depGroupId
				+ ", depArtifactId=" + depArtifactId + ", depPomUrl=" + depPomUrl + ", depPomDescription="
				+ depPomDescription + ", depVersion=" + depVersion + ", level=" + level + ", depLevel=" + depLevel
				+ ", depenOnGroupId= "+depenOnGroupId+", depenOnArtifactId="+depenOnArtifactId+", depenOnVersion="+depenOnVersion+"]";
	}
	public String getDepenOnGroupId() {
		return depenOnGroupId;
	}
	public void setDepenOnGroupId(String depenOnGroupId) {
		this.depenOnGroupId = depenOnGroupId;
	}
	public String getDepenOnArtifactId() {
		return depenOnArtifactId;
	}
	public void setDepenOnArtifactId(String depenOnArtifactId) {
		this.depenOnArtifactId = depenOnArtifactId;
	}
	public String getDepenOnVersion() {
		return depenOnVersion;
	}
	public void setDepenOnVersion(String depenOnVersion) {
		this.depenOnVersion = depenOnVersion;
	}
	

	
}
