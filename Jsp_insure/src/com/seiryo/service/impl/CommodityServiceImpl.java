package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.CommodityDao;
import com.seiryo.dao.impl.CommodityDaoImpl;
import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.service.CommodityService;

public class CommodityServiceImpl implements CommodityService{
	
	private CommodityDao commodityDao = new CommodityDaoImpl();
	
	@Override
	public List<Commodity_liability_info> selectAllCommodity(MyUserInfo myUserInfo) {
		return commodityDao.selectAllCommodity(myUserInfo);
	}

}
