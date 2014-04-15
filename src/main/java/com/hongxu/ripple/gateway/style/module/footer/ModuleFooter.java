package com.hongxu.ripple.gateway.style.module.footer;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.hongxu.ripple.gateway.style.metadata.Item;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleFooter extends Footer {

	public ModuleFooter(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private List<Item> itemList = null;
		private List<Item> otherItems = null;

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
				
		Item item = new Item();
		item.setName("About");
		item.setUrl("/gateway/sysText/about_us.r");
		itemList.add(item);
		
		item = new Item();
		item.setName("Fee");
		item.setUrl("/gateway/sysText/fee_terms.r");
		itemList.add(item);
		
		item = new Item();
		item.setName("Legal");
		item.setUrl("/gateway/sysText/legal_terms.r");
		itemList.add(item);
		
		List<Item> otherItems = new ArrayList<Item>();
		item = new Item();
		item.setName("Faqs");
		item.setUrl("/gateway/sysText/faqs.r");
		otherItems.add(item);
		
		ModuleFooter.VO footer = new ModuleFooter.VO();
		footer.setItemList(itemList);
		footer.setOtherItems(otherItems);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(footer));
	}
}

