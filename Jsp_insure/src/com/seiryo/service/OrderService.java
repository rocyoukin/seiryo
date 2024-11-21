package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.My_order_info;

public interface OrderService {
	
	boolean insertOrderInfo(My_order_info info);
	
	List<My_order_info> selectOrderByUserId(Integer user_id);

}
