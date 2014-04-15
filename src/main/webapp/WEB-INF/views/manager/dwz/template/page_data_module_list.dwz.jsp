<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="pageContent">
	<div class="panelBar"></div>
	<style>
		.grid .gridTbody td div { line-height:100%; height:100%;}
	</style>
	<table class="table" width="100%" layoutH="80">
		<thead>
			<tr>
				<th><spring:message code="manager.template.module_id"/></th>
				<th><spring:message code="manager.template.module_name"/></th>
				<th><spring:message code="manager.template.module_keyword"/></th>
				<th><spring:message code="manager.template.module_status"/></th>
				<th><spring:message code="manager.template.operation"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="d" items="${list }" varStatus="i">
				<tr rel="${i.index }">
					<td>${d.id }</td>
					<td>${d.name }</td>
					<td>${d.keyword }</td>
					<td>${d.status }</td>
					<td>
					 <a class="button" href="${ctx }/manager/template/page_data_module_edit.r?pageDataId=${d.id}" target="navTab" rel="page_data_module_edit"><span><spring:message code="manager.button.edit"/></span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

