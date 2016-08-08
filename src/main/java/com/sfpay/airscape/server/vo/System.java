package com.sfpay.airscape.server.vo;

import com.sfpay.airscape.server.vo.auxiliary.Base;

/**
 * 
 * @author sfhq1588
 */
@SuppressWarnings("serial")
public class System extends Base {
	private String systemId;
	
	private String businessId;
	
	private String systemEname;
	
	private String systemCname;
	
	private String level;
	
	private String ownerName;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSystemEname() {
		return systemEname;
	}

	public void setSystemEname(String systemEname) {
		this.systemEname = systemEname;
	}

	public String getSystemCname() {
		return systemCname;
	}

	public void setSystemCname(String systemCname) {
		this.systemCname = systemCname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
}
