package com.min.edu.model.dao;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.MemberDto;

@Repository
public class MemberImpl implements IMember{
	
	private final String NS = "com.min.edu.model.dao.IMember.";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;	//security-context의 PasswordEncoder 끌어옴

	@Override
	public List<MemberDto> memberList() {
		logger.info("welcome memberList{}", new Date());
		List<MemberDto> lists = sqlSession.selectList(NS+"memberList");
		return lists;
	}
	
	//암호화 전
//	@Override
//	public boolean signUpMember(MemberDto dto) {
//		logger.info("welcome signUpMember{}", dto);
//		int n = sqlSession.insert(NS+"signUpMember", dto);
//		return n>0?true:false;
//	}
	
	//암호화 후
	@Override
	public boolean signUpMember(MemberDto dto) {
		logger.info("welcome signUpMember{}", dto);
		String enPassword = passwordEncoder.encode(dto.getPw());
		dto.setPw(enPassword);
		int n = sqlSession.insert(NS+"signUpMember", dto);
		return n>0?true:false;
	}

	@Override
	public boolean idDuplicateCheck(String id) {
		logger.info("welcome idDuplicateCheck{}", id);
		int n = sqlSession.selectOne(NS+"idDuplicateCheck", id);
		return n>0?true:false;
	}

	
	//암호화 전
//	@Override
//	public MemberDto loginMember(MemberDto dto) {
//		logger.info("welcome loginMember{}", dto);
//		MemberDto mdto = sqlSession.selectOne(NS+"loginMember", dto);
//		return mdto;
//	}

	//암호화 후
	@Override
	public MemberDto loginMember(MemberDto dto) {
		logger.info("welcome loginMember{}", dto);
		
		System.out.println("암호화 로그인 실행 중");
		MemberDto mDto = null;
		
		System.out.printf("화면에서 전달받은 요청 값: ============= %s ============= \n", dto.getPw());
		String enPw = passwordEncoder.encode(dto.getPw());
		System.out.printf("전달받은 값 암호화: ============= %s ============= \n", enPw);
		
		String dbPw = sqlSession.selectOne(NS+"selStringPw", dto.getId());
		System.out.printf("DB에 입력되어 있는 값: ============= %s ============= \n", dto.getPw());
		
		//전달받은 값을 Spring security로 암호화하면 항상 다른 값으로 암호화된다.
		//따라서 DB에 입력된 값과 비교하기 위해서는 PasswordEncoder 클래스의 match를 사용해서 비교판단해야한다.
		//알아서 암호화해서 일치하는것이 있는지 판단해준다. 주의: 암호화된 패스워드를 입력하면 항상  false가 뜬다. ***패스워드의 원형을 입력해야*** matches가 알고리즘에 의해 일치 여부를 판단해 준다.
		if(passwordEncoder.matches(dto.getPw(), dbPw)) {
			System.out.println("=========== 비밀번호 일치");
			mDto = sqlSession.selectOne(NS+"enLogin", dto);
		}
		return mDto;
	
	}
	
}
