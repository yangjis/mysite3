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
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

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
								<td colspan="4"><button type="submit" id ="btnSubmit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- <button type="submit" id ="test">모달테스트</button> -->
					<!-- //guestWrite -->
					<div id="guestbookListArea">
					
					</div>
            </div>
		</div>
		<div class="clear"></div>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	
	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="text" name="modalNo" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
</body>

<script type="text/javascript">
	$(document).ready(function(){
		fetchList();
	});
	
	//모달테스트
	/*$("#test").on("click", function(){
		console.log("모달테스트");
		$("#delModal").modal();
	});*/
	
	//리스트의 삭제버튼 클릭
	$("#guestbookListArea").on("click","a", function(){
		console.log("삭제버튼 클릭");
		event.preventDefault();//부모태그로의 이벤트 전파를 stop 중지하라는 의미(a태그의 기능을 막기위해 사용)
		
		var $this = $(this);
		var no = $this.data("delno");
		console.log(no);
		
		$("#modalNo").val(no);
		
		$("#modalPassword").val("");
		
		$("#delModal").modal();
	});
	
	//글쓰기버튼 클릭할때
	$("#btnSubmit").on("click",function(){
		console.log("버튼 클릭함?");
		event.preventDefault();//부모태그로의 이벤트 전파를 stop 중지하라는 의미
		
		//데이터 추출
		var uname = $("#input-uname").val();
		var pass = $("#input-pass").val();
		var content = $("[name='content']").val();
		
		//객체만들기
		var guestBookVo = {name: uname, 
						   password: pass, 
						   content: content};
		
		console.log(guestBookVo);
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/write",		
			type : "post",
			contentType : "application/json", //보내는 데이터 타입 contentType : "application/json"가 없으면 그냥 파라미터로 데이터를 보내는 거고 있으면 제이슨을 이용해 바디에 넣어서 데이터를 보내겠다
			data : JSON.stringify(guestBookVo), //보내는 데이터를 제이슨으로 변환한다.
			dataType : "json",
			success : function(guestBookVo){
				/*성공시 처리해야될 코드 작성*/
				render(guestBookVo);
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	//모달창 삭제버튼 클릭할때
	$("#btnDel").on("click",function(){
		//이벤트 체크를 위한 콘솔로그
		console.log("모달창 삭제 버튼 클릭");
		
		//데이터 수집
		var password = $("#modalPassword").val();
		var no = $("#modalNo").val();
		
		//데이터 전송 -> 그리기 작업
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/delete",		
			type : "post",
			//contentType : "application/json",
			data : {password: password, no: no},
			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/
				console.log(count);
				if(count == 1){
					//모달창 닫고
					$("#delModal").modal("hide");
					//리스트에서 지우기
					$("#t-" + no).remove();
				}else{
					//모달창 닫기
					$("#delModal").modal("hide");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	
	function fetchList(){
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/list",		
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},
			dataType : "json",
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
		
		str += " <table id = 't-"+list.no+"' class='guestRead'>";
		str += ' <colgroup>'
		str += '	<col style="width: 10%;">'
		str += '	<col style="width: 40%;">'
		str += '	<col style="width: 40%;">'
		str += '	<col style="width: 10%;">'
		str += ' </colgroup>';
		str += ' <tr>'
		str += ' <td>'+list.no+'</td>'
		str += ' <td>'+list.name+'</td>'
		str += ' <td>'+list.reg_date+'</td>'
		str += ' <td><a href="" data-delno="'+list.no+'">[삭제]</a></td>'
		str += ' </tr>';
		str += ' <tr>'
		str += ' <td colspan=4 class="text-left">'+list.content+'</td>'
		str += ' </tr>';
		str += ' </table>';
		
		$("#guestbookListArea").prepend(str);
		
	}
	
</script>
</html>