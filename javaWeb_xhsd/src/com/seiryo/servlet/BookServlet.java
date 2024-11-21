package com.seiryo.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.seiryo.service.impl.BookServiceImpl;
import com.seiryo.service.impl.LogServiceImpl;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//服务层接口指向其实现类
	private BookService bookService = new BookServiceImpl();
	private LogService logService = new LogServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//设置编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//获取前端传递的flag的值，判断需要进行的操作
		String flag = req.getParameter("flag");
		
		if("selectBookByGjz".equals(flag)) {
			//通过关键字查询图书
			selectBookByGjz(req,resp);
		}else if("borrowBookById".equals(flag)) {
			//借阅图书
			borrowBookById(req, resp);
		}else if("returnBookById".equals(flag)) {
			//归还图书
			returnBookById(req, resp);
		}else if("insertBook".equals(flag)) {
			//添加图书
			insertBook(req, resp);
		}else if("deleteBook".equals(flag)) {
			//删除图书
			deleteBook(req, resp);
		}else if("Book".equals(flag)) {
			//通过图书id获取图书对象，并提交给upDate_Book.jsp页面
			book(req, resp);
		}else if("book_upDate".equals(flag)) {
			//更改图书
			bookUpDate(req, resp);
		}
		
	}
	
	/**
	 *@MethodName: bookUpDate
	 *@Description: 更改图书
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-19 05:36:04
	 */
	private void bookUpDate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//获取前端传递的内容
		Integer bookId = Integer.parseInt(req.getParameter("bookId"));
		String bookName = req.getParameter("bookName");
		String bookType = req.getParameter("bookType");
		String bookImg = req.getParameter("bookImg");
		Integer bookT = Integer.parseInt(req.getParameter("bookT"));
		Integer bookS = 1;
		
		//设置初始化参数
		int a = 0;
		
		if(bookName!=null) {
			//创建图书对象并保存图书需要更改的信息
			BookInfo book = new BookInfo();
			book.setBookId(bookId);
			book.setBookName(bookName);
			book.setBookType(bookType);
			book.setBookImg(bookImg);
			book.setBookT(bookT);
			book.setBookS(bookS);
			
			//调用更改数据库中图书数据的方法并返回boolean类型的bo
			boolean bo = bookService.upDateBookInfo(book);
			
			//图书信息更新成功
			if(bo) {
				a=1;
			}
		}
		resp.getWriter().println(a);
	}
	
	/**
	 *@MethodName: deleteBook
	 *@Description: 删除图书
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-19 02:44:07
	 */
	private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求中的图书ID
		Integer bookID = Integer.parseInt(req.getParameter("bookID"));
		
		//调用删除图书数据的方法
		if(bookService.deleteBookInfo(bookID)) {
			//请求转发
			req.getRequestDispatcher("UserServlet?flag=select_all").forward(req, resp);
		}
		
	}
	
	/**
	 *@MethodName: book
	 *@Description: 通过图书id获取图书对象，并提交给upDate_Book.jsp页面
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-19 02:25:34
	 */
	private void book(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求中的图书ID
		Integer bookID = Integer.parseInt(req.getParameter("bookID"));
		//获取所有图书数据
		List<BookInfo> bookList = bookService.selectBookInfoAll();
		
		if(bookList.size()>0) {
			for(BookInfo book:bookList) {
				if(book.getBookId()==bookID) {
					//将需要操作的用户对象保存至请求范围内
					req.setAttribute("upDateBook", book);
				}
			}
		}
		
		req.getRequestDispatcher("upData_Book.jsp").forward(req, resp);
	}
	
	/**
	 *@MethodName: insertBook
	 *@Description:添加图书
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-18 02:07:53
	 */
	private void insertBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//获取传递的数据
		String bookName = req.getParameter("bookName");
		String bookType = req.getParameter("bookType");
		String bookImg = req.getParameter("bookImg");
		Integer bookT = Integer.parseInt(req.getParameter("bookT"));
		
		//设置提示参数
		int a = 0;
		
		if(bookName!=null) {
			//创建新的图书对象保存图书信息
			BookInfo bookInfo = new BookInfo(null, bookName, bookType, bookImg, bookT, 1);
			//添加图书
			if(bookService.insertBookInfo(bookInfo)) {
				//添加成功提示参数a的值设置为1
				a = 1;
			}
		}
		
		resp.getWriter().println(a);
	}
	
	/**
	 *@MethodName: selectBookByGjz
	 *@Description: 通过关键字查询图书
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-13 01:26:39
	 */
	private void selectBookByGjz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求中的关键字
		String gjz = req.getParameter("Search");
		//初始化提示信息
		String msg = null;
		
		if(gjz!=null) {
			//调用通过关键字查询图书的方法
			List<BookInfo> books = bookService.selectBookInfo(gjz);
			if(books.size()>0) {
				//将查询到的图书储存至req范围内
				req.setAttribute("books", books);
				//请求转发至LogServlet页面
				req.getRequestDispatcher("LogServlet?flag=selectAllbook_LogList").forward(req, resp);
			}else {
				msg = "很抱歉，查询不到此图书。";
				//将提示储存至req范围内
				req.setAttribute("msg", msg);
				//请求转发至index_book.jsp页面
				req.getRequestDispatcher("index_book.jsp").forward(req, resp);
			}
		}else {
			msg = "书名不能为空！";
			//将提示储存至req范围内
			req.setAttribute("msg", msg);
			//请求转发至index_book.jsp页面
			req.getRequestDispatcher("index_book.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 *@MethodName: borrowBookById
	 *@Description: 借阅图书
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-13 05:28:18
	 */
	private void borrowBookById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//初始化提示变量
		int a = 0;
		//获取请求中的图书id
		Integer bookId = Integer.parseInt(req.getParameter("bookId"));
		
		//获取当前用户
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		
		if(user!=null) {
			int userId = user.getUserID();
			//获取当前时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String initTime = dateFormat.format(date);
			//初始化日志对象
			LogInfo logInfo = new LogInfo(null,userId,bookId,initTime,null);
			
			//调用生成借书日志的方法
			boolean bo = logService.insertLogInfo(logInfo);
			if(bo) {
				//设置图书状态
				int bookS = 0;
				//初始化图书对象
				BookInfo bookInfo = new BookInfo();
				bookInfo.setBookId(bookId);
				bookInfo.setBookS(bookS);
				//调用更改图书状态的方法
				boolean bo1 = bookService.upDateBookInfo(bookInfo);
				if(bo1) {
					//借阅成功，提示变量为1
					a = 1;
				}
			}
		}else {
			//没有用户登录
			a = 2;
		}
		
		//将初始化参数返回给前端页面
		resp.getWriter().println(a);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	/**
	 *@MethodName: returnBookById
	 *@Description: 归还图书
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-13 11:12:46
	 */
	private void returnBookById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//初始化提示变量
		int a = 0;
		//获取请求中的图书id
		Integer bookId = Integer.parseInt(req.getParameter("bookId"));
		
		//获取当前用户的id
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		int userId = user.getUserID();
		
		//获取当前时间
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String endTime = dateFormat.format(date);
		
		boolean bo = false;
		List<LogInfo> logInfoList = logService.selectLogInfo();
		if(logInfoList.size()>0) {
			for(LogInfo log:logInfoList) {
				if(log.getLogBookId()==bookId&&log.getLogUserId()==userId&&log.getEndTime()==null) {
					//初始化日志对象
					LogInfo logInfo = new LogInfo(null,userId,bookId,null,endTime);
					//调用更改日志的方法
					bo = logService.upDateLogInfo(logInfo);
				}
			}
		}
		
		if(bo) {
			//设置图书状态
			int bookS = 1;
			//初始化图书对象
			BookInfo bookInfo = new BookInfo();
			bookInfo.setBookId(bookId);
			bookInfo.setBookS(bookS);
			//调用更改图书状态的方法
			boolean bo1 = bookService.upDateBookInfo(bookInfo);
			if(bo1) {
				//归还成功，提示变量为2
				a = 2;
			}
		}
		//将初始化参数返回给前端页面
		resp.getWriter().println(a);
		resp.getWriter().flush();
		resp.getWriter().close();
	}

}
