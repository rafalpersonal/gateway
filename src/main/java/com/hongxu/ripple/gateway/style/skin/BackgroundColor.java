package com.hongxu.ripple.gateway.style.skin;

public class BackgroundColor {
	
	private String startColor;
	private String endColor;
	private String startColorForIE;
	private String endColorForIE;
	
	public String getStartColor() {
		return startColor;
	}
	public void setStartColor(String startColor) {
		this.startColor = startColor;
	}
	public String getEndColor() {
		return endColor;
	}
	public void setEndColor(String endColor) {
		this.endColor = endColor;
	}
	public String getStartColorForIE() {
		return startColorForIE;
	}
	public void setStartColorForIE(String startColorForIE) {
		this.startColorForIE = startColorForIE;
	}
	public String getEndColorForIE() {
		return endColorForIE;
	}
	public void setEndColorForIE(String endColorForIE) {
		this.endColorForIE = endColorForIE;
	}
	
	public BackgroundColor() {
	}
	
	public BackgroundColor(
			String startColor, String endColor, 
			String startColorForIE, String endColorForIE) 
	{
		this.startColor = startColor;
		this.endColor = endColor;
		this.startColorForIE = startColorForIE;
		this.endColorForIE = endColorForIE;
	}
}
