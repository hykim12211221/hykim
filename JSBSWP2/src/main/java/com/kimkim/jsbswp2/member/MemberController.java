package com.kimkim.jsbswp2.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO mDAO;
	
	@RequestMapping(value = "member.join.go", method = RequestMethod.GET)
	public String memberJoinGo(HttpServletRequest req, HttpServletResponse res) {
		mDAO.loginCheck(req, res);
		req.setAttribute("contentPage", "member/join.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "member.join", method = RequestMethod.POST)
	public String memberJoin(Member m,
			HttpServletRequest req, HttpServletResponse res) {
		mDAO.join(m, req, res);
		mDAO.loginCheck(req, res);
		req.setAttribute("contentPage", "home.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "member.login", method = RequestMethod.POST)
	public String memberLogin(Member m,
			HttpServletRequest req, HttpServletResponse res) {
		mDAO.login(m, req, res);
		mDAO.loginCheck(req, res);
		req.setAttribute("contentPage", "home.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "member.logout", method = RequestMethod.GET)
	public String memberLogout(Member m,
			HttpServletRequest req, HttpServletResponse res) {
		mDAO.logout(req, res);
		mDAO.loginCheck(req, res);
		req.setAttribute("contentPage", "home.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "member.info", method = RequestMethod.GET)
	public String memberInfo(Member m,
			HttpServletRequest req, HttpServletResponse res) {
		if (mDAO.loginCheck(req, res)) {
			mDAO.splitAddr(req, res);
			req.setAttribute("contentPage", "member/info.jsp");
		} else {
			req.setAttribute("contentPage", "home.jsp");
		}
		return "index";
	}
	
	@RequestMapping(value = "member.update", method = RequestMethod.POST)
	public String memberUpdate(Member m,
			HttpServletRequest req, HttpServletResponse res) {
		if (mDAO.loginCheck(req, res)) {
			mDAO.update(m, req, res);
			mDAO.splitAddr(req, res);
			req.setAttribute("contentPage", "member/info.jsp");
		} else {
			req.setAttribute("contentPage", "home.jsp");
		}
		return "index";
	}
	
	@RequestMapping(value = "member.bye", method = RequestMethod.GET)
	public String memberBye(HttpServletRequest req, HttpServletResponse res) {
		if (mDAO.loginCheck(req, res)) {
			mDAO.bye(req, res);
		}
		req.setAttribute("contentPage", "home.jsp");	
		return "index";
	}

	
}
