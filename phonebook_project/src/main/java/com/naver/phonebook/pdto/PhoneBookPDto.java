package com.naver.phonebook.pdto;

public class PhoneBookPDto {
	String email;
	String nmname;
	String phoneNumber;
	String address;
	String gubun;
	String gubunnum;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getnmname() {
		return nmname;
	}
	public void setNmname(String nmname) {
		this.nmname = nmname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getGubunnum() {
		return gubunnum;
	}
	public void setGubunnum(String gubunnum) {
		this.gubunnum = gubunnum;
	}
	
	@Override
	public String toString() {
		return "PhoneBookPDto [email=" + email + ", nmname=" + nmname + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", gubun=" + gubun + ", gubunnum=" + gubunnum + "]";
	}
	
	
}
