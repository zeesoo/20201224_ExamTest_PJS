package com.min.edu.model.dao;

import java.util.List;

import com.min.edu.dto.MemberDto;

public interface IMember {
	
	/**
	 * 회원 조회
	 */
	public List<MemberDto> memberList();
	
	/**
	 * 회원가입
	 */
	public boolean signUpMember(MemberDto dto);
	
	/**
	 * 아이디 중복체크
	 */
	public boolean idDuplicateCheck(String id);
	
	/**
	 * 로그인
	 */
	public MemberDto loginMember(MemberDto dto);

}
