<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta name='description' content="内容" http-equiv="refresh" charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>update</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script src="bootstrap/js/jquery-3.2.1.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <script>
    $(function () {

      $("input[type='button']").on("click",function(){
          <%--location.href = "${pageContext.request.contextPath}/FindUserByPageServlet";--%>
        location.replace("${pageContext.request.contextPath}/FindUserByPageServlet");
      })

    });
  </script>
</head>
<body>
<div class="container" style="width: 400px;">
  <h3 style="text-align: center;">修改联系人</h3>
  <form action="${pageContext.request.contextPath}/UpdateUserServlet" method="post">
    <%-- 隐藏域提交id，因为要通过id修改其他的数据 --%>
    <input type="hidden" name="id" value="${user.id}">
    <div class="form-group">
      <label for="name">姓名：</label>
      <input type="text" class="form-control" id="name" name="name"  value="${user.name}" readonly="readonly" placeholder="请输入姓名" />
    </div>

    <div class="form-group">
      <label>性别：</label>
      <c:if test="${user.gender == '男'}">
          <input type="radio" name="gender" value="男"  checked/>男
          <input type="radio" name="gender" value="女"  />女
      </c:if>
      <c:if test="${user.gender == '女'}">
        <input type="radio" name="gender" value="男"  />男
        <input type="radio" name="gender" value="女"  checked/>女
      </c:if>
    </div>

    <div class="form-group">
      <label for="age">年龄：</label>
      <input type="text" class="form-control" id="age" value="${user.age}" name="age" placeholder="请输入年龄" />
    </div>

    <div class="form-group">
      <label for="address">籍贯：</label>
      <select name="address" id="address" class="form-control" >
        <c:if test="${user.address == '陕西'}">
        <option value="陕西" selected>陕西</option>
        <option value="北京">北京</option>
        <option value="上海">上海</option>
        </c:if>
        <c:if test="${user.address == '北京'}">
          <option value="陕西" >陕西</option>
          <option value="北京" selected>北京</option>
          <option value="上海">上海</option>
        </c:if>
        <c:if test="${user.address == '上海'}">
          <option value="陕西" >陕西</option>
          <option value="北京">北京</option>
          <option value="上海" selected>上海</option>
        </c:if>
      </select>
    </div>

    <div class="form-group">
      <label for="qq">QQ：</label>
      <input type="text" id="qq" class="form-control" name="qq" value="${user.qq}" placeholder="请输入QQ号码"/>
    </div>

    <div class="form-group">
      <label for="email">Email：</label>
      <input type="text" id="email" class="form-control" name="email" value="${user.email}" placeholder="请输入邮箱地址"/>
    </div>

    <div class="form-group" style="text-align: center">
      <input class="btn btn-primary" type="submit" value="提交" />
      <input class="btn btn-default" type="reset" value="重置" />
      <input class="btn btn-default" type="button"  value="返回"/>
    </div>
  </form>
</div>

</body>
</html>
