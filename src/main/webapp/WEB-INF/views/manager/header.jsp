<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">

	<!-- BEGIN TOP NAVIGATION BAR -->

	<div class="navbar-inner">

		<div class="container-fluid">

			<!-- BEGIN LOGO -->

			<a class="brand" href="${ctx }/manager/index.r"> <!--
			<img src="${ctx }/static/metronic/media/image/logo.png" alt="logo"/>
			  --> RIPPLE-RMB

			</a>

			<!-- END LOGO -->

			<!-- BEGIN RESPONSIVE MENU TOGGLER -->

			<a href="javascript:;" class="btn-navbar collapsed"
				data-toggle="collapse" data-target=".nav-collapse"> <img
				src="${ctx }/static/metronic/media/image/menu-toggler.png" alt="" />

			</a>

			<!-- END RESPONSIVE MENU TOGGLER -->

			<!-- BEGIN TOP NAVIGATION MENU -->

			<ul class="nav pull-right">

				<!-- BEGIN NOTIFICATION DROPDOWN -->

				<li class="dropdown" id="header_notification_bar"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-warning-sign"></i> <span class="badge"></span>

				</a></li>

				<!-- END NOTIFICATION DROPDOWN -->

				<!-- BEGIN INBOX DROPDOWN -->

				<li class="dropdown" id="header_inbox_bar"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-envelope"></i> <span class="badge"></span>

				</a></li>

				<!-- END INBOX DROPDOWN -->

				<!-- BEGIN TODO DROPDOWN -->

				<li class="dropdown" id="header_task_bar"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-tasks"></i> <span class="badge"></span>

				</a></li>

				<!-- END TODO DROPDOWN -->

				<!-- BEGIN USER LOGIN DROPDOWN -->

				<li class="dropdown user"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <img alt=""
						src="${ctx }/static/metronic/media/image/avatar1_small.jpg" /> <span
						class="username"><sec:authentication
								property="principal.userName" /></span> <i class="icon-angle-down"></i>

				</a>

					<ul class="dropdown-menu">

						<li><a href="${ctx }/manager/logout.r"><i
								class="icon-key"></i> Log Out</a></li>

					</ul></li>

				<!-- END USER LOGIN DROPDOWN -->

			</ul>

			<!-- END TOP NAVIGATION MENU -->

		</div>

	</div>

	<!-- END TOP NAVIGATION BAR -->

</div>

<!-- END HEADER -->