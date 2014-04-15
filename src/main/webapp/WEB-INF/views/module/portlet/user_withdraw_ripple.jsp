<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<style type="text/css">
	.input-group { width:400px; }
	.input-group-addon { width:150px; text-align:right; }
	.round-corner { border-radius: 4px; }
</style>
<script type="text/javascript">
	function dataChanged(elem) {
		var amount = jQuery(elem).val();
		var fee = ${hongxu_withdraw_ripple.withdrawFee} * amount;
		jQuery('#fee').html(fee);
		jQuery('#amount2').html(amount);
		jQuery('#total').html(parseInt(amount) - fee);
	}
	
	function old_withdraw() {
		$.ajax({
			type: "POST",
	       	dataType: "json",
	       	async:false,
	       	url: "${ctx }/rest/v1.0/ripple/withdraw",
	       	data: {
	       		userId: '${userId }',
	       		amount: $('#amount').val(),
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
	
	var tagLength = 1000000;
	var destinationTag = Math.floor(Math.random()*tagLength);
	function withdrawInRippleWallet() {	
		var amount = $('#amount').val();
		var walletName = $('#walletAddress').find('option:selected').text();
		var walletAddress = $('#walletAddress').val();
		var currency = $('#gatewayCurrency').find("option:selected").text();
		var issuer = $('#gatewayCurrency').val();
		window.open(
			"https://ripple.com//send?username=" + walletName + "&to=${hongxu_withdraw_ripple.gatewayRippleAddress}&amount=" + amount + "/" + currency + "&dt=" + destinationTag, 
			"winRippleWithdraw", 
			null);	
	}
	
	function notifyGateway() {
		
		$.ajax({
			type: "POST",
	       	dataType: "json",
	       	async:false,
	       	url: "${ctx }/rest/v1.0/ripple/withdraw",
	       	data: {
	       		userId: '${hongxu_withdraw_ripple.user.id }',
	       		walletAddress: $('#walletAddress').val(),
	       		destinationTag: destinationTag,
	       		amount: $('#amount').val(),
	       		fee: $('#fee').html()
				},
      	 	error:function(msg){
	 				alert(msg);
 			},
	       	success: function(data){
	       		if(!data['isOk']) {
	       			alert('[failed]: ' + data['message']);
	       			return;
	       		}
	       		
	       		destinationTag = Math.floor(Math.random()*tagLength);
	       		alert('success');
			}
 		});
	}
	
</script>
<div class="col-sm-12 col-md-8 col-md-offset-2">
	<div align="center" class="round-corner" style="background:#EEE; padding:10px 15px 10px 15px;">
		<!-- 货币类型 -->
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon">${hongxu_withdraw_ripple.vo.withdrawCurrencyLabel }</span>
		  	<select class="form-control" name="gatewayCurrency" id="gatewayCurrency">
		  		<c:forEach items="${hongxu_withdraw_ripple.gatewayCurrencyList }" var="currency" varStatus="i" >
		  			<option value="${currency.issuer }">${currency.name }</option>
		  		</c:forEach>
		  	</select>
		</div>
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon">${hongxu_withdraw_ripple.vo.amountLabel }</span>
		  	<input type="text" class="form-control" name="amount" id="amount" 
		  		placeholder="0.0" onkeyup="dataChanged(this)" >
		</div>
		<div class="input-group" style="margin-top: 20px;">
			<span class="input-group-addon">${hongxu_withdraw_ripple.vo.selectWalletLabel }</span>
		  	<select class="form-control" name="walletAddress" id="walletAddress">
		  		<c:forEach items="${hongxu_withdraw_ripple.userWalletList }" var="wallet" varStatus="i" >
		  			<option value="${wallet.address }">${wallet.name }</option>
		  		</c:forEach>
		  	</select>
		</div>
		<div style="padding-top:10px; padding-left:60px; text-align:left;">
			<span style="font-weight:bold; display:inline-block; width:80px;">${hongxu_withdraw_ripple.vo.amountLabel }:</span>
			<span style="margin-right:20px;" id="amount2">0.0</span><br/>
			<span style="font-weight:bold; display:inline-block; width:80px;">${hongxu_withdraw_ripple.vo.feeLabel }:</span>
			<span style="margin-right:20px;" id="fee">0.0</span><br/>
			<span style="font-weight:bold; display:inline-block; width:80px;">${hongxu_withdraw_ripple.vo.totalLabel }:</span>
			<span style="margin-right:20px;" id="total">0.0</span>
		</div>
		<div style="margin-top:25px; margin-bottom:30px;">
			<button type="button" class="btn btn-default" onclick="withdrawInRippleWallet()">
				${hongxu_withdraw_ripple.vo.withdrawButtonLabel }
			</button>&nbsp;
			<button type="button" class="btn btn-default" onclick="notifyGateway()">
				${hongxu_withdraw_ripple.vo.notifyGatewayButtonLabel }
			</button>
		</div>
	</div>
</div>