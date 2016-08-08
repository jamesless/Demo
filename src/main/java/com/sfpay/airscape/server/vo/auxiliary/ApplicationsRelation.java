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
public class ApplicationsRelation {

	private String groupId;
	private String artifactId;
	private String version;
	private String depGroupId;
	private String depArtifactId;
	private String depVersion;
	private  String depenOnGroupId;
	private String depenOnArtifactId;
	private String depenOnVersion;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public String getDepVersion() {
		return depVersion;
	}
	public void setDepVersion(String depVersion) {
		this.depVersion = depVersion;
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
	@Override
	public String toString() {
		return "ApplicationsRelation [groupId=" + groupId + ", artifactId=" 
				+ artifactId + ", version=" + version + ", depGroupId=" + depGroupId 
				+ ", depArtifactId=" + depArtifactId + ", depVersion=" + depVersion
				+ ", depenOnGroupId=" + depenOnGroupId + ", depenOnArtifactId=" 
				+ depenOnArtifactId	+ ", depenOnVersion=" + depenOnVersion + "]";
	}
	
	
}
