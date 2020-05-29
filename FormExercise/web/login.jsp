<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta name='description' content="内容" http-equiv="refresh" charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Title</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script>
    function refreshCode() {
        var vcode = document.getElementById("vcode");
        vcode.src = "${pageContext.request.contextPath}/CheckCodeServlet?time="+new Date().getTime();
    }
  </script>
</head>
<body>
<div class="container" style="width: 400px;">
  <h3 style="text-align: center;">管理员登录</h3>
  <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
    <div class="form-group">
      <label for="user">用户名：</label>
      <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
    </div>

    <div class="form-group">
      <label for="password">密码：</label>
      <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
    </div>

    <div class="form-inline">
      <label for="vcode">验证码：</label>
      <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码" style="width: 120px;"/>
      <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/CheckCodeServlet" title="看不清点击刷新" id="vcode"/></a>
    </div>
    <hr/>
    <div class="form-group" style="text-align: center;">
      <input class="btn btn btn-primary" type="submit" value="登录">
    </div>
  </form>

  <!-- 出错显示的信息框 -->
  <div class="alert alert-warning alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert" >
      <span>&times;</span>
    </button>
    <strong>${login_err}</strong>
  </div>
</div>
<script src="bootstrap/js/jquery-3.2.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
