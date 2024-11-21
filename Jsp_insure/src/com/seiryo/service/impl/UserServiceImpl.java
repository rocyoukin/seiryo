package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.UserDao;
import com.seiryo.dao.impl.UserDaoImpl;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public boolean insertUser(MyUserInfo myUserInfo) {
		return userDao.insertUser(myUserInfo);
	}

	@Override
	public List<MyUserInfo> selectAllUserInfo() {
		return userDao.selectAllUserInfo();
	}

	@Override
	public boolean pdUserEmail(String email) {
		List<MyUserInfo> userList = userDao.selectAllUserInfo();
		if(userList.size() > 0) {
			for(MyUserInfo user:userList) {
				if(user.getUser_email().equals(email)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public MyUserInfo pdUserLogin(String email, String pass) {
		List<MyUserInfo> userList = userDao.selectAllUserInfo();
		if(userList.size() > 0) {
			for(MyUserInfo user:userList) {
				if(user.getUser_email().equals(email)) {
					if(user.getUser_password().equals(pass)) {
						return user;
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean upDataUserInfo(MyUserInfo myUserInfo) {
		return userDao.upDataUserInfo(myUserInfo);
	}

}
