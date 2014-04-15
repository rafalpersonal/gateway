<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="pageContent">

		<form method="post" action="${ctx }/manager/template/page_data_page_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="pageDataId" value="${d.id }">
		<div class="pageFormContent  nowrap" layoutH="56">
			<c:forEach items="${keys }" var="key">
				<c:choose>
					<c:when test="${key eq 'sectionList' }">
							<c:if test="${not empty map[key] }">
								<c:forEach items="${map[key] }" var="section" varStatus="i">
									<dl>
										<dt><spring:message code="manager.template.key.sectionTitle"/> ${i.index+1 } : </dt>
										<dd>
										<textarea class="editor" id="${key }.title" name="${key }.title" rows="1" cols="80" tools="Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,|,Cut,Copy,Paste,Pastetext,|,Source,Fullscreen,About">${ section.title}</textarea>
										</dd>
									</dl>
									<dl>
										<dt><spring:message code="manager.template.key.sectionContent"/> ${i.index+1 } : </dt>
										<dd><textarea class="editor" id="${key }.content" name="${key }.content" rows="16" cols="80" tools="mfull">${section.content}</textarea></dd>
									</dl>
								</c:forEach>
							</c:if>
					</c:when>
					<c:otherwise>
						<div class="unit">
							<label><spring:message code="manager.template.key.${key }"/> : </label>
							<!-- 
							<input type="text" name="" class="required" value="${map[key] }" style="width: 300px;"/>
							 -->
							 <textarea class="editor" id="${key }" name="${key }" rows="10" cols="80" tools="Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,|,Cut,Copy,Paste,Pastetext,|,Source,Fullscreen,About">${map[key] }</textarea>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<div class="divider"></div>
			
		</div>
		
		
		
		<div class="formBar">
			<ul>
				<li>
				    <div class="buttonActive"><div class="buttonContent"><button type="submit"><spring:message code="manager.button.save"/></button></div></div>
					<div class="button"><div class="buttonContent"><button type="button" class="close"><spring:message code="manager.button.cancel"/></button></div></div>
				</li>
			</ul>
		</div>
		
		</form>
	
</div>
