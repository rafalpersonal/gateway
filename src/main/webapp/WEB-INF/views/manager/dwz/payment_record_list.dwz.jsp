<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<form id="pagerForm" method="post" action="${ctx }/manager/payment_record_list.r">
	<input type="hidden" name="userId" value="${userId}">
	<input type="hidden" name="sn" value="${sn}">
	<input type="hidden" name="pmtStatus" value="${pmtStatus}" />
	<input type="hidden" name="pageNo" value="${page.pageNo}" />
	<input type="hidden" name="pageSize" value="${page.pageSize}" />
</form>

<script type="text/javascript">
	function sendIOU(paymentId) {
		$.ajax({
			type: "POST",
	       	dataType: "json",
	       	async:false,
	       	url: "${ctx }/rest/v1.0/ripple/sendIOU",
	       	data: {
	       		paymentId: paymentId
			},
      	 	error:function(msg){
	 				alert(msg);
 			},
	       	success: function(data){
	       		if(!data['isOk']) {
	       			alert('failed');
	       			return;
	       		}
	       		
	       		alert('success');
			}
 		});
	}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx }/manager/payment_record_list.r" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					User ID : <input type="text" name="userId" value="${userId}"/>
				</td>
				<td>
					Payment SN : <input type="text" name="sn" value="${sn}"/>
				</td>
				<td>Payment Status : </td>
				<td>
					<select class="combox" name="pmtStatus">
						<option value="">ALL</option>
						<c:forEach var="key" items="${paymentStatusKeys }">
							<option value="${key }" <c:if test="${key eq pmtStatus}">selected="selected"</c:if>>${paymentStatus[key]}</option>
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
				<th><spring:message code="manager.payment.payment_type"/></th>
				<th><spring:message code="manager.payment.payment_amount"/></th>
				<th><spring:message code="manager.payment.poundage"/></th>
				<th><spring:message code="manager.payment.payable_amount"/></th>
				<th><spring:message code="manager.payment.actual_amount"/></th>
				<th><spring:message code="manager.payment.status"/></th>
				<th><spring:message code="manager.payment.create_time"/></th>
				<th><spring:message code="manager.payment.operation"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="d" items="${page.result }" varStatus="i">
				<tr rel="${i.index }">
					<td>${d.paymentRecord.sn }</td>
					<td>${d.user.id}</td>
					<td>${d.user.email}</td>
					<td>${d.sysPayment.name}</td>
					<td>${d.paymentRecord.money}</td>
					<td>${d.paymentRecord.fee}</td>
					<td style="color: red;">${d.paymentRecord.total}</td>
					<td style="color: blue;">${d.paymentRecord.paid}</td>
					<td style="color: blue;">${paymentStatus[d.paymentRecord.pmtStatus]}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${d.paymentRecord.createTime }"/></td>
					<td>
						<a class="button" href="${ctx}/manager/recharge.r?id=${d.paymentRecord.id}" target="dialog" rel="Recharge" mask="true"><span>Recharge</span></a>
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

