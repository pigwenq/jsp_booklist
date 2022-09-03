<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员注册</title>
</head>
<style>
	#main{
		position:relative;
		top:100px;
		margin:0 auto;
		width: 350px;
		height: 370px;
		border: 1px solid red;
	}
	#button{
		
		position:relative;
		left:140px;
	}
	form{
		position: relative;
		left: 30px;
	}
	h1{
		text-align: center;
	}
	input{
		width: 150px;
	}
</style>
<body>
<a href="adlogin.jsp">返回登陆</a>
<div id="main">
	<h1>管 理 员 注 册</h1>
	<br/>
		<br/>
	<form action="AdRegisterServlet?source=adregister.jsp" method="post">
		&nbsp;&nbsp;&nbsp;&nbsp;
		AdminName:<input type="text" name="userName" value="${username}" />
		<br/>
		<br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Password:<input type="password" name="password1" />
		<br/>
		<br/>
		Again Password:<input type="password" name="password2"/>
		<br/>
		<br/>
		<div id="button">
			<button>注 册</button>
			<p>${msg}</p>
		</div>
		
	</form>
	
	
</div>
	
</body>
</html>