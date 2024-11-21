package com.seiryo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seiryo.entity.BookInfo;
import com.seiryo.entity.LogInfo;
import com.seiryo.entity.UserInfo;
import com.seiryo.service.BookService;
import com.seiryo.service.LogService;
import com.seiryo.service.UserService;
import com.seiryo.service.impl.BookServiceImpl;
import com.seiryo.service.impl.LogServiceImpl;
import com.seiryo.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private UserService userService = new UserServiceImpl();
    
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//设置编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//获取页面flag值进行判断
		String flag = req.getParameter("flag");
		//通过判断进行设置调用方法
		if("user_regist".equals(flag)) {
			//用户注册
			userRegister(req,resp);
		}else if("user_login".equals(flag)) {
			//用户登录
			userLogin(req,resp);
		}else if("getUser".equals(flag)) {
			//通过用户id获取用户对象，并提交给upDate_User.jsp页面
			user(req,resp);
		}else if("user_upDate".equals(flag)) {
			//更新用户数据
			userUPDate(req,resp);
		}else if("userUpDate_userT".equals(flag)) {
			//删除用户（更改用户状态）
			userUpDate_userT(req,resp);
		}else if("select_all".equals(flag)) {
			//获取所有用户信息
			selectAllUser(req,resp);
		}else if("userQuit".equals(flag)) {
			//用户退出登录
			userQuit(req,resp);
		}
		
	}
	
	/**
	 *@MethodName: userUpDate_userT
	 *@Description: 删除用户（更改用户状态）
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-18 12:32:37
	 */
	private void userUpDate_userT(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求中的用户ID
		Integer userID = Integer.parseInt(req.getParameter("userID"));
		//获取所有用户数据
		List<UserInfo> userList = userService.selectAllUserInfo();
		
		if(userList.size()>0) {
			for(UserInfo user:userList) {
				if(user.getUserID()==userID) {
					//设置用户状态为0
					user.setUserS(0);
					//调用更改用户的方法
					boolean bo = userService.upDataUserInfo(user);
					if(bo) {
						//获取所有用户信息
						selectAllUser(req,resp);
					}
				}
			}
		}
		
	}
	
	/**
	 *@MethodName: user
	 *@Description: 通过用户id获取用户对象，并提交给upDate_User.jsp页面
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-18 12:24:14
	 */
	private void user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求中的用户ID
		Integer userID = Integer.parseInt(req.getParameter("userID"));
		//获取所有用户数据
		List<UserInfo> userList = userService.selectAllUserInfo();
		
		if(userList.size()>0) {
			for(UserInfo user:userList) {
				if(user.getUserID()==userID) {
					//将需要操作的用户对象保存至请求范围内
					req.setAttribute("upDateUser", user);
					break;
				}
			}
		}
		
		req.getRequestDispatcher("upData_User.jsp").forward(req, resp);
		
	}
	
	/**
	 *@MethodName: userQuit
	 *@Description: 用户退出登录
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-18 11:17:42
	 */
	private void userQuit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取当前session
		HttpSession session = req.getSession();
		session.invalidate();
		
		//请求转发至登录页面
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	/**
	 *@MethodName: userLogin
	 *@Description: 用户登录
	 *@return void
	 *@author lzx
	 *@date 2024-11-13 05:11:42
	 */
	private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//获取前台传递的内容
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		String msg = req.getParameter("msg");
		
		//通过账号密码判断登录
		UserInfo user = userService.pdUserLogin(userName, userPass);
		
		int a = 0;
		if(user != null) {
			//获取session
			HttpSession session = req.getSession();
			//登录成功将返回的用户信息对象储存至session范围内
			session.setAttribute("user", user);
			if(msg.equals("user")) {
				//判断用户状态，0为已冻结用户
				if(user.getUserS()>0) {
					a = 1;
				}else {
					a = 3;
				}
			}else if(msg.equals("admin")) {
				//判断用户状态，0为已冻结用户
				if(user.getUserS()>0) {
					//判断登录用户是否为管理员
					if(user.getUserT()>0) {
						a = 2;
					}
				}else {
					a = 3;
				}
			}
		}
		
		resp.getWriter().println(a);
		
	}
	
	/**
	 *@MethodName: userRegister
	 *@Description: 用户注册
	 *@return void
	 *@author lzx
	 *@date 2024-11-13 05:19:02
	 */
	private void userRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//获取传递的数据
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		String userNameInfo = req.getParameter("userNameInfo");
		Integer userT = Integer.parseInt(req.getParameter("userT"));
		//设置提示参数
		int a = 0;
		if(userT!=null) {
			//获取当前用户权限
			HttpSession session = req.getSession();
			UserInfo user = (UserInfo)session.getAttribute("user");
			if(userT==0&&user.getUserT()>0) {
				//调用判断账号是否重复的方法
				if(!userService.pdUserName(userName)) {
					//创建新的对象进行保存用户信息
					UserInfo userInfo = new UserInfo(null, userName, userPass, userNameInfo, 1, userT);
					//插入用户信息
					if(userService.insertUser(userInfo)) {
						//添加用户成功提示参数a的值设置为1
						a = 1;
					}
				}
			}else if(userT==0&&user.getUserT()<=0){
				//权限不够，提示参数a的值设置为2
				a = 2;
			}
			if(userT==1&&user.getUserT()==2) {
				//调用判断账号是否重复的方法
				if(!userService.pdUserName(userName)) {
					//创建新的对象进行保存用户信息
					UserInfo userInfo = new UserInfo(null, userName, userPass, userNameInfo, 1, userT);
					//插入用户信息
					if(userService.insertUser(userInfo)) {
						//添加用户成功提示参数a的值设置为1
						a = 1;
					}
				}
			}else if(userT==1&&user.getUserT()!=2){
				//权限不够，提示参数a的值设置为2
				a = 2;
			}
			resp.getWriter().println(a);
		}
		//调用判断账号是否重复的方法
		if(!userService.pdUserName(userName)) {
			//创建新的对象进行保存用户信息
			UserInfo userInfo = new UserInfo(null, userName, userPass, userNameInfo, 1, 0);
			//插入用户信息
			if(userService.insertUser(userInfo)) {
				//注册成功提示参数a的值设置为1
				a = 1;
			}
			resp.getWriter().println(a);
		}		
		
	}
	
	/**
	 *@MethodName: userUPDate
	 *@Description: 更新用户数据
	 *@return void
	 *@author lzx
	 *@date 2024-11-13 05:18:29
	 */
	private void userUPDate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//获取前台传递的内容
		String userPass = req.getParameter("userPass");
		Integer user_s = Integer.parseInt(req.getParameter("user_s"));
		String userNameInfo = req.getParameter("userNameInfo");
		Integer user_id = Integer.parseInt(req.getParameter("user_id"));
		
		//设置初始化参数
		int a = 0;
		
		if(userPass!=null&userNameInfo!=null&user_s!=null) {
			//创建用户对象并保存用户需要更改的信息
			UserInfo user = new UserInfo();
			user.setUserID(user_id);
			user.setUserPass(userPass);
			user.setUserS(user_s);
			user.setUserNameInfo(userNameInfo);
			
			//调用更改数据库中用户数据的方法并返回boolean类型的bo
			boolean bo = userService.upDataUserInfo(user);
			
			//用户信息更新成功
			if(bo) {
				a=1;
			}
		}
		resp.getWriter().println(a);
		
	}
	
	/**
	 *@MethodName: selectAllUser
	 *@Description: 获取所有用户数据
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-13 12:40:41
	 */
	private void selectAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//服务层接口指向实现类
		LogService logService = new LogServiceImpl();
		BookService bookService = new BookServiceImpl();
		
		//获取所有用户数据
		List<UserInfo> userList = userService.selectAllUserInfo();
		//获取所有图书数据
		List<BookInfo> bookList = bookService.selectBookInfoAll();
		//获取所有日志数据
		List<LogInfo> logList = logService.selectLogInfo();
		
		//将用户数据集合保存至req范围内
		req.setAttribute("userList", userList);
		//将图书数据集合保存至req范围内
		req.setAttribute("bookList", bookList);
		//将日志数据集合保存至req范围内
		req.setAttribute("logList", logList);
		//请求转发至index_admin.jsp页面
		req.getRequestDispatcher("index_admin.jsp").forward(req, resp);
	}

}
