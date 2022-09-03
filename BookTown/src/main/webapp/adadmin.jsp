<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
</head>
<style>
	#usermain{
		position: relative;
		margin: 0 auto;
		width: 330px;
		height: auto;
	}
	#inp{
		position: relative;
		left:80px;
		
	}
	#usermain tr{
		height: 40px;
		text-align: center;
	}

	#usermain th{
		width: 150px;
	}
	h1{
		text-align: center;
	}
</style>


<body>
	<jsp:include page="adloginTag.jsp"/>
	<div id="usermain">
		<h1>管 理 员 管 理</h1>
		<form action="AdAdmin" method="post">
			<div id="inp">
				<input placeholder="输入管理员名搜索" name="findOfName" value="${findOfName }">
				<button>查找</button>
				<br/>
				
				<br/>
				<a style="position: relative;left:30px" href="addadmin.jsp">添加管理员</a>
				<br/>
				<br/>
				
			</div>
			
		</form>
		
		<table border="1">
			<tr>
				<th style="width:80px">管理员ID</th>
				<th>管理员名称</th>
			
				<th style="width:100px">操作</th>
			</tr>
			<c:forEach items="${admins}" var="admin">
			<tr>
				<td>${admin.id }</td>
				<td>${admin.name }</td>
				<td ><button style="color: red;" onclick="del(${admin.id })">删除</button></td>
			</tr>
			</c:forEach>	
		</table>
		
	</div>
</body>
<script>
	function del(id) {
		window.location.href="AdDeleteAdmin?id="+id;
	}
</script>
</html>
