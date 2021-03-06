<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/commons/taglibs.jsp" %><%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %><!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title><spring:message code="manager.name" /> v0.1</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<%@include file="/commons/metronic_css.jsp" %>
</head><body class="page-header-fixed">	<jsp:include page="/manager/header.r?key=blank"/>	<!-- BEGIN CONTAINER -->	<div class="page-container row-fluid">		<!-- BEGIN SIDEBAR -->		<div class="page-sidebar nav-collapse collapse">			<jsp:include page="/manager/menu.r?key=blank" />		</div>		<!-- END SIDEBAR -->		<!-- BEGIN PAGE CONTENT-->		<div class="page-content">			<!-- BEGIN PAGE CONTENT-->			<div class="portlet box red">				<div class="portlet-title">					<div class="caption">						<i class="icon-cogs"></i>System Text Info					</div>					<div class="tools">						<a href="javascript:;" class="collapse"></a>					</div>				</div>				<div class="portlet-body">					<div class="clearfix">						<div class="btn-group"></div>					</div>					<table class="table table-hover">						<thead>							<tr>								<th>Text Name</th>								<th><spring:message code="manager.template.operation" /></th>							</tr>						</thead>						<tbody>							<c:forEach items="${keys }" var="key" varStatus="i">								<tr rel="${i.index }">									<td>${map[key].txtTitle }</td>									<td><a class="btn red" id="txt${i.index }"										href="${ctx }/manager/txt_edit.r?txtKey=${key}"><span>Edit</span></a>									</td>								</tr>							</c:forEach>						</tbody>					</table>				</div>			</div>		</div>	</div>		<!-- BEGIN FOOTER -->	<jsp:include page="/manager/footer.r" />	<!-- END FOOTER -->	<%@include file="/commons/metronic_js.jsp"%>	<script>		jQuery(document).ready(function() {			App.init();		});	</script>		</body>
</html>