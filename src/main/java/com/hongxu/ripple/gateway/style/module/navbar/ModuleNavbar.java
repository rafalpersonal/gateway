package com.hongxu.ripple.gateway.style.module.navbar;

import java.util.List;

import com.hongxu.ripple.gateway.style.metadata.Item;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleNavbar extends NavBar {

	private String activeButton = null;
	
	public String getActiveButton() {
		return activeButton;
	}

	public void setActiveButton(String activeButton) {
		this.activeButton = activeButton;
	}

	public ModuleNavbar(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String logo = null;
		private List<Item> itemList = null;
		private List<Item> otherItems = null;
		
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		public List<Item> getItemList() {
			return itemList;
		}
		public void setItemList(List<Item> itemList) {
			this.itemList = itemList;
		}
		public List<Item> getOtherItems() {
			return otherItems;
		}
		public void setOtherItems(List<Item> otherItems) {
			this.otherItems = otherItems;
		}
		
	}
}
