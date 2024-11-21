package com.seiryo.entity;

/**
 *@ClassName: bookInfo
 *@Description: 图书类
 *@author lzx
 *@date 2024-11-13 10:57:31
 */
public class BookInfo {

	//图书ID
	private Integer bookId;
	
	//图书名
	private String bookName;
	
	//图书类型
	private String bookType;
	
	//图书照片
	private String bookImg;
	
	//图书种类
	private Integer bookT;
	
	//图书状态
	private Integer bookS;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public Integer getBookT() {
		return bookT;
	}

	public void setBookT(Integer bookT) {
		this.bookT = bookT;
	}

	public Integer getBookS() {
		return bookS;
	}

	public void setBookS(Integer bookS) {
		this.bookS = bookS;
	}

	public BookInfo(Integer bookId, String bookName, String bookType, String bookImg, Integer bookT, Integer bookS) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookImg = bookImg;
		this.bookT = bookT;
		this.bookS = bookS;
	}

	public BookInfo() {
		super();
	}

	@Override
	public String toString() {
		return "bookInfo [bookId=" + bookId + ", bookName=" + bookName + ", bookType=" + bookType + ", bookImg="
				+ bookImg + ", bookT=" + bookT + ", bookS=" + bookS + "]";
	}

}
