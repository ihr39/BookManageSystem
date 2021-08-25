<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/include.jsp" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> 
<html> <!--<![endif]-->
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="description" content="Bingo One page parallax responsive HTML Template ">
  
  <meta name="author" content="Themefisher.com">

  <title>BOOK</title>
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
	$(function(){
		$('.image').click(function(){
			var isbn = $(this).parent().find('.isbn').val();
			//alert(isbn);
			location.href='${path}/book/detail?isbn='+isbn;
			if('${sessionScope.userid}'==''){
				alert('로그인 먼저 해주세요');
				location.href='${path}/member/login';
			}
			
		});
		$('.title').click(function(){
			var isbn = $(this).parent().find('.isbn').val();
			//alert(isbn);	
			location.href='${path}/book/detail?isbn='+isbn;
			if('${sessionScope.userid}'==''){
				alert('로그인 먼저 해주세요');
				location.href='${path}/member/login';
			}
		});
		
		$('#serchBook').click(function(){
			var curPage=1; //페이지
			var query=$('#query').val();
			var findkey=$('#findkey').val();
			location.href='${path}/book/main?query='+query+'&findkey='+findkey+'&curPage='+curPage;
			$('#serchBookList').show();
		});
		
	});
</script>

<!-- Mobile Specific Meta
  ================================================== -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <!-- Favicon -->
  <link rel="shortcut icon" type="image/x-icon" href="/my/resources/images/favicon.png" />
  
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
<style type="text/css">
	.grade { text-align: center; }
	
	.gradLikeList{
		  display:inline-flex;
		  margin-left: 90px;
	}
	.LikeCntList{
		display:inline-flex;
		margin-left: 90px;
	}
	#input-group col-md-12{
		margin-left: 20cm;
	}
</style>
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


	<div class="hero-slider">
	<div class="slider-item th-fullpage hero-area" style="background-image: url(/my/resources/images/slider/book.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<h1 data-duration-in=".3" data-animation-in="fadeInUp" data-delay-in=".1">Comunication<br>
						</h1>
					<p data-duration-in=".3" data-animation-in="fadeInUp" data-delay-in=".5">책을 읽고난 후 당신의 감상을<br> 다른 사람들과 공유해보세요. <br></p>
					<a data-duration-in=".3" data-animation-in="fadeInUp" data-delay-in=".8" class="btn btn-main" href="${path}/member/join">Join</a>
				</div>
			</div>
		</div>
	</div>
	<div class="slider-item th-fullpage hero-area" style="background-image: url(/my/resources/images/slider/coffee.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<h1 data-duration-in=".3" data-animation-in="fadeInDown" data-delay-in=".1">Take Control of Your Mind<br></h1>
					<p data-duration-in=".3" data-animation-in="fadeInDown" data-delay-in=".5">책을 읽음으로써 생각을 정리하고
						<br> 지식을 쌓으세요</p>
					<a data-duration-in=".3" data-animation-in="fadeInDown" data-delay-in=".8"  class="btn btn-main" href="${path}/member/join">Join</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- =================================================== -->
<section class="about-2 section" id="about">
	<div class="container">
		<div id="searchDiv">
			<div class="row">
				<div id="custom-search-input" align="center">
					<div class="input-group col-md-12" id="input-group col-md-12">
						<select name="findkey" id="findkey">
							<option value="title">제목</option>
							<option value="author">작가</option>
							<option value="isbn">ISBN</option>
							<option value="description">내용</option>
							<option value="tidis">제목+내용</option>
						</select>
						<input type="text" class="search-query form-control" id="query" name="query" placeholder="검색어를 입력해주세요" />
						<button id="serchBook" class="btn btn-danger" type="button">
							<i class="fas fa-search"></i>
							<span class="glyphicon glyphicon-search"></span>
						</button>
	            	</div>
	            </div>
			</div>
		</div>
	</div>
</section> <!-- End section -->

<!--
		=========================================== -->
<section class="service-2 section">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <!-- section title -->
        <div class="title text-center">
         <h2> <span class="color">평점 높은 순</span></h2>
          <div class="border"></div>
        </div>
        <div>
	        <c:forEach var="book" items="${bookGradeList}">
	        	<div class="gradLikeList">
					<img alt="책 이미지" class="image" name="image" src="${book.IMAGE}" width="140">
					<input type="hidden" value="${book.ISBN}" class="isbn" name="isbn">
				</div>
			</c:forEach>
		</div>
        <!-- /section title -->
      </div>
    </div> <!-- End row -->
  </div> <!-- End container -->
</section> <!-- End section -->
<!-- ============================================ -->
<section class="blog" id="blog">
	<div class="container">
		<div class="row">
			<!-- section title -->
			<div class="col-12">
				<div class="title text-center ">
					<h2> <span class="color">사람들이 좋아하는 책</span></h2>
					<div class="border"></div>
				</div>
			</div>
			<div>
				<c:forEach var="book" items="${bookLikeCntList}">
					<div  class="LikeCntList">
						<img alt="책 이미지" class="image" name="image" src="${book.IMAGE}" width="140">
						<input type="hidden" value="${book.ISBN}" class="isbn" name="isbn">
					</div>
				</c:forEach>
			</div>
		</div> <!-- end row -->
	</div> <!-- end container -->
</section> <!-- end section -->

<%@include file="../footer.jsp" %>
  </body>
  </html>
