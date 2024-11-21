package com.seiryo.servlet;

import java.io.IOException;

import com.seiryo.entity.MyUserInfo;
import com.seiryo.service.UserService;
import com.seiryo.service.impl.UserServiceImpl;
import com.seiryo.utile.MD5UTIL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//a获取判断,ページから渡されたoptのデータを取得する
		String opt = req.getParameter("opt");
		//b通过判断进行设置调用方法、変数optのデータによって使うメソッドを決定する
		if("user_regist".equals(opt)) {
			//ユーザー登録のメソッド
			userRegister(req,resp);
		}else if("user_login".equals(opt)) {
			//ユーザーログインのメソッド
			userLogin(req,resp);
		}else if("user_upDate".equals(opt)) {
			//ユーザーのデータを更新するメソッド
			userUPDate(req,resp);
		}
		
	}
	
	/**
	 *@MethodName: userUPDate
	 *@Description: ユーザー情報の更新
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-10-30 02:45:26
	 */
	private void userUPDate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//a获取前台传递的内容、ページから渡されたユーザーのパスワードを取得する
		String password = req.getParameter("password");
		//将用户输入的新密码在加密之前进行保存
		String userPassword = password;
		
		//通过MD5进行密码加密、パスワードをMD5の形式で暗号化する
		password = MD5UTIL.md5(password);
		String userPhone = req.getParameter("userPhone");
		Double upMoney = new Double(req.getParameter("upMoney"));
		
		//a设置初始化参数
		int a = 0;
		
		if(password!=null&userPhone!=null) {
			//获取存储在session范围内的用户
			HttpSession session = req.getSession();
			MyUserInfo user = (MyUserInfo) session.getAttribute("user");
			Double userMoney = new Double(user.getUser_money());
			
			//进行增加用户余额
			Double newMoney = upMoney+userMoney;
			String newUserMoney = newMoney.toString();
			
			//保存用户需要更改的信息
			user.setUser_money(newUserMoney);
			user.setUser_phone(userPhone);
			user.setUser_password(password);
			
			//调用更改数据库中用户数据的方法并返回boolean类型的bo，データベースのユーザーデータを変更するメソッドを使って、boolean（ブーリアン）型のboを得る。
			boolean bo = userService.upDataUserInfo(user);
			
			//用户信息更新成功
			if(bo) {
				a=1;
				//将用户输入的加密之前的新密码保存在user对象中
				user.setUser_password(userPassword);
				//将更新后的用户对象储存至session范围内
				session.setAttribute("user", user);
			}
		}else {
			a=2;
		}
		resp.getWriter().println(a);
	}
	
	/**
	 * user_login
	 * @param req 请求
	 * @param resp 响应
	 * @throws ServletException 异常
	 * @throws IOException 异常
	 */
	private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//a获取前台传递的内容
		String user_email = req.getParameter("userEmail");
		String userpass = req.getParameter("userPass");
		//通过MD5进行密码加密
		String user_pass = MD5UTIL.md5(userpass);
		
		//a通过账号密码判断登录
		MyUserInfo user = userService.pdUserLogin(user_email, user_pass);
		if(user != null) {
			//获取session
			HttpSession session = req.getSession();
			//将用户对象中的密码设置成的MD5加密前的密码
			user.setUser_password(userpass);
			//登录成功将返回的用户信息对象储存至session范围内
			session.setAttribute("user", user);
			resp.getWriter().println(1);
		}else {
			resp.getWriter().println(0);
		}
	}
	
	/**
	 * user_regist
	 * @param req 请求
	 * @param resp 响应
	 * @throws ServletException 异常
	 * @throws IOException 异常
	 */
	private void userRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//a获取传递的数据
		String userName = req.getParameter("userName");
		String userBirthday = req.getParameter("userBirthday");
		String userSex = req.getParameter("userSex");
		String userPhone = req.getParameter("userPhone");
		String userEmail = req.getParameter("userEmail");
		String userPass = req.getParameter("userPass");
		//通过MD5进行密码加密
		userPass = MD5UTIL.md5(userPass);
		if("1".equals(userSex)) {
			userSex = "男";
		}else {
			userSex = "女";
		}
		//a设置初始化参数
		int a = 0;
		//a调用判断账号是否重复的方法
		if(!userService.pdUserEmail(userEmail)) {
			//a创建新的对象进行保存用户信息
			MyUserInfo myUserInfo = new MyUserInfo(null,userEmail,userPass, null, userPhone, "0", userName, userBirthday,userSex, 1);
			//a插入用户信息
			if(userService.insertUser(myUserInfo)) {
				a = 1;
			}
		}
		resp.getWriter().println(a);
	}
}
