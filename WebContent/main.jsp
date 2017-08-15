<%@page import="member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% 
	MemberDto login = (MemberDto)request.getAttribute("login"); 

	session.setAttribute("login", login);
	
	if (login == null) {
		out.println("로그인을 실패하였습니다");
	}
%>
</head>
<body>
<h1>여기가 main.jsp입니다</h1>
<h1>이메일: </h1><%=login.getEmail() %>
<h1>이름: </h1><%=login.getName() %> <br />
<a href="./login">로그인</a><br>	
<a href="./register">회원가입</a><br>	
<a href="../bbs/notice/write">공지사항 게시판 글쓰기</a><br>	 
<a href="../bbs/notice/list">공지사항 게시판</a><br>	 
</body>
</html>