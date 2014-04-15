<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<script type="text/javascript">
	function submit() {
		$('#forgotPasswordForm').submit();
	}
</script>
<div style="font-size:20px;">
	${hongxu_password_forgot.vo.title }
	<hr style="height:1px;"/>
	${hongxu_password_forgot.vo.content }
</div>
<form id="forgotPasswordForm" action="${ctx }/user/password_forgot_post.html" method="post" data-ajax="false">
<input type="hidden" name="userId" value="${userId }" />
<div align="left">
	<div class="input-group" style="margin-top: 20px;">
	  <input type="text" class="form-control" name="username" id="username" value="" placeholder='${hongxu_password_forgot.vo.emailPlaceHolder }'>
	</div>
	<div style="margin-top:15px; padding-left:30px; padding-bottom:30px;">
		<button type="button" class="btn btn-default" onclick="submit()">${hongxu_password_forgot.vo.submitButtonLabel }</button>
	</div>
</div>
</form>