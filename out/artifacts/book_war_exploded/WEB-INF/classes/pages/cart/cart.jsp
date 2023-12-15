<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%--静态包含 base标签、css样式、jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			//给 【删除】绑定单击事件
			$("a.deleteItem").click(function (){
				return confirm("你确定删除【" + $(this).parent().parent().find("td:first").text()+ "】吗？")
			});

			//给 【清空购物车】绑定单击事件
			$("#clearCart").click(function (){
				return confirm("你确定清空购物车吗？")
			});

			// 给输入框绑定 onchange 内容发生改变事件
			$(".updateCount").change(function (){
				//获取商品名称
				var name = $(this).parent().parent().find("td:first").text();
				//获取商品数量
				var count = this.value; //this是当前事件，即 内容发生改变change事件
				//获取id
				var bookId = $(this).attr('bookId');
				if( confirm("你确定要将【"+ name + "】商品修改数量为："+ count +" 吗？")){
					//用户点击【确定】，发起请求，修改数量
					location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+id;

				}else{
					//用户点击【取消】
					//defaultValue属性是表单项dom对象的属性。它表示默认的value属性值。是之前输入框value="¥{entry.value.count}"的值
					this.value = this.defaultValue;
				}

			});


		})

	</script>

</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

		<%--静态包含 登录成功后的统一菜单--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%--如果购物车是空的--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空!快跟小伙伴们去浏览商品吧!!!</a></td>
				</tr>
			</c:if>

			<%--如果购物车不是空的，即 有商品--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<%--加入购物车的数据都存在session中的cart对象里了。其中items是键值对--%>
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" type="text" value="${entry.value.count}"
								   bookId="${entry.value.id}" style="width: 80px"/>
						</td>

						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>

		<c:if test="${not empty sessionScope.cart.items}">	<%--如果购物车不是空的，即 有商品 才输出以下内容--%>
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	
	</div>


	<%--静态包含 页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>