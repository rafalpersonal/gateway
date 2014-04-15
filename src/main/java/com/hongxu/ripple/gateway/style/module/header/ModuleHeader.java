package com.hongxu.ripple.gateway.style.module.header;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.hongxu.ripple.gateway.style.metadata.Item;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleHeader extends Header {

	public ModuleHeader(SysTemplate sysTemplate, ModuleVO vo) {
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
	
	public static void main(String[] args) throws Exception{
		
		List<Item> itemList = new ArrayList<Item>();
		Item item = null;
		
		item = new Item();
		item.setName("Fee");
		item.setUrl("/gateway/sysText/fee_terms.r");
		itemList.add(item);
		
		item = new Item();
		item.setName("Legal");
		item.setUrl("/gateway/sysText/legal_terms.r");
		itemList.add(item);
		
		ModuleHeader.VO header = new ModuleHeader.VO();
		header.setItemList(itemList);
		header.setLogo(null);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(header));
	}
}


