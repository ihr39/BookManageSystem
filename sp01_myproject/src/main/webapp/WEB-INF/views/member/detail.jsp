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
<script type="text/javascript">
	$(function(){
		$('.image').click(function(){
			var isbn = $(this).parent().find('.isbn').val();
			//alert(isbn);
			location.href='${path}/book/detail?isbn='+isbn;			
		});
	});
</script>
<style type="text/css">
	.MyLikeBook{
		text-align: center;
		float: left;
		width: 28%;
	}
	.MyReadBook{
		text-align: center;
		float: left;
		width: 28%;
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
					  <li><a href="${path}/book/index">Home</a></li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	<section class="service-2 section">
	  <div class="container">
	  		<div class="title text-center">
	         	<h2> <span class="color">좋아요한 책</span></h2>
	          	<div class="border"></div>
	        </div>
	    <div class="row">
	      <div class="col-12">
	        <!-- section title -->
	        <div>
		        <c:forEach var="book" items="${booklikelist}">
					<div class="MyLikeBook">
						<div class="col-9">
							<input type="hidden" value="${book.ISBN}" class="isbn">
							<img alt="책 이미지"class="image" name="image" src="${book. IMAGE}" width="140"> <br>
							<label>${book. TITLE}</label> <br>
							<label>(${book.AUTHOR})</label>
						</div>
					</div>
				</c:forEach>
			</div>
	        <!-- /section title -->
	      </div>
	    </div> <!-- End row -->
	  </div> <!-- End container -->
	</section> <!-- End section -->
	
	<section class="blog" id="blog">
		<div class="container">
			<div class="title text-center ">
				<h2> <span class="color">읽은 책</span></h2>
				<div class="border"></div>
			</div>
			<div class="row">
				<div class="col-12">
					<div>
					<c:forEach var="book" items="${bookreviewlist}">
						<div class="MyReadBook">
							<div class="col-9">
								<input type="hidden" value="${book.ISBN}" class="isbn">
								<img alt="책 이미지" class="image" name="image" src="${book. IMAGE}" width="140"> <br>
								<label>${book. TITLE}</label> <br>
								<label>(${book.AUTHOR})</label>
							</div>
						</div>
					</c:forEach>
				</div>
				</div>
			</div> <!-- end row -->
		</div> <!-- end container -->
	</section> <!-- end section -->
	
	<%@include file="../footer.jsp" %>
</body>
</html>