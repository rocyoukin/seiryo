package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.BookInfo;

/**
 *@ClassName: bookDao
 *@Description: 图书数据层接口
 *@author lzx
 *@date 2024-11-13 11:24:19
 */
public interface BookDao {

	//获取数据库中所有图书数据
	List<BookInfo> selectBookInfo();
	
	//上架新图书
	boolean insertBookInfo(BookInfo bookInfo);
	
	//更改图书数据
	boolean upDateBookInfo(BookInfo bookInfo);
	
	//通过关键字查询图书
	List<BookInfo> selectBookInfoByGjz(String gjz);
	
	//删除图书数据
	boolean deleteBookInfo(int bookID);
	
}
