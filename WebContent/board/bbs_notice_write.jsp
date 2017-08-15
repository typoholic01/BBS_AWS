<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>write bbs notice.jsp</title>
</head>
<body>
<h1>write bbs notice.jsp</h1>
<form action="./writeAf" method="post">
<input type="hidden" name="category" value="notice" />
	<table>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="user_id" value="${login.email }" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" id="" cols="30" rows="10"></textarea></td>
		</tr>
		<tr>
			<td><input type="submit" /></td>
		</tr>
	</table>
</form>
</body>
</html>