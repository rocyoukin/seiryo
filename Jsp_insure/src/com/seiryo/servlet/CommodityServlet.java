package com.seiryo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.entity.Commodity_liability_info;
import com.seiryo.entity.MyUserInfo;
import com.seiryo.service.CommodityService;
import com.seiryo.service.impl.CommodityServiceImpl;
import com.seiryo.utile.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CommodityServlet")
public class CommodityServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private CommodityService commodityService = new CommodityServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取前端传递的opt的值，判断需要进行的操作
		String opt = req.getParameter("opt");
		
		if("selectAllCommodityList".equals(opt)) {
			selectAllCommodity(req,resp);
		}else if("commodityQueryById".equals(opt)) {
			commodityQueryById(req,resp);
		}else if("commodityQueryByType".equals(opt)) {
			commodityQueryByType(req, resp);
		}
		
	}
	
	/**
	 * c根据商品种类进行查询商品
	 * @param req 请求
	 * @param resp 响应
	 * @throws ServletException 异常
	 * @throws IOException 异常
	 */
	private void commodityQueryByType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		
		//获取请求中的保险类型
		String commodity_type = req.getParameter("commodity_type");
		//获取session对象
		HttpSession session = req.getSession();
		//将保险类型存储至session范围内
		session.setAttribute("commodityType", commodity_type);
		//请求转发至index.jsp（主页面）页面
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	/**
	 * b根据商品ID进行获取某个商品内容进行储存
	 * @param req 请求
	 * @param resp 响应
	 * @throws ServletException 异常
	 * @throws IOException 异常
	 */
	private void commodityQueryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String commodity_id = req.getParameter("commodity_id");
		String pd = req.getParameter("pd");
		HttpSession session = req.getSession();
		//session範囲内の保険コレクションを取得する
		List<Commodity_liability_info> commodityList = (ArrayList<Commodity_liability_info>)session.getAttribute("commodityList");
		for(Commodity_liability_info liability_info:commodityList) {
			if(liability_info.getCommodity_id() == Integer.parseInt(commodity_id)) {
				session.setAttribute("commodityInfo", liability_info);
				break;
			}
		}
		req.getRequestDispatcher(pd).forward(req, resp);
	}
	
	/**
	 * a查询相应的商品信息，并遍历
	 * @param req 请求
	 * @param resp 响应
	 * @throws ServletException 异常
	 * @throws IOException 异常
	 */
	private void selectAllCommodity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取当前登录用户的SESSION
		HttpSession session = req.getSession();
		
		//获取当前用户信息
		MyUserInfo myUserInfo = (MyUserInfo)session.getAttribute("user");
		//获取所有商品信息
		List<Commodity_liability_info> commodityList = commodityService.selectAllCommodity(myUserInfo);
		
		//获取储存在session中页面请求的商品类型
		String commodity_type = (String) session.getAttribute("commodityType");
		
		//保険コレクションをインスタンスする
		List<Commodity_liability_info> commodityListByType = new ArrayList<Commodity_liability_info>();
		if(commodity_type!=null) {
			//页面请求的商品类型不为空，进行保险类型筛选
			for(Commodity_liability_info commodity:commodityList) {
				if(commodity.getCommodity_type().equals(commodity_type)) {
					commodityListByType.add(commodity);
				}
			}
		}else {
			//页面请求的商品类型为空，不进行保险类型筛选
			commodityListByType = commodityList;
		}
		
		//将所有商品信息保存至session范围内
		session.setAttribute("commodityList", commodityList);
		
		String data = "";
		try {
			data = JSONUtil.toJSONString(commodityListByType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.getWriter().println(data);
		
	}
}
