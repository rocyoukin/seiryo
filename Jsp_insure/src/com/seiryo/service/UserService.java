package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.MyUserInfo;

public interface UserService {
	
	boolean insertUser(MyUserInfo myUserInfo);
	
	List<MyUserInfo> selectAllUserInfo();
	
	boolean pdUserEmail(String email);
	
	MyUserInfo pdUserLogin(String email,String pass);
	
	boolean upDataUserInfo(MyUserInfo myUserInfo);

}
