/**
 * @Title Notification.java
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
public class Notification implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String address;
	private String type;
	private String tx_direction;
	private String tx_state;
	private String tx_result;
	private long tx_ledger;
	private String tx_hash;
	private long tx_timestamp;
	private String tx_timestamp_human;
	private String tx_url;
	private String next_notification_url;
	private String confirmation_token;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTx_direction() {
		return tx_direction;
	}
	public void setTx_direction(String tx_direction) {
		this.tx_direction = tx_direction;
	}
	public String getTx_state() {
		return tx_state;
	}
	public void setTx_state(String tx_state) {
		this.tx_state = tx_state;
	}
	public String getTx_result() {
		return tx_result;
	}
	public void setTx_result(String tx_result) {
		this.tx_result = tx_result;
	}
	public long getTx_ledger() {
		return tx_ledger;
	}
	public void setTx_ledger(long tx_ledger) {
		this.tx_ledger = tx_ledger;
	}
	public String getTx_hash() {
		return tx_hash;
	}
	public void setTx_hash(String tx_hash) {
		this.tx_hash = tx_hash;
	}
	public long getTx_timestamp() {
		return tx_timestamp;
	}
	public void setTx_timestamp(long tx_timestamp) {
		this.tx_timestamp = tx_timestamp;
	}
	public String getTx_timestamp_human() {
		return tx_timestamp_human;
	}
	public void setTx_timestamp_human(String tx_timestamp_human) {
		this.tx_timestamp_human = tx_timestamp_human;
	}
	public String getTx_url() {
		return tx_url;
	}
	public void setTx_url(String tx_url) {
		this.tx_url = tx_url;
	}
	public String getNext_notification_url() {
		return next_notification_url;
	}
	public void setNext_notification_url(String next_notification_url) {
		this.next_notification_url = next_notification_url;
	}
	public String getConfirmation_token() {
		return confirmation_token;
	}
	public void setConfirmation_token(String confirmation_token) {
		this.confirmation_token = confirmation_token;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	
}
