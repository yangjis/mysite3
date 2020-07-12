<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysite3</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->
		
		<jsp:include page="/WEB-INF/views/include/nav.jsp"></jsp:include>
		<!-- //nav -->

		<jsp:include page="/WEB-INF/views/include/asideBoard.jsp"></jsp:include>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="writeForm">
					<c:if test="${param.boardType eq 'replyBoard' }">
						<form action="${pageContext.request.contextPath }/board/replyWriteAction" method="get">
						<input type = "hidden" name = "group_no" value="${param.group_no }">	
						<input type = "hidden" name = "order_no" value="${param.order_no }">
						<input type = "hidden" name = "depth" value="${param.depth }">
						<input type = "hidden" name = "parentsNo" value="${param.parentsNo }">
					</c:if>
					
					<c:if test="${empty param.boardType }">
					<form action="${pageContext.request.contextPath }/board/writeAction" method="get">
					</c:if>
						<!-- 제목 -->
						<div class="form-group">
							<label class="form-text" for="txt-title">제목</label>
							<input type="text" id="txt-title" name="title" placeholder="제목을 입력해 주세요">
							<input type="hidden" name="user_no" value="${authUser.no}">
						</div>
					
						<!-- 내용 -->
						<div class="form-group">
							<textarea id="txt-content" name = "content"></textarea>
						</div>
						
						<a id="btn_cancel" href="${pageContext.request.contextPath }/board/list?pg=1">취소</a>
						<button id="btn_add" type="submit" >등록</button>
						
					</form>
	                <!-- //form -->
				</div>
				<!-- //writeForm -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
