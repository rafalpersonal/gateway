<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<script type="text/javascript">
	function resetPassword() {
		$('#resetPasswordForm').submit();
	}
</script>
<div style="font-size:20px;">
	${hongxu_password_reset.vo.title }
	<hr style="height:1px;"/>
	${hongxu_password_reset.vo.content }
</div>
<form id="resetPasswordForm" action="${ctx }/user/password_reset_post.html" method="post" data-ajax="false">
<input type="hidden" name="email" value="${hongxu_password_reset.email }" />
<input type="hidden" name="verifiedCode" value="${hongxu_password_reset.verifiedCode }" />
<div align="left">
	<div class="input-group" style="margin-top: 20px;">
	  <input type="text" class="form-control" name="newPassword" id="newPassword" value="" placeholder='${hongxu_password_reset.vo.passwordPlaceHolder }'>
	</div>
	<div class="input-group" style="margin-top: 20px;">
	  <input type="text" class="form-control" name="verifiedPassword" id="verifiedPassword" value="" placeholder='${hongxu_password_reset.vo.verifiedPasswordPlaceHolder }'>
	</div>
   	<div style="margin-top:15px; padding-left:30px; padding-bottom:30px;">
		<button type="button" class="btn btn-default" onclick="resetPassword()">${hongxu_password_reset.vo.resetPasswordButtonLabel }</button>
	</div>
</div>
</form>