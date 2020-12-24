<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 화면</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
#container {
	width: 300px;
	margin: auto;
	margin-top: 130px;
	/* 	border: 1px solid gray; */
 	text-align: center;
}

</style>
</head>
<script type="text/javascript">

function duplicationId(){
// 	alert("작동");
	var title = "아이디 중복 검사";
	var url = "./duplication.do";
	var attr = "width=450px, height=550px";
	window.open(url, title, attr);
}


</script>
<body>
<div id="container">
	<form action="./signUp.do" method="post">
			<h1>&nbsp;&nbsp;&lt; 회원가입 &gt;</h1>
			<h3>필수 정보를 입력해주세요.</h3>
			<div>
				<input class="form-control" type="text" id="name" name="name" placeholder="이름" required><br>
				<input class="form-control" type="text" id="id" name="id" placeholder="아이디 (클릭 시 중복체크 화면으로 이동)" maxlength="20" onclick="return duplicationId()" required><br>
				<span id="result"></span>
				<input class="form-control" type="password" id="pw" name="pw" placeholder="비밀번호" required><br>
				<input class="form-control" type="password" id="passOk" placeholder="비밀번호 확인" required>
			</div>
			<hr>
			<div id="button">
				<input class="btn btn-success" type="submit" value="회원 가입">
				<input class="btn btn-primary" type="button" value="돌아가기" onclick="javascript:history.back()">
			</div>
	</form>
	
</div>
</body>
</html>