package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;

/**
 *@ClassName: CommodityDao
 *@Description: �ǩ`���٩`���Фα�ꓥǩ`�����B�Ӥ���
 *@author lzx
 *@date 2024-11-06 03:09:42
 */
public interface CommodityDao {
	
	List<Commodity_liability_info> selectAllCommodity(MyUserInfo myUserInfo);

}
