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
				<div id="list">
					<form action="${pageContext.request.contextPath }/board/search" method="get">
						<div class="form-group text-right">
							<input type="text" name = "keyword">
							<input type="hidden" name = "pg" value="1">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="vo" items="${requestScope.bList}">
							<tr>
								<td>${vo.no }</td>
								<td class="text-left"><a href="${pageContext.request.contextPath }/board/read?no=${vo.no}">${vo.title }</a></td>
								<td> ${vo.name }</td>
								<td> ${vo.hit }</td>
								<td> ${vo.reg_date }</td>
								<td>
								<c:if test="${authUser.no eq vo.user_no}">
									<a href="${pageContext.request.contextPath }/board/delete?no=${vo.no }&user_no=${vo.user_no}">[삭제]</a>
								</c:if>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
		
					<div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<c:forEach var="item" begin="${requestScope.pg.page_Start }" end="${requestScope.pg.page_End}" >
									
									<c:if test="${param.pg eq item}">
										<c:if test="${empty param.keyword}">
										<li class="active"><a href="${pageContext.request.contextPath }/board/list?pg=${item }"><c:out value="${item }"/></a></li>
										</c:if>
										
										<c:if test="${!empty param.keyword}">
										<li class="active"><a href="${pageContext.request.contextPath }/board/list?pg=${item }&keyword=${param.keyword}"><c:out value="${item }"/></a></li>
										</c:if>
									</c:if>
									 
									<c:if test="${param.pg != item }">
										<c:if test="${empty param.keyword}">
											<li><a href="${pageContext.request.contextPath }/board/list?pg=${item }"><c:out value="${item }"/></a></li>
										</c:if>
										
										<c:if test="${!empty param.keyword}">
											<li><a href="${pageContext.request.contextPath }/board/list?pg=${item }&keyword=${param.keyword}"><c:out value="${item }"/></a></li>
										</c:if>
									</c:if>
									
							</c:forEach>
							<li><a href="">▶</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div>
					<c:if test="${authUser.no != null}">
					<a id="btn_write" href="${pageContext.request.contextPath }/board/writeForm">글쓰기</a>
					</c:if>
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

</body>

</html>
