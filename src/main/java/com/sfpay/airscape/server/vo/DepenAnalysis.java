package com.sfpay.airscape.server.vo;

import java.util.Date;

/**
 * 
 * @author sfhq1588
 */
public class DepenAnalysis {
    private Integer dependenceId;

    private String groupId;

    private String artifactId;

    private String version;

    private String depenGroupId;

    private String depenArtifactId;

    private String depenVersion;

    private String depenOnGroupId;

    private String depenOnArtifactId;

    private String depenOnVersion;

    private String status;

    private Date createTime;

    private Date updateTime;

    public Integer getDependenceId() {
        return dependenceId;
    }

    public void setDependenceId(Integer dependenceId) {
        this.dependenceId = dependenceId;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getDepenGroupId() {
        return depenGroupId;
    }

    public void setDepenGroupId(String depenGroupId) {
        this.depenGroupId = depenGroupId == null ? null : depenGroupId.trim();
    }

    public String getDepenArtifactId() {
        return depenArtifactId;
    }

    public void setDepenArtifactId(String depenArtifactId) {
        this.depenArtifactId = depenArtifactId == null ? null : depenArtifactId.trim();
    }

    public String getDepenVersion() {
        return depenVersion;
    }

    public void setDepenVersion(String depenVersion) {
        this.depenVersion = depenVersion == null ? null : depenVersion.trim();
    }

    public String getDepenOnGroupId() {
        return depenOnGroupId;
    }

    public void setDepenOnGroupId(String depenOnGroupId) {
        this.depenOnGroupId = depenOnGroupId == null ? null : depenOnGroupId.trim();
    }

    public String getDepenOnArtifactId() {
        return depenOnArtifactId;
    }

    public void setDepenOnArtifactId(String depenOnArtifactId) {
        this.depenOnArtifactId = depenOnArtifactId == null ? null : depenOnArtifactId.trim();
    }

    public String getDepenOnVersion() {
        return depenOnVersion;
    }

    public void setDepenOnVersion(String depenOnVersion) {
        this.depenOnVersion = depenOnVersion == null ? null : depenOnVersion.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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