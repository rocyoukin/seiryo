package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.BookDao;
import com.seiryo.entity.BookInfo;
import com.seiryo.utile.JDBCUTILE;

/**
 *@ClassName: BookDaoImpl
 *@Description: 图书数据层实现类
 *@author lzx
 *@date 2024-11-13 02:03:55
 */
public class BookDaoImpl implements BookDao {
	
	/**
	 *@MethodName: seleteBookInfo
	 *@Description:获取数据库中所有图书数据
	 *@author lzx
	 *@date 2024-11-13 02:04:29
	 */
	@Override
	public List<BookInfo> selectBookInfo() {
		//设置一个集合用来保存所有图书数据
		List<BookInfo> allBook = new ArrayList<BookInfo>();
		
		//创建 SQL
		String sql = "SELECT * FROM book_info";
		
		//创建ResultSet对象
		ResultSet rst = null;
		
		try {
			//调用JDBCUTILE
			rst = JDBCUTILE.JDBCSELECT(sql);
			//通过 while 进行遍历
			while(rst.next()) {
				//创建图书对象
				BookInfo bookInfo = new BookInfo();
				//添加图书对象属性值
				bookInfo.setBookId(rst.getInt("book_id"));
				bookInfo.setBookName(rst.getString("book_name"));
				bookInfo.setBookType(rst.getString("book_type"));
				bookInfo.setBookImg(rst.getString("book_img"));
				bookInfo.setBookT(rst.getInt("book_t"));
				bookInfo.setBookS(rst.getInt("book_s"));
				//将图书对象保存到集合中
				allBook.add(bookInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据
			try {
				JDBCUTILE.CLOSEDATA(null, null, rst);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return allBook;
	}
	
	/**
	 *@MethodName: insertBookInfo
	 *@Description:上架新图书
	 *@author lzx
	 *@date 2024-11-13 02:04:34
	 */
	@Override
	public boolean insertBookInfo(BookInfo bookInfo) {
		//创建 SQL
		String sql = "INSERT INTO book_info(book_name,book_type,book_img,book_t,book_s) VALUES(?,?,?,?,?)";
		
		//设置一个boolean变量用来判断是否上架新图书成功
		boolean bo = false;
		
		try {
			//调用JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, bookInfo.getBookName(), bookInfo.getBookType(), bookInfo.getBookImg(), bookInfo.getBookT(), bookInfo.getBookS());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}
	
	/**
	 *@MethodName: upDateBookInfo
	 *@Description:更改图书数据
	 *@author lzx
	 *@date 2024-11-13 02:04:42
	 */
	@Override
	public boolean upDateBookInfo(BookInfo bookInfo) {
		//创建 SQL
		String sql = "UPDATE book_info SET book_name = ?,book_type = ?,book_img = ?,book_t = ?,book_s = ? WHERE book_id = ?";
		
		//设置一个boolean变量用来判断是否更改图书数据成功
		boolean bo = false;
		
		try {
			//调用JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, bookInfo.getBookName(), bookInfo.getBookType(), bookInfo.getBookImg(), bookInfo.getBookT(), bookInfo.getBookS(), bookInfo.getBookId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}
	
	/**
	 *@MethodName: selectBookInfoByGjz
	 *@Description:通过关键字查询图书
	 *@author lzx
	 *@date 2024-11-17 01:36:41
	 */
	@Override
	public List<BookInfo> selectBookInfoByGjz(String gjz) {
		//设置一个集合用来保存查询到的图书数据
		List<BookInfo> Books = new ArrayList<BookInfo>();
		
		//创建 SQL
		String sql = "SELECT * FROM book_info WHERE book_name like '%"+gjz+"%'";
		
		//创建ResultSet对象
		ResultSet rst = null;
		
		try {
			//调用JDBCUTILE
			rst = JDBCUTILE.JDBCSELECT(sql);
			//通过 while 进行遍历
			while(rst.next()) {
				//创建图书对象
				BookInfo bookInfo = new BookInfo();
				//添加图书对象属性值
				bookInfo.setBookId(rst.getInt("book_id"));
				bookInfo.setBookName(rst.getString("book_name"));
				bookInfo.setBookType(rst.getString("book_type"));
				bookInfo.setBookImg(rst.getString("book_img"));
				bookInfo.setBookT(rst.getInt("book_t"));
				bookInfo.setBookS(rst.getInt("book_s"));
				//将图书对象保存到集合中
				Books.add(bookInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭数据
			try {
				JDBCUTILE.CLOSEDATA(null, null, rst);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return Books;
	}
	
	/**
	 *@MethodName: deleteBookInfo
	 *@Description:删除图书数据
	 *@author lzx
	 *@date 2024-11-18 02:48:51
	 */
	@Override
	public boolean deleteBookInfo(int bookID) {
		//创建 SQL
		String sql = "DELETE FROM book_info WHERE book_id = ?";
		
		//设置一个boolean变量用来判断是否上架新图书成功
		boolean bo = false;
		
		try {
			//调用JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, bookID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

}
