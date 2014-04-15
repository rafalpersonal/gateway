package com.hongxu.ripple.gateway.style.module.sidebar;

import java.util.List;

import com.hongxu.ripple.gateway.style.metadata.Item;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleSidebarUserCenter extends Sidebar {
	
	private int activeItem = 0;
	
	public int getActiveItem() {
		return activeItem;
	}
	public void setActiveItem(int activeItem) {
		this.activeItem = activeItem;
	}
	
	public ModuleSidebarUserCenter(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private List<Item> itemList = null;
		
		public List<Item> getItemList() {
			return itemList;
		}
		public void setItemList(List<Item> itemList) {
			this.itemList = itemList;
		}
		
	}
}
