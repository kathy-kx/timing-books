<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>

	<%--静态包含 base标签、css样式、jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	
	<script type="text/javascript">
		//页面加载完成之后
		$(function (){
			//给注册绑定单击事件
			$("#sub_btn").click(function (){
				//function中写要做的事情——验证的内容

				// 验证用户名:必须由字母，数字下划线组成，并且长度为 5 到 12 位
				//1 获取用户名输入框里的内容
				var usernameText = $("#username").val();
				//2 创建正则表达式对象
				var usernamePatt = /^\w{5,12}$/;//\w代表字母数字下划线
				//3 使用test方法验证
				if( !usernamePatt.test(usernameText) ){
					//4 在不匹配时，提示用户结果
					$("span.errorMsg").text("用户名不合法！");
					//不合法则不跳转下一页
					return false;
				} else{
					$("span.errorMsg").text("");//如果合法，就把"用户名不合法"的信息去掉
				}

				// 验证密码:必须由字母，数字下划线组成，并且长度为 5 到 12 位
				//1 获取密码输入框里的内容
				var passwordText = $("#password").val();
				//2 创建正则表达式对象
				var passwordPatt = /^\w{5,12}$/;//\w代表字母数字下划线
				//3 使用test方法验证
				if( !passwordPatt.test(passwordText) ){
					//4 在不匹配时，提示用户结果
					$("span.errorMsg").text("密码不合法！");
					//不合法则不跳转下一页
					return false;
				} else{
					$("span.errorMsg").text("");//如果合法，就把"用户名不合法"的信息去掉
				}

				// 验证确认密码:和密码相同
				//1 获取输入的确认密码
				var repwdText = $("#repwd").val();
				//2 和密码相比较
				if(repwdText != passwordText){
					//3 如果确认密码与第一次密码不相同，则提示用户
					$("span.errorMsg").text("确认密码和密码不一致！");
					//不合法则不跳转下一页
					return false;
				} else{
					$("span.errorMsg").text("");//如果一致，就把"确认密码和密码不一致"的信息去掉
				}

				// 邮箱验证:xxxxx@xxx.com
				//1 获取邮箱输入框里的内容
				var emailText = $("#email").val();
				//2 创建正则表达式对象
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/; //
				//3 使用test方法验证
				if( !emailPatt.test(emailText) ){
					//4 在不匹配时，提示用户结果
					$("span.errorMsg").text("邮箱格式不合法！");
					//不合法则不跳转下一页
					return false;
				} else{
					$("span.errorMsg").text("");//如果合法，就把"邮箱不合法"的信息去掉
				}

				// 验证码:现在只需要验证用户已输入。由服务器生成，因为还没讲到服务器。验证码生成。
				var codeText = $("#code").val();

				//去掉验证码前后的空格
				codeText = $.trim(codeText);

				if(codeText == "" || codeText == null){
					$("span.errorMsg").text("验证码不能为空！");
					return false;
				} else{
					$("span.errorMsg").text("");//如果合法，就把"验证码不能为空"的信息去掉
				}


			});

			//给用户名输入框绑定失焦事件，验证用户名是否可用
			$("#username").blur(function (){
				//获取用户名
				// 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
				// 所以this表示input标签，value表示input标签里的value属性。即用户输入的username值
				var username = this.value;

				//发起ajax请求
				$.getJSON("http://localhost:8081/book/userServlet","action=ajaxExistsUsername&username=" + username ,function (data){
					if(data.existsUsername){//传回来的json字符串中，键为"existsUsername"对应的值 为true
						$("span.errorMsg").text("用户名已存在！");
					}else{
						$("span.errorMsg").text("用户名可用");
					}
				});

			});

			//给验证码图片绑定单击事件
			$("#code_img").click(function (){
				// 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
				// 所以this表示img标签，src表示img标签里的src属性。
				// this.src表示验证码img标签的图片路径。它可读，可写
				// alert(this.src);
				this.src = "${basePath}kaptcha.jpg?d=" + new Date();
			});

		});
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册有时书城会员</h1>
								<span class="errorMsg">
<%--									<%=(request.getAttribute("errMsg") == null) ? "" : (request.getAttribute("errMsg"))%>--%>
									${requestScope.errMsg}
								</span> <!-- 一个出现在"注册会员"右边的红色文本span-->
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
<%--										   value="<%=(request.getAttribute("username") == null) ? "" : (request.getAttribute("username"))%>"--%>
										   value="${requestScope.username}"
										   autocomplete="off" tabindex="1" name="username" id="username" />
<!--									这里写了value值，加载页面后自动填充，不用每次都敲-->
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
<%--										   value="<%=(request.getAttribute("email") == null) ? "" : (request.getAttribute("email"))%>"--%>
										   value="${requestScope.email}"
										   autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" name="code" id="code" />
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 110px; height: 30px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>

		<%--静态包含 页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>

</body>
</html>