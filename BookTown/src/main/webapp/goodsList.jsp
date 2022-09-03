<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单中心</title>
</head>
<style>
	#main{
		position: relative;
		margin: 0 auto;
		width: 1000px;
		height: auto;
	}
	#inp{
		position: relative;
		left:280px;
		
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
</style>


<body>
<jsp:include page="loginTag.jsp"/>
	<a href="Main">返回主页</a>
	
	<div id="main">
		<h1>订 单 中 心</h1>
		<form action="GoodsList" method="post">
			<div id="inp">
				<input placeholder="输入商品名称搜索" name="findOfName" value="${findOfName }">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				状态：
				<select name="state">
					<option value ="">全部</option>
					<option value ="已提交" ${state eq "已提交" ?'selected':''}> 已提交</option>
					<option value ="已发货" ${state eq "已发货" ?'selected':''}> 已发货</option>
					<option value ="已完成" ${state eq "已完成" ?'selected':''}> 已完成</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button>查找</button>
			</div>
			
		</form>
		
		<br/>
		<br/>
		<table border="1">
			<tr>
				<th>订单号</th>
				<th style="width:100px;height: 70px;">图片</th>
				<th>商品名称</th>
				<th>数量</th>
				<th>金额</th>
				<th style="width:200px">时间</th>
				<th>状态</th>
			</tr>
			<c:forEach items="${goodslist}" var="goods">
			<tr>
				<td>${goods.id }</td>
				<td><img style="width:60px;height: 60px;" src="${goods.photo}"/></td>
				<td>${goods.bookname }</td>
				<td>${goods.num }</td>
				<td>${goods.sumprice }</td>
				<td>${goods.time }</td>
				<td><div style="color: blue">${goods.state }</div></td>
			</tr>
			</c:forEach>	
		</table>
	</div>
</body>
</html>