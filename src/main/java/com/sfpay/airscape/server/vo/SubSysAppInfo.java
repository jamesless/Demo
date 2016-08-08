package com.sfpay.airscape.server.vo;

import java.util.Date;

/**
 * 
 * @author sfhq1588
 */
public class SubSysAppInfo {
    private Integer id;

    private Integer applicationId;

    private Integer subSystemId;
    
    private String level;
    
    private String packaging;
    
    private String applicationName;

    private String subSystemName;

    private Date createTime;

    private Date updateTime;

    private String groupId;
    
    private String artifactId;
    
    private String pomUrl;
    
    private String version;
    
    private String status;
    
    private String businessName;
    
    private String systemEname;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getSubSystemId() {
        return subSystemId;
    }

    public void setSubSystemId(Integer subSystemId) {
        this.subSystemId = subSystemId;
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

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getSubSystemName() {
		return subSystemName;
	}

	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}
	
    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getPackaging() {
		return packaging;
	}
	
	public void setPackaging(String packaging) {
		this.packaging = packaging;
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

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}
	
	public void setPomUrl(String pomUrl) {
		this.pomUrl = pomUrl;
	}
	
	public String getVersion() {
		return version;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSystemEname() {
		return systemEname;
	}

	public void setSystemEname(String systemEname) {
		this.systemEname = systemEname;
	}

//	private String formateDate(Date d)
//    {
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//    	return sdf.format(d);
//    }
    
//    private Date formateDate(String s)
//    {
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//    	try {
//			return sdf.parse(s);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//    }

}