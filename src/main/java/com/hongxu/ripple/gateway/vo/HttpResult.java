package com.hongxu.ripple.gateway.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class HttpResult implements Serializable{
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getReturnData() {
		return returnData;
	}
	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}
	public String getStatusLine() {
		return statusLine;
	}
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	private static final long serialVersionUID = 1L;
	
	private int statusCode;
	private String statusLine;
	private String returnData;
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	
	

}
