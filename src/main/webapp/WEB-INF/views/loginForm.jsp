<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<style type="text/css">
#container{
	width: 300px;
	margin: auto;
	margin-top: 200px;
/*  	border: 1px solid gray; */
	text-align: center;
}
table{
	margin: auto;
	margin-bottom: 10px;
}

tr>th{
	padding-right: 20px;
	padding-top: 5px;
	padding-bottom: 5px;
}

</style>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<body>
<div id="container">
<h1>&nbsp;&nbsp;&lt; Login &gt;</h1>
<br>
<form action="./login.do" method="post">
<table>
	<tr>
		<th>아이디</th>
		<td>
			<input class="form-control" name="id" type="text">
		</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>
			<input class="form-control" name="pw" type="password">
		</td>
	</tr>
</table>
	
<input class="btn btn-info" style="margin-left: 40px;" type="submit" value="로그인">
<input class="btn btn-primary" type="button" value="회원가입" onclick="location.href='./signUpform.do'">
</form>
</div>


</body>
</html>