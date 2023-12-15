
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    System.out.println("a.jsp页面执行了");
    Object user = session.getAttribute("user");

    if(user == null){
        //没有登录
        request.getRequestDispatcher("/login.jsp").forward(request,response);
        return;
    }
%>
    我是a.jsp文件
</body>
</html>
