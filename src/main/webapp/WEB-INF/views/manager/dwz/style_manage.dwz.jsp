<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="pageContent">
	<c:if test="${canEdit eq 'true' }">
		<form method="post" action="${ctx }/manager/style_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="template" value="${template }" />
		<div class="pageFormContent nowrap" layoutH="20">
					<dl>
						<dt>Style Content : </dt>
						<dd><textarea id="styleContent" name="styleContent" rows="20" cols="128" >${styleContent }</textarea></dd>
					</dl>
					
					<dl>
						<dt>&nbsp;</dt>
						<dd>
							<div class="buttonActive"><div class="buttonContent"><button type="submit"><spring:message code="manager.button.save"/></button></div></div>
							<a class="button" style="margin-left: 25px;" href="${ctx}/manager/style_restore_original.r?template=${template}" target="ajaxTodo" title="Confirm restore original css?"><span>Restore original css</span></a>
						</dd>
					</dl>
			
		</div>
		</form>
	</c:if>
	
	<c:if test="${canEdit eq 'false' }">
	  css file no exist.
	</c:if>
	
</div>

