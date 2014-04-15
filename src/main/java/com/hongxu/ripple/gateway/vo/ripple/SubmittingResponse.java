package com.hongxu.ripple.gateway.vo.ripple;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmittingResponse {
	
	@JsonProperty("success")
	public boolean success;
	
	@JsonProperty("error")
	public String error;
	
	@JsonProperty("message")
	public String message;

	@JsonProperty("confirmation_token")
	public String confirmation_token;
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
