<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$("button").click(function() {
		var param = $("#frm").serialize();
		$.ajax({
			url : "register.do",
			data : param,
			method : "get",
			dataType:"json",
			success:function(d){
				console.log(d);
				var arr = d.result;
				var text = "";
				for(i=0;i<arr.length;i++){
					text += arr[i].id + " " + arr[i].name + " " + arr[i].age + " " + arr[i].grade + "<br>"; 
				}
			},
	function delete_member(obj) {
		var data = "";
		data = "id=" + $(obj).parent().parent().find("input").first().val();
		console.log(data);
		$.ajax({
			url : "delete.do",
			data : data,
			method:"get",
			dataType:"json",
			success:function(d){
				d = Number(d);
				if(d==1){
					alert("삭제 성공");
				}else{
					alert("삭제 실패");						
				}
				location.href = "member_manager.do";
			}
		});
				$("div").html(text)
	}
$(function() {
</script>
</head>
<body>
	<form id="frm">
		<input type="text" name="id" placeholder="아이디"> 
		<input type="password" name="pass" placeholder="암호"> 
		<input type="text" name="name" placeholder="이름"> 
		<input type="text" name="age" placeholder="나이"> 
		<select>
			<option value="bronze">bronze</option>
			<option value="silver">silver</option>
			<option value="gold">gold</option>
		</select>
		<button type="button" id="btn_register">회원 등록</button>
	</form>
	<hr>
	<div>
		<table>
			<c:forEach var="member" items="${requestScope.list }">
				<tr> 
					<td>${member.id }</td>
					<td>${member.name }</td>
					<td>${member.age }</td>
					<td>${member.grade }</td>
					<td><a href="#" class="delete">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>