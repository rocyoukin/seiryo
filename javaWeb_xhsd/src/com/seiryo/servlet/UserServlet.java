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
		
		//���ñ����ʽ
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//��ȡҳ��flagֵ�����ж�
		String flag = req.getParameter("flag");
		//ͨ���жϽ������õ��÷���
		if("user_regist".equals(flag)) {
			//�û�ע��
			userRegister(req,resp);
		}else if("user_login".equals(flag)) {
			//�û���¼
			userLogin(req,resp);
		}else if("getUser".equals(flag)) {
			//ͨ���û�id��ȡ�û����󣬲��ύ��upDate_User.jspҳ��
			user(req,resp);
		}else if("user_upDate".equals(flag)) {
			//�����û�����
			userUPDate(req,resp);
		}else if("userUpDate_userT".equals(flag)) {
			//ɾ���û��������û�״̬��
			userUpDate_userT(req,resp);
		}else if("select_all".equals(flag)) {
			//��ȡ�����û���Ϣ
			selectAllUser(req,resp);
		}else if("userQuit".equals(flag)) {
			//�û��˳���¼
			userQuit(req,resp);
		}
		
	}
	
	/**
	 *@MethodName: userUpDate_userT
	 *@Description: ɾ���û��������û�״̬��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-18 12:32:37
	 */
	private void userUpDate_userT(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//��ȡ�����е��û�ID
		Integer userID = Integer.parseInt(req.getParameter("userID"));
		//��ȡ�����û�����
		List<UserInfo> userList = userService.selectAllUserInfo();
		
		if(userList.size()>0) {
			for(UserInfo user:userList) {
				if(user.getUserID()==userID) {
					//�����û�״̬Ϊ0
					user.setUserS(0);
					//���ø����û��ķ���
					boolean bo = userService.upDataUserInfo(user);
					if(bo) {
						//��ȡ�����û���Ϣ
						selectAllUser(req,resp);
					}
				}
			}
		}
		
	}
	
	/**
	 *@MethodName: user
	 *@Description: ͨ���û�id��ȡ�û����󣬲��ύ��upDate_User.jspҳ��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-18 12:24:14
	 */
	private void user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//��ȡ�����е��û�ID
		Integer userID = Integer.parseInt(req.getParameter("userID"));
		//��ȡ�����û�����
		List<UserInfo> userList = userService.selectAllUserInfo();
		
		if(userList.size()>0) {
			for(UserInfo user:userList) {
				if(user.getUserID()==userID) {
					//����Ҫ�������û����󱣴�������Χ��
					req.setAttribute("upDateUser", user);
					break;
				}
			}
		}
		
		req.getRequestDispatcher("upData_User.jsp").forward(req, resp);
		
	}
	
	/**
	 *@MethodName: userQuit
	 *@Description: �û��˳���¼
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-18 11:17:42
	 */
	private void userQuit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//��ȡ��ǰsession
		HttpSession session = req.getSession();
		session.invalidate();
		
		//����ת������¼ҳ��
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	/**
	 *@MethodName: userLogin
	 *@Description: �û���¼
	 *@return void
	 *@author lzx
	 *@date 2024-11-13 05:11:42
	 */
	private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//��ȡǰ̨���ݵ�����
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		String msg = req.getParameter("msg");
		
		//ͨ���˺������жϵ�¼
		UserInfo user = userService.pdUserLogin(userName, userPass);
		
		int a = 0;
		if(user != null) {
			//��ȡsession
			HttpSession session = req.getSession();
			//��¼�ɹ������ص��û���Ϣ���󴢴���session��Χ��
			session.setAttribute("user", user);
			if(msg.equals("user")) {
				//�ж��û�״̬��0Ϊ�Ѷ����û�
				if(user.getUserS()>0) {
					a = 1;
				}else {
					a = 3;
				}
			}else if(msg.equals("admin")) {
				//�ж��û�״̬��0Ϊ�Ѷ����û�
				if(user.getUserS()>0) {
					//�жϵ�¼�û��Ƿ�Ϊ����Ա
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
	 *@Description: �û�ע��
	 *@return void
	 *@author lzx
	 *@date 2024-11-13 05:19:02
	 */
	private void userRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//��ȡ���ݵ�����
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		String userNameInfo = req.getParameter("userNameInfo");
		Integer userT = Integer.parseInt(req.getParameter("userT"));
		//������ʾ����
		int a = 0;
		if(userT!=null) {
			//��ȡ��ǰ�û�Ȩ��
			HttpSession session = req.getSession();
			UserInfo user = (UserInfo)session.getAttribute("user");
			if(userT==0&&user.getUserT()>0) {
				//�����ж��˺��Ƿ��ظ��ķ���
				if(!userService.pdUserName(userName)) {
					//�����µĶ�����б����û���Ϣ
					UserInfo userInfo = new UserInfo(null, userName, userPass, userNameInfo, 1, userT);
					//�����û���Ϣ
					if(userService.insertUser(userInfo)) {
						//����û��ɹ���ʾ����a��ֵ����Ϊ1
						a = 1;
					}
				}
			}else if(userT==0&&user.getUserT()<=0){
				//Ȩ�޲�������ʾ����a��ֵ����Ϊ2
				a = 2;
			}
			if(userT==1&&user.getUserT()==2) {
				//�����ж��˺��Ƿ��ظ��ķ���
				if(!userService.pdUserName(userName)) {
					//�����µĶ�����б����û���Ϣ
					UserInfo userInfo = new UserInfo(null, userName, userPass, userNameInfo, 1, userT);
					//�����û���Ϣ
					if(userService.insertUser(userInfo)) {
						//����û��ɹ���ʾ����a��ֵ����Ϊ1
						a = 1;
					}
				}
			}else if(userT==1&&user.getUserT()!=2){
				//Ȩ�޲�������ʾ����a��ֵ����Ϊ2
				a = 2;
			}
			resp.getWriter().println(a);
		}
		//�����ж��˺��Ƿ��ظ��ķ���
		if(!userService.pdUserName(userName)) {
			//�����µĶ�����б����û���Ϣ
			UserInfo userInfo = new UserInfo(null, userName, userPass, userNameInfo, 1, 0);
			//�����û���Ϣ
			if(userService.insertUser(userInfo)) {
				//ע��ɹ���ʾ����a��ֵ����Ϊ1
				a = 1;
			}
			resp.getWriter().println(a);
		}		
		
	}
	
	/**
	 *@MethodName: userUPDate
	 *@Description: �����û�����
	 *@return void
	 *@author lzx
	 *@date 2024-11-13 05:18:29
	 */
	private void userUPDate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//��ȡǰ̨���ݵ�����
		String userPass = req.getParameter("userPass");
		Integer user_s = Integer.parseInt(req.getParameter("user_s"));
		String userNameInfo = req.getParameter("userNameInfo");
		Integer user_id = Integer.parseInt(req.getParameter("user_id"));
		
		//���ó�ʼ������
		int a = 0;
		
		if(userPass!=null&userNameInfo!=null&user_s!=null) {
			//�����û����󲢱����û���Ҫ���ĵ���Ϣ
			UserInfo user = new UserInfo();
			user.setUserID(user_id);
			user.setUserPass(userPass);
			user.setUserS(user_s);
			user.setUserNameInfo(userNameInfo);
			
			//���ø������ݿ����û����ݵķ���������boolean���͵�bo
			boolean bo = userService.upDataUserInfo(user);
			
			//�û���Ϣ���³ɹ�
			if(bo) {
				a=1;
			}
		}
		resp.getWriter().println(a);
		
	}
	
	/**
	 *@MethodName: selectAllUser
	 *@Description: ��ȡ�����û�����
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-13 12:40:41
	 */
	private void selectAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//�����ӿ�ָ��ʵ����
		LogService logService = new LogServiceImpl();
		BookService bookService = new BookServiceImpl();
		
		//��ȡ�����û�����
		List<UserInfo> userList = userService.selectAllUserInfo();
		//��ȡ����ͼ������
		List<BookInfo> bookList = bookService.selectBookInfoAll();
		//��ȡ������־����
		List<LogInfo> logList = logService.selectLogInfo();
		
		//���û����ݼ��ϱ�����req��Χ��
		req.setAttribute("userList", userList);
		//��ͼ�����ݼ��ϱ�����req��Χ��
		req.setAttribute("bookList", bookList);
		//����־���ݼ��ϱ�����req��Χ��
		req.setAttribute("logList", logList);
		//����ת����index_admin.jspҳ��
		req.getRequestDispatcher("index_admin.jsp").forward(req, resp);
	}

}
