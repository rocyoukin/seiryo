package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.LogDao;
import com.seiryo.dao.impl.LogDaoImpl;
import com.seiryo.entity.LogInfo;
import com.seiryo.service.LogService;

/**
 *@ClassName: LogServiceImpl
 *@Description: ��־�����ʵ����
 *@author lzx
 *@date 2024-11-13 05:28:12
 */
public class LogServiceImpl implements LogService {
	
	//���ݲ�ӿ�ָ��ʵ����
	private LogDao logDao = new LogDaoImpl();
	
	/**
	 *@MethodName: selectLogInfo
	 *@Description:��ȡ���ݿ��е�������־
	 *@author lzx
	 *@date 2024-11-13 05:28:57
	 */
	@Override
	public List<LogInfo> selectLogInfo() {
		return logDao.seleteLogInfo();
	}
	
	/**
	 *@MethodName: insertLogInfo
	 *@Description:�����־
	 *@author lzx
	 *@date 2024-11-13 05:29:03
	 */
	@Override
	public boolean insertLogInfo(LogInfo logInfo) {
		return logDao.insertLogInfo(logInfo);
	}

	/**
	 *@MethodName: upDateLogInfo
	 *@Description:�黹ͼ�飬������־
	 *@author lzx
	 *@date 2024-11-13 11:16:42
	 */
	@Override
	public boolean upDateLogInfo(LogInfo logInfo) {
		return logDao.upDateLogInfo(logInfo);
	}

}
