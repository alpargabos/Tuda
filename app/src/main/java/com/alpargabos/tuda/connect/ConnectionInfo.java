package com.alpargabos.tuda.connect;

public class ConnectionInfo {
	private String avatar;
	private String name;
	private String phone;
	private String sms;

	public ConnectionInfo() {
		//required
	}

	public ConnectionInfo(String avatar, String name, String phone, String sms) {
		this.avatar = avatar;
		this.name = name;
		this.phone = phone;
		this.sms = sms;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}
}
