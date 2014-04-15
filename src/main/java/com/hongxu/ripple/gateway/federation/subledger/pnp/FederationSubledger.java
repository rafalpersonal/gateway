package com.hongxu.ripple.gateway.federation.subledger.pnp;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hongxu.ripple.gateway.federation.subledger.pnp.to.Line;
import com.hongxu.ripple.gateway.federation.subledger.pnp.to.Lines;

public class FederationSubledger {
	
	private static final String HOST = "integration.subledger.com:83";
	private static final String USER = "gateway";
	private static final String PASSWORD = "boZ5KiX62qvNCPkv";
	private static final String GATEWAY_NAME = "user1@yourapp.com";
	
	private Client client = null;
	
	public FederationSubledger() throws ClientException, MalformedURLException {
		this.client = new Client(HOST, USER, PASSWORD);
	}
	
	public Lines getUserTransactionHistory(String userId)
			throws ClientException, ParseException 
	{
		Lines lines = null;

		int perPage = 10;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		//Date date = df.parse("1970-01-01T00:00:00+0000");
		Date date = new Date();
		
		lines = this.client.getUserTransactionHistory(userId, date,
				null, perPage, Order.DESC);

		return lines;
	}
	
	public boolean userDepositFund(
				String transactionId, 
				String userId, 
				String totalAmount, 
				String userAmount, 
				String gatewayFee)
	{
		try{
			String txId = transactionId + "-1"; 
			
			this.client.userFundsReceived(
				txId,
				"http://yourapp.com/userFundsReceived/" + txId,
				"User '" + userId + "' send money to gateway !",
				userId,
				totalAmount,
				userAmount,
				gatewayFee);
			
			txId = transactionId + "-2";
			this.client.userFundsReceived(
				txId,
				"http://yourapp.com/userFundsReceived/" + txId,
				"Gateway receive money from user '" + userId + "' !",
				GATEWAY_NAME,
				totalAmount,
				userAmount,
				gatewayFee);
			
			return true;
		
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean userWithdrawFund(
				String transactionId, 
				String userId, 
				String totalAmount, 
				String userAmount, 
				String gatewayFee) throws ClientException 
	{
		try{
			String txId = transactionId + "-1"; 
			this.client.userFundsTransferredOutOfBank(
					txId,
					"http://yourapp.com/userFundsTransferredOutOfBank/" + txId,
					"User '" + userId + "' withdraw money from gateway !",
					userId,
					totalAmount,
					userAmount,
					gatewayFee);
		
			this.client.userFundsTransferredOutOfBank(
					txId,
					"http://yourapp.com/userFundsTransferredOutOfBank/" + txId,
					"Gateway send money to user '" + userId + "'",
					GATEWAY_NAME,
					totalAmount,
					userAmount,
					gatewayFee);
			
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		String userId = "user1@yourapp.com";
		
		FederationSubledger subledger = new FederationSubledger();
	
		subledger.userDepositFund(
				"trans-id-001", 
				userId, 
				"100.0", 
				"99.9", 
				"0.1");
		
//		subledger.userWithdrawFund(
//				"trans-id-002", 
//				userId, 
//				"100.0", 
//				"99.8", 
//				"0.2");
		
		Lines lines = subledger.getUserTransactionHistory(userId);
		
		for(int i = 0; i < lines.getLines().size(); i++) {
			Line line = lines.getLines().get(i);
			System.out.println(line.getPostedAt());
			System.out.println(line.getDescription());
			System.out.println("--------------------------------");
		}
	}
}
