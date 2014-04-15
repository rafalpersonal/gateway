<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="pageContent">

		<form method="post" action="${ctx }/manager/template/page_data_module_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="pageDataId" value="${d.id }">
		<div class="pageFormContent  nowrap" layoutH="56">
			<c:forEach items="${keys }" var="key">
				<c:choose>
					<c:when test="${key eq 'itemList' }">
						<div class="divider"></div>
						<div>
							<dl>
								<dt><spring:message code="manager.template.key.${key }"/> : </dt>
								<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</dd>
							</dl>
							<c:if test="${not empty map[key] }">
								<c:forEach items="${map[key] }" var="item" varStatus="i">
									<fieldset style="float: left;">
									<legend>链接 ${i.index+1 }</legend>
										<dl>
											<dt><spring:message code="manager.template.key.name"/> : </dt>
											<dd><input id="${key }.name${i.index}" type="text" name="${key }_name" class="required" value="${ item.name}" style="width: 150px;"/></dd>
										</dl>
										<dl>
											<dt><spring:message code="manager.template.key.url"/> : </dt>
											<dd><input id="${key }.url${i.index}"  type="text" name="${key }_url" class="required" value="${item.url}" style="width: 300px;"/></dd>
										</dl>
									</fieldset>
								</c:forEach>
							</c:if>
						</div>
					</c:when>
					
					<c:when test="${key eq 'otherItems' }">
						<div class="divider"></div>
						<div id="otherItems">
							<dl>
								<dt><spring:message code="manager.template.key.${key }"/> : </dt>
								<dd><input  type="button" id="addTextImput" value="Add"/></dd>
							</dl>
							<c:if test="${not empty map[key] }">
								<c:forEach items="${map[key] }" var="item" varStatus="i">
									<fieldset style="float: left;">
									<legend><spring:message code="manager.template.key.otherItem"/></legend>
										<dl>
											<dt><spring:message code="manager.template.key.name"/> : </dt>
											<dd><input id="${key }.name${i.index}" type="text" name="${key }_name" class="required" value="${ item.name}" style="width: 150px;"/></dd>
										</dl>
										<dl>
											<dt><spring:message code="manager.template.key.url"/> : </dt>
											<dd><input id="${key }.url${i.index}" type="text" name="${key }_url" class="required" value="${item.url}" style="width: 300px;"/></dd>
										</dl>
										<input type="button" id="del-text" value="Delete">
									</fieldset>
								</c:forEach>
							</c:if>
							
						</div>
						
						<script type="text/javascript">
							$(function(){
								var otherItemsCount = ${(not empty map[key])?fn:length(map[key]):1};
								var i = otherItemsCount+1;
								//alert(i);
								$('#addTextImput').click(function(){
									if(i <= 10) {
										$('#otherItems').append(
												'<fieldset style="float: left;">'+
												'<legend><spring:message code="manager.template.key.otherItem"/> </legend>'+
												'<dl>'+
													'<dt><spring:message code="manager.template.key.name"/> : </dt>'+
													'<dd><input type="text" name="otherItems_name" class="required" value="" style="width: 150px;"/></dd>'+
												'</dl>'+
												'<dl>'+
													'<dt><spring:message code="manager.template.key.url"/> : </dt>'+
													'<dd><input type="text" name="otherItems_url" class="required" value="" style="width: 300px;"/></dd>'+
												'</dl>'+
												'<input type="button" id="del-text" value="Delete">'+
												'</fieldset>'
												);
										i++;
									} else {alert("Other Item list most 10.");}
							 	});
								$('.del-text').live('click',function(){
									$(this).parent().remove();
									i--;
								});
								$('#del-text').live('click',function(){
									$(this).parent().remove();
									i--;
								});
							});
						</script>
						
					</c:when>
					<c:when test="${key eq 'logo' }">
						<div class="unit">
							<dl>
								<dt><spring:message code="manager.template.key.${key }"/> : </dt>
								<dd><input type="text" name="${key }" value="${map[key] }" style="width: 300px;"/></dd>
							</dl>
						</div>
					</c:when>
					<c:otherwise>
						<div class="unit">
						 	<dl>
								<dt><spring:message code="manager.template.key.${key }"/> : </dt>
								<dd><input type="text" name="${key }" class="required" value="${map[key] }" style="width: 300px;"/></dd>
							</dl>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
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
