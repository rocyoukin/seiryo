package com.seiryo.entity;

/**
 *@ClassName: Book_Log
 *@Description: ��־��̳�ͼ����
 *@author lzx
 *@date 2024-11-13 11:52:36
 */
public class Book_Log extends BookInfo {

	//��־���
	private Integer logId;
	
	//�û�ID
	private Integer logUserId;
	
	//����ʱ��
	private String initTime;
	
	//����ʱ��
	private String endTime;
	
	//��ǰ��¼�û�ID
	private Integer nowUserId;

	public Book_Log(Integer bookId, String bookName, String bookType, String bookImg, Integer bookT, Integer bookS,
			Integer logId, Integer logUserId, String initTime, String endTime, Integer nowUserId) {
		super(bookId, bookName, bookType, bookImg, bookT, bookS);
		this.logId = logId;
		this.logUserId = logUserId;
		this.initTime = initTime;
		this.endTime = endTime;
		this.nowUserId = nowUserId;
	}
	
	public Book_Log() {
		
	}

//	public Book_Log(Integer bookId, String bookName, String bookType, String bookImg, Integer bookT, Integer bookS) {
//		super(bookId, bookName, bookType, bookImg, bookT, bookS);
//	}

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

	public Integer getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(Integer nowUserId) {
		this.nowUserId = nowUserId;
	}

	@Override
	public String toString() {
		return "Book_Log [logId=" + logId + ", logUserId=" + logUserId + ", initTime=" + initTime + ", endTime="
				+ endTime + ", nowUserId=" + nowUserId + "]";
	}
	
}
