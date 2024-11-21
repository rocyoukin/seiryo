package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.BookInfo;

/**
 *@ClassName: BookService
 *@Description: 图书服务层接口
 *@author lzx
 *@date 2024-11-13 05:10:31
 */
public interface BookService {

	//获取数据库中所有图书
	List<BookInfo> selectBookInfoAll();
	
	//通过关键字查询图书
	List<BookInfo> selectBookInfo(String gjz);
	
	//上架新图书
	boolean insertBookInfo(BookInfo bookInfo);
	
	//更改图书数据
	boolean upDateBookInfo(BookInfo bookInfo);
	
	//删除图书数据
	boolean deleteBookInfo(int bookID);
	
}
