<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	#tag{
		position: fixed;
		right:20px;
		height: 30px;
		z-index: 10;
	}
</style>
<body>
	<div id="tag">
		<a href = "adlogin.jsp">管理员入口</a>
		<br/>
		<br/>
		<c:if test="${loginTag!=null}">
			欢迎你：
			<c:out value="${loginTag}"/>
			<br/>
			<br/>
			<a href="ExitLogin">退出登录</a>
			<br/>
			<br/>
			<a href="GoodsList">订单中心</a>
			<br/>
			<br/>
		</c:if>
		<c:if test="${loginTag==null}">
			<a href="login.jsp">点击登录</a>
			<br/>
			<br/>
		</c:if>
		
		<a href="MyCart">我的购物车</a>
	</div>
</body>
</html>
