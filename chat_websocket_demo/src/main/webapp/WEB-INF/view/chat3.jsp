<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/layoutit/src/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/layoutit/src/css/style.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/layoutit/src/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layoutit/src/js/bootstrap.min.js"></script>
<title>聊天室</title>
</head>
<body>
<script type="text/javascript">
		var websocket;
		if('WebSocket' in window){
			websocket=new WebSocket("ws://localhost:8080/chat_websocket_demo/ws");
			}
		websocket.onopen = function(event) {
			console.log("WebSocket:已连接");
			console.log(event);
		};
		websocket.onmessage = function(event) {
			var data=JSON.parse(event.data);
			console.log("WebSocket:收到一条消息",data);
			$("#content").append("<p style=\"color:green\">"+data.username+"</p><p style=\"color:white\">"+data.msg+"</p>");
		};
		websocket.onerror = function(event) {
			console.log("WebSocket:发生错误 ");
			console.log(event);
		};
		websocket.onclose = function(event) {
			console.log("WebSocket:已关闭");
			console.log(event);
		}
		function send(){
			var message=$("#message").val();
			var data={};
			data["username"]="${username}";
			data["msg"]=message;
			$("#content").append("<p style=\"color:red\">"+'${username}'+"</p><p style=\"color:white\">"+message+"</p>");
			websocket.send(JSON.stringify(data));
			}
</script>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-12">
					<h2 style="text-align:center">${username},聊天室欢迎你</h2>
					<!-- 这里显示聊天室的内容 -->
					<div class="row" id="content" style="height:600px; background-color:gray"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
						<input type="text" name="message" class="form-control" id="message"/>
						<button type="button" class="btn btn-primary" onclick="send()">提交</button>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</div>
</body>
</html>