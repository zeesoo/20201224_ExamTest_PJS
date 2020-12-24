package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.model.dao.IDao;

@Service
public class ServiceImpl implements IServcie{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDao iDao;

	@Override
	public List<AnswerboardDto> selectDynamic(Map<String, Object> map) {
		logger.info("ServiceImpl 글조회 selectDynamic {}", map);
		return iDao.selectDynamic(map);
	}

	@Transactional
	@Override
	public boolean reply(AnswerboardDto dto) {
		logger.info("ServiceImpl 답글달기 reply {}", dto);
		boolean isc1 = iDao.replyUpdate(dto);
		boolean isc2 = iDao.replyInsert(dto);
		return (isc1||isc2)?true:false;
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		logger.info("ServiceImpl 글수정 modifyBoard {}", map);
		return iDao.modifyBoard(map);
	}

	@Override
	public boolean insertBoard(AnswerboardDto dto) {
		logger.info("ServiceImpl 글수정 insertBoard {}", dto);
		return iDao.insertBoard(dto);
	}

	@Override
	public boolean multiDelete(String seq) {
		logger.info("ServiceImpl 글삭제 multiDelete {}", seq);
		return iDao.multiDelete(seq);
	}

	@Override
	public int multiDelete2(Map<String, Object> map) {
		logger.info("ServiceImpl 글삭제 다이나믹쿼리 multiDelete2 {}", map);
		return iDao.multiDelete2(map);
	}
	

}
