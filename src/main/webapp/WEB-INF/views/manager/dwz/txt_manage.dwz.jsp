<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="pageContent">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span><spring:message code="manager.txt.help" /></span></a></li>
					<c:forEach items="${keys }" var="key" varStatus="i">
						<li><a id="txt${i.index }" href="${ctx }/manager/txt_edit.r?txtKey=${key}" class="j-ajax"><span>${map[key].txtTitle }</span></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
				<div class="alertInfo">
					<h1>Txt manage help</h1>
					<p></p>
					<p>网站静态文本编辑管理.</p>
					<p></p>
					<p>可维护网站前台的各种静态文本.</p>
				</div>
			</div>
			<c:forEach items="${keys }" var="key" varStatus="i">
				<div></div>
			</c:forEach>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
	<p>&nbsp;</p>
	
</div>

