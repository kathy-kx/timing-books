<%--
  Created by IntelliJ IDEA.
  User: xiwang
  Date: 2022/10/21
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  这是登录页面 login.jsp页面<br/>
    <form action="http://localhost:8081/15_filter/loginServlet" method="get">
        用户名：<input type="text" name="username"/> <br/>
        密码：<input type="password" name="password"/> <br/>
        <input type="submit"/>
    </form>

</body>
</html>
