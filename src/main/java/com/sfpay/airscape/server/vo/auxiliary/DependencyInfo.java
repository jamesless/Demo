package com.sfpay.airscape.server.vo.auxiliary;

/**
 * 依赖关系实体类
 * @author sfhq917 2016-01-04
 *
 */
public class DependencyInfo {
	// 依赖数据ID
	private String id; 
	// 当前项目的groupId
	private String currentProjectGroupId;
	// 当前项目的artifact
	private String currentProjectArtifact;
	// 当前项目的version
	private String currentProjectVersion;
	// 当前节点的groupId
	private String currentNodeGroupId;
	// 当前节点的artifact
	private String currentNodeArtifact;
	// 当前节点的version
	private String currentNodeVersion;
	// 当前节点的父节点的groupId
	private String currentNodeParentGroupId;
	// 当前节点的父节点的artifact
	private String currentNodeParentArtifact;
	// 当前节点的父节点的version
	private String currentNodeParentVersion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrentProjectGroupId() {
		return currentProjectGroupId;
	}
	public void setCurrentProjectGroupId(String currentProjectGroupId) {
		this.currentProjectGroupId = currentProjectGroupId;
	}
	public String getCurrentProjectArtifact() {
		return currentProjectArtifact;
	}
	public void setCurrentProjectArtifact(String currentProjectArtifact) {
		this.currentProjectArtifact = currentProjectArtifact;
	}
	public String getCurrentProjectVersion() {
		return currentProjectVersion;
	}
	public void setCurrentProjectVersion(String currentProjectVersion) {
		this.currentProjectVersion = currentProjectVersion;
	}
	public String getCurrentNodeGroupId() {
		return currentNodeGroupId;
	}
	public void setCurrentNodeGroupId(String currentNodeGroupId) {
		this.currentNodeGroupId = currentNodeGroupId;
	}
	public String getCurrentNodeArtifact() {
		return currentNodeArtifact;
	}
	public void setCurrentNodeArtifact(String currentNodeArtifact) {
		this.currentNodeArtifact = currentNodeArtifact;
	}
	public String getCurrentNodeVersion() {
		return currentNodeVersion;
	}
	public void setCurrentNodeVersion(String currentNodeVersion) {
		this.currentNodeVersion = currentNodeVersion;
	}
	public String getCurrentNodeParentGroupId() {
		return currentNodeParentGroupId;
	}
	public void setCurrentNodeParentGroupId(String currentNodeParentGroupId) {
		this.currentNodeParentGroupId = currentNodeParentGroupId;
	}
	public String getCurrentNodeParentArtifact() {
		return currentNodeParentArtifact;
	}
	public void setCurrentNodeParentArtifact(String currentNodeParentArtifact) {
		this.currentNodeParentArtifact = currentNodeParentArtifact;
	}
	public String getCurrentNodeParentVersion() {
		return currentNodeParentVersion;
	}
	public void setCurrentNodeParentVersion(String currentNodeParentVersion) {
		this.currentNodeParentVersion = currentNodeParentVersion;
	}
	@Override
	public String toString() {
		return "DependencyInfo [id=" + id + ", currentProjectGroupId="
				+ currentProjectGroupId + ", currentProjectArtifact="
				+ currentProjectArtifact + ", currentProjectVersion="
				+ currentProjectVersion + ", currentNodeGroupId="
				+ currentNodeGroupId + ", currentNodeArtifact="
				+ currentNodeArtifact + ", currentNodeVersion="
				+ currentNodeVersion + ", currentNodeParentGroupId="
				+ currentNodeParentGroupId + ", currentNodeParentArtifact="
				+ currentNodeParentArtifact + ", currentNodeParentVersion="
				+ currentNodeParentVersion + "]";
	}
	
}
