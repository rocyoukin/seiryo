package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.UserDao;
import com.seiryo.entity.UserInfo;
import com.seiryo.utile.JDBCUTILE;

/**
 *@ClassName: UserDaoImol
 *@Description: 用户数据层实现类
 *@author lzx
 *@date 2024-11-13 01:39:23
 */
public class UserDaoImpl implements UserDao {
	
	/**
	 *@MethodName: insertUserInfo
	 *@Description:用户注册
	 *@author lzx
	 *@date 2024-11-13 01:39:45
	 */
	@Override
	public boolean insertUserInfo(UserInfo userInfo) {
		//创建 SQL
		String sql = "INSERT INTO user_info(user_name,user_pass,user_name_info,user_s,user_t) VALUES(?,?,?,?,?)";
		
		//设置一个boolean变量用来判断是否注册成功
		boolean bo = false;
		
		try {
			//调用JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, userInfo.getUserName(), userInfo.getUserPass(), userInfo.getUserNameInfo(), userInfo.getUserS(), userInfo.getUserT());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}
	
	/**
	 *@MethodName: seleteUserInfo
	 *@Description:获取所有用户数据
	 *@author lzx
	 *@date 2024-11-13 01:39:51
	 */
	@Override
	public List<UserInfo> selectUserInfo() {
		//设置一个集合用来保存所有用户数据
		List<UserInfo> allUserInfoList = new ArrayList<UserInfo>();
		
		//创建 SQL
		String sql = "SELECT * FROM user_info";
		
		//创建ResultSet对象
		ResultSet rst = null;
		
		try {
			//调用JDBCUTILE
			rst = JDBCUTILE.JDBCSELECT(sql);
			//通过 while 进行遍历
			while(rst.next()) {
				//创建用户对象
				UserInfo userInfo = new UserInfo();
				//添加用户对象属性值
				userInfo.setUserID(rst.getInt("user_id"));
				userInfo.setUserName(rst.getString("user_name"));
				userInfo.setUserPass(rst.getString("user_pass"));
				userInfo.setUserNameInfo(rst.getString("user_name_info"));
				userInfo.setUserS(rst.getInt("user_s"));
				userInfo.setUserT(rst.getInt("user_t"));
				//将用户对象保存到集合中
				allUserInfoList.add(userInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据
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
	 *@Description:更改用户数据
	 *@author lzx
	 *@date 2024-11-13 01:39:56
	 */
	@Override
	public boolean upDataUserInfo(UserInfo newUserInfo) {
		//创建 SQL
		String sql = "UPDATE user_info SET user_pass = ?, user_name_info = ?, user_s = ? WHERE user_id = ?";
		
		//设置一个boolean变量用来判断是否注册成功
		boolean bo = false;
		
		try {
			//调用JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, newUserInfo.getUserPass(), newUserInfo.getUserNameInfo(), newUserInfo.getUserS());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

}
