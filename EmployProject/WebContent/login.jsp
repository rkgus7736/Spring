<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
.container{
	width:300px;
	margin:100px auto;
	height: 600px;
	padding-top:200px;
	box-sizing: border-box;
}
label{
	width:100px;
	display:inline-block;
	text-align: right;
	padding-right:10px;
}
p{
	text-align: center;
}
</style>
</head>
<body>
	
	<div class="container">
		<h2>XX회사 인사관리시스템</h2>
		<form method="post" action="login.do">
			<label for="name">이름 : </label><input type="text" name="name" id="name"><br> 
			<label for="sabun">사번 : </label><input type="password" name="sabun" id="sabun"><br>
			<p>
			<button type="submit">로그인</button>
			</p>
		</form>
	</div>
		
</body>
</html>




