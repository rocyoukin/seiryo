package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.UserInfo;

/**
 *@ClassName: UserService
 *@Description: �û������ӿ�
 *@author lzx
 *@date 2024-11-13 02:31:41
 */
public interface UserService {
	
	//�û�ע��
	boolean insertUser(UserInfo userInfo);
	
	//��ȡ�����û�����
	List<UserInfo> selectAllUserInfo();
	
	//�û���¼
	UserInfo pdUserLogin(String userName,String userPass);
	
	//�����û�����
	boolean upDataUserInfo(UserInfo newUserInfo);
	
	//�ж��û��˺�
	boolean pdUserName(String userName);
	
}
