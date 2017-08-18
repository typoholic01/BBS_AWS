<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
history.replaceState(null, " ", "${pageContext.request.contextPath}/account/login");
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<style>
.center {
	margin:auto;
	width: 60%;
	border: 3px solid #BA
	
}
</style>
<script type="text/javascript">
//TODO 체크
$(document).ready(function() {
	// id 저장
	var user_email = $.cookie("user_email");
	if (user_email != null) {
		$("#email").val(user_email);
		$("#_chk_save_email").attr("checked", "checked");
	};
	
	$("#_chk_save_email").click(function () {
		/* alert("1/2 체크"); */
		if ($(this).is(':checked')) {
			$.cookie("user_email",$("#email").val(), {path:"/", expires:365});
			/* alert("2/2 체크함"); */
		} else {
			$.cookie("user_email",$("#email").val(), {path:"/", expires:-1});
			/* alert("2/2 체크해제"); */
		}
		
	});
})

</script>
</head>
<body>
<h1>Login</h1>
<p>환영합니다</p>
<div align="center">
<form action="./loginAf" method="post">
<table border="1">
<tr>
	<td>이메일</td>
	<td><input type="text" id="email" name="email" size="20" /></td>
</tr>
<tr>
	<td>패스워드</td>
	<td><input type="password" id="pw" name="pw" size="20" /></td>
</tr>
<tr>
<td colspan="2">
	<input type="submit" value="로그인" /><input type="checkbox" id="_chk_save_email" /> ID 저장
</td>
</tr>
</table>


</form>

<a href="./register">회원가입</a>

</div>
</body>
</html>