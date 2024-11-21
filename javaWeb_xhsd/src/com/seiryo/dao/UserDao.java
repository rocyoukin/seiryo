package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.UserInfo;

/**
 *@ClassName: UserDao
 *@Description: 用户数据层接口
 *@author lzx
 *@date 2024-11-13 11:31:49
 */
public interface UserDao {
	
	//用户注册
	boolean insertUserInfo(UserInfo userInfo);
	
	//获取所有用户数据
	List<UserInfo> selectUserInfo();
	
	//更改用户数据
	boolean upDataUserInfo(UserInfo newUserInfo);
	
}
