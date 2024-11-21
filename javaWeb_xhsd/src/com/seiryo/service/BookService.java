package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.BookInfo;

/**
 *@ClassName: BookService
 *@Description: ͼ������ӿ�
 *@author lzx
 *@date 2024-11-13 05:10:31
 */
public interface BookService {

	//��ȡ���ݿ�������ͼ��
	List<BookInfo> selectBookInfoAll();
	
	//ͨ���ؼ��ֲ�ѯͼ��
	List<BookInfo> selectBookInfo(String gjz);
	
	//�ϼ���ͼ��
	boolean insertBookInfo(BookInfo bookInfo);
	
	//����ͼ������
	boolean upDateBookInfo(BookInfo bookInfo);
	
	//ɾ��ͼ������
	boolean deleteBookInfo(int bookID);
	
}
