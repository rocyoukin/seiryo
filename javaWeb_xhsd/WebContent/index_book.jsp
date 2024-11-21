<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="x-ua-compatible" content="ie=edge">
	    <title>主页面</title>
	    <meta name="description" content="">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="shortcut icon" type="image/x-icon" href="images/icon/favicon.png">
	
	    <!-- CSS here -->
	    <link rel="stylesheet" href="css/bootstrap.min.css">
	    <link rel="stylesheet" href="css/owl.carousel.min.css">
	    <link rel="stylesheet" href="css/slicknav.css">
	    <link rel="stylesheet" href="css/animate.min.css">
	    <link rel="stylesheet" href="css/price_rangs.css">
	    <link rel="stylesheet" href="css/magnific-popup.css">
	    <link rel="stylesheet" href="css/fontawesome-all.min.css">
	    <link rel="stylesheet" href="css/themify-icons.css">
	    <link rel="stylesheet" href="css/slick.css">
	    <link rel="stylesheet" href="css/nice-select.css">
	    <link rel="stylesheet" href="css/style.css">
	    <link rel="stylesheet" href="css/admin.css" />
	</head>
	<body>
    	<header>
        	<div class="header-area">
         		<div class="main-header ">
             		<div class="header-top ">
                		<div class="container">
                   			<div class="row">
                    			<div class="col-xl-12">
                        			<div class="d-flex justify-content-between align-items-center flex-sm">
                           				<div class="header-info-left d-flex align-items-center">
			                                <!-- logo -->
			                                <div class="logo">
			                                	<img src="images/logo/logo.png" alt="">
			                                </div>
			                                <!-- Search Box -->
			                                <form action="BookServlet?flag=selectBookByGjz" class="form-box" method="post">
			                                    <input type="text" name="Search" value="" />
			                                    <input type="submit" name="submit" value="查找" />
			                                </form>
			                                <c:if test="${msg != null}">
												<br /><h4 style="color:red ; font-size:40px"><c:out value="${msg}"></c:out></h4>
											</c:if>
                           				</div>
			                            <div class="header-info-right d-flex align-items-center">
			                            	<c:if test="${user == null}">
												<a href="login.jsp" class="btn header-btn">Login</a>
											</c:if>
											<c:if test="${user != null}">
												<div>
													用户：<b style="color: red;">${user.getUserNameInfo()}</b>欢迎来到新华书店！
													<a href="/javaWeb_xhsd/UserServlet?flag=userQuit">
														<button type="button" id="userQuit" class="sig">退出</button>
													</a>
												</div>
											</c:if>
			                            </div>
                        			</div>
                    			</div>
                			</div>
            			</div>
        			</div>
				</div>
			</div>
		</header>
		<!-- 遍历图书 -->
		<section class="our-client section-padding best-selling">
			<div class="container">
	        	<div class="row justify-content-between">
		            <div class="col-xl-5 col-lg-5 col-md-12">
		                <!-- Section Tittle -->
		                <div class="section-tittle  mb-40">
		                    <h2>日本文学</h2>
		                </div> 
		            </div>
		        </div>
		    </div>
			<div class="container">
		        <!-- Nav Card -->
		        <div class="tab-content" id="nav-tabContent">
		            <div class="tab-pane fade show active" id="nav-one" role="tabpanel" aria-labelledby="nav-one-tab"> 
		                <div class="row">
		                	 <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6">
		                        <div class="properties pb-30">
		                            <div class="properties-card">
		                                <c:forEach var="books" items="${book_LogList}">
		                                	<c:if test="${books.getBookT()==1}">
		                                		<div class="properties-img">
				                                    <img src="${books.getBookImg()}" alt="">
				                                </div>
				                                <div class="properties-caption properties-caption2">
				                                    <h3><c:out value="${books.getBookName()}" /></h3>
				                                    <div class="properties-footer d-flex justify-content-between align-items-center">
				                                        <c:if test="${books.getBookS()==0}">
				                                        	<c:if test="${books.getNowUserId()!=null&&books.getNowUserId()==books.getLogUserId()}">
				                                        		<div class="review">
				                                        			<input type="text" name="bookId" id="bookId" value="${books.getBookId()}" style = "display:none" />
				                                        			<input type="button" id="button_return" class="btn header-btn" value="归还图书"/>
						                                        </div>
				                                        	</c:if>
				                                        	<c:if test="${books.getNowUserId()!=null&&books.getNowUserId()!=books.getLogUserId()}">
				                                        		<div class="review">
						                                        	<p>当前图书已被借阅</p>
						                                        </div>
				                                        	</c:if>
				                                        	<c:if test="${books.getNowUserId()==null}">
				                                        		<div class="review">
						                                        	<p>当前图书已被借阅</p>
						                                        </div>
				                                        	</c:if>
				                                        </c:if>
				                                        <c:if test="${books.getBookS()==1}">
			                                        		<div class="review">
					                                        	<input type="text" name="bookId" id="bookId" value="${books.getBookId()}" style = "display:none" />
			                                        			<input type="button" id="button_borrow" class="btn header-btn" value="借阅图书"/>
					                                        </div>
			                                        	</c:if>
				                                    </div>
				                                </div>
		                                	</c:if>
		                                </c:forEach>
		                            </div>
		                        </div>
		                    </div>
		                </div>
	                </div>
                </div>
            </div>
			<div class="container">
	        	<div class="row justify-content-between">
		            <div class="col-xl-5 col-lg-5 col-md-12">
		                <!-- Section Tittle -->
		                <div class="section-tittle  mb-40">
		                    <h2>教科书</h2>
		                </div> 
		            </div>
		        </div>
		    </div>
		    <div class="container">
		        <!-- Nav Card -->
		        <div class="tab-content" id="nav-tabContent">
		            <div class="tab-pane fade show active" id="nav-one" role="tabpanel" aria-labelledby="nav-one-tab"> 
		                <div class="row">
		                	 <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6">
		                        <div class="properties pb-30">
		                            <div class="properties-card">
		                                <c:forEach var="books" items = "${book_LogList}">
		                                	<c:if test="${books.getBookT()==2}">
		                                		<div class="properties-img">
				                                    <img src="${books.getBookImg()}" alt="">
				                                </div>
				                                <div class="properties-caption properties-caption2">
				                                    <h3><c:out value="${books.getBookName()}" /></h3>
				                                    <div class="properties-footer d-flex justify-content-between align-items-center">
				                                        <c:if test="${books.getBookS()==0}">
				                                        	<c:if test="${books.getNowUserId()!=null&&books.getNowUserId()==books.getLogUserId()}">
				                                        		<div class="review">
				                                        			<input type="text" name="bookId" id="bookId" value="${books.getBookId()}" style = "display:none" />
				                                        			<input type="button" id="button_return" class="btn header-btn" value="归还图书"/>
						                                        </div>
				                                        	</c:if>
				                                        	<c:if test="${books.getNowUserId()!=null&&books.getNowUserId()!=books.getLogUserId()}">
				                                        		<div class="review">
						                                        	<p>当前图书已被借阅</p>
						                                        </div>
				                                        	</c:if>
				                                        	<c:if test="${books.getNowUserId()==null}">
				                                        		<div class="review">
						                                        	<p>当前图书已被借阅</p>
						                                        </div>
				                                        	</c:if>
				                                        </c:if>
				                                        <c:if test="${books.getBookS()==1}">
			                                        		<div class="review">
					                                        	<input type="text" name="bookId" id="bookId" value="${books.getBookId()}" style = "display:none" />
			                                        			<input type="button" id="button_borrow" class="btn header-btn" value="借阅图书"/>
					                                        </div>
			                                        	</c:if>
				                                    </div>
				                                </div>
		                                	</c:if>
		                                </c:forEach>
		                            </div>
		                        </div>
		                    </div>
		                </div>
	                </div>
                </div>
            </div>
			<div class="container">
	        	<div class="row justify-content-between">
		            <div class="col-xl-5 col-lg-5 col-md-12">
		                <!-- Section Tittle -->
		                <div class="section-tittle  mb-40">
		                    <h2>中文文学</h2>
		                </div> 
		            </div>
		        </div>
		    </div>
		    <div class="container">
		        <!-- Nav Card -->
		        <div class="tab-content" id="nav-tabContent">
		            <div class="tab-pane fade show active" id="nav-one" role="tabpanel" aria-labelledby="nav-one-tab"> 
		                <div class="row">
		                	 <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6">
		                        <div class="properties pb-30">
		                            <div class="properties-card">
		                                <c:forEach var="books" items = "${book_LogList}">
		                                	<c:if test="${books.getBookT()==3}">
		                                		<div class="properties-img">
				                                    <img src="${books.getBookImg()}" alt="">
				                                </div>
				                                <div class="properties-caption properties-caption2">
				                                    <h3><c:out value="${books.getBookName()}" /></h3>
				                                    <div class="properties-footer d-flex justify-content-between align-items-center">
				                                        <c:if test="${books.getBookS()==0}">
				                                        	<c:if test="${books.getNowUserId()!=null&&books.getNowUserId()==books.getLogUserId()}">
				                                        		<div class="review">
				                                        			<input type="text" name="bookId" id="bookId" value="${books.getBookId()}" style = "display:none" />
				                                        			<input type="button" id="button_return" class="btn header-btn" value="归还图书"/>
						                                        </div>
				                                        	</c:if>
				                                        	<c:if test="${books.getNowUserId()!=null&&books.getNowUserId()!=books.getLogUserId()}">
				                                        		<div class="review">
						                                        	<p>当前图书已被借阅</p>
						                                        </div>
				                                        	</c:if>
				                                        	<c:if test="${books.getNowUserId()==null}">
				                                        		<div class="review">
						                                        	<p>当前图书已被借阅</p>
						                                        </div>
				                                        	</c:if>
				                                        </c:if>
				                                        <c:if test="${books.getBookS()==1}">
			                                        		<div class="review">
					                                        	<input type="text" name="bookId" id="bookId" value="${books.getBookId()}" style = "display:none" />
			                                        			<input type="button" id="button_borrow" class="btn header-btn" value="借阅图书"/>
					                                        </div>
			                                        	</c:if>
				                                    </div>
				                                </div>
		                                	</c:if>
		                                </c:forEach>
		                            </div>
		                        </div>
		                    </div>
		                </div>
	                </div>
                </div>
            </div>
		</section>
		<div id="foot" align="center">
			<h5 class="h51">客服热线：4006666505</h5>
			<h5 class="h52">京ICP证：京B2—20171652号 &nbsp;&nbsp; Copyright © 2015-2024 新华互联电子商务有限责任公司  &nbsp;版权所有</h5>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    	<script type="text/javascript" src="js/index_book.js"></script>
	</body>
</html>