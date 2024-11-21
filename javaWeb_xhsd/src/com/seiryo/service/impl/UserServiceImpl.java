package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.UserDao;
import com.seiryo.dao.impl.UserDaoImpl;
import com.seiryo.entity.UserInfo;
import com.seiryo.service.UserService;

/**
 *@ClassName: UserServiceImpl
 *@Description: �û������ӿ�ʵ����
 *@author lzx
 *@date 2024-11-13 05:55:51
 */
public class UserServiceImpl implements UserService {
	
	//���ݲ�ӿ�ָ��ʵ����
	private UserDao userDao = new UserDaoImpl();
	
	/**
	 *@MethodName: insertUser
	 *@Description:�û�ע��
	 *@author lzx
	 *@date 2024-11-13 05:57:30
	 */
	@Override
	public boolean insertUser(UserInfo userInfo) {
		return userDao.insertUserInfo(userInfo);
	}
	
	/**
	 *@MethodName: selectAllUserInfo
	 *@Description:��ȡ�����û�����
	 *@author lzx
	 *@date 2024-11-13 05:57:36
	 */
	@Override
	public List<UserInfo> selectAllUserInfo() {
		return userDao.selectUserInfo();
	}
	
	/**
	 *@MethodName: pdUserLogin
	 *@Description:�û���¼
	 *@author lzx
	 *@date 2024-11-13 05:57:41
	 */
	@Override
	public UserInfo pdUserLogin(String userName, String userPass) {
		//��ȡ���ݿ��������û�����
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
	 *@Description:�����û�����
	 *@author lzx
	 *@date 2024-11-13 05:57:48
	 */
	@Override
	public boolean upDataUserInfo(UserInfo newUserInfo) {
		return userDao.upDataUserInfo(newUserInfo);
	}
	
	/**
	 *@MethodName: pdUserName
	 *@Description:�ж��û��˺�
	 *@author lzx
	 *@date 2024-11-13 05:57:53
	 */
	@Override
	public boolean pdUserName(String userName) {
		//��ȡ���ݿ��������û�����
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
