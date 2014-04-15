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
	
	<style type="text/css">
		.title-css { padding-left:20px; color:#666; font-size:1.8em; font-weight:bold;}
		.note-css { padding-left:20px;  color:red; }
	</style>
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

			<div class="portlet box red">

				<div class="portlet-title">

					<div class="caption">
						<i class="icon-reorder"></i>Payment
					</div>

					<div class="tools">

						<a href="javascript:;" class="collapse"></a>

					</div>

				</div>

				<div class="portlet-body form">

					<!-- BEGIN FORM-->

					<input type="hidden" name="id" value="${d.id }">

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


					<!-- logo -->
					<form action="${ctx }/manager/settings_edit_logo.r"
						enctype="multipart/form-data" class="form-horizontal"
						method="post">

						<input type="hidden" name="navTabId" value="ROLE_SETTINGS_MANAGE">
						<input type="hidden" name="attrName" value="logo"> <input
							type="hidden" name="attrType" value="image">
						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.logo" />:</label>
							<div class="controls">
								<input type="file" name="attrValue" class="span4 m-wrap" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
								<span class="note-css"><img
									src="${ctx }${ map['logo'].attrValue}" width="120px"
									height="40px"></span>
							</div>

						</div>


					</form>

					<!-- language -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.language" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="language"> 
								<input type="hidden" name="attrType" value="txt"> 
								<select name="attrValue" class="span4 m-wrap">
									<c:forEach var="l" items="${languages }">
										<option value="${l }" ${(l eq
											map['language'].attrValue) ? 'selected=selected'  : '' }>${l}
										</option>
									</c:forEach>
								</select>
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>

					</form>

					<!-- skins -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">
						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.skins" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="skins"> 
								<input type="hidden" name="attrType" value="txt"> 
								<select name="attrValue" class="span4 m-wrap">
									<option value="RED" ${('RED' eq
										map['skins'].attrValue) ? 'selected=selected'  : '' }>Red</option>
									<option value="BLUE" ${('BLUE' eq
										map['skins'].attrValue) ? 'selected=selected'  : '' }>Blue</option>
									<option value="GRAY" ${('GREY' eq
										map['skins'].attrValue) ? 'selected=selected'  : '' }>Grey</option>
								</select>
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>
					</form>

					<!-- root_url -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.root_url" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="root_url"> <input
									type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['root_url'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>

					</form>
					<hr />

					<!-- currency -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.currency" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value=currency> <input
									type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['currency'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>
					</form>


					<!-- gateway_ripple_address -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.gateway_ripple_address" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName"
									value="gateway_ripple_address"> <input type="hidden"
									name="attrType" value="txt"> <input type="text"
									name="attrValue" class="span4 m-wrap"
									value="${ map['gateway_ripple_address'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>
					</form>

					<!-- deposit Fee -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.depositFee" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="deposit_fee">
								<input type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['deposit_fee'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
								<span class="note-css"><spring:message
										code="manager.settings.depositFee.note" /></span>
							</div>
						</div>
					</form>

					<!-- withdraw Fee -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.withdrawFee" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="withdraw_fee">
								<input type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['withdraw_fee'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
								<span class="note-css"><spring:message
										code="manager.settings.withdrawFee.note" /></span>
							</div>
						</div>
					</form>
					<!-- transaction Fee -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.transactionFee" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="transaction_fee">
								<input type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['transaction_fee'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
								<span class="note-css"><spring:message
										code="manager.settings.transactionFee.note" /></span>
							</div>
						</div>
					</form>
					<hr />

					<!-- gateway_ripple_tag 
									<form action="${ctx }/manager/settings_edit.r"  class="form-horizontal" method="post">
									
										<div class="control-group">
											<label class="control-label"><spring:message code="manager.settings.gateway_ripple_tag" /> : </label>
											<div class="controls">
												<input type="hidden" name="attrName" value="gateway_ripple_tag">
												<input type="hidden" name="attrType" value="txt">
												<input type="text" name="attrValue"   class="span6 m-wrap"  value="${ map['gateway_ripple_tag'].attrValue}"/>
												<button type="submit" class="btn red"><spring:message code="manager.button.save"/></button>
											</div>
										</div>
									</form>
									<hr/>
									-->
					<div style="height: 50px;">
						<span class="title-css"><spring:message
								code="manager.settings.bank.notes" /></span>
					</div>
					<!-- bank_name -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.bank_name" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="bank_name">
								<input type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['bank_name'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>
					</form>

					<!-- bank_routing_number -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.bank_routing_number" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="bank_routing_number">
								<input type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['bank_routing_number'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>
					</form>

					<!-- bank_account_number -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.bank_account_number" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="bank_account_number">
								<input type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['bank_account_number'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>
					</form>
					<hr />

					<div style="height: 50px;">
						<span class="title-css"><spring:message
								code="manager.settings.smtp.notes" /></span>
					</div>
					<!-- smtp -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">
						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.smtp" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="smtp"> <input
									type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['smtp'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>
					</form>

					<!-- smtp username -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.smtpUsername" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="smtp_username">
								<input type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['smtp_username'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>
					</form>

					<!-- smtp username -->
					<form action="${ctx }/manager/settings_edit.r"
						class="form-horizontal" method="post">

						<div class="control-group">
							<label class="control-label"><spring:message
									code="manager.settings.smtpPassword" /> : </label>
							<div class="controls">
								<input type="hidden" name="attrName" value="smtp_password">
								<input type="hidden" name="attrType" value="txt"> <input
									type="text" name="attrValue" class="span4 m-wrap"
									value="${ map['smtp_password'].attrValue}" />
								<button type="submit" class="btn red">
									<spring:message code="manager.button.save" />
								</button>
							</div>
						</div>

					</form>

					<!-- END FORM-->

				</div>

			</div>
		</div>
	</div>


	<!-- BEGIN FOOTER -->
	<jsp:include page="/manager/footer.r"/>
	<!-- END FOOTER -->
	
	<%@include file="/commons/metronic_js.jsp" %>
	
	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript" src="${ctx }/static/metronic/media/js/jquery.validate.min.js"></script>

	<script type="text/javascript" src="${ctx }/static/metronic/media/js/additional-methods.min.js"></script>

	<script type="text/javascript" src="${ctx }/static/metronic/media/js/select2.min.js"></script>

	<script type="text/javascript" src="${ctx }/static/metronic/media/js/chosen.jquery.min.js"></script>

	<script src="${ctx }/static/metronic/media/js/manager-organization-validation.js"></script> 
	
	<!-- xheditor -->
	<script src="${ctx }/static/dwz1.4.6/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
    <script src="${ctx }/static/dwz1.4.6/xheditor/xheditor_lang/en.js" type="text/javascript"></script>

	<script>

		jQuery(document).ready(function() {   

		   // initiate layout and plugins
		   App.init();
		   FormValidation.init();
		});

	</script>
	
	
</body>

<!-- END BODY -->

</html>