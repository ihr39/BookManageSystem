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
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=vsz73eaohj">
</script>
<script type="text/javascript">
	$(function(){
		var mapOptions = {
			    center: new naver.maps.LatLng(37.4847794,126.9301180), //y,x 순
			    zoom: 17
			};
		var map = new naver.maps.Map('map', mapOptions);

		var marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(37.4847794,126.9301180),
		    map: map
		});
	});
</script>
</head>
<body id="body">
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
	
 <!--Start Contact Us
	=========================================== -->		
	<section class="contact-us" id="contact">
		<div class="container">
			<div class="row">
				<!-- section title -->
				<div class="col-12">
				<div class="title text-center" >
					<h2>오시는 길</h2>
					<div class="border"></div>
				</div>
				</div>
				<!-- /section title -->
				
				<!-- Contact Form -->
				<div class="contact-form col-md-6 " >
					<div id="map" style="width:500px;height:500px;"></div>
				</div>
				<!-- ./End Contact Form -->
				
				<!-- Contact Details -->
				<div class="contact-details col-md-6 " >
					<div id="contactList">
						<h3>Contact Details</h3>
						<ul class="contact-short-info" >
							<li>
								<i class="fas fa-home"></i>
								<span>서울특별시 관악구 신림로 340, 르네상스 쇼핑몰 6층</span>
							</li>
							<li>
								<i class="fas fa-mobile-alt"></i>
								<span>Phone: 010-000-000</span>
							</li>
							<li>
								<i class="fas fa-fax"></i>
								<span>Fax: +82-31-000-000</span>
							</li>
							<li>
								<i class="far fa-envelope"></i>
								<span>Email: hello@ezen.com</span>
							</li>
						</ul>
						
						<!-- Footer Social Links -->
						<div class="social-icon">
							<ul>
								<li><a href="https://www.facebook.com/"><i class="fab fa-facebook-f"></i></a></li>
								<li><a href="https://twitter.com/"><i class="fab fa-twitter"></i></a></li>
								<li><a href="https://www.instagram.com"><i class="fab fa-instagram"></i></a></li>
							</ul>
						</div>
					</div>
					<!--/. End Footer Social Links -->
				</div>
				<!-- / End Contact Details -->
					
				
			
			</div> <!-- end row -->
		</div> <!-- end container -->
	</section> <!-- end section -->
	
	<%@include file="../footer.jsp" %>
</body>
</html>