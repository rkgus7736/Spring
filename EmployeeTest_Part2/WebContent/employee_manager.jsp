<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{
		margin:0;
		padding:0;
	}
	nav, section{
		width:1200px;
		margin:0 auto;
	}
	nav *{
		margin:20px 0px;
	}
	table{
		margin:0 auto;
		margin-top:20px;
		
	}
	section p{
		text-align:center;
	}
	td,th{
		padding:10px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function update_employee(obj) {
		var data = "";
		$.each($(obj).parent().parent().find("input"), function(i,o) {
			console.log(i,$(o).val());
			data += $(o).attr("name") + "=" + $(o).val()  + "&"; 
		});
		data += "position="+$(obj).parent().parent().find("select").val();
		console.log(data);
		$.ajax({
			url : "update.do",
			data : data,
			method:"get",
			success:function(d){
				d = Number(d);
				if(d == 1){
					alert("수정 성공");
				}else{
					alert("수정 실패");						
				}
				location.href="refresh.do";
			}
		}); 
	}
	function delete_employee(obj) {
		var data = "";
		$.each($(obj).parent().parent().find("input"), function(i,o) {
			console.log(i,$(o).val());
			data += $(o).attr("name") + "=" + $(o).val()  + "&"; 
		});
		$.ajax({
			url : "delete.do",
			data : data,
			method:"get",
			success:function(d){
				d = Number(d);
				if(d == 1){
					alert("삭제 성공");
				}else{
					alert("삭제 실패");						
				}
				location.href="refresh.do";
			}
		}); 
	}
	$(function() {
		$("#btn_register").click(function(){
			var data = "";
			$.each($(this).parent().parent().find("input"), function(i,o) {
				console.log(i,$(o).val());
				data += $(o).attr("name") + "=" + $(o).val()  + "&"; 
			});
			data += "position="+$(this).parent().parent().find("select").val();
			$.ajax({
				url : "register.do",
				data : data,
				method:"get",
				success:function(d){
					d = Number(d);
					if(d == 1){
						alert("사원정보 등록 성공");
						location.href="refresh.do";
					}else{
						alert("사원정보 등록 실패 입력한 데이터를 확인하세요");
					}
				}
			}); 
		});
		$("#btn_search").click(function(){
			var data = "kind="+$("#kind").val() + "&search="+$("#search").val();
			$.ajax({
				url : "search.do",
				data : data,
				method:"get",
				success:function(result){
					console.log(result);
					var arr = JSON.parse(result);
					var list = arr.list;
					var position = arr.position;
					var str = "<tr>";
					for(i=0;i<list.length;i++){
						str += "<td><input  type='hidden' name='eno' value='"+list[i].sabun+"'>"+list[i].sabun+"</td>";
						str += "<td><input  type='text' name='sabun' value='"+list[i].name+"'></td>";
						str += "<td><input  type='text' name='department' value='"+list[i].department+"'></td>";
						str += "<td><select name='position'>";
						for(j=0;j<position.length;j++){
							if(Number(list[i].position)-1 == j)
							str += "<option value='"+list[i].position+"' selected>"+position[j]+"</option>";
							else
							str += "<option value='"+list[i].position+"'>"+position[j]+"</option>";
								
						}
						str += "<td><input  type='text' name='salary' value='"+list[i].salary+"'></td>";
								
						str +="<td><a href='#' class='update'>수정</a> / <a href='#' class='delete'>삭제</a></td></tr>";
					}
					$("tbody").html(str);
					$(".update").click(function() {
						update_employee($(this));
					});
					$(".delete").click(function() {
						delete_employee($(this));
					});
				}
			});
		});
		$(".update").click(function() {
			update_employee($(this));
		});
		$(".delete").click(function() {
			delete_employee($(this));
		});
	});
</script>
</head>
<body>
	<c:choose>
		<c:when test="${dto.position >= 4 and dto.department == '인사' }">
		<nav>
			<h2>인사관리 시스템에 로그인 하셨습니다.</h2>
			<p>로그인 정보 : ${dto.name }
			<c:choose>
				<c:when test="${dto.position == 4}">과장</c:when>
				<c:when test="${dto.position == 5}">차장</c:when>
				<c:when test="${dto.position == 6}">부장</c:when>
				<c:when test="${dto.position == 7}">사장</c:when>
				<c:when test="${dto.position == 8}">이사</c:when>
				<c:when test="${dto.position == 9}">전무</c:when>
			</c:choose> 
			 <a href="logout.do">로그아웃</a> 
			 </p>
		</nav>
		<hr>
			<section>
			<p>
				<select id="kind">
					<option value="name">이름</option>
					<option value="eno">사번</option>
					<option value="department">부서</option>
				</select><input type="text" id="search"><button id="btn_search">검색</button>
			</p>
			<table>
				<thead>
					<tr>
						<th>사번</th>
						<th>이름</th>
						<th>부서</th>
						<th>직급</th>
						<th>연봉</th>
						<th>비고</th>
					</tr>
					<tr>
						<th><input type="text" name="eno" placeholder="사번을 입력하세요 4자리"></th>
						<th><input type="text" name="name" placeholder="이름을 입력하세요"></th>
						<th><input type="text" name="department" placeholder="부서를 입력하세요"></th>
						<th>
							<select name="position">
								<option value="1">사원</option>
								<option value="2">주임</option>
								<option value="3">대리</option>
								<option value="4">과장</option>
								<option value="5">차장</option>
								<option value="6">부장</option>
								<option value="7">사장</option>
								<option value="8">이사</option>
								<option value="9">전무</option>
							</select>
						</th>
						<th><input type="text" name="salary" placeholder="연봉을 입력하세요"></th>
						<th><button type="button" id="btn_register">사원등록</button></th>
					</tr>
					
				</thead>
				<tbody>
				<c:forEach var="d" items="${sessionScope.list }" >
						<tr> 
							<td>${d.sabun }
							<input type="hidden" name="eno" value="${d.sabun }"></td>
							<td><input type="text" name="name" value="${d.name }"></td>
							<td><input type="text" name="department" value="${d.department }"></td>
							<td>
								<select name="position">
									<c:forEach var="pos" items="${sessionScope.position }" varStatus="j" >
										<c:choose>
											<c:when test="${d.position == j.count }">
												<option value="${j.count}" selected>${pos }</option>
											</c:when>
											<c:otherwise>
												<option value="${j.count}">${pos }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
							<td><input type="text" name="salary" value="${d.salary }"></td>
							<td>
								<a href="#" class="update">수정</a> / <a href="#" class="delete">삭제</a>  
							</td>
						</tr>
					
				</c:forEach>
				</tbody>
			
					
				</table>
			
			</section>
		</c:when>
		<c:otherwise>
			권한이 없습니다. <a href="logout.do">로그아웃</a><br>
			관리자 모드는 인사과 과장 이상들만 접근하실 수 있습니다.			
		</c:otherwise>
	</c:choose>
</body>
</html>