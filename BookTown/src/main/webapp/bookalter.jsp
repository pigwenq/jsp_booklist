<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改图书</title>
</head>
<style>
	#addbook{
		position:relative;
		top:60px;
		margin:0 auto;
		width: 600px;
		height: 600px;
		border: 1px solid red;
	}
	#addbook textarea{
		height: 100px;
		resize: none;
	}
	#form{
		position:relative;
		margin:0 auto;
		width: 600px;
		left:180px;
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
	#submit{
		width: 80px;
		height: 40px;
		background-color: red;
		color: white;
		font-size: 17px;
		position: relative;
		left:100px;
	}
	
</style>
<body>
	<jsp:include page="adloginTag.jsp"/>
	<div id="addbook">
		<h1>书 籍 修 改 </h1>
		<div id="form">
			<form action="BookAlter?bookid=${bookid }" method="post" enctype = "multipart/form-data">
				<br/>
				书 名：<input type="text" name="bookname" value="${book.bookname}"/>
				<br/>
				<br/>			
				价 格：<input type="number" name="price" value="${book.price}" min="0.00" step="0.01">
				<br/>
				<br/>
				类 型：<select name="booktype">
							<c:forEach items="${booktypes}" var="type">
								<option value="${type.name }" ${book.typename eq type.name ?'selected':''}>${type.name }</option>
							</c:forEach>
						</select>
				<br/>
				<br/>
				详 情 ：<textarea name="bookmsg">${book.bookmsg}</textarea>
				<br/>
				<br/>
				<br/>
				图 片：<img src="${book.photo }" style='width:80px;height: 80px'/>
						<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="photo" style="width: 180px" type="file">
				<br/>
				<br/>
				<br/>
				<br/>
				<button id="submit">提交</button>
				
			</form>
		</div>
		
		
	</div>
</body>
</html>