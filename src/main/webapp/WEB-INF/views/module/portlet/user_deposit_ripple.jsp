<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<style type="text/css">
	.input-group-addon { width:150px; text-align:right; }
	.input-group { width:400px; }
	.round-corner { border-radius: 4px; }
</style>
<script type="text/javascript">
	function dataChanged(elem) {
		var amount = jQuery(elem).val();
		var fee = ${hongxu_deposit_ripple.depositFee} * amount;
		jQuery('#fee').html(fee);
		jQuery('#amount2').html(amount);
		jQuery('#total').html(parseInt(amount) + fee);
	}
	
	function deposit() {
		var walletName = $('#walletAddress').find('option:selected').text();
		var walletAddress = $('#walletAddress').val();
		var currency = $('#gatewayCurrency').find("option:selected").text();
		var issuer = $('#gatewayCurrency').val();
		$.ajax({
			type: "POST",
	       	dataType: "json",
	       	async:false,
	       	url: "${ctx }/rest/v1.0/ripple/deposit",
	       	data: {
	       		userId: '${hongxu_deposit_ripple.user.id }',
	       		currency: currency,
	       		issuer: issuer,
	       		walletAddress: $('#walletAddress').val(),
	       		amount: $('#amount').val(),
	       		account: $('#account').val(),
	       		paymentId: $('#paymentId').val(),
	       		fee: $('#fee').html()
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
	
	function setTrustLine() {
		var walletName = $('#walletAddress').find('option:selected').text();
		var walletAddress = $('#walletAddress').val();
		var amount = Math.ceil($('#amount').val() * 1.2);
		var currency = $('#gatewayCurrency').find("option:selected").text();
		var issuer = $('#gatewayCurrency').val();
		window.open(
			"https://ripple.com//trust?username=" + walletName + "&to=" + issuer + "&amount=" + amount + "/" + currency, 
			"winRippleDeposit", 
			null);	
	}
	
	
</script>
<div class="col-sm-12 col-md-8 col-md-offset-2">
	<div align="center" class="round-corner" style="background:#EEE; padding:10px 15px 10px 15px;" >
		<!-- 货币类型 -->
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon">${hongxu_deposit_ripple.vo.depositCurrencyLabel }</span>
		  	<select class="form-control" name="gatewayCurrency" id="gatewayCurrency">
		  		<c:forEach items="${hongxu_deposit_ripple.gatewayCurrencyList }" var="currency" varStatus="i" >
		  			<option value="${currency.issuer }">${currency.name }</option>
		  		</c:forEach>
		  	</select>
		</div>
		<!-- 数量 -->
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon">${hongxu_deposit_ripple.vo.amountLabel }</span>
		  	<input type="text" class="form-control" name="amount" id="amount" 
		  		placeholder="0.0" onkeyup="dataChanged(this)" >
		</div>
		<!-- 支付类型  -->
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon">${hongxu_deposit_ripple.vo.accountLabel }</span>
		  	<select class="form-control" name="paymentId" id="paymentId">
		  		<c:forEach items="${hongxu_deposit_ripple.paymentList }" var="payment" varStatus="i" >
		  			<option value="${payment.id }">${payment.name }</option>
		  		</c:forEach>
		  	</select>
		</div>
		<!-- 钱包地址 -->
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon">${hongxu_deposit_ripple.vo.selectWalletLabel }</span>
		  	<select class="form-control" name="walletAddress" id="walletAddress">
		  		<c:forEach items="${hongxu_deposit_ripple.userWalletList }" var="wallet" varStatus="i" >
		  			<option value="${wallet.address }">${wallet.name }</option>
		  		</c:forEach>
		  	</select>
		</div>
		<div style="padding-top:10px; padding-top:10px;">
			<span style="font-weight:bold;">${hongxu_deposit_ripple.vo.amountLabel }:</span>
			<span style="margin-right:20px;" id="amount2">0.0</span>
			<span style="font-weight:bold;">${hongxu_deposit_ripple.vo.feeLabel }:</span>
			<span style="margin-right:20px;" id="fee">0.0</span>
			<span style="font-weight:bold;">${hongxu_deposit_ripple.vo.totalLabel }:</span>
			<span style="margin-right:20px;" id="total">0.0</span>
		</div>
		<div style="margin-top:25px; margin-bottom:30px;">
			<button type="button" class="btn btn-default" onclick="deposit()">
				${hongxu_deposit_ripple.vo.depositButtonLabel }
			</button>&nbsp;
			<button type="button" class="btn btn-default" onclick="setTrustLine()">
				${hongxu_deposit_ripple.vo.setTrustLineButtonLabel }
			</button>
		</div>
	</div>
</div>
