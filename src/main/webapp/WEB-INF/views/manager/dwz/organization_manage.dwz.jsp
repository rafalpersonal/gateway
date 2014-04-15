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
	<form method="post" action="${ctx }/manager/organization_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="id" value="${d.id }">
	<div class="pageFormContent nowrap" layoutH="56">
				<dl>
					<dt><spring:message code="manager.organization.org_name" /> : </dt>
					<dd><input class="required" name="orgName" type="text" value="${d.orgName }" style="width: 300px;"/></dd>
				</dl>
				<dl>
					<dt><spring:message code="manager.txt.content" /> : </dt>
					<dd><textarea class="editor" id="orgContent" name="orgContent" rows="20" cols="100" tools="mfull">${d.orgContent }</textarea></dd>
				</dl>
				<dl>
					<dt><spring:message code="manager.organization.template" /> : </dt>
					<dd>
					<select name="tempateId" class="required combox">
						<c:forEach var="t" items="${tlist }">
							<option value="${t.id }" ${(t.id eq d.tempateId) ? 'selected=selected'  : '' }>${t.name }</option>
						</c:forEach>
					</select>
					</dd>
				</dl>
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<div class="buttonActive"><div class="buttonContent"><button type="submit"><spring:message code="manager.button.save"/></button></div></div>
					</dd>
				</dl>
	
	</div>
	</form>
	
</div>

