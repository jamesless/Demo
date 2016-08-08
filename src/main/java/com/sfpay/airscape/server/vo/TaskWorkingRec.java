package com.sfpay.airscape.server.vo;

import java.util.Date;

/**
 * 
 * @author sfhq1588
 */
public class TaskWorkingRec {
	private Integer id;
	
    private String taskType ;
    
    private String status;
    
    private Date startTime;
    
    private String isErr;
    
    private Date endTime;


    public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getIsErr() {
		return isErr;
	}

	public void setIsErr(String isErr) {
		this.isErr = isErr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}