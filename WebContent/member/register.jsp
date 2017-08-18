<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
history.replaceState(null, " ", "${pageContext.request.contextPath}/account/register");
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register.jsp</title>
<style>
.center {
	margin:auto;
	width: 60%;
	border: 3px solid #BA
	
}
</style>
<script type="text/javascript">
<!--
//TODO 이메일 체크, 비밀번호 체크
-->
</script>
</head>
<body>
<h1>register.jsp</h1>
<h1 id="demo"></h1>
<div class="center">
<form action="./registerAf">
<input type="hidden" name="cmd" value="register">
<table border="1">
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" size="20" /></td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type="password" name="pw" size="20" /></td>
	</tr>
	<tr>
		<td>패스워드 확인</td>
		<td><input type="text" name="pw_check" size="20" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="회원가입" />
		</td>
	</tr>
</table>
</form>
</div>
<a href="./login">로그인</a>
</body>
</html>