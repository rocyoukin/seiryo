package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.LogInfo;

/**
 *@ClassName: LogService
 *@Description:日志服务层接口
 *@author lzx
 *@date 2024-11-13 05:25:02
 */
public interface LogService {

	//获取数据库中的所有日志
	List<LogInfo> selectLogInfo();
	
	//添加日志
	boolean insertLogInfo(LogInfo logInfo);
	
	//归还图书，更改日志
	boolean upDateLogInfo(LogInfo logInfo);
	
}
