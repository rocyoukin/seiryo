package com.seiryo.entity;

/**
 *@ClassName: UserInfo
 *@Description: 用户类
 *@author lzx
 *@date 2024-11-13 10:42:56
 */
public class UserInfo {

	//用户ID
	private Integer userID;
	
	//用户账号名
	private String userName;
	
	//用户密码
	private String userPass;
	
	//用户姓名
	private String userNameInfo;
	
	//用户状态
	private Integer userS;
	
	//用户类型
	private Integer userT;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserNameInfo() {
		return userNameInfo;
	}

	public void setUserNameInfo(String userNameInfo) {
		this.userNameInfo = userNameInfo;
	}

	public Integer getUserS() {
		return userS;
	}

	public void setUserS(Integer userS) {
		this.userS = userS;
	}

	public Integer getUserT() {
		return userT;
	}

	public void setUserT(Integer userT) {
		this.userT = userT;
	}

	public UserInfo(Integer userID, String userName, String userPass, String userNameInfo, Integer userS,
			Integer userT) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPass = userPass;
		this.userNameInfo = userNameInfo;
		this.userS = userS;
		this.userT = userT;
	}

	public UserInfo() {
		super();
	}

	@Override
	public String toString() {
		return "UserInfo [userID=" + userID + ", userName=" + userName + ", userPass=" + userPass + ", userNameInfo="
				+ userNameInfo + ", userS=" + userS + ", userT=" + userT + "]";
	}
	
}
