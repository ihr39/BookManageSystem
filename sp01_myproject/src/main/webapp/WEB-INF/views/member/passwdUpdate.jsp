<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(function(){
		//비밀번호 변경
		$('#passwdUpdate').click(function(){
			//alert('클릭');
			var passwd=$('#passwd').val();
			var passwdCheck=$('#passwdCheck').val();
			if(passwd==''){
				alert('비밀번호를 입력해주세요');
				$('#passwd').focus();
			}else if(passwdCheck != passwd){
				alert('비밀번호가 틀립니다');
				$('#passwdCheck').focus();
			}else{
				$('#passwdUpdateForm').submit();
			}
		});
	});

</script>
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
			<h1>비밀번호 변경</h1>
		</div>
		
		<form action="${path}/member/passwdUpdate" id="passwdUpdateForm" method="post">
			<fieldset>
				<div class="form-group">
					<label for="exampleInputPassword1">비밀번호 *</label>
					<input type="password" name="passwd" class="form-control" id="passwd">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">비밀번호 확인 *</label>
					<input type="password" name="passwdCheck" id="passwdCheck" class="form-control">
				</div>
				<div class="d-flex justify-content-between align-items-center">
					<div class="form-group d-flex justify-content-start">
						<button id="passwdUpdate" type="button" class="btn btn-primary">비밀번호 변경</button>
					</div>
				</div>
				</fieldset>
			</form>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>