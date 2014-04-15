<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="manager.name" /> v0.1</title>
<link href="${ctx }/static/dwz1.4.6/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo"></h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#"><spring:message code="manager.login.aboutus"/></a></li>
					</ul>
				</div>
				<h2 class="login_title"><spring:message code="manager.login.title"/></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="${ctx }/manager/j_spring_security_check"  method="post">
					<p style="color: red;">
						${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
					</p>
					<p>
						<label><spring:message code="manager.login.account"/></label>
						<input type="text" name="j_username" size="15" class="login_input" value=""/>
					</p>
					<p>
						<label><spring:message code="manager.login.password"/></label>
						<input type="password" name="j_password" size="16" class="login_input"  value=""/>
					</p>
					<!-- 
					<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p>
					 -->
					<div class="buttonActive"><div class="buttonContent"><button type="submit"><spring:message code="manager.button.login" /></button></div></div>
					<%--
					<p>扫描以下二维码直接下载回收客户端</p>
					<img src="${ctx }/static/images/download_ewaste.png" alt="" width="150"/>
					 --%>
				</form>
			</div>
			
			<div class="login_main">
			<!--  
				<ul class="helpList">
					<li><a href="#">技术支持：鸿旭图码</a></li>
				</ul>
				-->
				<div class="login_inner">
					<p><spring:message code="manager.name" /> v0.1 ></p>
				</div>
			</div>
			
		</div>
		<div id="login_footer">
			Copyright &copy; 2014 HXTM Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>