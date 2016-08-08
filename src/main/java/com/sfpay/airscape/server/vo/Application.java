package com.sfpay.airscape.server.vo;

import java.util.Date;

/**
 * 
 * @author sfhq1588
 */
public class Application {
    private Integer id;

    private String applicationName;

    private String packaging;

    private String groupId;

    private String artifactId;

    private String pomUrl;

    private String pomDescription;

    private String version;
    
    private String originVersion;

    private String status;

    private String parentGroupId;

    private String parentArtifactId;

    private String parentVersion;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

	public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId == null ? null : artifactId.trim();
    }

    public String getPomUrl() {
        return pomUrl;
    }

    public void setPomUrl(String pomUrl) {
        this.pomUrl = pomUrl == null ? null : pomUrl.trim();
    }

    public String getPomDescription() {
        return pomDescription;
    }

    public void setPomDescription(String pomDescription) {
        this.pomDescription = pomDescription == null ? null : pomDescription.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getOriginVersion() {
		return originVersion;
	}

	public void setOriginVersion(String originVersion) {
		this.originVersion = originVersion;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(String parentGroupId) {
        this.parentGroupId = parentGroupId == null ? null : parentGroupId.trim();
    }

    public String getParentArtifactId() {
        return parentArtifactId;
    }

    public void setParentArtifactId(String parentArtifactId) {
        this.parentArtifactId = parentArtifactId == null ? null : parentArtifactId.trim();
    }

    public String getParentVersion() {
        return parentVersion;
    }

    public void setParentVersion(String parentVersion) {
        this.parentVersion = parentVersion == null ? null : parentVersion.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
		 this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
		 this.updateTime = updateTime;
    }
}