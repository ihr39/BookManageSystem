<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	//버튼 활성화
	//alert('${sessionScope.usrid}');
	if('${sessionScope.userid}'==''){
		$('#login').show();
		$('#logout').hide();
		$('#nicname').hide();
		$('#btnDetail').hide();
	}else{
		$('#login').hide();
		$('#logout').show();
	}
	//로그아웃 버튼을 클릭했을 때
	$('#logout').click(function(){
		var result=confirm('로그아웃 하시겠습니까?');
		if(result){
			location.href='${path}/member/logout';
		}
	});
	$('#login').click(function(){
		location.href='${path}/member/login';
	});
	//useid를 클릭했을 때
	$('#btnDetail').click(function(e){
		location.href='${path}/member/detail';
	});
	
	//문의 접수를 눌렀을 때 팝업창이 뜨게
	function Openpopup(){
		var popup = window.open('${path}/member/inquiry','문의','width=700px,height=800px,scrollbars=yes');
	}
	
});
</script>
</head>
<body>
		<header>
		<div><h2>메인 화면</h2></div>
		<div>
			<p id="nicname">${sessionScope.nicname}님 반갑습니다</p>
			<button id="login">로그인</button>
			<button id="btnDetail">MY</button>
			<button id="logout">로그아웃</button>			
		</div>
		
	</header>
		<nav>
		<a href="${path}/board/">게시물 조회</a>
		<a href="${path}/board/add">게시물 작성</a>
		<a href="" onclick="Openpopup()" id="inquiry">문의 접수</a>
	</nav>
</body>
</html>