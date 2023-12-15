<%--
  Created by IntelliJ IDEA.
  User: xiwang
  Date: 2022/9/30
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.loginUserName}</span>光临有时书城</span>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
