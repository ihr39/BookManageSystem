<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기</title>
<script type="text/javascript">
	$(function(){
		$('#inq_Btn').click(function(){
			if($('#inq_subject').val() == ''){
				alert('제목을 입력해주세요');
				$('#inq_subject').focus();
			}else if($('#inq_content').val() == ''){
				alert('내용을 입력해주세요');
				$('#inq_content').focus();
			}else{
				$('#inq_Form').submit();
				
				window.close();
			}
		});


	});
</script>
</head>
<body>
	<h2 align="center">문의하기</h2>
	<!-- Contact Form -->
		<div class="contact-form col-md-6 "  align="center">
			<form action="${path}/member/inquiry" method="post" enctype="multipart/form-data" id="inq_Form">
				<div class="form-group">
					<input type="text" value="${sessionScope.userid}"class="form-control" name="userid" id="userid" readonly>
				</div>
					
				<div class="form-group">
					<input type="text" name="inq_subject" id="inq_subject" class="form-control" placeholder="제목을 입력해주세요">
				</div>
				<div class="form-group">
					<textarea rows="6" cols="54" placeholder="문의사항을 입력해주세요" name="inq_content" id="inq_content"></textarea> <br>
				</div>
					
				<div>
					<input type="file" name="file" id="inq_file" class="form-control"> 
				</div>
					
				<div id="success" class="success">
					Thank you. The Mailman is on His Way :)
				</div>
					
				<div id="error" class="error">
					Sorry, don't know what happened. Try later :(
				</div>
					
				<div id="cf-submit">
					<button type="button" id="inq_Btn" class="btn btn-transparent" style="background-color: #28ABE3">문의하기</button>
				</div>						
					
			</form>
		</div>
			<!-- ./End Contact Form -->
</body>
</html>