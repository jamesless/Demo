package com.sfpay.airscape.server.vo;

import java.util.Date;

/**
 * 
 * @author sfhq1588
 */
public class CircleDependence {
    private int id;

    private String dependenceId;

    private String type;

    private String status;

    private Date createTime;

    private Date updateTime;


    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDependenceId() {
        return dependenceId;
    }

    public void setDependenceId(String dependenceId) {
        this.dependenceId = dependenceId == null ? null : dependenceId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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