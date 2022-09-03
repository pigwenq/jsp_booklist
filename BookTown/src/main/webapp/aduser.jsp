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
		width: 750px;
		height: auto;
	}
	#inp{
		position: relative;
		left:280px;
		
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
		<h1>用 户 管 理</h1>
		<form action="AdUser" method="post">
			<div id="inp">
				<input placeholder="输入用户名搜索" name="findOfName" value="${findOfName }">
				<button>查找</button>
			</div>
			
		</form>
		
		<br/>
		<br/>
		<table border="1">
			<tr">
				<th>用户ID</th>
				<th>用户名</th>
				<th>订单数量</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.username }</td>
				<td>${user.sum}</td>
				<td>
					
						<c:if test="${user.power eq '启用'}">
							<div style="color: blue">${user.power }</div>
						</c:if>
						
						<c:if test="${user.power eq '禁用'}">
							<div style="color: red">${user.power }</div>
						</c:if>
					
				</td>
				<td>
					<c:if test="${user.power eq '启用'}">
						<button style="color: red" onclick="change(${user.id },1)">禁用</button>
					</c:if>
					
					<c:if test="${user.power eq '禁用'}">
						<button style="color: blue" onclick="change(${user.id },0)">启用</button>
					</c:if>
				</td>
			</tr>
			</c:forEach>	
		</table>
	</div>
</body>
<script>
	function change(id,state) {
		window.location.href="UserStateChange?userid="+id+"&state="+state; //跳转到页面
	}
</script>

</html>