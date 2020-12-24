<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
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
<h1>글 수정</h1>
<form action="./modify.do" method="post">
	<c:forEach var="list" items="${lists}">
		<input type="hidden" name="seq" value="${list.seq}">
		<table>
			<tr>
				<th>아이디</th><td>${list.id}</td>
			</tr>
			<tr>
				<th>제목</th><td>${list.title}</td>
			</tr>
			<tr>
				<th>내용</th><td><textarea rows="10" name="content">${list.content}</textarea></td>
			</tr>
			<tr>
				<th>등록일</th><td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</table>
	</c:forEach>
<input type="button" value="돌아가기" onclick="history.back(-1)">
<input type="submit" value="수정완료">
</form>
</div>


</body>
</html>