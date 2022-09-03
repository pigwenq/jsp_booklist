<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员登录</title>
</head>
<style>
	#login{
		position:relative;
		top:100px;
		margin:0 auto;
		width: 280px;
		height: 350px;
		border: 1px solid red;
	}
	#button{
		
		position:relative;
		left:120px;
	}
	
	h1{
		text-align: center;
	}
	input{
		width: 150px;
	}
	#adin{
		position: relative;
		left:210px;
		 
	}
</style>
<body>

	<div id="login">
		<h1>管 理 员 登 录</h1>
		<form action="AdLoginServlet" method="post">
			<br/>
			&nbsp;&nbsp;&nbsp;
			AdminUser:<input type="text" name="userName"/>
			<br/>
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Password:<input type="password" name="password"/>
			<br/>
			<br/>
			<br/>
			<div id="button">
				<button>登录</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="adregister.jsp" >注 册</a>
				<br/>
				<p>${msg}</p>
			</div> 
			<br/>
			<div id="adin"><a href="login.jsp">用户登录</a></div>
		</form>
		
	</div>
</body>
</html>