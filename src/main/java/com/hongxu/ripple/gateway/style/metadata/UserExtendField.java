package com.hongxu.ripple.gateway.style.metadata;

import com.hongxu.ripplermb.domain.core.po.UserExtendFieldCfg;

public class UserExtendField extends UserExtendFieldCfg {
	
	public static final long serialVersionUID = 1L;
	
	private String fieldValue = null;
	
	public UserExtendField() {}
	
	public UserExtendField(UserExtendFieldCfg cfg) {
		this.setDisplayOrder(cfg.getDisplayOrder());
		this.setFieldInputName(cfg.getFieldInputName());
		this.setFieldInputType(cfg.getFieldInputType());
		this.setFieldName(cfg.getFieldName());
		this.setId(cfg.getId());
		this.setIsNeed(cfg.getIsNeed());
	}
	
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
}
