<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书销售网</title>
</head>
<style>
	#main{
		position: relative;
		margin: 0 auto;
		width: 1160px;
		height: auto;
	}
	#inp{
		left: 350px;
		position: relative;
	}
	#inp input{
		outline: none;
		border: none;	
		border: 2px solid rgb(209, 10, 10);
		overflow: hidden;
	}
	h1{
		text-align: center;
	}
	#book_ul{
		width:1160px;
		list-style: none;
		background-color: rgb(223, 230, 233);
	}
	#book{
		width: 200px;
		height: 300px;
		position:relative;
		display:inline-block;
		padding: 5px;
		margin: 5px;
		background: white;
		cursor: pointer;
	}
	#book img{
		width: 200px;
		height: 200px;
	}
	#book:hover{
		border: 1px solid red; 
		padding: 3px;
		width: 202px;
	}
	span{
		position: relative;
	}
	
</style>

<body>
<%
	String tag = (String)request.getAttribute("firstOpen");
	if(tag==null){
		request.setAttribute("firstOpen", "1");
		request.getRequestDispatcher("Main").forward(request, response);
	}
%>
<jsp:include page="loginTag.jsp"/>
<div id="main">
	<h1>图书销售网</h1>
	<br/>
	<div id="inp">
		<form action="Main" method="post">
	
		书名：<input placeholder="输入书名搜索" name="findOfName" value="${findOfName }">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		类型：
		<select name="booktype">
			<option value ="">全部</option>
			<c:forEach items="${booktypes}" var="type">
				<option value="${type.name }" ${booktype eq type.name ?'selected':''}>${type.name }</option>
			</c:forEach>
		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button style="width: 80px">查找</button>
	</form>
	</div>
	
	<br/>
	<br/>
	
		
	<ul id="book_ul">
		<c:forEach items="${books}" var="book">
			<li id="book" onclick="window.location.href='BookMsg?bookId='+${book.bookId}">
				<img src="${book.photo }">
				<span style="left:10px; font-size:17px;">${book.bookname}</span>
				<br/>
				<br/>
				<span style="left:10px; font-size:19px; color: red">￥${book.price}元</span>
			</li>
			
		</c:forEach>
	</ul>
	
</div>
<br/>
</body>

</html>

