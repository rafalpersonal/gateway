<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="pageContent">
	<form method="post" action="${ctx }/manager/txt_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="txtKey" value="${po.txtKey }" />
	<div class="pageFormContent nowrap" layoutH="20">
				<dl>
					<dt><spring:message code="manager.txt.title" /> : </dt>
					<dd><input class="required" name="txtTitle" type="text" value="${po.txtTitle }" style="width: 300px;"/></dd>
				</dl>
				<dl>
					<dt><spring:message code="manager.txt.content" /> : </dt>
					<dd><textarea class="editor" id="txtContent" name="txtContent" rows="20" cols="128" tools="mfull">${po.txtContent }</textarea></dd>
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

