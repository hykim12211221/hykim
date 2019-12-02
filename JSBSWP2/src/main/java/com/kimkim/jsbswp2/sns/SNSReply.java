package com.kimkim.jsbswp2.sns;

import java.math.BigDecimal;
import java.util.Date;

public class SNSReply {
	private BigDecimal jsr_no;
	private String jsr_js_no;
	private String jsr_owner;
	private String jsr_txt;
	private Date jsr_when;
	
	public SNSReply() {
		// TODO Auto-generated constructor stub
	}

	public SNSReply(BigDecimal jsr_no, String jsr_js_no, String jsr_owner, String jsr_txt, Date jsr_when) {
		super();
		this.jsr_no = jsr_no;
		this.jsr_js_no = jsr_js_no;
		this.jsr_owner = jsr_owner;
		this.jsr_txt = jsr_txt;
		this.jsr_when = jsr_when;
	}

	public BigDecimal getJsr_no() {
		return jsr_no;
	}

	public void setJsr_no(BigDecimal jsr_no) {
		this.jsr_no = jsr_no;
	}

	public String getJsr_js_no() {
		return jsr_js_no;
	}

	public void setJsr_js_no(String jsr_js_no) {
		this.jsr_js_no = jsr_js_no;
	}

	public String getJsr_owner() {
		return jsr_owner;
	}

	public void setJsr_owner(String jsr_owner) {
		this.jsr_owner = jsr_owner;
	}

	public String getJsr_txt() {
		return jsr_txt;
	}

	public void setJsr_txt(String jsr_txt) {
		this.jsr_txt = jsr_txt;
	}

	public Date getJsr_when() {
		return jsr_when;
	}

	public void setJsr_when(Date jsr_when) {
		this.jsr_when = jsr_when;
	}
	
}
