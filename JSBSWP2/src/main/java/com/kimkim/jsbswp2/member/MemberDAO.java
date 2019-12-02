package com.kimkim.jsbswp2.member;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimkim.jsbswp2.sns.SNSDAO;
import com.kimkim.jsbswp2.sns.SNSMapper;
//import com.kwon.jsbswp.main.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class MemberDAO {
	
	@Autowired
	private SqlSession ss;
	
	@Autowired
	private SNSDAO sDAO;
	
	public void join(Member m, HttpServletRequest req, HttpServletResponse res) {
		// 파일업로드(전송방식 톰캣 -> cos.jar로 바꿈)
		// m에 정보없음
		
		//						↓바뀐점
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "가입실패");
			return;
		}

		try {

			String jm_id = mr.getParameter("jm_id");
			String jm_pw = mr.getParameter("jm_pw");
			String jm_name = mr.getParameter("jm_name");
			String jm_addr1 = mr.getParameter("jm_addr1");
			String jm_addr2 = mr.getParameter("jm_addr2");
			String jm_addr3 = mr.getParameter("jm_addr3");
			String jm_addr = jm_addr1 + "!" + jm_addr2 + "!" + jm_addr3;
			String jm_photo = mr.getFilesystemName("jm_photo");
			jm_photo = URLEncoder.encode(jm_photo, "utf-8");
			jm_photo = jm_photo.replace("+", " ");

			m.setJm_id(jm_id);
			m.setJm_pw(jm_pw);
			m.setJm_name(jm_name);
			m.setJm_addr(jm_addr);
			m.setJm_photo(jm_photo);
			
			if (ss.getMapper(MemberMapper.class).join(m) == 1) {
				req.setAttribute("result", "가입성공");
			} else {
				req.setAttribute("result", "가입실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			String fileName = mr.getFilesystemName("jm_photo");
			new File(path + "/" + fileName).delete();
			req.setAttribute("result", "가입실패");
		} 
				
	}
	
	public void login(Member m, HttpServletRequest req, HttpServletResponse res) {
		try {
			//							     ↓클라이언트가 입력하는것
			Member dbMember = ss.getMapper(MemberMapper.class).getMemberByID(m);
			if (dbMember != null) {
				if (m.getJm_pw().equals(dbMember.getJm_pw())) {
					req.getSession().setAttribute("loginMember", dbMember); // 로그인정보를 attribute화해서 로그인체크or현재로그인정보확인할때 불러씀
					//req.getSession().setMaxInactiveInterval(10);
				} else {
					req.setAttribute("result", "비번이 맞지않음");
				}
			} else {
				req.setAttribute("result", "해당아이디 없음!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "로그인실패(DB관련)");
		}
	}

	public boolean loginCheck(HttpServletRequest req, HttpServletResponse res) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m != null) {
			req.setAttribute("loginPage", "member/loginSuccess.jsp");
			return true;
		} else {
			req.setAttribute("loginPage", "member/login.jsp");
			return false;
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("loginMember", null);
	}
	
	public void splitAddr(HttpServletRequest request, HttpServletResponse response) {
		Member m = (Member) request.getSession().getAttribute("loginMember");

		String jm_addr = m.getJm_addr();
		String[] jm_addr2 = jm_addr.split("!");
		request.setAttribute("addr", jm_addr2);
	}
	
	public void update(Member m, HttpServletRequest req, HttpServletResponse res) {
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		MultipartRequest mr = null;
		Member loginMember = (Member) req.getSession().getAttribute("loginMember");
		String oldFile = loginMember.getJm_photo();
		String newFile = null;
		try {
			mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());
			newFile = mr.getFilesystemName("jm_photo");
			if (newFile == null) {
				newFile = oldFile;
			} else { 
				newFile = URLEncoder.encode(newFile, "utf-8");
				newFile = newFile.replace("+", " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정실패");
			return;
		}
		try {
			String jm_id = mr.getParameter("jm_id");
			String jm_pw = mr.getParameter("jm_pw");
			String jm_name = mr.getParameter("jm_name");
			String jm_addr1 = mr.getParameter("jm_addr1");
			String jm_addr2 = mr.getParameter("jm_addr2");
			String jm_addr3 = mr.getParameter("jm_addr3");
			String jm_addr = jm_addr1 + "!" + jm_addr2 + "!" + jm_addr3;
			String jm_photo = newFile;

			m.setJm_id(jm_id);
			m.setJm_pw(jm_pw);
			m.setJm_name(jm_name);
			m.setJm_addr(jm_addr);
			m.setJm_photo(jm_photo);
			
			if (ss.getMapper(MemberMapper.class).update(m) == 1) {
				req.setAttribute("result", "수정성공");
				req.getSession().setAttribute("loginMember", m);
				if (!oldFile.equals(newFile)) {
					oldFile = URLDecoder.decode(oldFile, "utf-8");
					new File(path + "/" + oldFile).delete();
				}
			} else {
				req.setAttribute("result", "수정실패");
				if (!oldFile.equals(newFile)) {
					newFile = URLDecoder.decode(newFile, "utf-8");
					new File(path + "/" + newFile).delete();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정실패");
			if (!oldFile.equals(newFile)) {
				try {
					newFile = URLDecoder.decode(newFile, "utf-8");
				} catch (UnsupportedEncodingException e1) {
				}
				new File(path + "/" + newFile).delete();
			}
		}
	}
	
	public void bye(HttpServletRequest req, HttpServletResponse res) {

		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			int msgCount = ss.getMapper(SNSMapper.class).getMsgCountByOwner(m);
			
			// 회원탈퇴할 때 총 게시글개수 가져와서
			int allMsgCount = sDAO.getAllMsgCount();
			System.out.println(allMsgCount);
			if (ss.getMapper(MemberMapper.class).bye(m) == 1) {
				req.setAttribute("result", "회원탈퇴완료");
				
				// 회원탈퇴가 성공하면 총 게시글에서 탈퇴한사람이 쓴 게시글개수만큼 빼기
				sDAO.setAllMsgCount(allMsgCount - msgCount);
				System.out.println(sDAO.getAllMsgCount());
				
				String path = req.getSession().getServletContext().getRealPath("resources/img");
				String jm_photo = m.getJm_photo();
				jm_photo = URLDecoder.decode(jm_photo, "euc-kr");
				new File(path + "/" + jm_photo).delete();

				logout(req, res);
				loginCheck(req, res);
			} else {
				req.setAttribute("result", "회원탈퇴실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "회원탈퇴실패");
		}
		
	}
}
