<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<form id="pagerForm" method="post" action="${ctx }/manager/withdraw_record_list.r">
	<input type="hidden" name="userId" value="${userId}">
	<input type="hidden" name="sn" value="${sn}">
	<input type="hidden" name="wdAccount" value="${wdAccount}" />
	<input type="hidden" name="wdStatus" value="${wdStatus}" />
	<input type="hidden" name="pageNo" value="${page.pageNo}" />
	<input type="hidden" name="pageSize" value="${page.pageSize}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx }/manager/withdraw_record_list.r" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					User ID : <input type="text" name="userId" value="${userId}"/>
				</td>
				<td>
					Withdraw SN : <input type="text" name="sn" value="${sn}"/>
				</td>
				<td>
					Withdraw Account : <input type="text" name="wdAccount" value="${wdAccount}"/>
				</td>
				<td>Withdraw Status : </td>
				<td>
					<select class="combox" name="wdStatus">
						<option value="">ALL</option>
						<c:forEach var="key" items="${withdrawStatusKeys }">
							<option value="${key }" <c:if test="${key eq wdStatus}">selected="selected"</c:if>>${withdrawStatus[key]}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button id="sub" type="submit"><spring:message code="manager.button.search" /></button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar"></div>
	<style>
		.grid .gridTbody td div { line-height:100%; height:100%;}
	</style>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="150"><spring:message code="manager.payment.no"/></th>
				<th><spring:message code="manager.payment.user_id"/></th>
				<th><spring:message code="manager.payment.user_email"/></th>
				<th><spring:message code="manager.payment.withdraw_type"/></th>
				<th><spring:message code="manager.payment.withdraw_amount"/></th>
				<th><spring:message code="manager.payment.poundage"/></th>
				<th><spring:message code="manager.payment.should_withdraw_amount"/></th>
				<th><spring:message code="manager.payment.actual_withdraw_amount"/></th>
				<th><spring:message code="manager.payment.status"/></th>
				<th><spring:message code="manager.payment.create_time"/></th>
				<th><spring:message code="manager.payment.operation"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="d" items="${page.result }" varStatus="i">
				<tr rel="${i.index }">
					<td>${d.withdrawRecord.sn }</td>
					<td>${d.user.id}</td>
					<td>${d.user.email}</td>
					<td>${d.sysPayment.name}</td>
					<td>${d.withdrawRecord.money}</td>
					<td>${d.withdrawRecord.fee}</td>
					<td style="color: red;">${d.withdrawRecord.total}</td>
					<td style="color: blue;">${d.withdrawRecord.paid}</td>
					<td style="color: blue;">${withdrawStatus[d.withdrawRecord.wdStatus]}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${d.withdrawRecord.createTime }"/></td>
					<td>
						<a class="button" ><span>Button</span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>Show</span>
			<select class="combox" name="pageSize" onchange="navTabPageBreak({pageSize:this.value})">
				<option value="15">15</option>
				<option value="20">20</option>
				<option value="25">25</option>
			</select>
			<span>Item，Total ${page.totalCount} Items</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.pageNo}"></div>

	</div>
</div>

