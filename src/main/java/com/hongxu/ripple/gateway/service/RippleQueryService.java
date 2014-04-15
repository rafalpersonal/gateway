package com.hongxu.ripple.gateway.service;

import java.util.List;

import jrippleapi.connection.RippleAccountTx;
import jrippleapi.connection.RippleAddressPublicInformation;
import jrippleapi.connection.RippleDaemonWebsocketConnection;
import jrippleapi.connection.RippleTransactionResult;
import jrippleapi.connection.TrustLines;

import org.springframework.stereotype.Service;

@Service
public class RippleQueryService {
	
	private RippleDaemonWebsocketConnection conn = null;
	
	public boolean open() {
		try {			
			conn = new RippleDaemonWebsocketConnection(
					RippleDaemonWebsocketConnection.RIPPLE_SERVER_URL);

			return true;
			
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean close() {
		try{
			if(conn != null) {	
				conn.close();
				conn = null;
			}
			
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}
	
	public RippleAddressPublicInformation getAccountInfo(
				String publicAddress) 
	{	
		if(conn == null) {
			if(!open()) {
				return null;
			}
		}
		
		return conn.getAccountInfo(publicAddress);
	}
	
	public TrustLines getTrustLines(
			String publicAddress)
	{
		if(conn == null) {
			if(!open()) {
				return null;
			}
		}
		
		return conn.getTrustLines(publicAddress);
	}
	
	public List<RippleTransactionResult> getAccountTx(
				String rippleAddress, int limit) 
	{
		if(conn == null) {
			if(!open()) {
				return null;
			}
		}
		
		RippleAccountTx accountTx = conn.getAccountTx(rippleAddress, -1, limit);
		List<RippleTransactionResult> txResultList = accountTx.getTransactions();
		return txResultList;
	}
}
