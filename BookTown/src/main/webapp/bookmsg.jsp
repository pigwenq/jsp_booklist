<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书籍信息</title>
</head>
<style>
	#main{
		position:relative;
		top:0px;
		margin:0 auto;
		width: 1200px;
		border: 1px solid red;
	}
	#img{
		width: 200px;
		height: 200px;
		border: 1px solid yellow;
		margin: 40px;
	}
	#img img{
		width: 100%;
		position: relative; 
	}
	#bookmsg1{
		position:absolute;
		top:40px;
		left:300px;
		width: 700px;
		height: 200px;
	}
	#bookmsg2{
		position:relative;
		width: 100%;
		height: auto;
		background-color: rgb(223, 230, 233);
		line-height: 30px;
	}
	h1{
		margin:50px;
		margin-bottom:30px;
		text-align:center;
	}
	#bt:hover {
	    cursor: pointer;
	    font-size: 15px;
	}
	#bt {
	    position: absolute;
	    bottom: 0px;
	    height: 30px;
	    background-color: red;
	    border-radius: 7px;
	    color: white;
	}
</style>
<body>
<a href="Main">返回主页</a>
	<jsp:include page="loginTag.jsp"/>
	<h1>图 书 详 情</h1>
	<div id="main">
		<div id="img"><img src="${book.photo}"></div>
		<div id="bookmsg1">
			<p>书名: ${book.bookname}</p>
			
			<p>价格: ￥${book.price}元</p>
			
			<p>类型: ${book.typename}</p>
			<br/>
			<button id='bt' onclick="window.location.href='CartUp?bookId=${book.bookId}&source=BookMsg'">加入购物车</button>
			
		</div>
		<h2>详情</h2>
		<div id="bookmsg2">
			<p style="margin:30px">${book.bookmsg}</p>
		</div>
		
	</div>


</body>
</html>