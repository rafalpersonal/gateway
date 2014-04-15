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
				<th><spring:message code="manager.template.template_id"/></th>
				<th><spring:message code="manager.template.template_name"/></th>
				<th><spring:message code="manager.template.template_status"/></th>
				<th><spring:message code="manager.template.operation"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="t" items="${list }" varStatus="i">
				<tr rel="${i.index }">
					<td>${t.id }</td>
					<td>${t.name }</td>
					<td>${t.status }</td>
					<td>
					<%--
					小图标说明
					btnAdd      一个小加号
					btnDel       一个小叉子  
					btnInfo      一个小本子
					btnAssign  两个小人儿
					btnView    一个眼珠子
					btnEdit     一个对勾
					btnSelect  一根破逼
					btnLook    一个放大镜
					btnAttach  一个曲别针
					themes\default\images\button\imageX.gif
					 --%>
					 <a class="button" href="${ctx}/manager/template/page_data_module_list.r?templateId=${t.id}" target="navTab" rel="page_data_module_list"><span><spring:message code="manager.template.module_manage"/></span></a>
					 <a class="button" href="${ctx}/manager/template/page_data_page_list.r?templateId=${t.id}" target="navTab"><span><spring:message code="manager.template.page_manage"/></span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

