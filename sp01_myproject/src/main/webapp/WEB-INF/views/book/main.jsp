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
			//페이징 처리
			var curPage=$('.curPage').val();
			$('.curPage').click(function(){
				curPage=$(this).val();
			});//페이지
			var query=$('#query').val();
			var findkey=$('#findkey').val();
			location.href='${path}/book/main?query='+query+'&findkey='+findkey+'&curPage='+curPage;
			$('#serchBookList').show();
		});
		
		$('.curPage').click(function(){
			var curPage=$(this).val();
			var query=$('#query').val();
			var findkey=$('#findkey').val();
			location.href='${path}/book/main?query='+query+'&findkey='+findkey+'&curPage='+curPage;
			$('#serchBookList').show();
		});
	
	});
</script>
<style type="text/css">
	#searchDiv{
		margin-left: 700px;
	}
</style>
</head>
<body>
<%@include file="../navigation.jsp" %>
	<section class="single-page-header">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h2>Search</h2>
					<ol class="breadcrumb header-bradcrumb">
					</ol>
				</div>
			</div>
		</div>
	</section>
	<!-- isbn, 책 제목으로 검색가능 -->
	<section class="about-2 section" id="about">
		<div class="container" id="searchDivDetail">
			<div class="row">
				<div id="custom-search-input" align="center">
					<div class="input-group col-md-12" id="input-group col-md-12">
						<select name="findkey" id="findkey">
							<option value="title" ${param.findkey == 'title'? 'selected':''}>제목</option>
							<option value="author" ${param.findkey == 'author'? 'selected':''}>작가</option>
							<option value="isbn" ${param.findkey == 'isbn'? 'selected':''}>ISBN</option>
							<option value="description" ${param.findkey == 'description'? 'selected':''}>내용</option>
							<option value="tidis" ${param.findkey == 'tidis'? 'selected':''}>제목+내용</option>
						</select>
						<input type="text" class="search-query form-control" id="query" name="query" placeholder="검색어를 입력해주세요" value="${param.query}" />
						<button id="serchBook" class="btn btn-danger" type="button">
							<i class="fas fa-search"></i>
							<span class="glyphicon glyphicon-search"></span>
						</button>
            		</div>
	            </div>
			</div>
		</div>
	</section> <!-- End section -->
	<br>
<%-- 	${blist } --%>
	<div class="divBookList">
		<div >
			<c:if test="${blist eq '[]'}">
				<h2 align="center">결과 값이 없습니다</h2>
			</c:if>
		</div>
		<c:forEach var="book" items="${blist}">
			<ul>
				<li>
					<div  class="bookList col-md-6">
						<div class="SearchBookImg">
							<img alt="책 이미지" class="image" name="image" src="${book.IMAGE}" width="120">
							<label class="title">${book.TITLE}</label>
						</div>
						<input type="hidden" value="${book.ISBN}" class="isbn" name="isbn">
						<hr>
					</div>
				</li>
			</ul>
		</c:forEach>
	</div>
	<c:if test="${blist ne '[]'}">
		<div class="container" align="center">
			<div class="page_wrap">
				<div class="page_nation">
					<c:if test="${page.startPage != 1}">
						<button class="curPage" value="${page.curPage -1}">
							<i class="fas fa-angle-left fa-2x"></i>
						</button>
					</c:if>
					<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}" step="1">
						<c:if test="${i eq param.curPage}">
							<button type="button" class="activePage" value="${i}">${i}</button>
						</c:if>
						<c:if test="${i ne param.curPage}">
							<button type="button" class="curPage" value="${i}">${i}</button>
						</c:if>
					</c:forEach>
					<c:if test="${page.curPage  < page.totalPage}">
						<button class="curPage" value="${page.endPage +1}">
							<i class="fas fa-angle-right fa-2x"></i>
						</button>
					</c:if>
				</div>
			</div>
		</div>
	</c:if>
		<%@include file="../footer.jsp" %>
</body>
</html>