package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.LogDao;
import com.seiryo.entity.LogInfo;
import com.seiryo.utile.JDBCUTILE;

/**
 *@ClassName: LogDaoImpl
 *@Description: 借书日志数据层实现类
 *@author lzx
 *@date 2024-11-13 02:21:34
 */
public class LogDaoImpl implements LogDao {
	
	/**
	 *@MethodName: seleteLogInfo
	 *@Description:获取数据库中所有借书日志数据
	 *@author lzx
	 *@date 2024-11-13 02:22:22
	 */
	@Override
	public List<LogInfo> seleteLogInfo() {
		//设置一个集合用来保存所有借书日志数据
		List<LogInfo> allLog = new ArrayList<LogInfo>();
		
		//创建 SQL
		String sql = "SELECT * FROM log_info";
		
		//创建ResultSet对象
		ResultSet rst = null;
		
		try {
			//调用JDBCUTILE
			rst = JDBCUTILE.JDBCSELECT(sql);
			//通过 while 进行遍历
			while(rst.next()) {
				//创建借书日志对象
				LogInfo logInfo = new LogInfo();
				//添加借书日志对象属性值
				logInfo.setLogId(rst.getInt("log_id"));
				logInfo.setLogUserId(rst.getInt("log_user_id"));
				logInfo.setLogBookId(rst.getInt("log_book_id"));
				logInfo.setInitTime(rst.getString("log_initTime"));
				logInfo.setInitTime(rst.getString("log_endTime"));
				//将借书日志对象保存到集合中
				allLog.add(logInfo);
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
		
		return allLog;
	}
	
	/**
	 *@MethodName: insertLogInfo
	 *@Description:添加借书日志
	 *@author lzx
	 *@date 2024-11-13 02:22:30
	 */
	@Override
	public boolean insertLogInfo(LogInfo logInfo) {
		//创建 SQL
		String sql = "INSERT INTO log_info(log_user_id, log_book_id, log_initTime, log_endTime) VALUES(?,?,?,?)";
		
		//设置一个boolean变量用来判断是否添加借书日志成功
		boolean bo = false;
		
		try {
			//调用JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, logInfo.getLogUserId(), logInfo.getLogBookId(), logInfo.getInitTime(), logInfo.getEndTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

	/**
	 *@MethodName: upDateLogInfo
	 *@Description:归还图书，更改日志
	 *@author lzx
	 *@date 2024-11-13 11:18:08
	 */
	@Override
	public boolean upDateLogInfo(LogInfo logInfo) {
		//创建 SQL
		String sql = "UPDATE log_info SET log_endTime = ? WHERE log_book_id = ? AND log_endTime = null";
		
		//设置一个boolean变量用来判断是否更改图书数据成功
		boolean bo = false;
		
		try {
			//调用JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, logInfo.getEndTime(), logInfo.getLogBookId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}
	
}
