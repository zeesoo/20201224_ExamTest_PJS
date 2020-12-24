<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세글 조회</title>
<style type="text/css">
table, th, td{
	border: 1px solid gray;
	border-collapse: collapse;
	padding: 5px;
}

a {
	text-decoration: none;
	font-weight: bold;
	color: black;
}

</style>
</head>
<body>
<div>
<h1>상세글 조회</h1>
	<c:forEach var="list" items="${lists}">
		<table>
			<tr>
				<th>아이디</th><td>${list.id}</td>
			</tr>
			<tr>
				<th>제목</th><td>${list.title}</td>
			</tr>
			<tr>
				<th>내용</th><td>${list.content}</td>
			</tr>
			<tr>
				<th>등록일</th><td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</table>

	<div>
	<input type="button" value="전체글목록" onclick="location.href='boardList.do'">
	<input type="button" value="글수정" onclick="location.href='./modifyBoard.do?seq=${list.seq}'">
	<input type="button" value="글삭제" onclick="location.href='./del.do?seq=${list.seq}'">
	<input type="button" value="답글작성" onclick="location.href='./replypage.do?seq=${list.seq}'">
	</div>		
	</c:forEach>
</div>


</body>
</html>