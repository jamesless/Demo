/**
 * =================================================================
 * 版权所有 2011-2020 泰海网络支付服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；
 * 不允许对程序代码以任何形式任何目的的再发布
 * =================================================================
 */
package com.sfpay.airscape.server.vo.auxiliary;

import java.io.Serializable;

/**
 * 类说明：<br>
 * 
 * 
 * <p>
 * 详细描述：<br>
 * 
 * @author sfhq1029
 * </p>
 * 
 * 
 * CreateDate: 2015-12-29
 */
public class DepenSkeletonModule implements Serializable{
	private static final long serialVersionUID = 1L;
	private int dependenceId ;
	private String groupId ;
	private String artifactId;
	private String version;
	private String depenGroupId;
	private String depenArtifactId;
	private String depenVersion;
	public int getDependenceId() {
		return dependenceId;
	}
	public void setDependenceId(int dependenceId) {
		this.dependenceId = dependenceId;
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
	public String getVersion() {
		return version;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDepenGroupId() {
		return depenGroupId;
	}
	public void setDepenGroupId(String depenGroupId) {
		this.depenGroupId = depenGroupId;
	}
	public void setDepenArtifactId(String depenArtifactId) {
		this.depenArtifactId = depenArtifactId;
	}
	public String getDepenVersion() {
		return depenVersion;
	}
	public String getDepenArtifactId() {
		return depenArtifactId;
	}
	public void setDepenVersion(String depenVersion) {
		this.depenVersion = depenVersion;
	}
	
	
}
