<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("th").click(function() {
			var data = $("#search").serialize();
			$.ajax({
				url : "bookSearch.do",
				data : data,
				method:"get",
				dataType:'json',
				success:function(d){
					console.log(d);
					var arr = d.result;
					console.log(arr);
					var result = "<table>";
					for(i=0;i<arr.length;i++){
						//한건당 한줄씩 표현
						result += 
							"<tr><th>"+arr[i].title+"{requestScope.book.title }</th>"
						+"<td><input type='text' name='name' value='"+arr[i].name+"'></td>"
						+"<td><input type='text' name='age' value='"+arr[i].age+"'></td>"
						+"<td><input type='text' name='grade' value='"+arr[i].grade+"'></td>"
						+"<td><a href='#' class='update'>수정</a> / <a href='#' class='delete'>삭제</a></td></tr>";
					}
					arr += "</table>";
					$("#content_area").html(result);
				}
				});
			});
		});
</script>
<style>
	*{
		margin:0;
		padding:0;
	}
	div{
		width:1200px;
		margin: 0 auto;
		padding: 20px 0px;
		text-align: center;
		border: 1px solid black;
	}
	form{
		display:inline-block;
	}
</style>

</head>
<body>
	<div>
		<form id="frm">
			<input type="text" name="title" placeholder="도서명을 입력해주세요"><button type="button">검색</button>
		</form>
	</div>
	<hr>
	<div id="result">
		<table>
			<tr>
				<th>${requestScope.book.title }</th>
				<td>
					<!-- 조회한 내용 -->
				 저자 : ${requestScope.book.writer }
				</td>
				<td>출판사 :${requestScope.book.publisher } </td>
				<td>${requestScope.book.content }</td>
			</tr>
			<tr>
				<th>${requestScope.book.title }</th>
				<td>
					<!-- 조회한 내용 -->
				 저자 : ${requestScope.book.writer }
				</td>
				<td>출판사 :${requestScope.book.publisher } </td>
				<td>${requestScope.book.content }</td>
			</tr>
			<tr>
				<th>${requestScope.book.title }</th>
				<td>
					<!-- 조회한 내용 -->
				 저자 : ${requestScope.book.writer }
				</td>
				<td>출판사 :${requestScope.book.publisher } </td>
				<td>${requestScope.book.content }</td>
			</tr>
		</table>
	</div>
</body>
</html>