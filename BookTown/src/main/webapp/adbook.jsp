<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理</title>
</head>
<style>
	#admain{
		position: relative;
		margin: 0 auto;
		width: 800px;
		height: auto;
	}
	#adinp{
		left: 170px;
		position: relative;
	}
	tr{
		height: 40px;
		text-align: center;
	}

	th{
		width: 150px;
	}
	h1{
		text-align: center;
	}
	img{
		width: 70px;
		height: 70px;
	}
</style>

<body>

<jsp:include page="adloginTag.jsp"/>
<div id="admain">
	<h1>图 书 管 理</h1>
	<br/>
	<div id="adinp">
		<form action="AdBook" method="post">
	
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
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href='AddBook'>添加图书</a>
		
	</form>
	</div>
	
	<br/>
	<br/>
	<table border="1">
		<tr>
			<th style="width: 200px">图片</th>
			<th>名称</th>
			<th>价格</th>
			<th>类型</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${books}" var="book">
		<tr>
			<td style="width: 50px;height: 70px;"><img src="${book.photo}"></td>
			<td>${book.bookname}</td>
			<td>${book.price}元</td>
			<td>${book.typename}</td>
			<td>
				<a href="BookAlterMsg?bookId=${book.bookId}">修改</a>
				<a href="BookDelete?bookId=${book.bookId}" style="color: red">删除</a>
			</td>
			
		</tr>
		</c:forEach>	
	</table>
</div>
	
</body>

</html>

