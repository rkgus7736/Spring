<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScpope.login == null }">
		<form action="login.do" method="">
			<input type="text" name="id"><br>
			<input type="password" name="passwd"><button>로그인</button>
		</form>
	</c:if>
	<c:if test="${sessionScope.login == null }">
		${sessionScope.id } 님이 로그인 하셨습니다.
	</c:if>
</body>
</html>