package com.jskz.network.model;

public class NumberBean {

	/*
 	"city": "北京",
    "phone": "18710057085",
    "prefix": "1871005",
    "province": "北京",
    "suit": "187卡",
    "supplier": "移动"
    */
	
	private String city;
	private String phone;
	private String prefix;
	private String province;
	private String suit;
	private String supplier;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Override
	public String toString() {
		return "您的手机信息为：[city=" + city + ", phone=" + phone + ", prefix="
				+ prefix + ", province=" + province + ", suit=" + suit
				+ ", supplier=" + supplier ;
	}
	
}
