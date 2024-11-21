package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seiryo.dao.CommodityDao;
import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.utile.JDBCUTILE;

public class CommodityDaoImpl implements CommodityDao{
	
	@Override
	public List<Commodity_liability_info> selectAllCommodity(MyUserInfo myUserInfo) {
		String sql = "SELECT c1.commodity_id,c1.commodity_name,c1.commodity_price,"
						  + "c1.commodity_introduce,c1.commodity_img,c1.commodity_age_start,"
						  + "c1.commodity_age_end,c1.commodity_time_length,c1.commodity_applicable_gender,"
						  + "c1.commodity_applicable_location,c1.commodity_signing_form,c1.commodity_liability_zjs,"
						  + "c1.commodity_detailed_information1,c1.commodity_detailed_information2,c1.commodity_detailed_information3,"
						  + "c1.c1,"
						  + "c2.commodity_liability_info_id,c2.commodity_liability_info1,c2.commodity_liability_info2,"
						  + "c2.commodity_liability_info3,c2.commodity_liability_info4,c2.commodity_liability_info5,"
						  + "c2.commodity_liability_info6,c2.commodity_liability_info7 "
						  + "FROM commodity c1 JOIN commodity_liability_info c2 "
						  + "ON c1.commodity_id = c2.commodity_id ";
		String sql2 = sql;
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		if(myUserInfo != null) {
			if("ÄÐ".equals(myUserInfo.getUser_sex())) {
				sql2 = sql + "WHERE c1.commodity_applicable_gender in(1,2) ";
				sql3 = sql + "WHERE c1.commodity_applicable_gender in (0,2) ";
			}else {
				sql2 = sql + "WHERE c1.commodity_applicable_gender in(0,2) ";
				sql3 = sql + "WHERE c1.commodity_applicable_gender in(1,2) ";
			}
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			Integer dateTime = Integer.parseInt(dateFormat.format(date)) - Integer.parseInt(myUserInfo.getUser_birthday().substring(0, 4));
			sql2 = sql2 + "AND (c1.commodity_age_start <= " + dateTime + " AND c1.commodity_age_end >=" + dateTime + ")";
			sql4 = sql3 + "AND c1.commodity_age_end < " + dateTime;
			sql5 = sql3 + "AND c1.commodity_age_start > " + dateTime;
		}

		List<Commodity_liability_info> commodityList = new ArrayList<Commodity_liability_info>();
		commodityList = fl(sql2);
		if(myUserInfo != null) {
			List<Commodity_liability_info> commodityList2 = fl(sql4);
			for(Commodity_liability_info liability_info:commodityList2) {
				commodityList.add(liability_info);
			}
			List<Commodity_liability_info> commodityList3 = fl(sql5);
			for(Commodity_liability_info liability_info:commodityList3) {
				commodityList.add(liability_info);
			}
		}
		return commodityList;
	}
	
	private List<Commodity_liability_info> fl(String sql) {
		ResultSet rest = null;
		List<Commodity_liability_info> commodityList = new ArrayList<Commodity_liability_info>();
		try {
			rest = JDBCUTILE.selectDataBaseTable(sql);
			while(rest.next()) {
				Commodity_liability_info info = new Commodity_liability_info();
				info.setCommodity_id(rest.getInt("commodity_id"));
				info.setCommodity_name(rest.getString("commodity_name"));
				info.setCommodity_price(rest.getString("commodity_price"));
				info.setCommodity_introduce(rest.getString("commodity_introduce"));
				info.setCommodity_img(rest.getString("commodity_img"));
				info.setCommodity_age_start(rest.getInt("commodity_age_start"));
				info.setCommodity_age_end(rest.getInt("commodity_age_end"));
				info.setCommodity_time_length(rest.getString("commodity_time_length"));
				info.setCommodity_applicable_gender(rest.getInt("commodity_applicable_gender"));
				info.setCommodity_applicable_location(rest.getString("commodity_applicable_location"));
				info.setCommodity_signing_form(rest.getString("commodity_signing_form"));
				info.setCommodity_liability_zjs(rest.getString("commodity_liability_zjs"));
				info.setCommodity_detailed_information1(rest.getString("commodity_detailed_information1"));
				info.setCommodity_detailed_information2(rest.getString("commodity_detailed_information2"));
				info.setCommodity_detailed_information3(rest.getString("commodity_detailed_information3"));
				info.setCommodity_type(rest.getString("c1"));
				info.setCommodity_liability_info_id(rest.getInt("commodity_liability_info_id"));
				info.setCommodity_liability_info1(rest.getString("commodity_liability_info1"));
				info.setCommodity_liability_info2(rest.getString("commodity_liability_info2"));
				info.setCommodity_liability_info3(rest.getString("commodity_liability_info3"));
				info.setCommodity_liability_info4(rest.getString("commodity_liability_info4"));
				info.setCommodity_liability_info5(rest.getString("commodity_liability_info5"));
				info.setCommodity_liability_info6(rest.getString("commodity_liability_info6"));
				info.setCommodity_liability_info7(rest.getString("commodity_liability_info7"));
				commodityList.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUTILE.dbClose(rest, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return commodityList;
	}

}
