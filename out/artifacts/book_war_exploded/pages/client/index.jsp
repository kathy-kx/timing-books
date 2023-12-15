<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>

	<%--静态包含 base标签、css样式、jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function (){
			//给"加入购物车"按钮绑定单击事件
			$("button.addToCart").click(function (){
				var bookId = $(this).attr("bookId");
				//this是当前事件，即单击"加入购物车"这个标签
				// attr()：设置或返回（获取）被选元素的属性值

				//跳转到cartServlet，执行addItem方法
				// location.href = "http://localhost:8081/book/cartServlet?action=addItem&id=" + bookId;// 让整个页面刷新，发起请求

				//发ajax请求，添加商品到购物车
				$.getJSON("http://localhost:8080/book/cartServlet","action=ajaxAddItem&id=" + bookId, function (data){
					// jQuery中，【服务器Servlet返回的数据】用function的参数接收，即data
					//此处服务器返回的数据是json字符串。内含totalCount和lastName
					// console.log(data);
					//更新到页面
					$("#cartTotalCount").text("您的购物车中有"+ data.totalCount +"件商品");
					$("#cartLastName").text("您刚刚将《" + data.lastName + "》加入到了购物车中");//简易解决 但不是完美

				})
			});
		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<%--如果用户没有登录，显示【登录和注册】菜单--%>
				<c:if test="${empty sessionScope.loginUserName}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				</c:if>
				<%--如果用户登录成功，则session域中存有userName，显示【欢迎xxxx】--%>
				<c:if test="${not empty sessionScope.loginUserName}">
					<span>欢迎<span class="um_span">${sessionScope.loginUserName}</span>光临有时书城</span>
					<a href="pages/order/order.jsp">我的订单</a>
					<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
				</c:if>
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<%--购物车为空的输出--%>
					<span id="cartTotalCount"> </span>
					<div>
<%--						<span id="cartLastName" style="color: red">当前购物车空空的</span>--%>
						<span id="cartLastName" > </span>
					</div>
				</c:if>
<%--				这里有个bug，在jsp页面显示的时候要加两个为空时的空标签，才能不用刷新就显示总数和最后一件商品。或可以用thymeleaf解决--%>

				<c:if test="${not empty sessionScope.cart.items}">
					<%--购物车非空的输出--%>
<%--					<span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>--%>
					<span id="cartTotalCount"> </span>
					<div>
<%--						您刚刚将<span id="cartLastName" style="color: red">${sessionScope.lastName}</span>加入到了购物车中--%>
						<span id="cartLastName" > </span>
					</div>
				</c:if>

			</div>
<%--items:遍历的数据源；var：当前遍历到的数据。items（List型）里装的是book（Book型）--%>
			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button bookId="${book.id}" class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>

		</div>

		<%--静态包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>


	</div>

	<%--静态包含 页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>