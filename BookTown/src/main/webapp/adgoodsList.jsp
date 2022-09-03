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
	#goodsmain{
		position: relative;
		margin: 0 auto;
		width: 1040px;
		height: auto;
		left:70px;
	}
	#goodsinp{
		position: relative;
		left:320px;
		
	}
	tr{
		height: 40px;
		text-align: center;
	}

	th{
		width: 120px;
	}
	h1{
		text-align: center;
	}
</style>


<body>
	<jsp:include page="adloginTag.jsp"/>
	<div id="goodsmain">
		<h1>订 单 管 理</h1>
		<form action="AdGoods" method="post">
			<div id="goodsinp">
				<input placeholder="输入用户名搜索" name="findOfName" value="${findOfName }">
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
				<th>用户</th>
				<th>商品名称</th>
				<th>数量</th>
				<th>单价</th>
				<th>实付款</th>
				<th>状态</th>
				<th style="width:200px">时间</th>
			</tr>
			<c:forEach items="${goodslist}" var="goods">
			<tr>
				<td>${goods.id }</td>
				<td>${goods.username }</td>
				<td>${goods.bookname }</td>
				<td>${goods.num }</td>
				<td>￥${goods.price }</td>
				<td>￥${goods.sumprice }</td>
				<td><div style="color: blue">${goods.state }</div></td>
				<td>${goods.time }</td>
				
			</tr>
			</c:forEach>	
		</table>
	</div>
</body>
</html>