<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<style type="text/css">
	.input-group-addon { width:120px; text-align:right; }
	.round-corner 	{ border-radius: 4px; }
	.balance-css	{ background:#EEE; padding:20px 15px 20px 15px; margin-top:20px; }
	.balance-css .amount-css 	{ font-weight:bold; font-size:20px; padding-right:10px; display:inline-block; }
	.balance-css .currency-css 	{ font-size:14px; }
</style>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type: "POST",
	       	dataType: "json",
	       	async:false,
	       	url: "${ctx }/rest/v1.0/ripple/walletAddressInfo",
	       	data: {
	       		walletAddress: '${hongxu_wallet_balance.walletAddress }'
				},
      	 	error:function(msg){
	 				alert(msg);
 			},
	       	success: function(data){
	       		if(!data['isOk']) {
	       			alert('failed: ' + data['message']);
	       			return;
	       		}
	       		
	       		for(var i = 0; i < data['data']['currencyList'].length; i++) {
	       			var balance = data['data']['currencyList'][i];
	       			var amount = balance['amount'];
	       			var currency = balance['currency'];
	       			if(currency == null) {
	       				amount = amount / 1000000;
	       				currency = 'xrp';
	       			}
	       			$('#account_balance').append(
	       				'<div align="center" class="round-corner balance-css">'+
	       				'<span class="amount-css">' + amount + '</span>'+
	       				'<span class="currency-css">' + currency + '</span>'+
	       				'</div>');
	       		}
			}
 		});
	});
</script>
<div id="account_balance" class="col-sm-12 col-md-8 col-md-offset-1">
	
</div>
