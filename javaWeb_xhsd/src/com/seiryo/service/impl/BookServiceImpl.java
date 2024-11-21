package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.BookDao;
import com.seiryo.dao.impl.BookDaoImpl;
import com.seiryo.entity.BookInfo;
import com.seiryo.service.BookService;

/**
 *@ClassName: BookServiceImpl
 *@Description: ͼ������ʵ����
 *@author lzx
 *@date 2024-11-13 05:20:34
 */
public class BookServiceImpl implements BookService {
	
	//���ݲ�ӿ�ָ��ʵ����
	private BookDao bookDao = new BookDaoImpl();
	
	/**
	 *@MethodName: selectBookInfoAll
	 *@Description:��ȡ���ݿ�������ͼ��
	 *@author lzx
	 *@date 2024-11-14 05:20:54
	 */
	@Override
	public List<BookInfo> selectBookInfoAll() {
		return bookDao.selectBookInfo();
	}
	
	/**
	 *@MethodName: selectBookInfo
	 *@Description:ͨ���ؼ��ֲ�ѯͼ��
	 *@author lzx
	 *@date 2024-11-14 05:21:00
	 */
	@Override
	public List<BookInfo> selectBookInfo(String gjz) {
		return bookDao.selectBookInfoByGjz(gjz);
	}
	
	/**
	 *@MethodName: insertBookInfo
	 *@Description:�ϼ���ͼ��
	 *@author lzx
	 *@date 2024-11-14 05:21:07
	 */
	@Override
	public boolean insertBookInfo(BookInfo bookInfo) {
		return bookDao.insertBookInfo(bookInfo);
	}
	
	/**
	 *@MethodName: upDateBookInfo
	 *@Description:����ͼ������
	 *@author lzx
	 *@date 2024-11-14 05:21:13
	 */
	@Override
	public boolean upDateBookInfo(BookInfo bookInfo) {
		return bookDao.upDateBookInfo(bookInfo);
	}
	
	/**
	 *@MethodName: deleteBookInfo
	 *@Description:ɾ��ͼ������
	 *@author lzx
	 *@date 2024-11-18 02:47:11
	 */
	@Override
	public boolean deleteBookInfo(int bookID) {
		return bookDao.deleteBookInfo(bookID);
	}

}
