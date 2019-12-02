package com.kimkim.jsbswp2.sns;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimkim.jsbswp2.SiteOption;
import com.kimkim.jsbswp2.member.Member;

@Service
public class SNSDAO {	
	private int allMsgCount;
	
	
	public int getAllMsgCount() {
		return allMsgCount;
	}

	public void setAllMsgCount(int allMsgCount) {
		this.allMsgCount = allMsgCount;
	}

	// servlet-context의 beans들을 부른것
	@Autowired
	private SqlSession ss;
	@Autowired
	private SiteOption so;
	
	// 전체메세지 개수확인용 메소드
	public void calcAllMsgCount() {
		//													  ↓전체메세지검색할려고 빈칸
		SNSSelector sSel = new SNSSelector("", null, null);
		allMsgCount = ss.getMapper(SNSMapper.class).getMsgCount(sSel);
		System.out.println(allMsgCount);
	}
	
	// 게시판 쓰기
	public void writeMsg(SNSMsg sm, HttpServletRequest req, HttpServletResponse res) {
		try {
			String token = req.getParameter("token");
			String successToken = (String) req.getSession().getAttribute("successToken");

			if (successToken != null && token.equals(successToken)) {
				return;
			}

			Member m = (Member) req.getSession().getAttribute("loginMember");
			sm.setJs_owner(m.getJm_id());

			String js_txt = sm.getJs_txt();
			sm.setJs_txt(js_txt.replace("\r\n", "<br>"));

			if (ss.getMapper(SNSMapper.class).writeMsg(sm) == 1) {
				req.setAttribute("result", "글쓰기성공");
				allMsgCount++; // 글쓰기성공 할때마다 카운터 올림
				req.getSession().setAttribute("successToken", token);
			} else {
				req.setAttribute("result", "글쓰기실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글쓰기실패");
		}
	}
	
	// 댓글쓰기
	public void writeReply(SNSReply sr, HttpServletRequest req, HttpServletResponse res) {
		try {
			String token = req.getParameter("token");
			String successToken = (String) req.getSession().getAttribute("successToken");

			if (successToken != null && token.equals(successToken)) {
				return;
			}
			Member m = (Member) req.getSession().getAttribute("loginMember");
			sr.setJsr_owner(m.getJm_id());

			// reply에는 글 줄바꾸기 필요없음
			//String js_txt = sm.getJs_txt();
			//sm.setJs_txt(js_txt.replace("\r\n", "<br>"));

			if (ss.getMapper(SNSMapper.class).writeReply(sr) == 1) {
				req.setAttribute("result", "댓글쓰기성공");
				req.getSession().setAttribute("successToken", token);
			} else {
				req.setAttribute("result", "댓글쓰기실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "댓글쓰기실패");
		}
	}
	
	// 게시판페이지에 게시목록 보이게하기
	public void getMsg(int pageNo,
			HttpServletRequest req, HttpServletResponse res) {
		try {
			
			// ↓한페이지당 몇개씩할지(수정할수 있게 servlet-context에 등록)
			int count = so.getSnsCountPerPage();//servlet에 있는 so를 불러서 사용
			
			int start = (pageNo - 1) * count + 1;
			int end = start + (count - 1);
			
			SNSSelector search = (SNSSelector) req.getSession().getAttribute("search"); // 검색한 글자를 가져옴
			int msgCount = 0;
			
			if(search==null) { // 검색에 아무것도 안썻을때 실행
				search= new SNSSelector("", new BigDecimal(start), new BigDecimal(end));
				msgCount = allMsgCount;
			} else { // 검색에 무언가를 썻을때 실행
				search.setStart(new BigDecimal(start));
				search.setEnd(new BigDecimal(end));
				msgCount = ss.getMapper(SNSMapper.class).getMsgCount(search);
			}
			
			// 게시판 조회
			List<SNSMsg> msgs = ss.getMapper(SNSMapper.class).getMsg(search);
			
			// 게시판 조회할때 리플도 같이 보이도록
			for (SNSMsg snsMsg : msgs) {
				snsMsg.setJs_replys(
						ss.getMapper(SNSMapper.class).getReply(snsMsg)
				);
			}
			
			req.setAttribute("msgs", msgs);
			req.setAttribute("curPage", pageNo);
			
			int pageCount = (int) Math.ceil(msgCount/ (double)count);
			req.setAttribute("pageCount", pageCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시판 검색할때 쓴 글자 저장
	public void searchMsg(SNSSelector sSel, HttpServletRequest req, HttpServletResponse res) {
		req.getSession().setAttribute("search", sSel);
	}
	
	// 게시판 글 수정
	public void updateMsg(SNSMsg sm, HttpServletRequest req, HttpServletResponse res) {
		try {
			if (ss.getMapper(SNSMapper.class).updateMsg(sm) == 1) {
				req.setAttribute("result", "글수정성공");
			} else {
				req.setAttribute("result", "글수정실패");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글수정실패");
		}		
	}
	
	// 게시판 글 삭제
	public void deleteMsg(SNSMsg sm, HttpServletRequest req, HttpServletResponse res) {
		try {
			if (ss.getMapper(SNSMapper.class).deleteMsg(sm) == 1) {
				req.setAttribute("result", "글삭제성공");
				allMsgCount--;
			} else {
				req.setAttribute("result", "글삭제실패");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글삭제실패");
		}
	}
	
	// 댓글삭제
	public void deleteReply(SNSReply sr, HttpServletRequest req, HttpServletResponse res) {
		try {
			if (ss.getMapper(SNSMapper.class).deleteReply(sr) == 1) {
				req.setAttribute("result", "댓글삭제성공");
			} else {
				req.setAttribute("result", "댓글삭제실패");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "댓글삭제실패");
		}
	}
	
}
