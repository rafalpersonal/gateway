<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style type="text/css">
	.select .required { width:200px; }
	textarea
	{
	width:100%;
	height:100%;
	}
</style>

<div class="pageContent">
	<form method="post" action="${ctx }/manager/user_extend_field_cfg_add.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="56">
				<dl>
					<dt><spring:message code="manager.userextendfield.field_name"/> : </dt>
					<dd><input class="required" name="fieldName" type="text" value="" style="width: 300px;"/></dd>
				</dl>
				<dl>
					<dt><spring:message code="manager.userextendfield.field_input_name"/> : </dt>
					<dd><input class="required lettersonly" name="fieldInputName" type="text" value="" style="width: 300px;"/></dd>
				</dl>
				
				<dl>
					<dt><spring:message code="manager.userextendfield.field_input_type"/>：</dt>
					<dd>
						<select class="combox" name="fieldInputType">
							<option value="text" >text</option>
							<option value="file" >file</option>
					</select>
					</dd>
				</dl>
				
				<dl>
					<dt><spring:message code="manager.userextendfield.is_need"/>：</dt>
					<dd>
						<select class="combox" name="isNeed">
							<option value="true" >true</option>
							<option value="false" >false</option>
					</select>
					</dd>
				</dl>
				
				<dl>
					<dt><spring:message code="manager.userextendfield.display_order"/>：</dt>
					<dd>
						<select class="combox" name="displayOrder">
							<c:forEach var="i" begin="0" end="10" step="1">
								<option value="${i }" >${i }</option>
							</c:forEach>
						</select>
					</dd>
				</dl>

	</div>
	
	<div class="formBar">
			<ul>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="manager.button.add"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button></div></div>
			</ul>
		</div>
	</form>
	
</div>

