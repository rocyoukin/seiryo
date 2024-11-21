<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>管理员主页面</title>
		<link rel="stylesheet" href="css/admin.css" />
		<link rel="stylesheet" href="css/table.css" />
	</head>
	<body>
		<div>
			管理员：<b style="color: red;">${user.getUserNameInfo()}</b>欢迎您!
			<a href="/javaWeb_xhsd/UserServlet?flag=userQuit">
				<button type="button" id="userQuit" class="sig">退出</button>
			</a>
		</div>
		<!-- 遍历用户信息 -->
		<div class="all">
           				<table>
           					<caption>用户列表</caption>
           					<thead>
           						<tr>
           							<th>编号</th>
           							<th>账号</th>
           							<th>密码</th>
           							<th>昵名</th>
           							<th>状态</th>
           							<th>类型</th>
           							<th>操作</th>
           						</tr>
           					</thead>
           					<tbody>
           						<c:forEach var="userInfo" items="${userList}">
           							<tr>
           								<td>${userInfo.getUserID()}</td>
           								<td>${userInfo.getUserName()}</td>
           								<td>${userInfo.getUserPass()}</td>
           								<td>${userInfo.getUserNameInfo()}</td>
           								<td>${userInfo.getUserS()}</td>
           								<c:if test="${userInfo.getUserT()<=0}">
           									<td>普通用户</td>
           									<td>
           										<a href="/javaWeb_xhsd/UserServlet?flag=getUser&userID=${userInfo.getUserID()}">
													<button type="button" id="userUpDate" style="background-color:#7DDA58;color:#FFFFFF">修改</button>
												</a>
												<a href="/javaWeb_xhsd/UserServlet?flag=userUpDate_userT&userID=${userInfo.getUserID()}">
													<button type="button" id="userUpDate_userT" style="background-color:#E4080A;color:#FFFFFF">删除</button>
												</a>
           									</td>
           								</c:if>
           								<c:if test="${userInfo.getUserT()>0}">
           									<td>管理员</td>
           									<td>此账号为管理员不可修改或删除</td>
           								</c:if>
           							</tr>
           						</c:forEach>
           					</tbody>
           					<tfoot>
           						<tr>
           							<td colspan="7">
           								<a href="/javaWeb_xhsd/insertUser.jsp">
											<button type="button" id="userInster" style="background-color:#5DE2E7;color:#FFFFFF">添加用户</button>
										</a>
           							</td>
           						</tr>
           					</tfoot>
           				</table>
          			</div>
		<!-- 遍历图书信息 -->
		<div class="all">
        		<table>
        			<caption>图书列表</caption>
       					<thead>
       						<tr>
       							<th>编号</th>
       							<th>名称</th>
       							<th>介绍</th>
       							<th>封面</th>
       							<th>种类</th>
       							<th>状态</th>
       							<th>操作</th>
       						</tr>
       					</thead>
       					<tbody>
       						<c:forEach var="bookInfo" items="${bookList}">
       							<tr>
       								<td>${bookInfo.getBookId()}</td>
       								<td>${bookInfo.getBookName()}</td>
       								<td>${bookInfo.getBookType()}</td>
       								<td>${bookInfo.getBookImg()}</td>
       								<td>${bookInfo.getBookT()}</td>
       								<td>${bookInfo.getBookS()}</td>
       								<c:if test="${bookInfo.getBookS()>0}">
       									<td>
       										<a href="/javaWeb_xhsd/BookServlet?flag=book&bookID=${bookInfo.getBookId()}">
												<button type="button" id="bookUpDate" style="background-color:#7DDA58;color:#FFFFFF">修改</button>
											</a>
											<a href="/javaWeb_xhsd/BookServlet?flag=deleteBook&bookID=${bookInfo.getBookId()}">
												<button type="button" id="bookDelete" style="background-color:#E4080A;color:#FFFFFF">删除</button>
											</a>
       									</td>
       								</c:if>
       								<c:if test="${bookInfo.getBookS()<=0}">
       									<td>此书已被借走！不可操作！</td>
       								</c:if>
       							</tr>
       						</c:forEach>
       					</tbody>
       					<tfoot>
       						<tr>
       							<td colspan="7">
   									<a href="/javaWeb_xhsd/insertBook.jsp">
										<button type="button" id="bookInster" style="background-color:#5DE2E7;color:#FFFFFF">添加图书</button>
									</a>
       							</td>
       						</tr>
   					</tfoot>
   				</table>
      	</div>
		<!-- 遍历日志信息 -->
		<div class="all">
           				<table>
           					<caption>日志列表</caption>
           					<thead>
           						<tr>
           							<th>编号</th>
           							<th>用户编号</th>
           							<th>图书编号</th>
           							<th>借书时间</th>
           							<th>还书时间</th>
           						</tr>
           					</thead>
           					<tbody>
           						<c:forEach var="logInfo" items="${logList}">
           							<tr>
           								<td>${logInfo.getLogId()}</td>
           								<td>${userInfo.getLogUserId()}</td>
           								<td>${userInfo.getLogBookId()}</td>
           								<td>${userInfo.getInitTime()}</td>
           								<td>${userInfo.getEndTime()}</td>
           							</tr>
           						</c:forEach>
           					</tbody>
           				</table>
		</div>
		<div id="foot" align="center">
			<h5 class="h51">客服热线：4006666505</h5>
			<h5 class="h52">京ICP证：京B2—20171652号 &nbsp;&nbsp; Copyright © 2015-2024 新华互联电子商务有限责任公司  &nbsp;版权所有</h5>
		</div>
	</body>
</html>