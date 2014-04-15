<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hongxu.ripple.gateway.style.WebsiteConfig" %>
<%@ include file="/commons/taglibs.jsp" %>
<style type="text/css">
	.input-group { margin-top:15px; width:480px;}
	.input-group-addon { width:160px; text-align:right; }
</style>
<script type="text/javascript">
	function register() {
		
		var rippleAddress = $('#rippleAddress').val();
		var email = $('#email').val();
		var password = $('#password').val();
		var password2 = $('#password2').val();
		if(password != password2) {
			$('#msg').html('corform password is not match, please confirm it again !');
			return;
		}
		
		$.ajax({
			type: "POST",
	       	dataType: "json",
	       	async:false,
	       	url: "${ctx }/user/register_post.html",
	       	data: {
	       		rippleAddress: rippleAddress,
	       		email: email,
	       		password: password
				},
      	 	error:function(msg){
	 				alert(msg);
 			},
	       	success: function(data){
	       		if(!data['isOk']) {
	       			alert('register failed, ' + data['message']);
	       			return;
	       		}
	       		
	       		window.location.href = '${ctx}/<%=WebsiteConfig.PageUrl.USER_REGISTER_FINISHED_URL%>';
			}
 		});
	}
</script>
<div style="font-size:20px;">
	${hongxu_register.vo.moduleTitle }
	<hr style="height:1px;"/>
</div>
<form id="registerForm" action="${ctx }/user/register_post.html" method="post">
<div>
	<div id="msg" style="text-align:center; color:red; ">
		
	</div>
	<div style="width:480px; margin-top:15px; text-align:right;">
		<a href="https://ripple.com//register" target="_blank">create Ripple Wallet</a>
	</div>
	<div class="input-group" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_register.vo.rippleAddressTitle }</span>
	  	<input type="text" class="form-control" name="rippleAddress" id="rippleAddress">
	</div>
	<div class="input-group" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_register.vo.emailTitle }</span>
	  	<input type="text" class="form-control" name="email" id="email">
	</div>
	<div class="input-group" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_register.vo.passwordTitle }</span>
	  	<input type="password" class="form-control" name="password" id="password">
	</div>
	<div class="input-group" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_register.vo.confirmPasswordTitle }</span>
	  	<input type="password" class="form-control" name="password2" id="password2">
	</div>
	<div class="input-group" style="margin-top: 20px;">
		<input id="agreeBtn" data-mini="true" type="checkbox" />
		<label for="agreeBtn" style="border-style:none;" >
			<a id="serviceTerm" href="${ctx }/legal/legal_terms.html" data-ajax="false" target="_blank">
				${hongxu_register.vo.agreeCheckBoxTitle } ${hongxu_register.org.orgName }
				${hongxu_register.vo.termsOfServiceTitle }
			</a>
		</label>
	</div>
   	<div style="margin-top:15px; margin-bottom:60px;">
		<button type="button" class="btn btn-default" onclick="register()">${hongxu_register.vo.submitTitle }</button>
	</div>
</div>
</form>