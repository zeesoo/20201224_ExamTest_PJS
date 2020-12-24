package com.min.edu.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.dto.MemberDto;
import com.min.edu.model.service.IMemberService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IMemberService iService;
	
	@RequestMapping(value = "/loginform.do", method=RequestMethod.GET)
	public String loginform() {
		logger.info("welcome LoginController loginform.do {}", new Date());
		return "loginForm";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(MemberDto dto, HttpServletResponse response, HttpSession session) throws IOException {
		logger.info("welcome LoginController login.do {}", dto);
		MemberDto mdto = iService.loginMember(dto);
		if(mdto == null) {
			response.setContentType("text/html; charst=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인하세요.')</script>;");
			out.flush();
			return "loginForm";
		}else {
			session.setAttribute("loginDto", mdto);
			return "redirect:/boardList.do";
		}
	}
	
	@RequestMapping(value = "/signUpform.do", method =RequestMethod.GET)
	   public String signUpform() {
		   logger.info("welcome LoginController signUpform.do: \t {}", new Date());
		   return "signUpform";
	   }
	
	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public String signUp(MemberDto dto, HttpServletResponse response) throws IOException {
		logger.info("welcome LoginController signUp.do {}", dto);
		boolean isc = iService.signUpMember(dto);
		System.out.println(isc);
		response.setContentType("text/html; charst=UTF-8;");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원가입을 축하합니다.')</script>;");
		out.flush();
		return "loginForm";
	}
	
	@RequestMapping(value = "/duplication.do", method= RequestMethod.GET)
	public String duplication() {
		logger.info("welcome LoginController duplication.do");
		return "duplication";
	}
	
	@RequestMapping(value = "/duplicateAjax.do", method = RequestMethod.POST)
	@ResponseBody
	public String duplicateAjax(String checkId){
		logger.info("welcome LoginController duplicateAjax {}", checkId);
		boolean isc = iService.idDuplicateCheck(checkId);
		logger.info("LoginController duplicateAjax 아이디 존재여부: {}", isc);
		return isc?"true":"false";
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("welcome LoginController logout.do {}", new Date());
		MemberDto loginDto = (MemberDto)session.getAttribute("loginDto");
			if(loginDto != null) {
				session.removeAttribute("loginDto");
//				System.out.println("세션이 남아있나요?"+session.getAttribute("loginDto"));
			}
			return "redirect:/loginform.do";
	}
}
