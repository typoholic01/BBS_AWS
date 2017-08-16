<%@page import="member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
var pageUrl = "?seq=" + ${ param.seq };
window.history.pushState(null, '', "${pageContext.request.contextPath}/bbs/notice/detail"+pageUrl);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css" charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>bbsdetail.jsp</title>

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

<h1>상세 글 보기</h1>



<div class="center">

<table border="2">
<col width="200"><col width="500">

<tr>
	<td>글번호</td>
	<td>${bbs.seq } </td>
</tr>
<tr>
	<td>아이디</td>
	<td>${bbs.user_id } </td>
</tr>
<tr>
	<td>제목</td>
	<td>${bbs.title } </td>
</tr>
<tr>
	<td>작성일</td>
	<td>${bbs.create_at } </td>
</tr>
<tr>
	<td>조회수</td>
	<td>${bbs.count } </td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea rows="10" cols="50" 
name="content">${bbs.content }
</textarea>
</td>
</tr>
</table>
<c:if test="${login.email == bbs.user_id }">
<button onclick="location.href='./modify?seq=${bbs.seq}'">수정</button>
<button onclick="location.href='./delete?seq=${bbs.seq}'">삭제</button>
</c:if>
</div>

<a href='./list'>글 목록</a>

</body>
</html>







