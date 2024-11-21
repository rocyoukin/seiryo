package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.My_order_info;

public interface OrderDao {
	
	boolean insertOrderInfo(My_order_info info);
	
	List<My_order_info> selectAllOrder();

}
