/**
 * @Title Amount.java
 * @Package com.hongxu.ripple.gateway.vo.ripple
 * @Project ripple-gateway
 * @Author 王欣
 * @Version v1.0
 * @Date 2014-3-10 下午2:52:54
 */
package com.hongxu.ripple.gateway.vo.ripple;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author 王欣
 * @Version v1.0
 * @Date 2014-3-10 下午2:52:54
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Amount implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String value;
	private String currency;
	private String issuer;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	

}
