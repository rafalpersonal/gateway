package com.hongxu.ripple.gateway.style.skin;

public class ModuleSkin {
	
	private String styleType = null;
	private BackgroundColor bgColor = null;
	private String fontColor = null;
	private Border border = null;
	
	public String getStyleType() {
		return styleType;
	}
	public void setStyleType(String styleType) {
		this.styleType = styleType;
	}
	public BackgroundColor getBgColor() {
		return bgColor;
	}
	public void setBgColor(BackgroundColor bgColor) {
		this.bgColor = bgColor;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public Border getBorder() {
		return border;
	}
	public void setBorder(Border border) {
		this.border = border;
	}
	public ModuleSkin() {
	}
	
	public ModuleSkin(BackgroundColor bgColor, String fontColor) {
		this.bgColor = bgColor;
		this.fontColor = fontColor;
	}
}
