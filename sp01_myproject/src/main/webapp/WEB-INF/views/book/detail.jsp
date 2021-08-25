<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/include.jsp" %>
    <%@include file="../include/detailscript.jsp" %>
<!DOCTYPE html>
<html> <!--<![endif]-->
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="description" content="Bingo One page parallax responsive HTML Template ">
  
  <meta name="author" content="Themefisher.com">

  <title>Bingo | Responsive Multipurpose Parallax HTML5 Template</title>

<!-- Mobile Specific Meta
  ================================================== -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <!-- Favicon -->
  <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png" />
  
  <!-- CSS
  ================================================== -->
  <!-- Themefisher Icon font -->
  <link rel="stylesheet" href="/my/resources/plugins/themefisher-font/style.css">
  <!-- bootstrap.min css -->
  <link rel="stylesheet" href="/my/resources/plugins/bootstrap/css/bootstrap.min.css">
  <!-- animation css -->
  <link rel="stylesheet" href="/my/resources/plugins/animate/animate.css">
  <!-- Slick Carousel -->
  <link rel="stylesheet" href="/my/resources/plugins/slick/slick.css">
  <!-- Main Stylesheet -->
  <link rel="stylesheet" href="/my/resources/css/style.css">  

</head>

<body id="body">
 <!--
  Start Preloader
  ==================================== -->
  <div id="preloader">
    <div class='preloader'>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
  </div> 
  <!--
  End Preloader
  ==================================== -->
<!--
Fixed Navigation
==================================== -->
<%@include file="../navigation.jsp" %>
<!--
End Fixed Navigation
==================================== -->

<section class="single-page-header">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h2>Book Detail</h2>
			</div>
		</div>
	</div>
</section>
	<h4 align="center" id="bookTitle">${blist.TITLE}</h4>
		<label id="author">${blist.AUTHOR} <b id="marFont1">저</b></label> <br>
      	<label id="publisher">${blist.PUBLISHER} <b id="marFont2">출판</b></label> 
	<input type="hidden" value="${blist.ISBN}" id="isbn">
	<section class="blog-details section">
  		<div class="container" id="bookDetail">
    		<div class="row">
      			<div class="col-lg-8">
      				<article class="post">
      					<div class="post-image">
      						<img class="img-fluid" id="image" name="image" src="${blist.IMAGE}" alt="book-image" width="160">
      					</div>
      					<div id="bookGrade">
							<h5>평점:${blist.GRADE} /5점</h5>
      						<label id="likeBtn"></label>
      					</div>
      					<div class="post-content">
      						<h5>책 간략 소개</h5>
      						<p>${blist.DESCRIPTION}
      							더 많은 내용은 -><a id="link" href="${blist.LINK}">링크</a>
      						</p>
      					</div>
      					<hr>
      				</article>
      			</div>
     		</div>
    	</div>
   </section>
  		<h2 align="center">Comment</h2>
  		<hr>
	<div id="reviewWindow" align="center">
		<div class="col-lg-6 col-md-6" id="requireNic">
			<input type="text" class="form-control" id="Usernicname" name="nicname" value="${sessionScope.nicname }" readonly>  
			<div class="col-lg-6 col-md-6" id="requireScore">
				<select name="reviewscore" id="reviewscore" class="form-control">
				<option value="5">★★★★★</option>
				<option value="4">★★★★☆</option>
				<option value="3">★★★☆☆</option>
				<option value="2">★★☆☆☆</option>
				<option value="1">★☆☆☆☆</option>
			</select>
			</div>
		</div>
			<div class="col-lg-12 col-md-12">
				<textarea id="shortreview" name="shortreview" class="form-control"></textarea>
			</div>
			<div>
				<button id="reviewSave" class="btn btn-primary">등록</button>
			</div>
		</div>
		<hr>
		<!-- 책의 리뷰 -->
		<div id="reviewList" align="center">
		</div>
		<!-- 리뷰에 대한 댓글 -->
		<div id="reviewReplyList" align="center" >
		</div>
		
		<div id="reviewReply" >
			<input type="hidden" value="0" id="reviewno">
			<input type="hidden" value="0" id="reviewstep">
			<input type="hidden" value="0" id="reviewlevel">
			<input type="text" value="${sessionScope.nicname }" class="form-control" id="Replynicname" name="nicname" readonly>
			<textarea rows="2" cols="50" class="form-control" id="content"></textarea>
			<button id="reReplyBtn" class="btn btn-primary">등록</button>
		</div>
	<%@include file="../footer.jsp" %>
</body>
</html>