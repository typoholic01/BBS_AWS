<%@page import="bbs.BBSDto"%>
<%@page import="java.util.List"%>
<%@page import="member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css" charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>bbslist.jsp</title>


<style type="text/css">
.write{
  text-align:left;  
  background-color: #666666;  
  border-left-color:#666666;
  border-right-color:#666666;
  border-bottom-color: #666666;
}
.search{
  text-align:center;  
  background-color: #666666;  
  border-left-color:#666666;
  border-right-color:#666666;
  border-bottom-color: #666666;
}
.center{
  text-align:center; 

}
.right{
  text-align:right;
}
#wrapper {
  display: inline-table;
  float:right;
}
#cell {
  display: table-cell; 
  vertical-align: middle;
}
</style>
</head>
<body>
<h1>bbs_notice_list.jsp</h1>
<%
List<BBSDto> bbsList = (List<BBSDto>)request.getAttribute("bbsList");
%>

<%-- <h3>환영합니다 <%=mem.getName() %>님 반갑습니다</h3> --%>
<h3>p: ${ paging.cur_page }</h3>
<h3>total_article: ${ paging.total_article }</h3>
<h3>환영합니다 ${login.name }님 반갑습니다</h3>
<a href="./calendar.jsp">달력 보기</a>
<a href="logout.jsp">로그아웃</a>
<hr>

<div align="center">
<h1>게시판</h1>

<table border="1">
<col width="70"><col width="500"><col width="100"><col width="150">

<tr>
<th>순서</th><th>제목</th><th>글쓴이</th><th>글쓴 날짜</th>	
</tr>
	<c:forEach items="${bbsList}" var="bbs">
		<tr>
			<td><c:out value="${bbs.seq}" escapeXml="false"/></td>
			<td>
				<c:if test="${bbs.reply != null }">
					<c:forEach var="i" begin="0" end="${ fn:length(bbs.reply)}" step="1" >&nbsp;&nbsp;</c:forEach><i class="fa fa fa-level-up fa-rotate-90"></i>
				</c:if>
				<a href="./detail?seq=${bbs.seq}"><c:out value="${bbs.title}" escapeXml="false"/></a>
			</td>
			<td><c:out value="${bbs.user_id }"/></td>
			<td><c:out value="${bbs.create_at}" escapeXml="false"/></td>
		</tr>
	</c:forEach>
<tr>
	<td colspan="4" class="center">
		<ul class="pagination">
			<c:if test="${paging.cur_page != 1 }">
				<li><a href="?&p=${paging.cur_page-1 }${queryURL}">❮</a></li>
			</c:if>
			<c:forEach var="i" begin="${paging.start_page }" end="${paging.end_page }" step="1">				
				<c:choose>
					<c:when test="${i == paging.cur_page }">
						<li class="active">
					</c:when>
					<c:when test="${i != paging.cur_page }">
						<li>
					</c:when>
				</c:choose>
				<a href="?p=${i }${queryURL}">${i }</a></li>
			</c:forEach>
			<c:if test="${paging.cur_page != paging.end_page }">
				<li><a href="?p=${paging.cur_page+1 }${queryURL}">❯</a></li>
			</c:if>
		</ul>
		<div id="wrapper">
			<div id="cell"><a href="./write">글쓰기</a></div>
		</div>
	</td>
</tr>
<form action="./list" method="get">
<tr>
	<td colspan="3" class="search">
	<select name="s_type">
		<optgroup label="">
			<option value="email">이메일</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
		</optgroup>
	</select>
	<input type="text" id="search" name="q">
	<input type="submit" value="검색" />
	</td>
</tr>	
</form>
</table>
</div>
</body>
</html>