package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.UserInfo;

/**
 *@ClassName: UserDao
 *@Description: �û����ݲ�ӿ�
 *@author lzx
 *@date 2024-11-13 11:31:49
 */
public interface UserDao {
	
	//�û�ע��
	boolean insertUserInfo(UserInfo userInfo);
	
	//��ȡ�����û�����
	List<UserInfo> selectUserInfo();
	
	//�����û�����
	boolean upDataUserInfo(UserInfo newUserInfo);
	
}
