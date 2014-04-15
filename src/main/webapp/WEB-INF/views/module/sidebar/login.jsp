<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hongxu.ripple.gateway.style.WebsiteConfig" %>
<%@ include file="/commons/taglibs.jsp" %>
<script type="text/javascript">
	function login() {
		
		var username = $('#username').val();
		var password = $('#password').val();
		$.ajax({
			type: "POST",
	       	dataType: "json",
	       	async:false,
	       	url: "${ctx }/user/login_post.html",
	       	data: {
	       		username: username,
	       		password: password
				},
      	 	error:function(msg){
	 				alert(msg);
 			},
	       	success: function(data){
	       		if(!data['isOk']) {
	       			alert('failed');
	       			return;
	       		}
	       		
	       		window.location.href = '${ctx}/<%=WebsiteConfig.PageUrl.USER_CENTER_URL%>';
			}
 		});
	}
</script>
<div id="loginForm">
<div class="sidebar-module sidebar-module-inset">
	<div class="input-group" style="width:95%; margin-top: 20px;">
	  <input type="text" class="form-control" name="username" id="username" placeholder='${hongxu_login.vo.usernamePlaceHolder }'>
	</div>
	<div class="input-group" style="width:95%; margin-top: 20px;">
	  <input type="password" class="form-control" id="password" name="password" placeholder='${hongxu_login.vo.passwordPlaceHolder }' >
	</div>
	<div style="margin-top:15px;">
		<a data-ajax="false" href="${ctx }/<%=WebsiteConfig.PageUrl.USER_ERGISTER_URL%>">${hongxu_login.vo.registerLink }</a>&nbsp;|&nbsp;
		<a data-ajax="false" href="${ctx }/<%=WebsiteConfig.PageUrl.PASSWORD_FORGOT_URL%>">${hongxu_login.vo.forgotPasswordLink }</a>
	</div>
	<div style="margin-top:15px; padding-bottom:30px; text-align:center;">
		<button type="button" class="btn btn-default" onclick="login()">${hongxu_login.vo.submitTitle }</button>
	</div>
</div>
</div>