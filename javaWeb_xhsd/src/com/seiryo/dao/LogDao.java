package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.LogInfo;

/**
 *@ClassName: LogDao
 *@Description: 借书日志数据层接口
 *@author lzx
 *@date 2024-11-13 01:35:07
 */
public interface LogDao {

	//获取数据库中所有借书日志数据
	List<LogInfo> seleteLogInfo();
	
	//添加借书日志
	boolean insertLogInfo(LogInfo logInfo);
	
	//归还图书，更改日志
	boolean upDateLogInfo(LogInfo logInfo);
	
}
