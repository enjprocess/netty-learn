<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/7 0007
  Time: 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>WebSocket</title>
    <script type="text/javascript">
      var socket;
      //如果浏览器支持websocket的话，实例化它
      if (window.WebSocket) {
          //尝试与远程websocket进行连接
          socket = new WebSocket("ws://localhost:8899/ws");
          //配置监听
          //接受消息
          socket.onmessage = function (event) {
              var obj = document.getElementById("dataShowArea");
              obj.value = obj.value + "\n" + event.data;
          }

          socket.onopen = function () {
              var obj = document.getElementById("dataShowArea");
              obj.value = "连接开启\n"
          }

          socket.onclose = function() {
              var obj = document.getElementById("dataShowArea");
              obj.value = obj.value + "\n" + "连接关闭"
          }
      }

      function sendMessage(message) {
          if (!window.WebSocket) {
              return
          }
          if (socket.readyState == WebSocket.OPEN) {
              socket.send(message);
          } else {
              alert("连接未开启");
          }
      }

    </script>
  </head>
  <body>
    <form method="post" onsubmit="return false;" name="form1">
       <textarea cols="50" rows="20" name="content">

      </textarea>
      <input type="button" value="send" onclick="javascript:sendMessage(form1.content.value);">
      <h3>服务器端数据</h3>
      <textarea cols="50" rows="20" id="dataShowArea">

      </textarea>
    </form>
  </body>
</html>
