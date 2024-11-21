package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.BookDao;
import com.seiryo.entity.BookInfo;
import com.seiryo.utile.JDBCUTILE;

/**
 *@ClassName: BookDaoImpl
 *@Description: ͼ�����ݲ�ʵ����
 *@author lzx
 *@date 2024-11-13 02:03:55
 */
public class BookDaoImpl implements BookDao {
	
	/**
	 *@MethodName: seleteBookInfo
	 *@Description:��ȡ���ݿ�������ͼ������
	 *@author lzx
	 *@date 2024-11-13 02:04:29
	 */
	@Override
	public List<BookInfo> selectBookInfo() {
		//����һ������������������ͼ������
		List<BookInfo> allBook = new ArrayList<BookInfo>();
		
		//���� SQL
		String sql = "SELECT * FROM book_info";
		
		//����ResultSet����
		ResultSet rst = null;
		
		try {
			//����JDBCUTILE
			rst = JDBCUTILE.JDBCSELECT(sql);
			//ͨ�� while ���б���
			while(rst.next()) {
				//����ͼ�����
				BookInfo bookInfo = new BookInfo();
				//���ͼ���������ֵ
				bookInfo.setBookId(rst.getInt("book_id"));
				bookInfo.setBookName(rst.getString("book_name"));
				bookInfo.setBookType(rst.getString("book_type"));
				bookInfo.setBookImg(rst.getString("book_img"));
				bookInfo.setBookT(rst.getInt("book_t"));
				bookInfo.setBookS(rst.getInt("book_s"));
				//��ͼ����󱣴浽������
				allBook.add(bookInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
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
	 *@Description:�ϼ���ͼ��
	 *@author lzx
	 *@date 2024-11-13 02:04:34
	 */
	@Override
	public boolean insertBookInfo(BookInfo bookInfo) {
		//���� SQL
		String sql = "INSERT INTO book_info(book_name,book_type,book_img,book_t,book_s) VALUES(?,?,?,?,?)";
		
		//����һ��boolean���������ж��Ƿ��ϼ���ͼ��ɹ�
		boolean bo = false;
		
		try {
			//����JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, bookInfo.getBookName(), bookInfo.getBookType(), bookInfo.getBookImg(), bookInfo.getBookT(), bookInfo.getBookS());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}
	
	/**
	 *@MethodName: upDateBookInfo
	 *@Description:����ͼ������
	 *@author lzx
	 *@date 2024-11-13 02:04:42
	 */
	@Override
	public boolean upDateBookInfo(BookInfo bookInfo) {
		//���� SQL
		String sql = "UPDATE book_info SET book_name = ?,book_type = ?,book_img = ?,book_t = ?,book_s = ? WHERE book_id = ?";
		
		//����һ��boolean���������ж��Ƿ����ͼ�����ݳɹ�
		boolean bo = false;
		
		try {
			//����JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, bookInfo.getBookName(), bookInfo.getBookType(), bookInfo.getBookImg(), bookInfo.getBookT(), bookInfo.getBookS(), bookInfo.getBookId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}
	
	/**
	 *@MethodName: selectBookInfoByGjz
	 *@Description:ͨ���ؼ��ֲ�ѯͼ��
	 *@author lzx
	 *@date 2024-11-17 01:36:41
	 */
	@Override
	public List<BookInfo> selectBookInfoByGjz(String gjz) {
		//����һ���������������ѯ����ͼ������
		List<BookInfo> Books = new ArrayList<BookInfo>();
		
		//���� SQL
		String sql = "SELECT * FROM book_info WHERE book_name like '%"+gjz+"%'";
		
		//����ResultSet����
		ResultSet rst = null;
		
		try {
			//����JDBCUTILE
			rst = JDBCUTILE.JDBCSELECT(sql);
			//ͨ�� while ���б���
			while(rst.next()) {
				//����ͼ�����
				BookInfo bookInfo = new BookInfo();
				//���ͼ���������ֵ
				bookInfo.setBookId(rst.getInt("book_id"));
				bookInfo.setBookName(rst.getString("book_name"));
				bookInfo.setBookType(rst.getString("book_type"));
				bookInfo.setBookImg(rst.getString("book_img"));
				bookInfo.setBookT(rst.getInt("book_t"));
				bookInfo.setBookS(rst.getInt("book_s"));
				//��ͼ����󱣴浽������
				Books.add(bookInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
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
	 *@Description:ɾ��ͼ������
	 *@author lzx
	 *@date 2024-11-18 02:48:51
	 */
	@Override
	public boolean deleteBookInfo(int bookID) {
		//���� SQL
		String sql = "DELETE FROM book_info WHERE book_id = ?";
		
		//����һ��boolean���������ж��Ƿ��ϼ���ͼ��ɹ�
		boolean bo = false;
		
		try {
			//����JDBCUTILE
			bo = JDBCUTILE.JDBCUPDATA(sql, bookID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

}
