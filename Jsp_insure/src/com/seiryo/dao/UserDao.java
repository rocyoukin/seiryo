package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.MyUserInfo;

public interface UserDao {
	

	boolean insertUser(MyUserInfo myUserInfo);
	
	List<MyUserInfo> selectAllUserInfo();

	boolean upDataUserInfo(MyUserInfo myUserInfo);
}
