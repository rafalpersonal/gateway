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
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${ctx }/static/metronic/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx }/static/metronic/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx }/static/metronic/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx }/static/metronic/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx }/static/metronic/media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx }/static/metronic/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx }/static/metronic/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${ctx }/static/metronic/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<link rel="shortcut icon" href="${ctx }/static/metronic/media/image/favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner">
			<div class="container-fluid">
				<!-- BEGIN LOGO -->
				<a class="brand" href="${ctx }/manager/index.r">
				<!--				<img src="${ctx }/static/metronic/media/image/logo.png" alt="logo"/>				  -->				  RIPPLE-RMB
				</a>
				<!-- END LOGO -->
				<!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
				<img src="${ctx }/static/metronic/media/image/menu-toggler.png" alt="" />
				</a>          
				<!-- END RESPONSIVE MENU TOGGLER -->            
				<!-- BEGIN TOP NAVIGATION MENU -->              
				<ul class="nav pull-right">
					<!-- BEGIN NOTIFICATION DROPDOWN -->   
					<li class="dropdown" id="header_notification_bar">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-warning-sign"></i>
						<span class="badge"></span>
						</a>
					</li>
					<!-- END NOTIFICATION DROPDOWN -->
					<!-- BEGIN INBOX DROPDOWN -->
					<li class="dropdown" id="header_inbox_bar">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-envelope"></i>
						<span class="badge"></span>
						</a>
					</li>
					<!-- END INBOX DROPDOWN -->
					<!-- BEGIN TODO DROPDOWN -->
					<li class="dropdown" id="header_task_bar">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-tasks"></i>
						<span class="badge"></span>
						</a>
					</li>
					<!-- END TODO DROPDOWN -->
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img alt="" src="${ctx }/static/metronic/media/image/avatar1_small.jpg" />
						<span class="username"><sec:authentication property="principal.userName"/></span>
						<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a href="${ctx }/manager/logout.r"><i class="icon-key"></i> Log Out</a></li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
				<!-- END TOP NAVIGATION MENU --> 
			</div>
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->   
	<div class="page-container row-fluid">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">			<jsp:include page="/manager/menu.r?key=blank"/>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div id="portlet-config" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3>portlet Settings</h3>
				</div>
				<div class="modal-body">
					<p>Here will be a configuration form</p>
				</div>
			</div>
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							Blank Page <small>blank page</small>
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="${ctx }/manager/index.r">Home</a> 
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="#">Layouts</a>
								<i class="icon-angle-right"></i>
							</li>
							<li><a href="#">Blank Page</a></li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						Blank page content goes here
					</div>
				</div>
				<!-- END PAGE CONTENT-->
			</div>
			<!-- END PAGE CONTAINER--> 
		</div>
		<!-- END PAGE -->    
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner">
			Copyright &copy; 2014 HXTM Inc. All Rights Reserved.
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
		</div>
	</div>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${ctx }/static/metronic/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${ctx }/static/metronic/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${ctx }/static/metronic/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${ctx }/static/metronic/media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="${ctx }/static/metronic/media/js/excanvas.min.js"></script>
	<script src="${ctx }/static/metronic/media/js/respond.min.js"></script>  
	<![endif]-->   
	<script src="${ctx }/static/metronic/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${ctx }/static/metronic/media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${ctx }/static/metronic/media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${ctx }/static/metronic/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<script src="${ctx }/static/metronic/media/js/app.js"></script>      
	<script>
		jQuery(document).ready(function() {    
		   App.init();
		});
	</script>
	<!-- END JAVASCRIPTS -->
<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
<!-- END BODY -->
</html>