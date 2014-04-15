<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

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

<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">

	<jsp:include page="/manager/header.r?key=blank"/>

	<!-- BEGIN CONTAINER -->

	<div class="page-container row-fluid">

		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">
			<jsp:include page="/manager/menu.r?key=blank" />
		</div>
		<!-- END SIDEBAR -->

		<!-- BEGIN PAGE CONTENT-->
		<div class="page-content">
			<!-- BEGIN VALIDATION STATES-->
			<div class="portlet box red">

				<div class="portlet-title">

					<div class="caption">
						<i class="icon-reorder"></i>Style Manage
					</div>

					<div class="tools">

						<a href="javascript:;" class="collapse"></a>

					</div>

				</div>

				<div class="portlet-body form">

					<!-- BEGIN FORM-->

					<form id="form01" action="${ctx }/manager/style_edit.r"
						class="form-horizontal" method="post">

						<input type="hidden" name="template" value="${template }" />

						<div class="alert alert-error hide">

							<button class="close" data-dismiss="alert"></button>

							You have some form errors. Please check below.

						</div>

						<div class="alert alert-success hide">

							<button class="close" data-dismiss="alert"></button>

							Your form validation is successful!

						</div>

						<c:if test="${not empty result }">
							<c:if test="${result eq true }">
								<div class="alert alert-success">
									<button class="close" data-dismiss="alert"></button>
									Save Success!
								</div>
							</c:if>
							<c:if test="${result eq false }">
								<div class="alert alert-error">
									<button class="close" data-dismiss="alert"></button>
									Save Error!
								</div>
							</c:if>
						</c:if>

						<div class="control-group">

							<label class="control-label">Style Content : </label>

							<div class="controls">
								<textarea class="span8" id="styleContent" name="styleContent"
									rows="20" cols="100" tools="mfull">${styleContent }</textarea>
							</div>

						</div>

						<div class="form-actions">

							<button type="submit" class="btn red">Save</button>

						</div>

					</form>

					<!-- END FORM-->

				</div>

			</div>
		</div>
	</div>

	<!-- BEGIN FOOTER -->
	<jsp:include page="/manager/footer.r" />
	<!-- END FOOTER -->

	<%@include file="/commons/metronic_js.jsp"%>
	<script>
		jQuery(document).ready(function() {   
		   App.init();
		});
	</script>
	
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript"
		src="${ctx }/static/metronic/media/js/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${ctx }/static/metronic/media/js/additional-methods.min.js"></script>
	<script type="text/javascript"
		src="${ctx }/static/metronic/media/js/select2.min.js"></script>
	<script type="text/javascript"
		src="${ctx }/static/metronic/media/js/chosen.jquery.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
</body>
</html>