package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.BookInfo;

/**
 *@ClassName: bookDao
 *@Description: ͼ�����ݲ�ӿ�
 *@author lzx
 *@date 2024-11-13 11:24:19
 */
public interface BookDao {

	//��ȡ���ݿ�������ͼ������
	List<BookInfo> selectBookInfo();
	
	//�ϼ���ͼ��
	boolean insertBookInfo(BookInfo bookInfo);
	
	//����ͼ������
	boolean upDateBookInfo(BookInfo bookInfo);
	
	//ͨ���ؼ��ֲ�ѯͼ��
	List<BookInfo> selectBookInfoByGjz(String gjz);
	
	//ɾ��ͼ������
	boolean deleteBookInfo(int bookID);
	
}
