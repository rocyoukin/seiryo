package com.seiryo.entity;

/**
 *@ClassName: logInfo
 *@Description: ������־��
 *@author lzx
 *@date 2024-11-13 11:03:31
 */
public class LogInfo {

	//��־���
	private Integer logId;
	
	//�û�ID
	private Integer logUserId;
	
	//ͼ��ID
	private Integer logBookId;
	
	//����ʱ��
	private String initTime;
	
	//����ʱ��
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
