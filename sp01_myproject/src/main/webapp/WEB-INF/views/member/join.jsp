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
<script>
	if('${resultmap.msg}'!=''){
		alert('${resultmap.msg}');
	}
	$(function(){
		$('#btnJoin').click(function(){
			var userid=$('#userid').val();
			var passwd=$('#passwd').val();
			var IdCheck=$('#IdCheck').val();
			var nicname=$('#nicname').val();
			var name=$('#name').val();
			var passwdCheck=$('#passwdCheck').val();
			
			if(userid==''){
				alert('아이디를 입력하세요');
				$('#userid').focus();
			}else if(passwd==''){
				alert('비밀번호를 입력하세요');
				$('#passwd').focus();
			}else if(name==''){
				alert('이름을 입력해주세요');
				$('#name').focus();
			}
			else if(nicname==''){
				alert('닉네임을 입력해주세요');
				$('#name').focus();
			}else{
				if(IdCheck != 'Y'){
					alert('아이디 체크를 해주세요');
				}else if(passwdCheck == ''){
					alert('비밀번호 확인을 해주세요');
				}else if(passwdCheck!=passwd){
					alert('비밀번호가 다릅니다');
				}
				else{
					$('#form').submit();
				}
			}
			
		});
		
		$('#btnIdCheck').click(function(){
			var userid=$('#userid').val();
			//alert(userid);
			if(userid==''){
				alert('아이디를 입력하세요');
				$('#userid').focus();
			}else{
				$.ajax({
					url:'${path}/member/idcheck/'+userid,
					type:'get',
					dataType:'json',
					success: function(data){
						//console.log(data);
						//recode값이 0일때
						alert(data.msg);
						if(data.recode==0){
							$('#IdCheck').val('Y');
						}
						else{
							$('#userid').val('');
						}
					},
					error: function(){
						alert('실패');
					}
				});
			}
 			
		});
		
  		//닉네임 중복 확인
		$('#btnNicnameCheck').click(function(){
			var nicname=$('#nicname').val();
			if(nicname==''){
				alert('닉네임을 입력해주세요');
				$('#nicname').focus();
			}else{
				$.ajax({
					url:'${path}/member/nicCheck/'+nicname,
					type:'get',
					dataType:'json',
					success: function(data){
						alert(data.msg);
						if(data.recode==0){
							$('#btnNicnameCheck').val('Y');
						}else{
							$('#nicname').val('');
							$('#nicname').focus();
						}
					},
					error: function(){
						alert('실패');
					}
				});	
			}
			
		});
		
	});

</script>
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
			<h1>Sign up</h1>
		</div>
		
		<form action="${path}/member/join" id="form" name="form" method="post">
			<fieldset>
				<div class="form-group">
					<label for="exampleInputPassword1">아이디 *</label>
					<input type="hidden" name="IdCheck" id="IdCheck" value="N">
					<input type="text" name="userid" class="form-control" id="userid">
					<button type="button" id="btnIdCheck">아이디 중복 체크</button>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">비밀번호 *</label>
					<input type="password" name="passwd" class="form-control" id="passwd">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">비밀번호 확인 *</label>
					<input type="password" name="passwdCheck" class="form-control" id="passwdCheck">
				</div>
				<div class="form-group">
					<label >이름 *</label>
					<input type="text" class="form-control" id="name" name="name">
				</div>
				<div class="form-group">
					<label >닉네임 *</label> 
					<input type="text" class="form-control" name="nicaname" id="nicname">
					<button type="button" id="btnNicnameCheck" value="N">닉네임 중복 체크</button>
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
						<button type="button" id="btnJoin" class="btn btn-primary">회원가입</button>
					</div>
					<div class="form-check form-group d-flex justify-content-end">
						<a href="${path}/member/login">Sign in instead</a>
					</div>
				</div>
				</fieldset>
			</form>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>