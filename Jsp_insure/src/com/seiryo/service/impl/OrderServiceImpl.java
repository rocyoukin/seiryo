package com.seiryo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.OrderDao;
import com.seiryo.dao.impl.OrderDaoImpl;
import com.seiryo.entity.My_order_info;
import com.seiryo.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	private OrderDao dao = new OrderDaoImpl();

	@Override
	public boolean insertOrderInfo(My_order_info info) {
		return dao.insertOrderInfo(info);
	}

	@Override
	public List<My_order_info> selectOrderByUserId(Integer user_id) {
		List<My_order_info> infoList = dao.selectAllOrder();
		List<My_order_info> orderList = new ArrayList<My_order_info>();
		if(orderList != null) {
			for(My_order_info order:infoList) {
				if(order.getUser_id() == user_id) {
					orderList.add(order);
				}
			}
		}
		return orderList;
	}

}
