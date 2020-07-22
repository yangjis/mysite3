<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->
		<jsp:include page="/WEB-INF/views/include/nav.jsp"></jsp:include>
		<!-- //nav -->
		<jsp:include page="/WEB-INF/views/include/galleryAside.jsp"></jsp:include>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="gallery">
				<div id="list">
			
					
					<c:if test="${!empty authUser.no}">	
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
					</c:if>
			
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath }/api/gallery/addGallery" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="comments" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
							<input type="hidden" name="user_no" value="${sessionScope.authUser.no}">
							<input type="hidden" name="name" value="${sessionScope.authUser.name}">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" id = "img">
						 <img id='viewModelImg' src="" ><!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent" >코멘트</p>
					</div>
					
				</div>
				<form method="get" action="${pageContext.request.contextPath }/gallery/delGallery" >
					<div class="modal-footer" >
					<input type = "hidden" value="${authUser.no }" id= "user_no">
					<input id="no" type = "hidden" value="" name="no">
					<button type="submit" class="btn btn-danger" id="btnDel">삭제</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
				
				
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">
$(document).ready(function(){
	fetchList();
});

$("#btnImgUpload").on("click", function(){
	console.log("클릭했는감?");
	$("#addModal").modal();
	

});

$("#viewArea").on("click", "img",function(){
	console.log("그림을 클릭했는가?");
	
	
	var no = $(this).data("no");
	console.log(no);
	
	var user_no = $("#user_no").val();
	
	$.ajax({
			
			url : "${pageContext.request.contextPath }/api/gallery/getSaveName",		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(no),
			dataType : "json",
			success : function(GalleryVo){
				console.log(GalleryVo);
				console.log(GalleryVo.saveName);
				$("#viewModelImg").attr("src", "${pageContext.request.contextPath }/upload/" + GalleryVo.saveName)
				$("#viewModelContent").text(GalleryVo.comments);
				
				$("#no").attr("value", GalleryVo.no);
				$("#viewModal").modal();
				
				if(user_no != GalleryVo.user_no){
					$("#btnDel").hide();
				}
					
				
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
		}
	});
	
});

function fetchList(){
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/gallery/list",		
		type : "post",
		contentType : "application/json",
		//data : {name: "홍길동"},
		//dataType : "json",
		success : function(list){
			
			for(var i = (list.length-1); i >= 0; i--){
				render(list[i]);
			}
			/*성공시 처리해야될 코드 작성*/
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}

function render(list){
	var str="";
	
	str += "<li><div class='view'>";
	str += "<img class='imgItem' src='${pageContext.request.contextPath }/upload/";
	str +=list.saveName;
	str +="' data-no="+list.no+">";
	str += "<div class='imgWriter'> 작성자: <strong>" +list.name+ "</strong></div>";
	str += "</div></li>";
	$("#viewArea").prepend(str);
	
};


</script>




</html>

