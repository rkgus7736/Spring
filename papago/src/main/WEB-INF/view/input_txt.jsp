<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	*{
	margin:0;
	adding:0;
	}
	
	
</style>
</head>
<body>
	<form action="translation.do" method="post">
		
	<div id="total">
		<div id="content_area">
			<table>
				<tr>	
					<td><select>
						<option>한국어</option>
						<option>영어</option>
					</select></td>
				</tr>
				<tr>
					<td><h3>번역할 내용</h3></td>
					<td><textarea></textarea></td>
				</tr>
				<tr>
				 <td><button>번역</button></td>
				</tr>
			</table>
		</div>
		<div id="line">
		<img src="/img/next.png">
		</div>
		<div id="content_area">
			<table>
					<tr>	
						<td><select>
							<option>한국어</option>
							<option>영어</option>
						</select></td>
					</tr>
				<c:forEach var="language" items="${requestScope.list }">
					<tr> 
						<td>${papago.content }
						<input type="hidden" name="content" value="${papago.content }"></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	</form>
</body>
</html>