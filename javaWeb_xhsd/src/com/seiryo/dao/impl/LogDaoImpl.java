package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.LogDao;
import com.seiryo.entity.LogInfo;
import com.seiryo.utile.JDBCUTILE;

/**
 *@ClassName: LogDaoImpl
 *@Description: ������־���ݲ�ʵ����
 *@author lzx
 *@date 2024-11-13 02:21:34
 */
public class LogDaoImpl implements LogDao {
	
	/**
	 *@MethodName: seleteLogInfo
	 *@Description:��ȡ���ݿ������н�����־����
	 *@author lzx
	 *@date 2024-11-13 02:22:22
	 */
	@Override
	public List<LogInfo> seleteLogInfo() {
		//����һ�����������������н�����־����
		List<LogInfo> allLog = new ArrayList<LogInfo>();
		
		//���� SQL
		String sql = "SELECT * FROM log_info";
		
		//����ResultSet����
		ResultSet rst = null;
		
		try {
			//����JDBCUTILE
			rst = JDBCUTILE.JDBCSELECT(sql);
			//ͨ�� while ���б���
			while(rst.next()) {
				//����������־����
				LogInfo logInfo = new LogInfo();
				//��ӽ�����־��������ֵ
				logInfo.setLogId(rst.getInt("log_id"));
				logInfo.setLogUserId(rst.getInt("log_user_id"));
				logInfo.setLogBookId(rst.getInt("log_book_id"));
				logInfo.setInitTime(rst.getString("log_initTime"));
				logInfo.setInitTime(rst.getString("log_endTime"));
				//��������־���󱣴浽������
				allLog.add(logInfo);
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
		
		return allLog;
	}
	
	/**
	 *@MethodName: insertLogInfo
	 *@Description:��ӽ�����־
	 *@author lzx
	 *@date 2024-11-13 02:22:30
	 */
	@Override
	public boolean insertLogInfo(LogInfo logInfo) {
		//���� SQL
		String sql = "INSERT INTO log_info(log_user_id, log_book_id, log_initTime, log_endTime) VALUES(?,?,?,?)";
		
		//����һ��boolean���������ж��Ƿ���ӽ�����־�ɹ�
		boolean bo = false;
		
		try {
			//����JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, logInfo.getLogUserId(), logInfo.getLogBookId(), logInfo.getInitTime(), logInfo.getEndTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

	/**
	 *@MethodName: upDateLogInfo
	 *@Description:�黹ͼ�飬������־
	 *@author lzx
	 *@date 2024-11-13 11:18:08
	 */
	@Override
	public boolean upDateLogInfo(LogInfo logInfo) {
		//���� SQL
		String sql = "UPDATE log_info SET log_endTime = ? WHERE log_book_id = ? AND log_endTime = null";
		
		//����һ��boolean���������ж��Ƿ����ͼ�����ݳɹ�
		boolean bo = false;
		
		try {
			//����JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, logInfo.getEndTime(), logInfo.getLogBookId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}
	
}
