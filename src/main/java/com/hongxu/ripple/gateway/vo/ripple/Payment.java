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
public class Payment implements Serializable{

	private static final long serialVersionUID = 1L;
	/*User Specified*/

	private String src_address;
	private String src_tag;
	private Amount src_amount;
	private String src_slippage;
	
	private String dst_address;
	private String dst_tag;
	private Amount dst_amount;
	
	/* Advanced Options */
	
	private String invoice_id;
	private String paths;
	private boolean flag_no_direct_ripple;
	private boolean flag_partial_payment;
	
	public String getSrc_address() {
		return src_address;
	}
	public void setSrc_address(String src_address) {
		this.src_address = src_address;
	}
	public String getSrc_tag() {
		return src_tag;
	}
	public void setSrc_tag(String src_tag) {
		this.src_tag = src_tag;
	}
	public Amount getSrc_amount() {
		return src_amount;
	}
	public void setSrc_amount(Amount src_amount) {
		this.src_amount = src_amount;
	}
	public String getSrc_slippage() {
		return src_slippage;
	}
	public void setSrc_slippage(String src_slippage) {
		this.src_slippage = src_slippage;
	}
	public String getDst_address() {
		return dst_address;
	}
	public void setDst_address(String dst_address) {
		this.dst_address = dst_address;
	}
	public String getDst_tag() {
		return dst_tag;
	}
	public void setDst_tag(String dst_tag) {
		this.dst_tag = dst_tag;
	}
	public Amount getDst_amount() {
		return dst_amount;
	}
	public void setDst_amount(Amount dst_amount) {
		this.dst_amount = dst_amount;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getPaths() {
		return paths;
	}
	public void setPaths(String paths) {
		this.paths = paths;
	}
	public boolean isFlag_no_direct_ripple() {
		return flag_no_direct_ripple;
	}
	public void setFlag_no_direct_ripple(boolean flag_no_direct_ripple) {
		this.flag_no_direct_ripple = flag_no_direct_ripple;
	}
	public boolean isFlag_partial_payment() {
		return flag_partial_payment;
	}
	public void setFlag_partial_payment(boolean flag_partial_payment) {
		this.flag_partial_payment = flag_partial_payment;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
