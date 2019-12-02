package com.kimkim.jsbswp2.sns;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SNSMsg {
	private BigDecimal js_no;
	private String js_owner;
	private String js_txt;
	private Date js_when;	
	private String jm_photo;
	private List<SNSReply> js_replys;
	
	public List<SNSReply> getJs_replys() {
		return js_replys;
	}

	public void setJs_replys(List<SNSReply> js_replys) {
		this.js_replys = js_replys;
	}

	public SNSMsg() {
		// TODO Auto-generated constructor stub
	}

	public SNSMsg(BigDecimal js_no, String js_owner, String js_txt, Date js_when, String jm_photo) {
		super();
		this.js_no = js_no;
		this.js_owner = js_owner;
		this.js_txt = js_txt;
		this.js_when = js_when;
		this.jm_photo = jm_photo;
	}

	public BigDecimal getJs_no() {
		return js_no;
	}

	public void setJs_no(BigDecimal js_no) {
		this.js_no = js_no;
	}

	public String getJs_owner() {
		return js_owner;
	}

	public void setJs_owner(String js_owner) {
		this.js_owner = js_owner;
	}

	public String getJs_txt() {
		return js_txt;
	}

	public void setJs_txt(String js_txt) {
		this.js_txt = js_txt;
	}

	public Date getJs_when() {
		return js_when;
	}

	public void setJs_when(Date js_when) {
		this.js_when = js_when;
	}

	public String getJm_photo() {
		return jm_photo;
	}

	public void setJm_photo(String jm_photo) {
		this.jm_photo = jm_photo;
	}

}
