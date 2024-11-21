package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.UserDao;
import com.seiryo.entity.UserInfo;
import com.seiryo.utile.JDBCUTILE;

/**
 *@ClassName: UserDaoImol
 *@Description: �û����ݲ�ʵ����
 *@author lzx
 *@date 2024-11-13 01:39:23
 */
public class UserDaoImpl implements UserDao {
	
	/**
	 *@MethodName: insertUserInfo
	 *@Description:�û�ע��
	 *@author lzx
	 *@date 2024-11-13 01:39:45
	 */
	@Override
	public boolean insertUserInfo(UserInfo userInfo) {
		//���� SQL
		String sql = "INSERT INTO user_info(user_name,user_pass,user_name_info,user_s,user_t) VALUES(?,?,?,?,?)";
		
		//����һ��boolean���������ж��Ƿ�ע��ɹ�
		boolean bo = false;
		
		try {
			//����JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, userInfo.getUserName(), userInfo.getUserPass(), userInfo.getUserNameInfo(), userInfo.getUserS(), userInfo.getUserT());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}
	
	/**
	 *@MethodName: seleteUserInfo
	 *@Description:��ȡ�����û�����
	 *@author lzx
	 *@date 2024-11-13 01:39:51
	 */
	@Override
	public List<UserInfo> selectUserInfo() {
		//����һ�������������������û�����
		List<UserInfo> allUserInfoList = new ArrayList<UserInfo>();
		
		//���� SQL
		String sql = "SELECT * FROM user_info";
		
		//����ResultSet����
		ResultSet rst = null;
		
		try {
			//����JDBCUTILE
			rst = JDBCUTILE.JDBCSELECT(sql);
			//ͨ�� while ���б���
			while(rst.next()) {
				//�����û�����
				UserInfo userInfo = new UserInfo();
				//����û���������ֵ
				userInfo.setUserID(rst.getInt("user_id"));
				userInfo.setUserName(rst.getString("user_name"));
				userInfo.setUserPass(rst.getString("user_pass"));
				userInfo.setUserNameInfo(rst.getString("user_name_info"));
				userInfo.setUserS(rst.getInt("user_s"));
				userInfo.setUserT(rst.getInt("user_t"));
				//���û����󱣴浽������
				allUserInfoList.add(userInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
			try {
				JDBCUTILE.CLOSEDATA(null, null, rst);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return allUserInfoList;
	}
	
	/**
	 *@MethodName: upDateUserInfo
	 *@Description:�����û�����
	 *@author lzx
	 *@date 2024-11-13 01:39:56
	 */
	@Override
	public boolean upDataUserInfo(UserInfo newUserInfo) {
		//���� SQL
		String sql = "UPDATE user_info SET user_pass = ?, user_name_info = ?, user_s = ? WHERE user_id = ?";
		
		//����һ��boolean���������ж��Ƿ�ע��ɹ�
		boolean bo = false;
		
		try {
			//����JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, newUserInfo.getUserPass(), newUserInfo.getUserNameInfo(), newUserInfo.getUserS());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

}
