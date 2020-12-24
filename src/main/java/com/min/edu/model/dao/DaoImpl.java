package com.min.edu.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.AnswerboardDto;

@Repository
public class DaoImpl implements IDao{

	private final String NS = "com.min.edu.model.dao.IDao.";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<AnswerboardDto> selectDynamic(Map<String, Object> map) {
		logger.info("DaoImpl 글조회 selectDynamic {}", map);
		return sqlSession.selectList(NS+"selectDynamic",map);
	}

	@Override
	public boolean replyInsert(AnswerboardDto dto) {
		logger.info("DaoImpl 답글입력 replyInsert {}", dto);
		int n = sqlSession.insert(NS+"replyInsert", dto);
		return n>0?true:false;
	}

	@Override
	public boolean replyUpdate(AnswerboardDto dto) {
		logger.info("DaoImpl 답글입력 전 업데이트 replyUpdate {}", dto);
		int n = sqlSession.update(NS+"replyUpdate", dto);
		return n>0?true:false;
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		logger.info("DaoImpl 글 수정 modifyBoard {}", map);
		int n = sqlSession.update(NS+"modifyBoard", map);
		return n>0?true:false;
	}

	@Override
	public boolean insertBoard(AnswerboardDto dto) {
		logger.info("DaoImpl 루트글 입력 insertBoard {}", dto);
		int n = sqlSession.insert(NS+"insertBoard", dto);
		return n>0?true:false;
	}

	@Override
	public boolean multiDelete(String seq) {
		logger.info("DaoImpl 글삭제 multiDelete {}", seq);
		int n = sqlSession.update(NS+"multiDelete", seq);
		return n>0?true:false;
	}

	@Override
	public int multiDelete2(Map<String, Object> map) {
		logger.info("DaoImpl 글삭제 다이나믹쿼리 multiDelete2 {}", map);
		int n = sqlSession.update(NS+"multiDelete2", map);
		return n;
	}
	
	
	
	

}
