<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
	.pageFormContent label { width:150px; }
	.select .required { width:100px; }
</style>
<div class="pageContent">

		<div class="pageFormContent" layoutH="56">
			
			<!-- logo -->
			<form method="post" action="${ctx }/manager/settings_edit_logo.r"  class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this, dialogAjaxDone);">
			<div>
				<input type="hidden" name="navTabId" value="ROLE_SETTINGS_MANAGE">
				<input type="hidden" name="attrName" value="logo">
				<input type="hidden" name="attrType" value="image">
				<div class="unit" style="float:left;">
					<label><spring:message code="manager.settings.logo" /> : </label>
					<img src="${ctx }${ map['logo'].attrValue}" width="120px" height="40px">
					<input type="file" name="attrValue" />
				</div>
				<div class="buttonActive" style="float:left;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- language -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit" style="float:left;">
					<label><spring:message code="manager.settings.language" /> : </label>
					<input type="hidden" name="attrName" value="language">
					<input type="hidden" name="attrType" value="txt">
					<select name="attrValue" class="required combox">
						<c:forEach var="l" items="${languages }">
							<option value="${l }" ${(l eq map['language'].attrValue) ? 'selected=selected'  : '' }>${l }</option>
						</c:forEach>
					</select>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- skins -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit" style="float:left;">
					<label><spring:message code="manager.settings.skins" /> : </label>
					<input type="hidden" name="attrName" value="skins">
					<input type="hidden" name="attrType" value="txt">
					<select name="attrValue" class="required combox" size="200px">
						<option value="RED" ${('RED' eq map['skins'].attrValue) ? 'selected=selected'  : '' }>Red</option>
						<option value="BLUE" ${('BLUE' eq map['skins'].attrValue) ? 'selected=selected'  : '' }>Blue</option>
						<option value="GRAY" ${('GREY' eq map['skins'].attrValue) ? 'selected=selected'  : '' }>Grey</option>
					</select>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- root_url -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="unit" style="float:left;">
				<label><spring:message code="manager.settings.root_url" /> : </label>
				<input type="hidden" name="attrName" value="root_url">
				<input type="hidden" name="attrType" value="txt">
				<input type="text" name="attrValue"  class="required url"  style="width: 200px;"  value="${ map['root_url'].attrValue}"/>
			</div>
			<div class="buttonActive" style="float:left; margin-left:20px;">
				<div class="buttonContent">
					<button type="submit"><spring:message code="manager.button.save"/></button>
				</div>
			</div>
			</form>
			
			<div class="divider" style="margin:15px 0px 15px 0px;"></div>

			<!-- currency -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit" style="float:left;">
					<label><spring:message code="manager.settings.currency" /> : </label>
					<input type="hidden" name="attrName" value="currency">
					<input type="hidden" name="attrType" value="txt">
					<input type="text" name="attrValue" minlength="3" maxlength="20" style="width: 200px;" class="required" value="${ map['currency'].attrValue}"/>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			
			<!-- gateway_ripple_address -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit" style="float:left;">
					<label><spring:message code="manager.settings.gateway_ripple_address" /> : </label>
					<input type="hidden" name="attrName" value="gateway_ripple_address">
					<input type="hidden" name="attrType" value="txt">
					<input type="text" name="attrValue" minlength="10" maxlength="50" style="width: 200px;" class="required" value="${ map['gateway_ripple_address'].attrValue}"/>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- gateway_ripple_tag -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit" style="float:left;" >
					<label><spring:message code="manager.settings.gateway_ripple_tag" /> : </label>
					<input type="hidden" name="attrName" value="gateway_ripple_tag">
					<input type="hidden" name="attrType" value="txt">
					<input type="text" name="attrValue" minlength="10" maxlength="50" style="width: 200px;" class="required" value="${ map['gateway_ripple_tag'].attrValue}"/>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<div class="divider" style="margin:15px 0px 15px 0px;"></div>
			
			<!-- bank_name -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit"  style="float:left;">
					<label><spring:message code="manager.settings.bank_name" /> : </label>
					<input type="hidden" name="attrName" value="bank_name">
					<input type="hidden" name="attrType" value="txt">
					<input type="text" name="attrValue"  class="required" style="width: 200px;" value="${ map['bank_name'].attrValue}"/>
				</div>
				<div class="buttonActive"  style="margin-left:20px; float:left;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- bank_routing_number -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit" style="float:left;">
					<label><spring:message code="manager.settings.bank_routing_number" /> : </label>
					<input type="hidden" name="attrName" value="bank_routing_number">
					<input type="hidden" name="attrType" value="txt">
					<input type="text" name="attrValue"  class="required digits" style="width: 200px;" value="${ map['bank_routing_number'].attrValue}"/>
				</div>
				<div class="buttonActive"  style="margin-left:20px; float:left;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- bank_account_number -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit"  style="float:left;">
					<label><spring:message code="manager.settings.bank_account_number" /> : </label>
					<input type="hidden" name="attrName" value="bank_account_number">
					<input type="hidden" name="attrType" value="txt">
					<input type="text" name="attrValue"  class="required digits" style="width: 200px;" value="${ map['bank_account_number'].attrValue}"/>
				</div>
				<div class="buttonActive"  style="margin-left:20px; float:left;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			<div class="divider" style="margin:15px 0px 15px 0px;"></div>
			
			<!-- deposit -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit" style="float:left;">
					<label><spring:message code="manager.settings.deposit" /> : </label>
					<input type="hidden" name="attrName" value="deposit_fee">
					<input type="hidden" name="attrType" value="txt">
					<input type="text" name="attrValue"  class="required number" value="${ map['deposit_fee'].attrValue}"/>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- withdraw -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>
				<div class="unit" style="float:left;">
					<label><spring:message code="manager.settings.withdraw" /> : </label>
					<input type="hidden" name="attrName" value="withdraw_fee">
					<input type="hidden" name="attrType" value="txt">
					<input type="text" name="attrValue"  class="required number" value="${ map['withdraw_fee'].attrValue}"/>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			<div class="divider" style="margin:15px 0px 15px 0px;"></div>

			<!-- smtp -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>	
				<div class="unit" style="float:left;">
					<div style="float:left;">
						<label><spring:message code="manager.settings.smtp" /> : </label>
						<input type="hidden" name="attrName" value="smtp">
						<input type="hidden" name="attrType" value="txt">
						<input type="text" name="attrValue"  class="required"  style="width: 200px;"  value="${ map['smtp'].attrValue}"/>
					</div>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- smtp username -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>	
				<div class="unit" style="float:left;">
					<div style="float:left;">
						<label><spring:message code="manager.settings.smtpUsername" /> : </label>
						<input type="hidden" name="attrName" value="smtp_username">
						<input type="hidden" name="attrType" value="txt">
						<input type="text" name="attrValue"  class="required"  style="width: 200px;"  value="${ map['smtp_username'].attrValue}"/>
					</div>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>
			
			<!-- smtp username -->
			<form method="post" action="${ctx }/manager/settings_edit.r"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div>	
				<div class="unit" style="float:left;">
					<div style="float:left;">
						<label><spring:message code="manager.settings.smtpPassword" /> : </label>
						<input type="hidden" name="attrName" value="smtp_password">
						<input type="hidden" name="attrType" value="txt">
						<input type="text" name="attrValue"  class="required"  style="width: 200px;"  value="${ map['smtp_password'].attrValue}"/>
					</div>
				</div>
				<div class="buttonActive" style="float:left; margin-left:20px;">
					<div class="buttonContent">
						<button type="submit"><spring:message code="manager.button.save"/></button>
					</div>
				</div>
			</div>
			</form>

		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close"><spring:message code="manager.button.cancel"/></button></div></div>
				</li>
			</ul>
		</div>
	
</div>
