<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysite3</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->

		<jsp:include page="/WEB-INF/views/include/nav.jsp"></jsp:include>
		<!-- //nav -->

		<jsp:include page="/WEB-INF/views/include/asideGuest.jsp"></jsp:include>
		<!-- //aside -->
		
		<div id="content">
			<div id="content-head">
            	<h3>ajax방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">ajax방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            
            <div id="guestbook">
            	<form action="${pageContext.request.contextPath }/guestBook/addGuestBook" method="get">
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></td>
								<td><input id="input-pass"type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button type="submit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- //guestWrite -->
					<div id="guestbookListArea">
					
					</div>
				</form>	
					
            </div>
		</div>
		<div class="clear"></div>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		fetchList();
	});
	
	function fetchList(){
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/list",		
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},
			dataType : "json",
			success : function(list){
				console.log(list.length);
				
				for(var i = 0; i < list.length; i++){
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
		
		str += " <table class='guestRead'>";
		str += ' <colgroup>'
		str += '	<col style="width: 10%;">'
		str += '	<col style="width: 40%;">'
		str += '	<col style="width: 40%;">'
		str += '	<col style="width: 10%;">'
		str += ' </colgroup>';
		str += ' <tr>'
		str += ' <td>'+list.no+'</td>'
		str += ' <td>'+list.name+'</td>'
		str += ' <td>'+list.regDate+'</td>'
		str += ' <td><a href="/mysite3/book/deleteForm?no="'+list.no+'>[삭제]</a></td>'
		str += ' </tr>';
		str += ' <tr>'
		str += ' <td colspan=4 class="text-left">'+list.content+'</td>'
		str += ' </tr>';
		str += ' </table>';
		
		$("#guestbookListArea").prepend(str);
	}
	
</script>
</html>