<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的购物车</title>
</head>
<style>
	#main{
		position: relative;
		margin: 0 auto;
		width: 500px;
		height: auto;
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
	#cart_img{
		width: 60px;
		height: 60px;
	}
</style>
<body>
	<jsp:include page="loginTag.jsp" />
	<a href="Main">返回主页</a>
	
	<div id="main">
		<h1>购物车</h1>
		<table border="1">
			<tr>
				<th style="width: 50px">选择</th>
				<th>图片</th>
				<th>名称</th>
				<th>价格</th>
				<th>数量</th>
			</tr>
			<c:forEach items="${carts}" var="cart">
			<tr>
				<!-- 复选框 -->
				<td><input type="checkbox" name="select" value="${cart.book.bookId}"></td>
				<td><img id="cart_img" src="${cart.book.photo}"/></td>
				<td>${cart.book.bookname}</td>
				<td>${cart.book.price}</td>
				<td>
				<button onclick="window.location.
				href='CartDown?bookId=${cart.book.bookId}'">-</button>	
				${cart.num}
				<button onclick="window.location.
				href='CartUp?bookId=${cart.book.bookId}&source=MyCart'">+</button>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td><button onclick="selectAll()">全选</button></td>
				<td colspan="4"><button onclick="submit()">提交订单</button></td>
			</tr>
			
			
		</table>
	</div>
	
	
</body>
<script>
	function selectAll() {
		var select = document.getElementsByName("select");
		var tag = true;
		for(var i=0;i<select.length;i++){
			if(select[i].checked==false){
				tag=false;
			}
		}
		
		for(var i=0;i<select.length;i++){
			if(tag==false)
				select[i].checked = true;
			else
				select[i].checked = !select[i].checked;
		}
	}
	
	//提交
	function submit() {
		var s = document.getElementsByName("select");
		var select='';
		for(var i=0;i<s.length;i++){
			if(s[i].checked==true){
				select += s[i].value+"*";
			}
		}
		window.location.href='CartSubmit?select='+select;
	}
</script>
</html>
