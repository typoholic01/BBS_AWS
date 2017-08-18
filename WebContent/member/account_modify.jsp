<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
history.replaceState(null, " ", "${pageContext.request.contextPath}/account/modify");
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>account_modify.jsp</h1>
<div class="center">
<form action="./modifyAf">
<table border="1">
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" size="20" value="${login.email }" readonly="readonly"/></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" size="20"/></td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type="password" name="pw" size="20" /></td>
	</tr>
	<tr>
		<td>패스워드 확인</td>
		<td><input type="password" name="pw_check" size="20" /></td>
	</tr>
	<tr>
		<td>핸드폰</td>
		<td><input type="text" name="phone" size="20"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="회원정보 수정" />
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>