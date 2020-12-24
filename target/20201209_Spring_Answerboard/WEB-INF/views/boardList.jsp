<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 글 조회</title>
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
<jsp:useBean id="usebean" class="com.min.edu.usebean.usebean" scope="page"/>
<div>
<h1>전체 글 목록</h1>
<div>
<c:if test="${loginDto.id != null}">
${loginDto.id}님 환영합니다.

<input type="button" value="로그아웃" onclick="location.href='./logout.do'">
</c:if>

</div>
<form action="./multiDel.do" method="post" id="frm" name="frm" onsubmit="return chkbox()">
<input type="submit" value="다중삭제">
<input type="button" value="새글입력" onclick="location.href='./insertBoard.do'">
<table>
	<thead>
	<tr>
		<th><input type="checkbox" onclick="chkAll(this.checked)"></th>
		<th>연번</th>
		<th>ID</th>
		<th>제목</th>
		<th>등록일</th>
	</tr>
	</thead>
	<tbody>
		<c:set var="len" value="${fn:length(lists)}"/>
		<c:forEach var="list" items="${lists}" varStatus="vs">
			<c:if test="${list.delflag eq 'N'}">
				<tr>
					<td><input type="checkbox" name="chkVal" value="${list.seq}"></td>
					<td>${len - vs.index}</td>
					<td>${list.id}</td>
					<td>
						<jsp:setProperty property="depth" name="usebean" value="${list.depth}"/>
						<jsp:getProperty property="photo" name="usebean"/>
						<a href="./detailBoard.do?seq=${list.seq}">${list.title}</a>
					</td>
					<td>
						<fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>
</form>
</div>
</body>
<script type="text/javascript">
function chkAll(bool){
// 	alert(bool);
	var chkVal = document.getElementsByName('chkVal');	//nodeList
	
	for (var i = 0; i < chkVal.length; i++) {
		chkVal[i].checked = bool;
	}
}

function chkbox(){
	var chkVal = document.getElementsByName('chkVal');
	var n = 0;
	for (var i = 0; i < chkVal.length; i++) {
		if(chkVal[i].checked){
			n++;
		}
	}
	if(n>0){
		document.getElementById('frm').action = "./multiDel.do"
	}else{
		alert("한 개 이상의 글을 선택하세요.");
		return false;	//propagation, 실행을 막기 위해 반드시 return false를 작성해야함
	}
}
</script>
</html>