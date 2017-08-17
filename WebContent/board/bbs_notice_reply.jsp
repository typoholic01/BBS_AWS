<%@page import="member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
var pageUrl = "?seq=" + ${ param.seq };
window.history.replaceState(null, '', "${pageContext.request.contextPath}/bbs/notice/reply"+pageUrl);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>answer.jsp</title>

<style type="text/css">
.center{
	margin: auto;
	width: 60%;
	border: 3px solid #8AC007;
	padding: 10px;
}
input {
	size: 50;
}
</style>

</head>
<body>

<a href="logout.jsp">로그아웃</a>



<div class="center">

<hr>

<h1>답글달기</h1>

<form action="./replyAf" method="post">
<input type="hidden" name="seq" value="${param.seq }">
<input type="hidden" name="ancestor" value="${bbs.ancestor }">
<input type="hidden" name="reply" value="${bbs.reply }">

<table border="1">
<col width="200"><col width="500">

<tr>
	<td>아이디</td>
	<td>
		<input type="text" name="user_id" readonly="readonly" size="50"
		 value="${login.email }">
	</td>
</tr>

<tr>
	<td>제목</td>
	<td>
		<input type="text" name="title" size='50'>
	</td>
</tr>

<tr>
	<td>내용</td>
	<td>
		<textarea rows="10" cols="50" name="content"></textarea>
	</td>
</tr>

<tr>
	<td colspan="2">
		<input type="submit" value="답글">
	</td>	
</tr>

</table>


</form>
</div>

<a href='./list'>글 목록</a>

</body>
</html>