package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.LogInfo;

/**
 *@ClassName: LogService
 *@Description:��־�����ӿ�
 *@author lzx
 *@date 2024-11-13 05:25:02
 */
public interface LogService {

	//��ȡ���ݿ��е�������־
	List<LogInfo> selectLogInfo();
	
	//�����־
	boolean insertLogInfo(LogInfo logInfo);
	
	//�黹ͼ�飬������־
	boolean upDateLogInfo(LogInfo logInfo);
	
}
