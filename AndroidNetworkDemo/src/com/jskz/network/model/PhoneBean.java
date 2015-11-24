package com.jskz.network.model;

public class PhoneBean {

	private String retMsg;
	private String errNum;
	private NumberBean retData;
	
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public String getErrNum() {
		return errNum;
	}
	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}
	public NumberBean getRetData() {
		return retData;
	}
	public void setRetData(NumberBean retData) {
		this.retData = retData;
	}
	
	@Override
	public String toString() {
		return retData + "]";
	}

	
}
