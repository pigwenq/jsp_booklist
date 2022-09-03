<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	#main{
		position: fixed;
		left:30px;
		border: 1px solid red;
		width: 160px;
		height:100%;
		background: blue;
	}
	#tagul{
		list-style: none;
		width: 160px;
	}
	#tagul li{
		position:relative;
		left:-40px;
		border-bottom:1px solid white;
		width: 160px;
		color: white;
		text-align: center;
		height: 70px;
		font-size: 20px;
		
	}
	#tagul a{
		color: white;
	}
</style>
<body>
	
	<div id="main">
		<ul id="tagul">
			<li style="height: 100px;">
				欢迎你:<br/>
				<span style="color: black;">${adlogintag}</span><br/>
				<a href="AdExit">退出登录</a>	
			</li>
			<li style="line-height: 70px;"><a href="AdUser">用户管理</a></li>
			<li style="line-height: 70px;"><a href="AdAdmin">管理员管理</a></li>
			<li style="line-height: 70px;"><a href="AdGoods">订单管理</a></li>
			<li style="line-height: 70px;"><a href="AdBook">书籍管理</a></li>
		</ul>
	</div>
</body>
</html>