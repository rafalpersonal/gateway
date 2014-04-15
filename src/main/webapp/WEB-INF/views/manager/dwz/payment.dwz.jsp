<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<style type="text/css">
	.select .required { width:200px; }
	textarea
	{
	width:100%;
	height:100%;
	}
</style>

<div class="pageContent">
	<form method="post" action="${ctx }/manager/payment.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="56">
			<div>
				User Email : ${user.email }
				</div>
				<br/>
				<div>
				User Ripple Address : ${user.rippleAddress }
				<input type="hidden" name="userId" value="${user.id }">
				<input type="hidden" name="dstAddress" value="${user.rippleAddress }">
				</div>
				<dl>
					<dt>Currency : </dt>
					<dd>
					<select class="combox" name="currency">
						<c:forEach var="currency" items="${currencylist }">
							<option value="${currency }" >${currency}</option>
						</c:forEach>
					</select>
					</dd>
				</dl>
				<dl>
					<dt>Amount : </dt>
					<dd><input class="required number" name="value" type="text" style="width: 150px;"/></dd>
				</dl>

	</div>
	
	<div class="formBar">
			<ul>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Payment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button></div></div>
			</ul>
		</div>
	</form>
	
</div>

