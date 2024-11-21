package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.UserDao;
import com.seiryo.dao.impl.UserDaoImpl;
import com.seiryo.entity.UserInfo;
import com.seiryo.service.UserService;

/**
 *@ClassName: UserServiceImpl
 *@Description: 用户服务层接口实现类
 *@author lzx
 *@date 2024-11-13 05:55:51
 */
public class UserServiceImpl implements UserService {
	
	//数据层接口指向实现类
	private UserDao userDao = new UserDaoImpl();
	
	/**
	 *@MethodName: insertUser
	 *@Description:用户注册
	 *@author lzx
	 *@date 2024-11-13 05:57:30
	 */
	@Override
	public boolean insertUser(UserInfo userInfo) {
		return userDao.insertUserInfo(userInfo);
	}
	
	/**
	 *@MethodName: selectAllUserInfo
	 *@Description:获取所有用户数据
	 *@author lzx
	 *@date 2024-11-13 05:57:36
	 */
	@Override
	public List<UserInfo> selectAllUserInfo() {
		return userDao.selectUserInfo();
	}
	
	/**
	 *@MethodName: pdUserLogin
	 *@Description:用户登录
	 *@author lzx
	 *@date 2024-11-13 05:57:41
	 */
	@Override
	public UserInfo pdUserLogin(String userName, String userPass) {
		//获取数据库中所有用户数据
		List<UserInfo> userList = userDao.selectUserInfo();
		if(userList.size() > 0) {
			for(UserInfo user:userList) {
				if(user.getUserName().equals(userName)) {
					if(user.getUserPass().equals(userPass)) {
						return user;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 *@MethodName: upDateUserInfo
	 *@Description:更改用户数据
	 *@author lzx
	 *@date 2024-11-13 05:57:48
	 */
	@Override
	public boolean upDataUserInfo(UserInfo newUserInfo) {
		return userDao.upDataUserInfo(newUserInfo);
	}
	
	/**
	 *@MethodName: pdUserName
	 *@Description:判断用户账号
	 *@author lzx
	 *@date 2024-11-13 05:57:53
	 */
	@Override
	public boolean pdUserName(String userName) {
		//获取数据库中所有用户数据
		List<UserInfo> userList = userDao.selectUserInfo();
		if(userList.size() > 0) {
			for(UserInfo user:userList) {
				if(user.getUserName().equals(userName)) {
					return true;
				}
			}
		}
		return false;
	}

}
