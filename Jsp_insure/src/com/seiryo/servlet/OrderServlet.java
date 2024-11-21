package com.seiryo.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.entity.My_order_info;
import com.seiryo.service.OrderService;
import com.seiryo.service.UserService;
import com.seiryo.service.impl.OrderServiceImpl;
import com.seiryo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderServiceImpl();
	private UserService userService = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求中的falg属性值，并判断操作
		String flag = req.getParameter("flag");
		
		if("insertOrder".equals(flag)) {
			//调用添加订单的方法
			insertOrder(req,resp);
		}else if("select_order_by_user_id".equals(flag)) {
			//调用查看订单的方法
			selectOrder(req,resp);
		}
		
	}
	
	/**
	 *@MethodName: insertOrder
	 *@Description: 添加订单
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-02 12:14:55
	 */
	private void insertOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//a设置初始化参数
		int a = 0;
		
		//通过当前时间设置订单id
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String order_id = dateFormat.format(date);
		
		//获取session中储存的用户id和商品id
		HttpSession session = req.getSession();
		MyUserInfo user = (MyUserInfo)session.getAttribute("user");
		Integer user_id = user.getUser_id();
		Commodity_liability_info commodityInfo = (Commodity_liability_info)session.getAttribute("commodityInfo");
		Integer commodity_id = commodityInfo.getCommodity_id();
		
		//获取请求中的订单信息
		String userName2 = req.getParameter("userName2");
		String userAge = req.getParameter("userCity2");
		String userPhone2 = req.getParameter("userPhone2");
		String order_commodity_time_start = req.getParameter("order_commodity_time_start");
		String order_commodity_time_length = req.getParameter("order_commodity_time_length");
		String order_sum_price = req.getParameter("order_sum_price");
		String order_commodity_liability1 = req.getParameter("order_commodity_liability1");
		String order_commodity_liability2 = req.getParameter("order_commodity_liability2");
		String order_commodity_liability3 = req.getParameter("order_commodity_liability3");
		String order_commodity_liability4 = req.getParameter("order_commodity_liability4");
		String order_commodity_liability5 = req.getParameter("order_commodity_liability5");
		String order_commodity_liability6 = req.getParameter("order_commodity_liability6");
		String order_commodity_liability7 = req.getParameter("order_commodity_liability7");
		
		//获取当前保险的适用年龄
		int age_start = commodityInfo.getCommodity_age_start();
		int age_end = commodityInfo.getCommodity_age_end();
		//获取被保险者的年龄并转换为Integer类型
		Integer userAgeInt = Integer.parseInt(userAge);
		
		//将订单价格转换成Double类型
		Double order_sum_priceDouble = Double.parseDouble(order_sum_price);
		
		//通过保险有效日年份和有效年限得到终止年份
		Integer time_end = Integer.parseInt(order_commodity_time_start) + Integer.parseInt(order_commodity_time_length);
		String order_commodity_time_end = time_end + "";
		
		//设置一个My_order_info对象用来保存订单信息
		My_order_info info = new My_order_info(order_id, user_id, commodity_id, null,
				order_commodity_liability1, order_commodity_liability2, order_commodity_liability3, 
				order_commodity_liability4, order_commodity_liability5, order_commodity_liability6, 
				order_commodity_liability7, userName2, userAge, userPhone2, order_commodity_time_start,
				order_commodity_time_end, order_commodity_time_length, order_sum_price);
		
		//判断被保险人是否在适用年龄之内
		if(age_start<=userAgeInt&&age_end>=userAgeInt) {
			//用户余额
			Double userMoney = Double.parseDouble(user.getUser_money());
			//减少用户余额
			Double newUserMoneyDouble = userMoney - order_sum_priceDouble;
			if(newUserMoneyDouble<0) {
				//用户余额不足以购买当前保险
				a = 2;
			}else {
				//调用添加数据库中用户订单的方法
				boolean bo = orderService.insertOrderInfo(info);
				if(bo) {
					//重新设置当前用户对象的余额
					String newUserMoney = newUserMoneyDouble + "";
					user.setUser_money(newUserMoney);
					//调用更改用户数据的方法
					boolean bo1 = userService.upDataUserInfo(user);
					if(bo1) {
						//用户订单生成成功设置初始化参数为1
						a=1;
					}
				}
			}
		}
		
		//将初始化参数返回给前端页面
		resp.getWriter().println(a);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	/**
	 *@MethodName: selectOrder
	 *@Description: 查看当前用户订单
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-02 02:26:53
	 */
	private void selectOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取session中的当前用户id
		HttpSession session = req.getSession();
		MyUserInfo user = (MyUserInfo)session.getAttribute("user");
		Integer user_id = user.getUser_id();
		
		//调用获取当前用户的订单集合的方法
		List<My_order_info> orderList = orderService.selectOrderByUserId(user_id);
		
		//将当前用户的订单集合储存到req范围内，并请求转发至my_order.jsp页面
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("my_order.jsp").forward(req, resp);
	}
	
}
