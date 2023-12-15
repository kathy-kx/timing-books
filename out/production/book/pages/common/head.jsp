<%--
  Created by IntelliJ IDEA.
  User: xiwang
  Date: 2022/9/30
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath", basePath);
    //String basePath = "http://localhost:8081/book/";
%>

<!--	在title下写base标签，固定相对路径跳转的基础-->
<base href="<%=basePath%>"> <%--表达式脚本的内容，作为字符串，作为base标签的超链接--%>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
