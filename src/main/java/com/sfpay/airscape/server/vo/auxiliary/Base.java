package com.sfpay.airscape.server.vo.auxiliary;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sfpay.airscape.server.utils.CustomDateDeserializer;
import com.sfpay.airscape.server.utils.CustomDateSerializer;

/**
 * @author sfhq593 2016年1月12日17:41:41 基础对象，封装部分实例对象共有属性字段
 */
public class Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 记录生成时间
	 */
	private Date createTime;

	/**
	 * 记录修改时间
	 */
	private Date updateTime;

	/**
	 * 产品业务分类ID
	 */
	private String businessId;

	/**
	 * 业务大类名称
	 */
	private String businessName;

	/**
	 * 子系统名称
	 */
	private String subSystemName;
	/**
	 * 包类型
	 **/
	public String packaging;
	/**
	 * 模块的GROUP_ID
	 */
	private String groupId;

	/**
	 * 模块的ARTIFACT_ID
	 */
	private String artifactId;

	/**
	 * 解析 pom文件所在的路径
	 */

	private String pomUrl;
	/**
	 * 模块的VERSION
	 */
	private String version;

	/**
	 * pom文件描述
	 */
	private String pomDescription;

	/**
	 * 记录描述
	 */
	private String description;

	/**
	 * qaSequence
	 */
	private String qaSequence;

	/**
	 * svnTrunkUrl
	 */
	private String svnTrunkUrl;

	private String depenGroupId;

	private String depenArtifactId;

	private String depenVersion;

	private String role = "C";

	private String parentName;

	private String status;

	private String applicationName;

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSubSystemName() {
		return subSystemName;
	}

	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
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
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getPomUrl() {
		return pomUrl;
	}

	public void setPomUrl(String pomUrl) {
		this.pomUrl = pomUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPomDescription() {
		return pomDescription;
	}

	public void setPomDescription(String pomDescription) {
		this.pomDescription = pomDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQaSequence() {
		return qaSequence;
	}

	public void setQaSequence(String qaSequence) {
		this.qaSequence = qaSequence;
	}

	public String getSvnTrunkUrl() {
		return svnTrunkUrl;
	}

	public void setSvnTrunkUrl(String svnTrunkUrl) {
		this.svnTrunkUrl = svnTrunkUrl;
	}

	public String getDepenGroupId() {
		return depenGroupId;
	}

	public void setDepenGroupId(String depenGroupId) {
		this.depenGroupId = depenGroupId;
	}

	public String getDepenArtifactId() {
		return depenArtifactId;
	}

	public void setDepenArtifactId(String depenArtifactId) {
		this.depenArtifactId = depenArtifactId;
	}

	public String getDepenVersion() {
		return depenVersion;
	}

	public void setDepenVersion(String depenVersion) {
		this.depenVersion = depenVersion;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
