<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>

	<%--静态包含 base标签、css样式、jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		// 页面加载完成之后
		$(function (){
			//给删除的a标签绑定单击事件，点击删除时的确认提示
			$("a.deleteClass").click(function (){
				//干什么事
				/**
				 * confirm()是确认提示框函数：
				 * 参数：是其提示内容
				 * 表现：它有两个按钮，确认和取消
				 * 返回值：返回true表示点了确认；返回false表示点了取消
				 */

				//在事件的 function 函数中，有一个this对象。
				//这个this对象，是当前正在响应事件的dom对象(你所点击的删除)。
				//当前点击删除是<a>标签，其父元素<td>的父元素<tr>中，的第一个<td>标签，的文本内容，就是书名，也就是需要提示用户的信息
				return confirm("你确定删除【"+ $(this).parent().parent().find("td:first").text()+"】？");

				//return false: 阻止元素的默认行为===不提交请求
				//return true: 告诉浏览器可以继续提交
			});
		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>

		<%--静态包含 manager管理模块的菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		
<%--			<tr>--%>
<%--				<td>时间简史</td>--%>
<%--				<td>20.00</td>--%>
<%--				<td>霍金</td>--%>
<%--				<td>200</td>--%>
<%--				<td>400</td>--%>
<%--				<td><a href="pages/manager/book_edit.jsp">修改</a></td>--%>
<%--				<td><a href="#">删除</a></td>--%>
<%--			</tr>	--%>

			<%--items是遍历的数据源（存在request域中的page对象的items属性，items是是当前页数据，List类型，其中存放Book类型），
			var是当前遍历到的数据，即items的某一项--%>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>

	<%--静态包含分页条--%>
	<%@include file="/pages/common/page_nav.jsp"%>

	</div>


	<%--静态包含 页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>