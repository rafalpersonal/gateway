<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="pageContent">
	<div class="panelBar">
	<ul class="toolBar">
			<li><a class="add" href="${ctx}/manager/user_extend_field_cfg_add.r" target="navTab"><span>Add User Extend Field</span></a></li>
		</ul>
	</div>
	<style>
		.grid .gridTbody td div { line-height:100%; height:100%;}
	</style>
	<table class="table" width="100%" layoutH="80">
		<thead>
			<tr>
				<th><spring:message code="manager.userextendfield.field_name"/></th>
				<th><spring:message code="manager.userextendfield.field_input_name"/></th>
				<th><spring:message code="manager.userextendfield.field_input_type"/></th>
				<th><spring:message code="manager.userextendfield.display_order"/></th>
				<th><spring:message code="manager.userextendfield.is_need"/></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="p" items="${list }" varStatus="i">
				<tr rel="${i.index }">
					<td>${p.fieldName }</td>
					<td>${p.fieldInputName }</td>
					<td>${p.fieldInputType }</td>
					<td>${p.displayOrder }</td>
					<td>${p.isNeed }</td>
					<td>
					 <a class="button" href="${ctx}/manager/user_extend_field_cfg_delete.r?id=${p.id}" target="ajaxTodo" title="Sure to delete?"><span><spring:message code="manager.button.delete"/></span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

