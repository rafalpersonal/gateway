<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<style type="text/css">
	.input-group-addon { width:120px; text-align:right; }
	.input-group { width: 500px; }
	.round-corner { border-radius: 4px; }
	.form-label { width: 180px; }
</style>
<script type="text/javascript">

	
</script>
<div class="col-sm-12 col-md-8 col-md-offset-1">
	<form action="${ctx }/user_center/wallet_binding_post.html" method="post">
	<input type="hidden" name="userId" id="userId" value="${hongxu_portlet_wallet_binding.user.id }" />
	<div align="center" class="round-corner" style="background:#EEE; padding:10px 15px 10px 15px;" >
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon form-label">${hongxu_portlet_wallet_binding.vo.walletNameLabel }</span>
		  	<input type="text" class="form-control" name="walletName" id="walletName" >
		</div>
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon form-label">${hongxu_portlet_wallet_binding.vo.walletAddressLabel }</span>
		  	<input type="text" class="form-control" name="walletAddress" id="walletAddress" >
		</div>
		<div style="margin-top:25px; margin-bottom:30px;">
			<button type="submit" class="btn btn-default" onclick="deposit()">
				${hongxu_portlet_wallet_binding.vo.bindingButtonLabel }
			</button>
		</div>
	</div>
	</form>
</div>
