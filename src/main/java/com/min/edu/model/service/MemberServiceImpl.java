package com.min.edu.model.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.MemberDto;
import com.min.edu.model.dao.IMember;

@Service
public class MemberServiceImpl implements IMemberService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IMember dao;

	@Override
	public List<MemberDto> memberList() {
		logger.info("welcome memberList {}", new Date());
		return dao.memberList();
	}

	@Override
	public boolean signUpMember(MemberDto dto) {
		logger.info("welcome signUpMember {}", dto);
		return dao.signUpMember(dto);
	}

	@Override
	public boolean idDuplicateCheck(String id) {
		logger.info("welcome idDuplicateCheck{} ", id);
		return dao.idDuplicateCheck(id);
	}

	@Override
	public MemberDto loginMember(MemberDto dto) {
		logger.info("welcome loginMember {}", dto);
		return dao.loginMember(dto);
	}

}
