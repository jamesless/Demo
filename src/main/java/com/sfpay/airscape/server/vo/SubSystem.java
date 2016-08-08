package com.sfpay.airscape.server.vo;

import java.util.Date;

/**
 * 
 * @author sfhq1588
 */
public class SubSystem {
    private Integer subSystemId;

    private String subSystemEname;

    private String subSystemCname;

    private String systemId;

    private String ownerName;

    private String sysFileDiretoryLink;
    
    private String description;

    private String status;
    
    private String level;
    
    private String importantLevel;

    private Date createTime;

    private Date updateTime;

    public Integer getSubSystemId() {
        return subSystemId;
    }

    public void setSubSystemId(Integer subSystemId) {
        this.subSystemId = subSystemId;
    }

    public String getSubSystemEname() {
        return subSystemEname;
    }

    public void setSubSystemEname(String subSystemEname) {
        this.subSystemEname = subSystemEname == null ? null : subSystemEname.trim();
    }

    public String getSubSystemCname() {
        return subSystemCname;
    }

    public void setSubSystemCname(String subSystemCname) {
        this.subSystemCname = subSystemCname == null ? null : subSystemCname.trim();
    }

    public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

    public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getSysFileDiretoryLink() {
		return sysFileDiretoryLink;
	}

	public void setSysFileDiretoryLink(String sysFileDiretoryLink) {
		this.sysFileDiretoryLink = sysFileDiretoryLink;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getImportantLevel() {
		return importantLevel;
	}

	public void setImportantLevel(String importantLevel) {
		this.importantLevel = importantLevel;
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