<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<style type="text/css">
	.input-group { margin-top:15px; width:100%; }
	.input-group-addon { width:170px; }
	
	.img-border {
		border-bottom-right-radius: 4px;
		border-top-right-radius: 4px;
		border-bottom-left-radius: 0;
    	border-top-left-radius: 0;
    	border: 1px solid #CCCCCC;
    	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    	padding: 10px 20px;
	}
</style>
<script type="text/javascript">
	function save() {
		$('#profileForm').submit();
	}
</script>
<c:set var="isReadOnly" value="" scope="page" />
<c:if test="${!hongxu_account_verify.needReset }">
	<c:set var="isReadOnly" value="readonly" />
</c:if>
<form id="profileForm" action="${ctx }/user_center/account_profile_post.html" enctype="multipart/form-data" method="post" data-ajax="false">
<input type="hidden" name="userId" value="${hongxu_account_verify.userId }" />
<div>
	<div style="font-size:20px;">
		Profile Detail
		<hr style="height:1px;"/>
	</div>
	<div class="input-group" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.firstNameLabel }</span>
	  	<input ${isReadOnly } type="text" class="form-control" name="firstName" id="firstName" 
	  		value="${hongxu_account_verify.userInfo.firstName }"
	  		placeholder='${hongxu_account_verify.vo.firstNamePlaceHolder }'>
	</div>
	<div class="input-group" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.lastNameLabel }</span>
	  	<input ${isReadOnly } type="text" class="form-control" name="lastName" id="lastName" 
	  		value="${hongxu_account_verify.userInfo.lastName }"
	  		placeholder='${hongxu_account_verify.vo.lastNamePlaceHolder }'>
	</div>
	<div class="input-group col-md-12" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.birthdayLabel }</span>
	  	<input ${isReadOnly } type="text" class="form-control" name="birthday" id="birthday" 
	  		value="${hongxu_account_verify.userInfo.birthday }"
	  		placeholder='${hongxu_account_verify.vo.birthdayPlaceHolder }'>
	</div>
	<div class="input-group col-md-12" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.phoneLabel }</span>
	  	<input ${isReadOnly } type="text" class="form-control" name="phone" id="phone" 
	  		value="${hongxu_account_verify.userInfo.phone }"
	  		placeholder='${hongxu_account_verify.vo.phonePlaceHolder }'>
	</div>
	<div class="input-group col-md-12" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.addressLabel }</span>
	  	<input ${isReadOnly } type="text" class="form-control" name="address" id="address" 
	  		value="${hongxu_account_verify.userInfo.address }"
	  		placeholder='${hongxu_account_verify.vo.addressPlaceHolder }'>
	</div>
	<div class="input-group col-md-12" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.cityLabel }</span>
	  	<input ${isReadOnly } type="text" class="form-control" name="city" id="city" 
	  		value="${hongxu_account_verify.userInfo.city }"
	  		placeholder='${hongxu_account_verify.vo.cityPlaceHolder }'>
	</div>
	<div class="input-group col-md-12" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.stateLabel }</span>
	  	<input ${isReadOnly } type="text" class="form-control" name="state" id="state" 
	  		value="${hongxu_account_verify.userInfo.state }"
	  		placeholder='${hongxu_account_verify.vo.statePlaceHolder }'>
	</div>
	<div class="input-group col-md-12" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.countryLabel }</span>
	  	<input ${isReadOnly } type="text" class="form-control" name="country" id="country" 
	  		value="${hongxu_account_verify.userInfo.country }"
	  		placeholder='${hongxu_account_verify.vo.countryPlaceHolder }'>
	</div>
	
	<c:forEach items="${hongxu_account_verify.userExtendFields}" var="extendField" varStatus="idx">
		<div class="input-group col-md-12" style="margin-top: 20px;">
			<span class="input-group-addon">${extendField.fieldName }</span>
			<c:choose>
			<c:when test="${extendField.fieldInputType == 'text'}">
			  	<input ${isReadOnly } type="${extendField.fieldInputType }" class="form-control" 
			  		name="${extendField.fieldInputName }" id="${extendField.fieldInputName }"
			  		value="${extendField.fieldValue }">
			</c:when>
			<c:when test="${extendField.fieldInputType == 'file' && empty extendField.fieldValue }">
		  		<input type="${extendField.fieldInputType}" class="form-control" 
		  			name="${extendField.fieldInputName }" id="${extendField.fieldInputName }">
		  	</c:when>
		  	<c:when test="${extendField.fieldInputType == 'file' && not empty extendField.fieldValue }">
		  		<div class="img-border">
					<img src="${ctx }${extendField.fieldValue}" style="max-height:100px;" />
				</div>
		  	</c:when>
		  	</c:choose>
		</div>
	</c:forEach>
	
	<div class="input-group col-md-12" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.idCardFrontLabel }</span>
		<c:choose>
			<c:when test="${ empty hongxu_account_verify.userInfo.idCardFront }">
			  	<input type="file" class="form-control" name="id_card_front" id="id_card_front">
			</c:when>
			<c:otherwise>
				<div class="img-border">
					<img src="${ctx }${hongxu_account_verify.userInfo.idCardFront}" style="max-height:150px;" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="input-group col-md-12" style="margin-top: 20px;">
		<span class="input-group-addon">${hongxu_account_verify.vo.idCardBackLabel }</span>
		<c:choose>
			<c:when test="${ empty hongxu_account_verify.userInfo.idCardBack }">
				  <input type="file" class="form-control" name="id_card_back" id="id_card_back">
			</c:when>
			<c:otherwise>
				<div class="img-border">
					<img src="${ctx }${hongxu_account_verify.userInfo.idCardBack}" style="max-height:150px;" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	
	<c:if test="${hongxu_account_verify.needReset }">
		<div style="margin-top:15px; margin-left:100px; margin-bottom:60px;">
			<button type="button" class="btn btn-default" onclick="save()"> ${hongxu_account_verify.vo.submitButtonLabel }</button>
		</div>
	</c:if>
</div>
</form>