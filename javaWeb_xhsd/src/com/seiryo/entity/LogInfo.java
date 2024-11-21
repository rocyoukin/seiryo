package com.seiryo.entity;

/**
 *@ClassName: logInfo
 *@Description: 借书日志类
 *@author lzx
 *@date 2024-11-13 11:03:31
 */
public class LogInfo {

	//日志编号
	private Integer logId;
	
	//用户ID
	private Integer logUserId;
	
	//图书ID
	private Integer logBookId;
	
	//借书时间
	private String initTime;
	
	//还书时间
	private String endTime;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(Integer logUserId) {
		this.logUserId = logUserId;
	}

	public Integer getLogBookId() {
		return logBookId;
	}

	public void setLogBookId(Integer logBookId) {
		this.logBookId = logBookId;
	}

	public String getInitTime() {
		return initTime;
	}

	public void setInitTime(String initTime) {
		this.initTime = initTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public LogInfo(Integer logId, Integer logUserId, Integer logBookId, String initTime, String endTime) {
		super();
		this.logId = logId;
		this.logUserId = logUserId;
		this.logBookId = logBookId;
		this.initTime = initTime;
		this.endTime = endTime;
	}

	public LogInfo() {
		super();
	}

	@Override
	public String toString() {
		return "logInfo [logId=" + logId + ", logUserId=" + logUserId + ", logBookId=" + logBookId + ", initTime="
				+ initTime + ", endTime=" + endTime + "]";
	}
	
}
