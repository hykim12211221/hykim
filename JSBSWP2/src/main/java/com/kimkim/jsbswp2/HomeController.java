package com.kimkim.jsbswp2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kimkim.jsbswp2.member.MemberDAO;
import com.kimkim.jsbswp2.sns.SNSDAO;

@Controller
public class HomeController {
	// 첫 요청인지 판달할 변수
	private boolean firstReq;
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private SNSDAO sDAO;
	
	// JSP에서는 DAO가 static영역에 있어서
	//			-> DAO가 먼저 생성 다음에 Controller가 생성되어서 문제없었음
	//				HomeController생성 될 때 전체 메세지 갯수가 나왓음
	// 하지만 Spring에서는 DAO가 static영역에 없음
	//			-> DAO가 먼저 생성되는지? Controller가 먼저 생성되는지? 모름
	//	그래서 다른 접근방식이 필요함
	// 			-> 클라이언트의 첫 요청 때, 전체 메세지 갯수 셀 수 있게
	// 홈 킬때 전체 메세지개수 확인하려고 홈에만듬
	public HomeController() {
		firstReq = true;
	}
	
	// 첫GET방식
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletResponse res) {		
		if (firstReq) {
			sDAO.calcAllMsgCount();
			firstReq = false;
		}
		mDAO.loginCheck(req, res);
		req.setAttribute("contentPage", "home.jsp");		
		return "index";
	}
	
	// 제목 클릭시 이동되는 메소드
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String indexDo(HttpServletRequest req, HttpServletResponse res) {	
		return home(req,res);
	}
	
}
