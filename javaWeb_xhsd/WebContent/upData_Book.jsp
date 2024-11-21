<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>更改图书信息</title>
		<link rel="stylesheet" href="css/admin.css" />
	</head>
	<body>
		<div class="all">
      		<div class="reg">
      			<h3>修改图书信息</h3>
      			<div class="input">
     				<span>图书编号: </span>
   					<input type="text" id="bookId" id="bookId" value="${upDateBook.getBookId()}" class="input-text" readonly="readonly" />
   				</div>
   				<div class="input">
     				<span>名称: </span>
     				<input type="text" name="bookName" id="bookName" value="${upDateBook.getBookName()}" class="input-text" />
   				</div>
   				<div class="input">
     				<span>介绍: </span>
     				<input type="text" name="bookType" id="bookType" value="${upDateBook.getBookType()}" class="input-text" />
   				</div>
   				<div class="input">
     				<span>封面: </span>
     				<input type="text" name="bookImg" id="bookImg" value="${upDateBook.getBookImg()}" class="input-text" />
   				</div>
   				<div class="input">
     				<span>种类: </span>
     				<input type="text" name="bookT" id="bookT" value="${upDateBook.getBookT()}" class="input-text" />
   				</div>
   				<div class="text-center">
      				<input type="button" value="保存并提交" class="tijiao" id="book_upDate" />
         		</div>
      		</div>
   		</div>
   		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    	<script type="text/javascript" src="js/upDate.js"></script>
	</body>
</html>