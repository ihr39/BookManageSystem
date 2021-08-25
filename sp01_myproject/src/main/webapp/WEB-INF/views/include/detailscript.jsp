<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script id="reviewlist_template" type="text/x-handlebars-template">
	{{#each .}}
	<div id="reviewReply{{REVIEWNO}}" class="reviewReply">
		<input type="hidden" value="{{ISBN}}" class="isbn">
			<div class="division1">
				<p class="reviewscore">
				<label id="star">★</label>
				<label class="score">{{REVIEWSCORE}}<label>
				<label class="nicname">{{NICNAME}}</label></p>
				<div class="reviewSize">
					<p class="shortreview">{{SHORTREVIEW}}</p>
				</div>
				<p class="regdata">{{REGDATE}}</p>
			</div>
			<div class="division2">
				<input type="hidden" value="{{GUBUN}}" class="gubun">
				<input type="hidden" value="{{REVIEWSCORE}}" class="reviewscore">
				<button class="reviewLikeBtn" id="reviewLikeBtn{{REVIEWNO}}" style="border: none; outline: none;">
					{{#reviewLike GUBUN}}{{/reviewLike}}
				</button>
				<span class="reviewlikecnt" id="reviewlikecnt{{REVIEWNO}}">
						{{REVIEWLIKECNT}}
				</span>
				<button class="reReply" value="{{REVIEWNO}}">
					<i class="far fa-comment-dots fa-lg"></i>
				</button>
				{{#deleteBtn NICNAME}}{{/deleteBtn}}
				{{#updateBtn NICNAME}}{{/updateBtn}}
			</div>
	</div>
	<hr style="width: 700px">
	{{/each}}
</script>
<script id="reviewreplylist_template" type="text/x-handlebars-template">
	{{#each .}}
		<div class="reviewReplyList{{reviewno}}" >
		<p>
			<label class="nicname" style="margin-left: 800px"> {{nicname}}</label>    {{regdate}}  
			<input type="hidden" value="{{reviewstep}}" class="reviewstep">
			{{#deleteReplyBtn nicname}}{{/deleteReplyBtn}}
			{{#updateReplyBtn nicname}}{{/updateReplyBtn}}
			
			<legend class="content" style="font-size: 15px">{{content}}</legend>
		</p> 
		</div>
		<hr style="width: 700px">
	{{/each}}
</script>

<script type="text/javascript">
	$(function(){
		var nicname='${sessionScope.nicname}';
		var image=$('#image').attr('src');
		var author=$('#author').html();
		var publisher=$('#publisher').html();
		var title=$('h3').text();
		var likeNo='<i class="far fa-heart fa-lg" ></i>';
		var like='<i class="fas fa-heart fa-lg" style="color:red"></i>';
		var isbn=$('#isbn').val();
		var gubun='${blist.GUBUN}';
		var reviewno='';

		function LikeController(){
			if(gubun=='B'){
				$('#likeBtn').html(like);
			}
			else{
				$('#likeBtn').html(likeNo);
			}
		}

		//리뷰의 댓글 창 숨기기
		$('#reviewReply').hide();
		LikeController();
		//좋아요 보이게
		LikeController();
		//리뷰 리스트 뿌림
		reviewSee();
		$('#reviewSave').click(function(){
			var reviewscore=$('#reviewscore').val();
			var shortreview=$('#shortreview').val();
			if(nicname==''){
				alert('로그인 후 이용가능 합니다');
				location.href='${path}/member/login';
			}else{
  				//리뷰를 insert
  				if(reviewno==''){
  					 $.ajax({
  	  	 				url:'${path}/review/insert/',
  	  	 				type:'post',
  	  	 				data:JSON.stringify({nicname,reviewscore,shortreview,isbn}),
  	  	 				dataType:'text',
  	  	 				contentType:'application/json;charset=UTF-8',
  	  	 				success:function(data){
  	  	 					$('#shortreview').val('');
  	  	 					$('#reviewscore').val('5');
  	  	 					reviewSee();
  	  	 					//책의 별점도 바뀌어야 함
  	  	 					
  	  	 				},
  	  	 				error: function(){
  	  	 					alert('실패');
  	  	 				}
  	  	 			});
  				}
  				
  			}
		});
		//리뷰가 항상 보이게
		function reviewSee(){
			var reviewno=$(this).parent().find('.reReply').val();
			//좋아요에 넣기
			Handlebars.registerHelper('reviewLike', function(gubun){
				if (gubun=='R'){ //리뷰 좋아요 상태
					return like;
				}else{
					return likeNo;
				}
			});

			//본인 리뷰, 댓글에만 삭제가 뜨게
			Handlebars.registerHelper('deleteBtn',function(nicname){
				var usernicname='${sessionScope.nicname}';
				if(nicname==usernicname){
					return '<button class="reviewDelete">삭제</button>';
				}
				return '';
			});
			//본인 리뷰, 댓글에만 수정이 뜨게
			Handlebars.registerHelper('updateBtn',function(nicname){
				var usernicname='${sessionScope.nicname}';
				if(nicname==usernicname){
					return '<button class="reviewUpdate">수정</button>';
				}
				return '';
			});
			//리뷰 리스트 뿌림
			$.ajax({
				url:'${path}/review/list/'+isbn,
				dataType:'json',
				type:'get',
				success: function(data){
					//alert(data);
					var source =$('#reviewlist_template').html();
					var templete=Handlebars.compile(source);
					$('#reviewList').html(templete(data));
					
				},
				error: function(){
					alert('실패');
				}
			});
			
			
		}
		
		function replymove(reviewno,eleId){
			$('#reviewReply').show();
 			$('#reviewno').val(reviewno);//그 위치의 리뷰 넘버를 넣음
			//alert($('#reviewno').val());
			$('#reviewReply').insertAfter(eleId);
		};
		//리뷰의 댓글 위치 맞게 조정하기
		function replyListmove(reviewno,eleId){
 			$('#reviewno').val(reviewno);//그 위치의 리뷰 넘버를 넣음
			//alert($('#reviewno').val());
			$('#reviewReplyList').insertAfter(eleId);
		};
		
		
		//리뷰의 댓글 버튼 클릭시 댓글 창 나옴
		$('#reviewList').on('click','.reReply',function(){
			var reviewno=$(this).val();
			replymove(reviewno,'#reviewReply'+reviewno);
			//alert(reviewno);
			var reviewno=$(this).parent().find('.reReply').val();
			var nicname=$('#reviewReplyList'+reviewno).find('.nicname').text();
			
			//본인 댓글에만 삭제가 뜨게
			Handlebars.registerHelper('deleteReplyBtn',function(nicname){
				var usernicname='${sessionScope.nicname}';
				if(nicname==usernicname){
					return '<button class="replyDeleteBtn" value="{{reviewno}}" style="display: inline-block; background: white; border-radius: 5px; border-width: 1px; border-color:#848484;">삭제</button>';
				}
				return '';
			});
			//본인 댓글에만 수정이 뜨게
			Handlebars.registerHelper('updateReplyBtn',function(nicname){
				var usernicname='${sessionScope.nicname}';
				if(nicname==usernicname){
					return '<button class="replyUpdateBtn" value="{{reviewno}}" style="display: inline-block; background: white; border-radius: 5px; border-width: 1px; border-color:#848484;">수정</button>';
				}
				return '';
			});
			
			//리뷰의 댓글 보이기
			$('.reviewReplyList'+reviewno).css('display','block');
			$.ajax({
				url:'${path}/reply/selectList/'+reviewno,
				dataType:'json',
				type:'get',
				success:function(data){
					//alert(reviewno);
					var source =$('#reviewreplylist_template').html();
					var templete=Handlebars.compile(source);
					$('#reviewReplyList').html(templete(data));
					replyListmove(reviewno,'#reviewReply'+reviewno);
				},
				error:function(){
					alert('실패')
				}
				
			});
			
		});
		
		//댓글 창의 등록 버튼 클릭시 댓글 등록
		$('#reviewReply').on('click','#reReplyBtn',function(){
				var reviewno=$('#reviewno').val();
				var reviewstep=$('#reviewstep').val();
				var reviewlevel=$('#reviewlevel').val();
				//alert(reviewstep);
				var content=$('#content').val();
				var nicname='${sessionScope.nicname}';
				if(content==''){
					alert('내용을 입력해주세요');
					$('#content').focus();
				}else{
					//alert(nicname);
					if(reviewstep == '0'){
						$.ajax({
							url:'${path}/reply/insert',
							data:JSON.stringify({reviewno,reviewlevel,content,nicname,reviewstep,isbn}),
							type:'post',
							dataType:'text',
							contentType:'application/json;charset=UTF-8',
							success:function(data){
								console.log(data);
								//리뷰 댓글 처리
								reviewSee();
							},
							error:function(){
								alert('실패');
							}
						});
					}
				}

				
			});
		
			//좋아요 처리
			//책 좋아요 처리 (isbn,유저아이디)
 			$('#likeBtn').click(function(){
				var gubun='${blist.GUBUN}';
				//좋아요 처리
				if(gubun!='B'){
					$.ajax({
						url:'${path}/manage/booklike/'+isbn,
						data:'text',
						type:'get',
						success:function(data){
							console.log(data);
							gubun='B';
							//alert(data.gubun);
							$('#likeBtn').html(like);
						},
						error:function(){
							alert('실패');
						}
					});
				}else{
					//좋아요 취소 처리
					$.ajax({
						url:'${path}/manage/booklikeCancle/'+isbn,
						data:'text',
						type:'get',
						success:function(data){
							console.log(data);
							gubun='N';
							//alert(data.gubun);
							$('#likeBtn').html(likeNo);
						},
						error:function(){
							alert('실패');
						}
					});
				}
			});
		//리뷰 좋아요 처리
		$('#reviewList').on('click','.reviewLikeBtn',function(){
			//리뷰 좋아요 버튼을 누르면 R로 바뀌게
			var reviewno=$(this).parent().find('.reReply').val();
			var gubun=$(this).parent().find('.gubun').val();
			var reviewlikecnt=$(this).parent().find('.reviewlikecnt').val();
			//alert(gubun);
			//리뷰 좋아요
			if(gubun!='R'){
				$.ajax({
					url:'${path}/manage/reviewlike/'+reviewno,
					type:'get',
					success:function(data){
						//console.log(data);
						gubun='R';
						$('#reviewLikeBtn'+reviewno).html(like);
						$('#reviewlikecnt'+reviewno).text(parseInt($('#reviewlikecnt'+reviewno).text()) + 1);
					},
					error:function(){
						alert('실패');
					}
				});
			}
			
  			//리뷰 좋아요 취소
			if(gubun=='R'){
				$.ajax({
					url:'${path}/manage/reviewlikeCancle/'+reviewno,
					type:'get',
					success:function(data){
						console.log(data);
						gubun='0';
						//alert(reviewlikecnt);
						$('#reviewLikeBtn'+reviewno).html(likeNo);
						$('#reviewlikecnt'+reviewno).text(parseInt($('#reviewlikecnt'+reviewno).text()) - 1);
					},
					error:function(){
						alert('실패');
					}
				}); 
			}
		});
		
		//리뷰 삭제 버튼 누르면
 		$('#reviewList').on('click','.reviewDelete',function(){
			var reviewno=$(this).parent().find('.reReply').val();
			//alert(reviewno);
			var result=confirm('삭제하시겠습니까?');
			if(result){
				$.ajax({
					url:'${path}/review/delete/'+reviewno,
					type:'get',
					contentType:'application/json;charset=UTF-8',
					success:function(data){
						console.log(data);
						reviewSee();
					},
					error:function(){
						alert('실패');
					}
				});

			}
		});
		
		//리뷰 수정 버튼을 누르면
 		$('#reviewList').on('click','.reviewUpdate',function(){
			reviewno=$(this).parent().find('.reReply').val();
			//alert(reviewno);
			$('#reviewscore').val();
			$('#shortreview').val($(this).parent().find('.shortreview').text());

			$('#reviewSave').click(function(){
				var result=confirm('수정하시겠습니까?');
				if(result){
					var reviewscore=$('#reviewscore').val();
					var shortreview=$('#shortreview').val();
					//새로 넣는 것은 리뷰 넘버가 없음 그러므로 리뷰넘버가 있다면 수정을 한다
					if(reviewno!=''){ 
						$.ajax({
							url:'${path}/review/update',
							type:'post',
							data:JSON.stringify({shortreview,reviewno,reviewscore}),
							contentType:'application/json;charset=UTF-8',
							success:function(data){
								console.log(data);
								reviewSee();
							},
							error:function(){
								alert('실패');
							}
						});
					}
					
				}

			});
		});
		
 		//리뷰의 댓글 삭제버튼 누르면 삭제
 		$('#reviewList').on('click','.replyDeleteBtn',function(){
 			var reviewno=$(this).val();
 			var reviewstep=$(this).parent().find('.reviewstep').val();
 			var result=confirm('정말 삭제하시겠습니까?');
 			if(result){
 				$.ajax({
 	 				url:'${path}/reply/delete',
 	 				data:{reviewno,reviewstep},
 	 				type:'get',
 	 				dataType:'text',
 	 				success:function(data){
 	 					alert(data);
 	 				},
 	 				error:function(){
 	 					alert('실패');
 	 				}
 	 			});
 			}
 			
 		});
 		
 		//리뷰의 댓글 수정 버튼 누르면 수정
 		$('#reviewList').on('click','.replyUpdateBtn',function(){
 			$('#reviewno').val($(this).val());
 			$('#reviewstep').val($(this).parent().find('.reviewstep').val());
 			$('#content').val($(this).parent().find('.content').text());
 			
 			var reviewno=$('#reviewno').val();
 			$('#reviewReply').show();
 			replymove(reviewno,'#reviewReply'+reviewno);
 			//alert(reviewstep);
 			//수정버튼 클릭시 수정
 			if(reviewstep != '0'){
 				$('#reReplyBtn').click(function(){
 					var result=confirm('정말 수정하시겠습니까?');
 					if(result){
 						var content=$('#content').val();
 	 					var reviewstep=$('#reviewstep').val();
 	 	 				$.ajax({
 	 	 	 				url:'${path}/reply/update',
 	 	 	 				data:JSON.stringify({reviewno,reviewstep,content}),
 	 	 	 				dataType:'text',
 	 	 	 				contentType:'application/json;charset=UTF-8',
 	 	 	 				type:'post',
 	 	 	 				success:function(data){
 	 	 	 					alert(data);
 	 	 	 					reviewSee();
 	 	 	 				},
 	 	 	 				error:function(){
 	 	 	 					alert('실패');
 	 	 	 				}
 	 	 	 			});
 					}
 					
 	 			});
 			}
 		});
 		
 		
	});
</script>
</head>