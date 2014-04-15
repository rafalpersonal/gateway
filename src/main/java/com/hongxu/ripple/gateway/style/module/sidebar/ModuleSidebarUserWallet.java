package com.hongxu.ripple.gateway.style.module.sidebar;

import java.util.ArrayList;
import java.util.List;

import com.hongxu.ripple.gateway.style.metadata.Item;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.UserWallet;

public class ModuleSidebarUserWallet extends Sidebar {
	
	private int activeItem = 0;
	private String currentWalletAddress = null;
	private List<UserWallet> walletList = new ArrayList<UserWallet>();
	
	public int getActiveItem() {
		return activeItem;
	}
	public void setActiveItem(int activeItem) {
		this.activeItem = activeItem;
	}
	public String getCurrentWalletAddress() {
		return currentWalletAddress;
	}
	public void setCurrentWalletAddress(String currentWalletAddress) {
		this.currentWalletAddress = currentWalletAddress;
	}
	public List<UserWallet> getWalletList() {
		return walletList;
	}
	public void setWalletList(List<UserWallet> walletList) {
		this.walletList = walletList;
	}
	
	public ModuleSidebarUserWallet(SysTemplate sysTemplate, ModuleVO vo) {
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
