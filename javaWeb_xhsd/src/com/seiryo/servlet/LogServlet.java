package com.seiryo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seiryo.entity.BookInfo;
import com.seiryo.entity.Book_Log;
import com.seiryo.entity.LogInfo;
import com.seiryo.entity.UserInfo;
import com.seiryo.service.BookService;
import com.seiryo.service.LogService;
import com.seiryo.service.impl.BookServiceImpl;
import com.seiryo.service.impl.LogServiceImpl;

/**
 * Servlet implementation class LogServlet
 */
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//服务层接口指向其实现类
	private LogService logService = new LogServiceImpl();
	private BookService bookService = new BookServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//设置编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//获取前端传递的flag的值，判断需要进行的操作
		String flag = req.getParameter("flag");
		
		if("selectAllbook_LogList".equals(flag)) {
			selectAllbook_LogList(req,resp);
		}
	}
	
	/**
	 *@MethodName: selectAllLog
	 *@Description: 获取所有日志和图书数据
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-13 11:49:11
	 */
	private void selectAllbook_LogList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求中的books集合
		List<BookInfo> books = (List<BookInfo>)req.getAttribute("books");
		//设置一个日志类继承图书类对象的集合
		List<Book_Log> book_LogList = new ArrayList<Book_Log>();
		//获取所有日志数据
		List<LogInfo> allLogInfoList = logService.selectLogInfo();
		
		//获取session
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		int userID = user.getUserID();
		
		if(books!=null){
			//如果通过关键字获取到图书，只遍历搜索到的图书
			if(allLogInfoList.size()>0) {
				for(BookInfo book:books) {
					for(LogInfo logs:allLogInfoList) {
						if(book.getBookS()==0) {
							if(logs.getLogBookId()==book.getBookId()&&logs.getEndTime()==null) {
								//初始化日志类继承图书类对象
								Book_Log book_Log = new Book_Log();
								book_Log.setBookId(book.getBookId());
								book_Log.setBookName(book.getBookName());
								book_Log.setBookType(book.getBookType());
								book_Log.setBookImg(book.getBookImg());
								book_Log.setBookT(book.getBookT());
								book_Log.setBookS(book.getBookS());
								book_Log.setLogId(logs.getLogId());
								book_Log.setLogUserId(logs.getLogUserId());
								book_Log.setInitTime(logs.getInitTime());
								book_Log.setNowUserId(userID);
								book_LogList.add(book_Log);
							}
						}else if(book.getBookS()==1){
							//初始化日志类继承图书类对象
							Book_Log book_Log = new Book_Log();
							book_Log.setBookId(book.getBookId());
							book_Log.setBookName(book.getBookName());
							book_Log.setBookType(book.getBookType());
							book_Log.setBookImg(book.getBookImg());
							book_Log.setBookT(book.getBookT());
							book_Log.setBookS(book.getBookS());
							book_Log.setNowUserId(userID);
							book_LogList.add(book_Log);
						}
					}
				}
			}else{
				for(BookInfo book:books) {
					//初始化日志类继承图书类对象
					Book_Log book_Log = new Book_Log();
					book_Log.setBookId(book.getBookId());
					book_Log.setBookName(book.getBookName());
					book_Log.setBookType(book.getBookType());
					book_Log.setBookImg(book.getBookImg());
					book_Log.setBookT(book.getBookT());
					book_Log.setBookS(book.getBookS());
					book_Log.setNowUserId(userID);
					book_LogList.add(book_Log);
				}
			}
		}else {
			//遍历所有图书
			//获取所有图书数据
			List<BookInfo> allBookInfoList = bookService.selectBookInfoAll();
			if(allLogInfoList.size()>0) {
				for(BookInfo book:allBookInfoList) {
					for(LogInfo logs:allLogInfoList) {
						if(book.getBookS()==0) {
							if(logs.getLogBookId()==book.getBookId()&&logs.getEndTime()==null) {
								//初始化日志类继承图书类对象
								Book_Log book_Log = new Book_Log();
								book_Log.setBookId(book.getBookId());
								book_Log.setBookName(book.getBookName());
								book_Log.setBookType(book.getBookType());
								book_Log.setBookImg(book.getBookImg());
								book_Log.setBookT(book.getBookT());
								book_Log.setBookS(book.getBookS());
								book_Log.setLogId(logs.getLogId());
								book_Log.setLogUserId(logs.getLogUserId());
								book_Log.setInitTime(logs.getInitTime());
								book_Log.setNowUserId(userID);
								book_LogList.add(book_Log);
							}
						}else if(book.getBookS()==1){
							//初始化日志类继承图书类对象
							Book_Log book_Log = new Book_Log();
							book_Log.setBookId(book.getBookId());
							book_Log.setBookName(book.getBookName());
							book_Log.setBookType(book.getBookType());
							book_Log.setBookImg(book.getBookImg());
							book_Log.setBookT(book.getBookT());
							book_Log.setBookS(book.getBookS());
							book_Log.setNowUserId(userID);
							book_LogList.add(book_Log);
						}
					}
				}
			}else{
				for(BookInfo book:allBookInfoList) {
					//初始化日志类继承图书类对象
					Book_Log book_Log = new Book_Log();
					book_Log.setBookId(book.getBookId());
					book_Log.setBookName(book.getBookName());
					book_Log.setBookType(book.getBookType());
					book_Log.setBookImg(book.getBookImg());
					book_Log.setBookT(book.getBookT());
					book_Log.setBookS(book.getBookS());
					book_Log.setNowUserId(userID);
					book_LogList.add(book_Log);
				}
			}
		}
		//将日志类继承图书类对象的集合储存至req范围内
		req.setAttribute("book_LogList", book_LogList);
		//请求转发至index_book.jsp页面
		req.getRequestDispatcher("index_book.jsp").forward(req, resp);
	}
	
}
