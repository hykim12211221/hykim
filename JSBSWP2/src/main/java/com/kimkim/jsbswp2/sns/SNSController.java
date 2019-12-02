package com.kimkim.jsbswp2.sns;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kimkim.jsbswp2.SiteOption;
import com.kimkim.jsbswp2.TokenMaker;
import com.kimkim.jsbswp2.member.MemberDAO;

@Controller
public class SNSController {
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private SNSDAO sDAO;
	
	@RequestMapping(value = "sns.go", method = RequestMethod.GET)
	public String snsGo(HttpServletRequest req, HttpServletResponse res) {
		SiteOption.clesrSearch(req, res); // 게시판 갈때마다 검색목록 초기화
		TokenMaker.make(req, res);
		mDAO.loginCheck(req, res);
		sDAO.getMsg(1, req, res);// sns페이지 열때 1페이지 보이게
		req.setAttribute("contentPage", "sns/sns.jsp");	
		return "index";
	}
	
	// 페이지 바꾸는 메소드
	@RequestMapping(value = "sns.page.change", method = RequestMethod.GET)
	public String snsPageChange(HttpServletRequest req, HttpServletResponse res) {
		TokenMaker.make(req, res);
		int p = Integer.parseInt(req.getParameter("p"));
		mDAO.loginCheck(req, res);
		sDAO.getMsg(p, req, res);
		req.setAttribute("contentPage", "sns/sns.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "sns.write", method = RequestMethod.POST)
	public String snsWriteMsg(SNSMsg sm, HttpServletRequest req, HttpServletResponse res) {
		TokenMaker.make(req, res);
		if (mDAO.loginCheck(req, res)) {
			sDAO.writeMsg(sm, req, res);
		}
		sDAO.getMsg(1, req, res);
		req.setAttribute("contentPage", "sns/sns.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "sns.reply.write", method = RequestMethod.GET)
	public String snsReplyWrite(SNSReply sr, HttpServletRequest req, HttpServletResponse res) {
		TokenMaker.make(req, res);
		int p = Integer.parseInt(req.getParameter("p"));
		if (mDAO.loginCheck(req, res)) {
			sDAO.writeReply(sr, req, res);
		}
		sDAO.getMsg(p, req, res);
		req.setAttribute("contentPage", "sns/sns.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "sns.search", method = RequestMethod.POST)
	public String snsSearch(SNSSelector sSel, HttpServletRequest req, HttpServletResponse res) {
		TokenMaker.make(req, res);
		mDAO.loginCheck(req, res);
		sDAO.searchMsg(sSel, req, res); // 검색한 내용 처리
		sDAO.getMsg(1, req, res);// sns페이지 열때 1페이지 보이게
		req.setAttribute("contentPage", "sns/sns.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "sns.update", method = RequestMethod.GET)
	public String snsUpdate(SNSMsg sm, HttpServletRequest req, HttpServletResponse res) {
		TokenMaker.make(req, res);
		int p = Integer.parseInt(req.getParameter("p"));
		if (mDAO.loginCheck(req, res)) {
			sDAO.updateMsg(sm, req, res);
		}
		sDAO.getMsg(p, req, res);
		req.setAttribute("contentPage", "sns/sns.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "sns.delete", method = RequestMethod.GET)
	public String snsDelete(SNSMsg sm, HttpServletRequest req, HttpServletResponse res) {
		TokenMaker.make(req, res);
		SiteOption.clesrSearch(req, res);
		if (mDAO.loginCheck(req, res)) {
			sDAO.deleteMsg(sm, req, res);
		}
		sDAO.getMsg(1, req, res);
		req.setAttribute("contentPage", "sns/sns.jsp");	
		return "index";
	}
	
	@RequestMapping(value = "sns.reply.delete", method = RequestMethod.GET)
	public String snsReplyDelete(SNSReply sr, HttpServletRequest req, HttpServletResponse res) {
		TokenMaker.make(req, res);
		int p = Integer.parseInt(req.getParameter("p"));
		SiteOption.clesrSearch(req, res);
		if (mDAO.loginCheck(req, res)) {
			sDAO.deleteReply(sr, req, res);
		}
		sDAO.getMsg(p, req, res);
		req.setAttribute("contentPage", "sns/sns.jsp");	
		return "index";
	}

}
