<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복체크</title>
<style type="text/css">
#container {
	width: 200px;
	margin: auto;
	margin-top: 130px;
	/* 	border: 1px solid gray; */
 	text-align: center;
}
</style>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	document.getElementById("btnuseid").style.display="none";
});

function checkId(){
// 	alert("동작");
	var doc = document.getElementById("id").value;
	if (doc != ''){
		$.ajax({
			url: "./duplicateAjax.do",
			type: "post",
			data: "checkId="+doc,
			async: true,
// 			dataType: "json",
			success: function(msg){
// 	 			alert(msg);
				if(msg == "false"){
					document.getElementById("msgidcondition").innerHTML = "사용 가능한 아이디입니다.";
					document.getElementById("btnuseid").style.display="block";
				}else{
					document.getElementById("msgidcondition").innerHTML = "사용할 수 없는 아이디입니다.";
					document.getElementById("btnuseid").style.display="none";
				}
			},
			error: function(){
				alert("잘못된 요청 처리");
			}
		});
	}else {
		document.getElementById("msgidcondition").innerHTML = "사용할 수 없는 아이디입니다.";
		document.getElementById("btnuseid").style.display="none";
		alert("아이디를 입력해주세요.");
	}
}

function useid(){
// 	console.log(window);
	var doc = document.getElementById("id").value;
	window.opener.document.getElementById("id").value = doc; 	//window.은 안 적어도 됨
	window.opener.document.getElementById("name").focus();
	window.close();
}

</script>

<body>
<div id="container">
	<div>
		<h3>&lt; ID 중복확인 &gt;</h3><br>
		<h4>ID를 입력하세요.</h4>
		<input type="text" id="id" class="form-control"><br>
		<input type="button" value="확인" class="btn btn-success" onclick="checkId()">
		<input type="button" value="사용하기" class="btn btn-info" id="btnuseid" onclick="useid()" style="margin: 5px 0px 5px 59px;">
	</div>
	<div id=msgidcondition>
	
	</div>
</div>
</body>
</html>