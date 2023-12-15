<%--
  Created by IntelliJ IDEA.
  User: xiwang
  Date: 2022/9/27
  Time: 09:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件的上传</title>
</head>
<body>
    <form action="http://localhost:8081/09_EL_JSTL/uploadServlet2" method="post" enctype="multipart/form-data" >
        用户名：<input type="text" name="username"/><br>
        头像：<input type="file" name="photo"/><br>
        <input type="submit" value="上传"/>


    </form>

</body>
</html>
