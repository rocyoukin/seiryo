package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.LogDao;
import com.seiryo.dao.impl.LogDaoImpl;
import com.seiryo.entity.LogInfo;
import com.seiryo.service.LogService;

/**
 *@ClassName: LogServiceImpl
 *@Description: 日志服务层实现类
 *@author lzx
 *@date 2024-11-13 05:28:12
 */
public class LogServiceImpl implements LogService {
	
	//数据层接口指向实现类
	private LogDao logDao = new LogDaoImpl();
	
	/**
	 *@MethodName: selectLogInfo
	 *@Description:获取数据库中的所有日志
	 *@author lzx
	 *@date 2024-11-13 05:28:57
	 */
	@Override
	public List<LogInfo> selectLogInfo() {
		return logDao.seleteLogInfo();
	}
	
	/**
	 *@MethodName: insertLogInfo
	 *@Description:添加日志
	 *@author lzx
	 *@date 2024-11-13 05:29:03
	 */
	@Override
	public boolean insertLogInfo(LogInfo logInfo) {
		return logDao.insertLogInfo(logInfo);
	}

	/**
	 *@MethodName: upDateLogInfo
	 *@Description:归还图书，更改日志
	 *@author lzx
	 *@date 2024-11-13 11:16:42
	 */
	@Override
	public boolean upDateLogInfo(LogInfo logInfo) {
		return logDao.upDateLogInfo(logInfo);
	}

}
