package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.LogInfo;

/**
 *@ClassName: LogDao
 *@Description: ������־���ݲ�ӿ�
 *@author lzx
 *@date 2024-11-13 01:35:07
 */
public interface LogDao {

	//��ȡ���ݿ������н�����־����
	List<LogInfo> seleteLogInfo();
	
	//��ӽ�����־
	boolean insertLogInfo(LogInfo logInfo);
	
	//�黹ͼ�飬������־
	boolean upDateLogInfo(LogInfo logInfo);
	
}
