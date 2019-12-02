package com.kimkim.jsbswp2.member;

// DB변수명=멤버변수명=요청파라매터명
public class Member {
	private String jm_id;
	private String jm_pw;
	private String jm_name;
	private String jm_addr;
	private String jm_photo;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String jm_id, String jm_pw, String jm_name, String jm_addr, String jm_photo) {
		super();
		this.jm_id = jm_id;
		this.jm_pw = jm_pw;
		this.jm_name = jm_name;
		this.jm_addr = jm_addr;
		this.jm_photo = jm_photo;
	}

	public String getJm_id() {
		return jm_id;
	}

	public void setJm_id(String jm_id) {
		this.jm_id = jm_id;
	}

	public String getJm_pw() {
		return jm_pw;
	}

	public void setJm_pw(String jm_pw) {
		this.jm_pw = jm_pw;
	}

	public String getJm_name() {
		return jm_name;
	}

	public void setJm_name(String jm_name) {
		this.jm_name = jm_name;
	}

	public String getJm_addr() {
		return jm_addr;
	}

	public void setJm_addr(String jm_addr) {
		this.jm_addr = jm_addr;
	}

	public String getJm_photo() {
		return jm_photo;
	}

	public void setJm_photo(String jm_photo) {
		this.jm_photo = jm_photo;
	}
	
}
