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

	//�����ӿ�ָ����ʵ����
	private LogService logService = new LogServiceImpl();
	private BookService bookService = new BookServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//���ñ����ʽ
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//��ȡǰ�˴��ݵ�flag��ֵ���ж���Ҫ���еĲ���
		String flag = req.getParameter("flag");
		
		if("selectAllbook_LogList".equals(flag)) {
			selectAllbook_LogList(req,resp);
		}
	}
	
	/**
	 *@MethodName: selectAllLog
	 *@Description: ��ȡ������־��ͼ������
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-13 11:49:11
	 */
	private void selectAllbook_LogList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//��ȡ�����е�books����
		List<BookInfo> books = (List<BookInfo>)req.getAttribute("books");
		//����һ����־��̳�ͼ�������ļ���
		List<Book_Log> book_LogList = new ArrayList<Book_Log>();
		//��ȡ������־����
		List<LogInfo> allLogInfoList = logService.selectLogInfo();
		
		//��ȡsession
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		int userID = user.getUserID();
		
		if(books!=null){
			//���ͨ���ؼ��ֻ�ȡ��ͼ�飬ֻ������������ͼ��
			if(allLogInfoList.size()>0) {
				for(BookInfo book:books) {
					for(LogInfo logs:allLogInfoList) {
						if(book.getBookS()==0) {
							if(logs.getLogBookId()==book.getBookId()&&logs.getEndTime()==null) {
								//��ʼ����־��̳�ͼ�������
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
							//��ʼ����־��̳�ͼ�������
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
					//��ʼ����־��̳�ͼ�������
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
			//��������ͼ��
			//��ȡ����ͼ������
			List<BookInfo> allBookInfoList = bookService.selectBookInfoAll();
			if(allLogInfoList.size()>0) {
				for(BookInfo book:allBookInfoList) {
					for(LogInfo logs:allLogInfoList) {
						if(book.getBookS()==0) {
							if(logs.getLogBookId()==book.getBookId()&&logs.getEndTime()==null) {
								//��ʼ����־��̳�ͼ�������
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
							//��ʼ����־��̳�ͼ�������
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
					//��ʼ����־��̳�ͼ�������
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
		//����־��̳�ͼ�������ļ��ϴ�����req��Χ��
		req.setAttribute("book_LogList", book_LogList);
		//����ת����index_book.jspҳ��
		req.getRequestDispatcher("index_book.jsp").forward(req, resp);
	}
	
}
