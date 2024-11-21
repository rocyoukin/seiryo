package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.UserInfo;

/**
 *@ClassName: UserService
 *@Description: 用户服务层接口
 *@author lzx
 *@date 2024-11-13 02:31:41
 */
public interface UserService {
	
	//用户注册
	boolean insertUser(UserInfo userInfo);
	
	//获取所有用户数据
	List<UserInfo> selectAllUserInfo();
	
	//用户登录
	UserInfo pdUserLogin(String userName,String userPass);
	
	//更改用户数据
	boolean upDataUserInfo(UserInfo newUserInfo);
	
	//判断用户账号
	boolean pdUserName(String userName);
	
}
