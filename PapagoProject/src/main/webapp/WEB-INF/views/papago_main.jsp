<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>번역 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	$(function(){
		$("#btn_translate").click(function(){
			var param = "";
			param += "target="+$("#target").val();
			param += "&source="+$("#source").val();
			param += "&text="+$("#text").val();
			$.ajax({
				url : "translate.do",
				data : param,
				method : "get",
				success:function(d){
					var responseCode = d.responseCode;
					if(responseCode == 200)
						$("#result").val(d.resultText);
					else
						$("#result").val("번역 오류가 발생했습니다.");
						
		//에러는 클라이언트에서 처리하면 더 복잡해지기 때문에 서버에서 처리하는게 좋다
				} 
			});
		});
	});
	
</script>
<style>
	*{ 
		/*꼭 해줘야 동일한 형태에서 여백을 잡을수 있다.*/
		margin:0;
		padding:0;
		font-size: 18px;
	}
	section{
		width:800px;
		position: relative; /*버튼때문에 잡아주는 것*/
		margin: 0 auto;
		text-align: center;
	}
	article{
		width:400px;
		padding:10px 60px;
		box-sizing: border-box;
		float:left;
	}
	select{
		padding:15px 30px;
	}
	article:first-child{
		border-right: 2px solid #c8c8c8;
	}
	h1{
		font-size: 24px;
		padding: 20px 0px;
		text-align: center;
	}
	textarea{
		width:100%;
		height: 500px;
		padding:10px;
		box-sizing: border-box;
		resize:none;
	}
	#btn_translate{
		width:50px;
		height:50px;
		background-color:black;
		font-weight: bold;
		color:white;
		border-radius: 50px;
		position:absolute; /* 스크롤 올리면 같이 움직여야해서 */
		left:375px;
		top:300px;
		border:none;
		outline:none;
	}
</style>
</head>
<body>
	<h1>파파고 번역 페이지</h1>
	<section>
		<article>
			<!-- form으로 안묶어주면 id도 써줘야 함 -->
			<select name="source" id="source"> 
				<option value="ko">한국어</option>
				<option value="en">영어</option>
				<option value="de">독일어</option>
				<option value="ja">일본어</option>
				<option value="zh-CN">중국어</option>
			</select>
			<h1>번역할 내용</h1>
			<textarea name="text" id="text"></textarea>
		</article>
		<article>
			<select name="target" id="target">
				<option value="ko">한국어</option>
				<option value="en">영어</option>
				<option value="de">독일어</option>
				<option value="ja">일본어</option>
				<option value="zh-CN">중국어</option>
			</select>
			<h1>번역된 내용</h1>
			<textarea name="result" id="result"></textarea>
		</article>
		<button id="btn_translate">>></button>
	</section>
	
</body>
</html>