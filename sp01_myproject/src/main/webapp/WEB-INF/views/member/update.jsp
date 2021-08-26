<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if('${msg}'!=''){
		alert('${msg}');
	}
	$(function(){
		//수정 버튼 클릭시
		$('#btnUpdate').click(function(){
			var passwd=$('#passwd').val();
			var passwdCheck=$('#passwdCheck').val();
			var nicCheck=$('#btnNicnameCheck').val();
			var nicname=$('#nicname').val();
			if(passwd==''){
				alert('비밀번호를 입력해주세요');
				$('#passwd').focus();
			}
			else if(passwd!=passwdCheck){
				alert('비밀번호가 틀립니다');
				$('#passwdCheck').focus();
			}else{
				var result=confirm('정말 수정하시겠습니까?');
				if(result){
					$('#form').submit();
				}
			}
		});
		
		//삭제버튼 클릭시
		$('#btnDelete').click(function(){
			var result=confirm('탈퇴하시겠습니까?');
			if(result){
				location.href='${path}/member/delete';
			}
		});
		

	});
</script>
<style type="text/css">
	#userid{
		font-size:15px; 
	}
	#name{
		font-size:15px; 
	}
	#tel{
		font-size:15px; 
	}
	#nicname{
		font-size:15px; 
	}
	#email{
		font-size:15px; 
	}
</style>
</head>
<body>
<%@include file="../navigation.jsp" %>
	<section class="single-page-header">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h2>Contact Us</h2>
					<ol class="breadcrumb header-bradcrumb">
					</ol>
				</div>
			</div>
		</div>
	</section>

	<div class="container">
	<div class="registration mx-auto d-block w-100">
		<div class="page-header text-center">
			<h1>정보수정</h1>
		</div>
		<label id="passwdChangeLink"><a href="${path}/member/passwdUpdate">비밀번호 변경</a></label>
		
		<form action="${path}/member/update" id="form" name="form" method="post">
			<fieldset>
				<div class="form-group">
					<label for="exampleInputPassword1">아이디 *</label>
					<input type="text" name="userid" class="form-control" id="userid" value="${sessionScope.userid}" readonly>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">이름 *</label>
					<input type="text" class="form-control" id="name">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">닉네임 *</label>
					<input type="text" class="form-control" name="nicaname" id="nicname" value="${sessionScope.nicname }">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">이메일</label>
					<input type="email" name="email" class="form-control" id="email">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">전화번호</label>
					<input type="tel" class="form-control" name="tel" id="tel">
				</div>
				<div class="d-flex justify-content-between align-items-center">
					<div class="form-group d-flex justify-content-start">
						<div id="btnUpdateDiv">
							<button type="button" id="btnUpdate" class="btn btn-primary">수정</button>
						</div>
						<div id="btnDeleteDiv">
							<button type="button" id="btnDelete" class="btn btn-primary">회원탈퇴</button>
						</div>
					</div>
				</div>
				</fieldset>
			</form>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>
