<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//문의 창 클릭 시 팝업 뜸
	
	$(function(){
		if('${sessionScope.userid}'==''){
			$('#login').show();
			$('#inquiry').hide();
			$('#logout').hide();
			$('#btnDetail').hide();
		}else{
			$('#login').hide();
			$('#logout').show();
			$('#inquiry').show();
		}
	
	});

</script>
</head>
<body>
<header class="navigation fixed-top">
  <div class="container">
    <!-- main nav -->
    <nav class="navbar navbar-expand-lg navbar-light">
      <!-- logo -->
      <a class="navbar-brand logo" href="${path}/book/index">
        <i  class="fas fa-book-reader fa-3x" ></i>
      </a>
      <!-- /logo -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation"
        aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

       <div class="collapse navbar-collapse" id="navigation">
        <ul class="navbar-nav ml-auto text-center">
          <li class="nav-item ">
            <a class="nav-link" href="${path}/book/index">Home</a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="" id="inquiry" onclick="window.open('${path}/member/inquiry','_blank', 'width=700,height=450')" >Inquire</a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" id="login" href="${path}/member/login">Login</a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" id="logout" href="${path}/member/logout">LogOut</a>
          </li>
          <li class="nav-item dropdown" id="btnDetail" >
            <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown"
              aria-haspopup="true" aria-expanded="false">
              My
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" href="${path}/member/detail">MY</a>
              <a class="dropdown-item" href="${path}/member/update">정보수정</a>
            </div>
          </li>
          
          <li class="nav-item ">
            <a class="nav-link" id="contact" href="${path}/member/contact">Contact</a>
          </li>

        </ul>
      </div>
    </nav>
    <!-- /main nav -->
  </div>
</header>
</body>
</html>