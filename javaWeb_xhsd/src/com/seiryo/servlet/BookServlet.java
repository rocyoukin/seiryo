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
       
	//�����ӿ�ָ����ʵ����
	private BookService bookService = new BookServiceImpl();
	private LogService logService = new LogServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//���ñ����ʽ
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//��ȡǰ�˴��ݵ�flag��ֵ���ж���Ҫ���еĲ���
		String flag = req.getParameter("flag");
		
		if("selectBookByGjz".equals(flag)) {
			//ͨ���ؼ��ֲ�ѯͼ��
			selectBookByGjz(req,resp);
		}else if("borrowBookById".equals(flag)) {
			//����ͼ��
			borrowBookById(req, resp);
		}else if("returnBookById".equals(flag)) {
			//�黹ͼ��
			returnBookById(req, resp);
		}else if("insertBook".equals(flag)) {
			//���ͼ��
			insertBook(req, resp);
		}else if("deleteBook".equals(flag)) {
			//ɾ��ͼ��
			deleteBook(req, resp);
		}else if("Book".equals(flag)) {
			//ͨ��ͼ��id��ȡͼ����󣬲��ύ��upDate_Book.jspҳ��
			book(req, resp);
		}else if("book_upDate".equals(flag)) {
			//����ͼ��
			bookUpDate(req, resp);
		}
		
	}
	
	/**
	 *@MethodName: bookUpDate
	 *@Description: ����ͼ��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-19 05:36:04
	 */
	private void bookUpDate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//��ȡǰ�˴��ݵ�����
		Integer bookId = Integer.parseInt(req.getParameter("bookId"));
		String bookName = req.getParameter("bookName");
		String bookType = req.getParameter("bookType");
		String bookImg = req.getParameter("bookImg");
		Integer bookT = Integer.parseInt(req.getParameter("bookT"));
		Integer bookS = 1;
		
		//���ó�ʼ������
		int a = 0;
		
		if(bookName!=null) {
			//����ͼ����󲢱���ͼ����Ҫ���ĵ���Ϣ
			BookInfo book = new BookInfo();
			book.setBookId(bookId);
			book.setBookName(bookName);
			book.setBookType(bookType);
			book.setBookImg(bookImg);
			book.setBookT(bookT);
			book.setBookS(bookS);
			
			//���ø������ݿ���ͼ�����ݵķ���������boolean���͵�bo
			boolean bo = bookService.upDateBookInfo(book);
			
			//ͼ����Ϣ���³ɹ�
			if(bo) {
				a=1;
			}
		}
		resp.getWriter().println(a);
	}
	
	/**
	 *@MethodName: deleteBook
	 *@Description: ɾ��ͼ��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-19 02:44:07
	 */
	private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//��ȡ�����е�ͼ��ID
		Integer bookID = Integer.parseInt(req.getParameter("bookID"));
		
		//����ɾ��ͼ�����ݵķ���
		if(bookService.deleteBookInfo(bookID)) {
			//����ת��
			req.getRequestDispatcher("UserServlet?flag=select_all").forward(req, resp);
		}
		
	}
	
	/**
	 *@MethodName: book
	 *@Description: ͨ��ͼ��id��ȡͼ����󣬲��ύ��upDate_Book.jspҳ��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-19 02:25:34
	 */
	private void book(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//��ȡ�����е�ͼ��ID
		Integer bookID = Integer.parseInt(req.getParameter("bookID"));
		//��ȡ����ͼ������
		List<BookInfo> bookList = bookService.selectBookInfoAll();
		
		if(bookList.size()>0) {
			for(BookInfo book:bookList) {
				if(book.getBookId()==bookID) {
					//����Ҫ�������û����󱣴�������Χ��
					req.setAttribute("upDateBook", book);
				}
			}
		}
		
		req.getRequestDispatcher("upData_Book.jsp").forward(req, resp);
	}
	
	/**
	 *@MethodName: insertBook
	 *@Description:���ͼ��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-18 02:07:53
	 */
	private void insertBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//��ȡ���ݵ�����
		String bookName = req.getParameter("bookName");
		String bookType = req.getParameter("bookType");
		String bookImg = req.getParameter("bookImg");
		Integer bookT = Integer.parseInt(req.getParameter("bookT"));
		
		//������ʾ����
		int a = 0;
		
		if(bookName!=null) {
			//�����µ�ͼ����󱣴�ͼ����Ϣ
			BookInfo bookInfo = new BookInfo(null, bookName, bookType, bookImg, bookT, 1);
			//���ͼ��
			if(bookService.insertBookInfo(bookInfo)) {
				//��ӳɹ���ʾ����a��ֵ����Ϊ1
				a = 1;
			}
		}
		
		resp.getWriter().println(a);
	}
	
	/**
	 *@MethodName: selectBookByGjz
	 *@Description: ͨ���ؼ��ֲ�ѯͼ��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-11-13 01:26:39
	 */
	private void selectBookByGjz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//��ȡ�����еĹؼ���
		String gjz = req.getParameter("Search");
		//��ʼ����ʾ��Ϣ
		String msg = null;
		
		if(gjz!=null) {
			//����ͨ���ؼ��ֲ�ѯͼ��ķ���
			List<BookInfo> books = bookService.selectBookInfo(gjz);
			if(books.size()>0) {
				//����ѯ����ͼ�鴢����req��Χ��
				req.setAttribute("books", books);
				//����ת����LogServletҳ��
				req.getRequestDispatcher("LogServlet?flag=selectAllbook_LogList").forward(req, resp);
			}else {
				msg = "�ܱ�Ǹ����ѯ������ͼ�顣";
				//����ʾ������req��Χ��
				req.setAttribute("msg", msg);
				//����ת����index_book.jspҳ��
				req.getRequestDispatcher("index_book.jsp").forward(req, resp);
			}
		}else {
			msg = "��������Ϊ�գ�";
			//����ʾ������req��Χ��
			req.setAttribute("msg", msg);
			//����ת����index_book.jspҳ��
			req.getRequestDispatcher("index_book.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 *@MethodName: borrowBookById
	 *@Description: ����ͼ��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-13 05:28:18
	 */
	private void borrowBookById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//��ʼ����ʾ����
		int a = 0;
		//��ȡ�����е�ͼ��id
		Integer bookId = Integer.parseInt(req.getParameter("bookId"));
		
		//��ȡ��ǰ�û�
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		
		if(user!=null) {
			int userId = user.getUserID();
			//��ȡ��ǰʱ��
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String initTime = dateFormat.format(date);
			//��ʼ����־����
			LogInfo logInfo = new LogInfo(null,userId,bookId,initTime,null);
			
			//�������ɽ�����־�ķ���
			boolean bo = logService.insertLogInfo(logInfo);
			if(bo) {
				//����ͼ��״̬
				int bookS = 0;
				//��ʼ��ͼ�����
				BookInfo bookInfo = new BookInfo();
				bookInfo.setBookId(bookId);
				bookInfo.setBookS(bookS);
				//���ø���ͼ��״̬�ķ���
				boolean bo1 = bookService.upDateBookInfo(bookInfo);
				if(bo1) {
					//���ĳɹ�����ʾ����Ϊ1
					a = 1;
				}
			}
		}else {
			//û���û���¼
			a = 2;
		}
		
		//����ʼ���������ظ�ǰ��ҳ��
		resp.getWriter().println(a);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	/**
	 *@MethodName: returnBookById
	 *@Description: �黹ͼ��
	 *@return void
	 *@author lzx
	 * @throws IOException 
	 *@date 2024-11-13 11:12:46
	 */
	private void returnBookById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ʼ����ʾ����
		int a = 0;
		//��ȡ�����е�ͼ��id
		Integer bookId = Integer.parseInt(req.getParameter("bookId"));
		
		//��ȡ��ǰ�û���id
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		int userId = user.getUserID();
		
		//��ȡ��ǰʱ��
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String endTime = dateFormat.format(date);
		
		boolean bo = false;
		List<LogInfo> logInfoList = logService.selectLogInfo();
		if(logInfoList.size()>0) {
			for(LogInfo log:logInfoList) {
				if(log.getLogBookId()==bookId&&log.getLogUserId()==userId&&log.getEndTime()==null) {
					//��ʼ����־����
					LogInfo logInfo = new LogInfo(null,userId,bookId,null,endTime);
					//���ø�����־�ķ���
					bo = logService.upDateLogInfo(logInfo);
				}
			}
		}
		
		if(bo) {
			//����ͼ��״̬
			int bookS = 1;
			//��ʼ��ͼ�����
			BookInfo bookInfo = new BookInfo();
			bookInfo.setBookId(bookId);
			bookInfo.setBookS(bookS);
			//���ø���ͼ��״̬�ķ���
			boolean bo1 = bookService.upDateBookInfo(bookInfo);
			if(bo1) {
				//�黹�ɹ�����ʾ����Ϊ2
				a = 2;
			}
		}
		//����ʼ���������ظ�ǰ��ҳ��
		resp.getWriter().println(a);
		resp.getWriter().flush();
		resp.getWriter().close();
	}

}
