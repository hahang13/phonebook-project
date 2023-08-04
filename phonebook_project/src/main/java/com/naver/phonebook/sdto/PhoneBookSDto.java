package com.naver.phonebook.sdto;

public class PhoneBookSDto {
	private String email;
	private String pw;
	private String phoneNumber;
	private String nmname;
	
	@Override
	public String toString() {
		return "PhoneBookSDto [email=" + email + ", pw=" + pw + ", phoneNumber=" + phoneNumber + ", nmname=" + nmname + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNmname() {
		return nmname;
	}
	public void setNmname(String nmname) {
		this.nmname = nmname;
	}

	
	

}
