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
	if('${msg}'!=''){
		alert('${msg}');
	}
	$(function(){
		$('#loginBtn').click(function(){
			var userid=$('#Loginuserid').val();
			var passwd=$('#Loginpasswd').val();
			if(userid==''){
				alert('아이디를 입력해주세요');
				$('#Loginuserid').focus();
			}else if(passwd==''){
				alert('비밀번호를 입력해주세요');
				$('#Loginpasswd').focus();
			}else{
				$('#loginFrm').submit();
			}
		});
		//회원가입 버튼을 눌렀을 때
		$('#joinBtn').click(function(){
			location.href="${path}/member/join"	
		});

		
	});
</script>
<style type="text/css">
	#userid{
		font-size: 15px;
	}
	#passwd{
		font-size: 15px;
	}
	.input-group-addon{
		align-content: center;
		
	}
</style>
</head>
<body id="loginbody">
<%@include file="../navigation.jsp" %>
	<section class="single-page-header">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h2>Login</h2>
					<ol class="breadcrumb header-bradcrumb">
					</ol>
				</div>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="omb_login">
    	<h3 class="omb_authTitle">Login or <a href="${path}/member/join">Sign up</a></h3>

		<div class="row omb_row-sm-offset-3 omb_socialButtons">
    	    <div class="omb_btn-naver_DIV">
		        <a href="${NaverApiURL}" class="omb_btn-naver">
			        <img alt="네이버 로그인" id="naverLogin" src="${path}/resources/image/naverloginicon.png" width="165" height="51">
		        </a>
	        </div>
        	<div class="omb_btn-kakao_DIV">
		        <a href="${KakaoApiURL}" class="omb_btn-kakao">
					<img alt="카카오 로그인" src="${path}/resources/image/kakaologinicon.png" width="180" height="50">
		        </a>
	        </div>	
		</div>
		<div class="row omb_row-sm-offset-3 omb_loginOr">
			<div class="col-xs-12 col-sm-6">
				<hr class="omb_hrOr">
				<span class="omb_spanOr">or</span>
			</div>
		</div>
		<div class="row omb_row-sm-offset-3">
			<div class="col-xs-12 col-sm-5" id="loginInput">	
			    <form class="omb_loginForm" action="${path}/member/login" id="loginFrm" method="POST" >
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user fa-2x"></i></span>
						<input type="text" class="form-control" name="userid" id="Loginuserid" placeholder="userid">
					</div>
					<span class="help-block"></span>
										
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock fa-2x"></i></span>
						<input  type="password" class="form-control" name="passwd" id="Loginpasswd" placeholder="Password">
					</div>
                    <br>

					<button class="btn btn-lg btn-primary btn-block" id="loginBtn" type="button">Login</button>
				</form>
			</div>
    	</div>
		<div class="row omb_row-sm-offset-3">
			<div class="col-xs-12 col-sm-3">
				<label class="checkbox">
					<input type="checkbox" value="remember-me">Remember Me
				</label>
			</div>
			<div class="col-xs-12 col-sm-3">
				<p class="omb_forgotPwd" >
					<a href="#">Forgot password?</a>
				</p>
			</div>
		</div>	    	
	</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>