package com.hongxu.ripple.gateway.style.module.portlet;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.hongxu.ripple.gateway.style.metadata.UserExtendField;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.UserExtendFieldCfg;
import com.hongxu.ripplermb.domain.core.po.UserRealInfo;

public class ModuleUserAccountProfile extends Portlet {

	private String userId = null;
	private UserRealInfo userInfo = null;
	private List<UserExtendFieldCfg> userExtendFieldCfg = 
				new ArrayList<UserExtendFieldCfg>();
	private List<UserExtendField> userExtendFields = 
				new ArrayList<UserExtendField>();
	private boolean needReset;
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public UserRealInfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UserRealInfo userInfo) {
		this.userInfo = userInfo;
	}


	public ModuleUserAccountProfile(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public List<UserExtendFieldCfg> getUserExtendFieldCfg() {
		return userExtendFieldCfg;
	}

	public void setUserExtendFieldCfg(List<UserExtendFieldCfg> userExtendFieldCfg) {
		this.userExtendFieldCfg = userExtendFieldCfg;
	}

	public List<UserExtendField> getUserExtendFields() {
		return userExtendFields;
	}

	public void setUserExtendFields(List<UserExtendField> userExtendFields) {
		this.userExtendFields = userExtendFields;
	}

	public boolean isNeedReset() {
		return needReset;
	}
	
	public void setNeedReset(boolean needReset) {
		this.needReset = needReset;
	}

	public static class VO extends ModuleVO {
		
		private String firstNameLabel = null;
		private String firstNamePlaceHolder = null;
		private String lastNameLabel = null;
		private String lastNamePlaceHolder = null;
		private String phoneLabel = null;
		private String phonePlaceHolder = null;
		private String birthdayLabel = null;
		private String birthdayPlaceHolder = null;
		private String addressLabel = null;
		private String addressPlaceHolder = null;
		private String cityLabel = null;
		private String cityPlaceHolder = null;
		private String stateLabel = null;
		private String statePlaceHolder = null;
		private String countryLabel = null;
		private String countryPlaceHolder = null;
		private String idCardFrontLabel = null;
		private String idCardBackLabel = null;
		private String submitButtonLabel = null;
		
		public String getFirstNameLabel() {
			return firstNameLabel;
		}
		public void setFirstNameLabel(String firstNameLabel) {
			this.firstNameLabel = firstNameLabel;
		}
		public String getFirstNamePlaceHolder() {
			return firstNamePlaceHolder;
		}
		public void setFirstNamePlaceHolder(String firstNamePlaceHolder) {
			this.firstNamePlaceHolder = firstNamePlaceHolder;
		}
		public String getLastNameLabel() {
			return lastNameLabel;
		}
		public void setLastNameLabel(String lastNameLabel) {
			this.lastNameLabel = lastNameLabel;
		}
		public String getLastNamePlaceHolder() {
			return lastNamePlaceHolder;
		}
		public void setLastNamePlaceHolder(String lastNamePlaceHolder) {
			this.lastNamePlaceHolder = lastNamePlaceHolder;
		}
		public String getPhoneLabel() {
			return phoneLabel;
		}
		public void setPhoneLabel(String phoneLabel) {
			this.phoneLabel = phoneLabel;
		}
		public String getPhonePlaceHolder() {
			return phonePlaceHolder;
		}
		public void setPhonePlaceHolder(String phonePlaceHolder) {
			this.phonePlaceHolder = phonePlaceHolder;
		}
		public String getBirthdayLabel() {
			return birthdayLabel;
		}
		public void setBirthdayLabel(String birthdayLabel) {
			this.birthdayLabel = birthdayLabel;
		}
		public String getBirthdayPlaceHolder() {
			return birthdayPlaceHolder;
		}
		public void setBirthdayPlaceHolder(String birthdayPlaceHolder) {
			this.birthdayPlaceHolder = birthdayPlaceHolder;
		}
		public String getAddressLabel() {
			return addressLabel;
		}
		public void setAddressLabel(String addressLabel) {
			this.addressLabel = addressLabel;
		}
		public String getAddressPlaceHolder() {
			return addressPlaceHolder;
		}
		public void setAddressPlaceHolder(String addressPlaceHolder) {
			this.addressPlaceHolder = addressPlaceHolder;
		}
		public String getCityLabel() {
			return cityLabel;
		}
		public void setCityLabel(String cityLabel) {
			this.cityLabel = cityLabel;
		}
		public String getCityPlaceHolder() {
			return cityPlaceHolder;
		}
		public void setCityPlaceHolder(String cityPlaceHolder) {
			this.cityPlaceHolder = cityPlaceHolder;
		}
		public String getStateLabel() {
			return stateLabel;
		}
		public void setStateLabel(String stateLabel) {
			this.stateLabel = stateLabel;
		}
		public String getStatePlaceHolder() {
			return statePlaceHolder;
		}
		public void setStatePlaceHolder(String statePlaceHolder) {
			this.statePlaceHolder = statePlaceHolder;
		}
		public String getCountryLabel() {
			return countryLabel;
		}
		public void setCountryLabel(String countryLabel) {
			this.countryLabel = countryLabel;
		}
		public String getCountryPlaceHolder() {
			return countryPlaceHolder;
		}
		public void setCountryPlaceHolder(String countryPlaceHolder) {
			this.countryPlaceHolder = countryPlaceHolder;
		}
		public String getIdCardFrontLabel() {
			return idCardFrontLabel;
		}
		public void setIdCardFrontLabel(String idCardFrontLabel) {
			this.idCardFrontLabel = idCardFrontLabel;
		}
		public String getIdCardBackLabel() {
			return idCardBackLabel;
		}
		public void setIdCardBackLabel(String idCardBackLabel) {
			this.idCardBackLabel = idCardBackLabel;
		}
		public String getSubmitButtonLabel() {
			return submitButtonLabel;
		}
		public void setSubmitButtonLabel(String submitButtonLabel) {
			this.submitButtonLabel = submitButtonLabel;
		}
	}

	
	public static void main(String[] args) throws Exception {
		
		ModuleUserAccountProfile.VO vo = new ModuleUserAccountProfile.VO();
		vo.setFirstNameLabel("First Name");
		vo.setFirstNamePlaceHolder("First Name");
		vo.setLastNameLabel("Last Name");
		vo.setLastNamePlaceHolder("Last Name");
		vo.setBirthdayLabel("Date of Birth");
		vo.setBirthdayPlaceHolder("YYYY-MM-DD");
		vo.setPhoneLabel("Phone");
		vo.setPhonePlaceHolder("Phone");
		vo.setAddressLabel("Address");
		vo.setAddressPlaceHolder("street of you live... ");
		vo.setCityLabel("City");
		vo.setCityPlaceHolder("City");
		vo.setStateLabel("State");
		vo.setStatePlaceHolder("State");
		vo.setCountryLabel("Country");
		vo.setCountryPlaceHolder("Country");
		vo.setIdCardFrontLabel("ID Card Front Image");
		vo.setIdCardBackLabel("ID Card Back Image");
		vo.setSubmitButtonLabel("Save Profile");
		
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(vo));
	}
}
