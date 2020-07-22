<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysite3 갤러리</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">

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
				<h3>첨부파일연습</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">첨부파일연습</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->
			
			
			<div id="file">
				
					<div>
						<img id="resultImg" src="${pageContext.request.contextPath }/upload/${requestScope.saveName}">
					</div>
					<p>
						<a id="btnUpload" href="${pageContext.request.contextPath }/fileupload/form"> 다시 업로드 하기 </a>
					</p>
				
			</div>
			<!-- //gallery -->
			
		
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>