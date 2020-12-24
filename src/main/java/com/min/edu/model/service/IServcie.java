package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.AnswerboardDto;

public interface IServcie {
	
	/**
	 * 글 조회 다이나믹 쿼리 
	 * @param map (seq 있으면 상세글 조회, 없으면 전체글 조회)
	 * @return List<AnswerboardDto>
	 */
	public List<AnswerboardDto> selectDynamic(Map<String, Object> map);
	
	
	/**
	 * 답글 입력 (Dao의 update와  insert를 조합하여 트랜잭션으로 실행)
	 * @param dto
	 * @return
	 */
	public boolean reply(AnswerboardDto dto);
	
	/**
	 * 글수정
	 * @param map (seq, content)
	 * @return
	 */
	public boolean modifyBoard(Map<String, Object> map);
	
	/**
	 * 루트글 작성
	 * @param dto (id, title, content)
	 * @return
	 */
	public boolean insertBoard(AnswerboardDto dto);
	
	/**
	 * 글삭제 delflag
	 * @param seq
	 * @return
	 */
	public boolean multiDelete(String seq);
	
	/**
	 * 다중 글삭제 다이나믹 쿼리 delflag
	 * @param map (seq)
	 * @return 삭제된 글의 갯수
	 */
	public int multiDelete2(Map<String, Object> map);

}
