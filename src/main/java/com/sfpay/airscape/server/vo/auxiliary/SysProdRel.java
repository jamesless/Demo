package com.sfpay.airscape.server.vo.auxiliary;

/**
 * 
 * @author sfhq1588
 */
public class SysProdRel {
	private String systemId;
	
	private String businessId;

	/**空参构造方法*/
	public SysProdRel()
	{
		
	}
	
	/**
	 * 
	 * @param businessId businessId
	 * @param systemId systemId
	 */
	public SysProdRel(String businessId,String systemId)
	{
		this.businessId = businessId;
		this.systemId = systemId;
	}
	
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	
}
