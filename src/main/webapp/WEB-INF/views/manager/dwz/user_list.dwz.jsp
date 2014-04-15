<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<form id="pagerForm" method="post" action="${ctx }/manager/user_list.r">
	<input type="hidden" name="id" value="${id}">
	<input type="hidden" name="email" value="${email}">
	<input type="hidden" name="pageNo" value="${page.pageNo}" />
	<input type="hidden" name="pageSize" value="${page.pageSize}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx }/manager/user_list.r" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					User ID : <input type="text" name="id" value="${id}"/>
				</td>
				<td>
					Email : <input type="text" name="email" value="${email}"/>
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
				<th>Id</th>
				<th>Email</th>
				<th>Ripple Address </th>
				<th>Balance</th>
				<th>Status</th>
				<th>Operation</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="d" items="${page.result }" varStatus="i">
				<tr rel="${i.index }">
					<td>${d.id }</td>
					<td>${d.email}</td>
					<td>${d.rippleAddress}</td>
					<td style="color: blue;">${d.balance}</td>
					<td>${d.status}</td>
					<td>
						<a class="button" href="${ctx}/manager/payment.r?id=${d.id}" target="dialog" rel="Payment" mask="true"><span>Payment</span></a>
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

