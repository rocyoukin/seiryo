package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;

/**
 *@ClassName: CommodityDao
 *@Description: ¥Ç©`¥¿¥Ù©`¥¹ÖÐ¤Î±£ê“¥Ç©`¥¿¤òßB½Ó¤¹¤ë
 *@author lzx
 *@date 2024-11-06 03:09:42
 */
public interface CommodityDao {
	
	List<Commodity_liability_info> selectAllCommodity(MyUserInfo myUserInfo);

}
