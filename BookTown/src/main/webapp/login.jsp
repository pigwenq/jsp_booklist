<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<style>
	#login{
		position:relative;
		top:100px;
		margin:0 auto;
		width: 250px;
		height: 300px;
		border: 1px solid red;
	}
	#button{
		
		position:relative;
		left:90px;
	}
	
	h1{
		text-align: center;
	}
	input{
		width: 150px;
	}
	#adin{
		position: relative;
		left:160px;
		 
	}
</style>
<body>
<a href="Main">退出</a>
<div id="login">
	<h1>系 统 登 录</h1>
	<form action="LoginServlet" method="post">
	
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		User:<input type="text" name="userName" value="${userName}"/>
		<br/>
		<br/>
		&nbsp;Password:<input type="password" name="password"/>
		<br/>
		<br/>
		<div id="button">
			<button>用户登录</button>
			<a href="register.jsp" >注 册</a>
			<br/>
			<p>${msg}</p>
		</div>
		<br/>
		<div id="adin"><a href="adlogin.jsp">管理员登录</a></div>
	</form>
	
</div>

</body>

</html>



