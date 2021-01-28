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
	$("#sno").onkeyup(function(e){
		if ($(this).val().length > $(this).attr('maxlength')) { 
			alert('제한길이 초과'); 
			$(this).val($(this).val().substr(0,
			$(this).attr('maxlength')); 
					} 
				});
		);

	$("button").click(function() {
		var param = $("#frm").serialize();
		$.ajax({
			url : "register.do",
			data : param,
			method : "get",
			dataType:"json",
			//정상처리부분
			success:function(d){
				console.log(d);
				var arr = d.result;
				var text = "";
				for(i=0;i<arr.length;i++){
					text += arr[i].eno + " " + arr[i].name + " " + arr[i].department + " " + arr[i].position + "<br>"; 
				}
				$("div").html(text)
			},
			//exception 처리
			error:function(xhr,text,error){
				//alert(xhr.status); //에러코드 확인
				switch(xhr.status){
				case 1001:
				case 1002:
					alert(xhr.responseText);//에러 메세지 수신
					break;
				}
			}
			
		});
	});
});

</script>
</head>
<body>
	<form id="frm">
		<input type="text" name="sno" id="sno" maxlength="8">
		<input type="text" name="name">
		<input type="text" name="">
		<select name="major">
			<option value="1">사원</option>
			<option value="2">주임</option>
			<option value="3">대리</option>
			<option value="4">과장</option>
			<option value="5">차장</option>
		</select>
		<button type="button">전송</button>
	</form>
	<hr>
<div>

</div>

</body>
</html>