package com.kimkim.jsbswp2;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenMaker {
	public static void make(HttpServletRequest request, HttpServletResponse response) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:SS");
		String token = sdf.format(d);
		request.setAttribute("token", token);
	}
}



