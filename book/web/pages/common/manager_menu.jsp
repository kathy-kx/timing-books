<%--
  Created by IntelliJ IDEA.
  User: kxzhu
  Date: 2022/9/30
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div>
        <a href="manager/bookServlet?action=page">图书管理</a>
        <%--action表示它要调用服务器功能里的哪一个方法，此处调用BookServlet的page() 方法--%>
<%--    本页面（manager_menu）是被管理模块的各个jsp页面静态包含的，
        而这些页面在跳转href链接时，其页面是有base标签的（静态包含head.jsp的方式），
        所以此处的href使用的相对地址也是基于base的，即到工程路径--%>
        <a href="pages/manager/order_manager.jsp">订单管理</a>
        <a href="index.jsp">返回商城</a>
    </div>

<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
