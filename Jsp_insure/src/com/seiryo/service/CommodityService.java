package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;

public interface CommodityService {
	
	List<Commodity_liability_info> selectAllCommodity(MyUserInfo myUserInfo);

}
