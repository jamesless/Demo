/**
 * =================================================================
 * 版权所有 2011-2020 泰海网络支付服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；
 * 不允许对程序代码以任何形式任何目的的再发布
 * =================================================================
 */
package com.sfpay.airscape.server.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明：<br>
 * 
 * 
 * <p>
 * 详细描述：<br>
 * 
 * 
 * </p>
 * @author sfhq1029
 * 
 * CreateDate: 2015-12-29
 */
public class DependencyAnalysis implements Serializable{
	private static final long serialVersionUID = 1L;
	private int dependenceId ;
	private String groupId ;
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
	public void setDependenceId(int dependenceId) {
		this.dependenceId = dependenceId;
	}
	public int getDependenceId() {
		return dependenceId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersion() {
		return version;
	}
	public void setDepenGroupId(String depenGroupId) {
		this.depenGroupId = depenGroupId;
	}
	public String getDepenGroupId() {
		return depenGroupId;
	}
	public void setDepenArtifactId(String depenArtifactId) {
		this.depenArtifactId = depenArtifactId;
	}
	public String getDepenArtifactId() {
		return depenArtifactId;
	}
	public void setDepenVersion(String depenVersion) {
		this.depenVersion = depenVersion;
	}
	public String getDepenVersion() {
		return depenVersion;
	}
	public void setDepenOnGroupId(String depenOnGroupId) {
		this.depenOnGroupId = depenOnGroupId;
	}
	public String getDepenOnGroupId() {
		return depenOnGroupId;
	}
	public void setDepenOnArtifactId(String depenOnArtifactId) {
		this.depenOnArtifactId = depenOnArtifactId;
	}
	public String getDepenOnArtifactId() {
		return depenOnArtifactId;
	}
	public void setDepenOnVersion(String depenOnVersion) {
		this.depenOnVersion = depenOnVersion;
	}
	public String getDepenOnVersion() {
		return depenOnVersion;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
