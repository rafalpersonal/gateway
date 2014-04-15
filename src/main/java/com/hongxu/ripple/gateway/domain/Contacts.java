/**
 * @Title Contacts.java
 * @Package jrippleapi.hongxu.domain
 * @Project ripple-rmb-api
 * @Author 王欣
 * @Version v1.0
 * @Date 2013-11-28 下午6:17:21
 */
package com.hongxu.ripple.gateway.domain;

import java.io.Serializable;

/**
 * @author 王欣
 * @Version v1.0
 * @Date 2013-11-28 下午6:17:21
 */
public class Contacts  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
