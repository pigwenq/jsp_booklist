<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	#addmain{
		position:relative;
		top:70px;
		margin:0 auto;
		width: 350px;
		height: 300px;
		border: 1px solid red;
	}
	#addbutton{
		
		position:relative;
		left:140px;
	}
	#addform{
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
<jsp:include page="adloginTag.jsp"/>
<h1>管 理 员 添 加</h1>
<div id="addmain">
	
	<br/>
		<br/>
	<form id="addform" action="AdRegisterServlet?source=addadmin.jsp" method="post">
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
		
		<div id="addbutton">
			<button>添加</button>
			<p>${msg}</p>
		</div>
		
	</form>
	
	
</div>
	
</body>
</html>