<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" type="text/css" href="${ctx }/static/metronic/media/css/select2_metro.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/metronic/media/css/chosen.css" />
	<!-- END PAGE LEVEL STYLES -->
</head>
	<script type="text/javascript" src="${ctx }/static/metronic/media/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/metronic/media/js/additional-methods.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/metronic/media/js/select2.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/metronic/media/js/chosen.jquery.min.js"></script>
	<script src="${ctx }/static/metronic/media/js/manager-payment-validation.js"></script> 	
</html>