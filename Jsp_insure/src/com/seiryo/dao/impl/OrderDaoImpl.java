package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.OrderDao;
import com.seiryo.entity.My_order_info;
import com.seiryo.utile.JDBCUTILE;

public class OrderDaoImpl implements OrderDao{

	@Override
	public boolean insertOrderInfo(My_order_info info) {
		String sql1 = "INSERT INTO my_order(order_id,user_id,commodity_id) VALUES(?,?,?)";
		String sql2 = "INSERT INTO my_order_info(order_id,order_commodity_liability1,order_commodity_liability2,order_commodity_liability3,order_commodity_liability4,order_commodity_liability5,order_commodity_liability6,order_commodity_liability7,order_beneficiary_name,order_beneficiary_city,order_beneficiary_phone,order_commodity_time_start,order_commodity_time_end,order_commodity_time_length,order_sum_price) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		boolean bo = false;
		try {
			bo = JDBCUTILE.upDataBaseTable(sql1, info.getOrder_id(),info.getUser_id(),info.getCommodity_id());
			if(bo) {
				bo = JDBCUTILE.upDataBaseTable(sql2, info.getOrder_id(),info.getOrder_commodity_liability1(),info.getOrder_commodity_liability2(),info.getOrder_commodity_liability3(),info.getOrder_commodity_liability4(),info.getOrder_commodity_liability5(),info.getOrder_commodity_liability6(),info.getOrder_commodity_liability7(),info.getOrder_beneficiary_name(),info.getOrder_beneficiary_city(),info.getOrder_beneficiary_phone(),info.getOrder_commodity_time_start(),info.getOrder_commodity_time_end(),info.getOrder_commodity_time_length(),info.getOrder_sum_price());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}

	@Override
	public List<My_order_info> selectAllOrder() {
		String sql = "SELECT m1.order_id,m1.user_id,m1.commodity_id,m2.order_info_id,m2.order_commodity_liability1,m2.order_commodity_liability2,m2.order_commodity_liability3,m2.order_commodity_liability4,m2.order_commodity_liability5,m2.order_commodity_liability6,m2.order_commodity_liability7,m2.order_beneficiary_name,m2.order_beneficiary_city,m2.order_beneficiary_phone,m2.order_commodity_time_start,m2.order_commodity_time_end,m2.order_commodity_time_length,m2.order_sum_price FROM my_order m1 JOIN my_order_info m2 ON m1.order_id = m2.order_id";
		ResultSet rest = null;
		List<My_order_info> list = new ArrayList<My_order_info>();
		try {
			rest = JDBCUTILE.selectDataBaseTable(sql);
			while(rest.next()) {
				My_order_info info = new My_order_info();
				info.setOrder_id(rest.getString("order_id"));
				info.setUser_id(rest.getInt("user_id"));
				info.setCommodity_id(rest.getInt("commodity_id"));
				info.setOrder_info_id(rest.getInt("order_info_id"));
				info.setOrder_commodity_liability1(rest.getString("order_commodity_liability1"));
				info.setOrder_commodity_liability2(rest.getString("order_commodity_liability2"));
				info.setOrder_commodity_liability3(rest.getString("order_commodity_liability3"));
				info.setOrder_commodity_liability4(rest.getString("order_commodity_liability4"));
				info.setOrder_commodity_liability5(rest.getString("order_commodity_liability5"));
				info.setOrder_commodity_liability6(rest.getString("order_commodity_liability6"));
				info.setOrder_commodity_liability7(rest.getString("order_commodity_liability7"));
				info.setOrder_beneficiary_name(rest.getString("order_beneficiary_name"));
				info.setOrder_beneficiary_city(rest.getString("order_beneficiary_city"));
				info.setOrder_beneficiary_phone(rest.getString("order_beneficiary_phone"));
				info.setOrder_commodity_time_start(rest.getString("order_commodity_time_start"));
				info.setOrder_commodity_time_end(rest.getString("order_commodity_time_end"));
				info.setOrder_commodity_time_length(rest.getString("order_commodity_time_length"));
				info.setOrder_sum_price(rest.getString("order_sum_price"));
				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
