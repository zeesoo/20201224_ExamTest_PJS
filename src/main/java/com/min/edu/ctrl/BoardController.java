package com.min.edu.ctrl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.dto.MemberDto;
import com.min.edu.model.service.IServcie;


@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private IServcie iService;

	@RequestMapping(value = "home.do", method = RequestMethod.POST)
	public String home(String home) {
		logger.info("welcome home.do: \t {}", new Date());
		System.out.println(home);
		return "home";
	}
	
	//전체 글 조회
	@RequestMapping(value = "boardList.do", method = RequestMethod.GET)
	public String boardList(Model model, Map<String, Object> map) {
		logger.info("welcome boardList.do: \t {}", map);
		List<AnswerboardDto> lists = iService.selectDynamic(map);
		model.addAttribute("lists", lists);
		return "boardList";
	}
	
	//상세글 조회
	@RequestMapping(value = "detailBoard.do", method = RequestMethod.GET)
	public String detailBoard(Model model, Map<String, Object> map, String seq) {
		logger.info("welcome detailBoard.do: \t {}", new Date());
		if(seq != null) {
			map.put("seq", seq);
			List<AnswerboardDto> lists = iService.selectDynamic(map);
			model.addAttribute("lists", lists);
			return "detailBoard";
		}else {
			return "redirect:/boardList.do";
		}
	}
	
	//글 삭제 1
	@RequestMapping(value = "del.do", method = RequestMethod.GET)
	public String del(String seq) {
		logger.info("welcome multiDel.do: \t {}", seq);
		boolean isc = iService.multiDelete(seq);
		System.out.println("글삭제 성공?"+isc);
		return "redirect:/boardList.do";
	}
	
	//글 삭제 다이나믹쿼리
	@RequestMapping(value = "multiDel.do", method = RequestMethod.POST)
	public String multiDel(String[] chkVal) {
		logger.info("welcome multiDel.do: \t {}", new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seqs", chkVal);
		int n = iService.multiDelete2(map);
		System.out.println("삭제된 글의 갯수: "+n);
		return "redirect:/boardList.do";
	}
	
	//글 수정 페이지 이동
	@RequestMapping(value = "modifyBoard.do", method = RequestMethod.GET)
	public String modifyBoard(Model model, String seq) {
		logger.info("welcome modifyBoard.do: \t {}", new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		List<AnswerboardDto> lists = iService.selectDynamic(map);
		model.addAttribute("lists", lists);
		return "modifyBoard";
	}
	
	//글 수정
	@RequestMapping(value = "modify.do", method = RequestMethod.POST)
	public String modify(Model model, String seq, String content) {
		logger.info("welcome modify.do: \t {}", new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("seq, content"+seq+content);
		map.put("seq", seq);
		map.put("content", content);
		model.addAttribute("seq", seq);
		System.out.println("수정 성공? "+iService.modifyBoard(map));
		return "redirect:/detailBoard.do";
	}
	
	//새글 입력 페이지 이동
	@RequestMapping(value = "insertBoard.do", method = RequestMethod.GET)
	public String insertBoard() {
		logger.info("welcome insertBoard.do: \t {}", new Date());
		return "insertBoard";
	}
	
	
	//새글 입력
	@RequestMapping(value = "insert.do", method = RequestMethod.POST)
	public String insert(AnswerboardDto dto) {
		logger.info("welcome insert.do: \t {}", dto);
		boolean isc = iService.insertBoard(dto);
		System.out.println("새글 입력 성공? "+ isc);
		return "redirect:/detailBoard.do";
	}
	
	
	//답글 작성 페이지 이동
	@RequestMapping(value = "replypage.do", method = RequestMethod.GET)
	public String replypage(Model model, String seq) {
		logger.info("welcome replypage.do: \t {}", new Date());
		System.out.println("seq는????????????????????????????"+seq);
		model.addAttribute("seq",seq);
		return "reply";
	}
	
	//답글 작성
	@RequestMapping(value = "reply.do", method = RequestMethod.POST)
	public String reply(AnswerboardDto dto) {
		logger.info("welcome reply.do: \t {}", dto);
//		dto.setSeq(Integer.parseInt(seq));
		boolean isc = iService.reply(dto);
		System.out.println("답글 입력 성공?"+isc);
		return "redirect:/detailBoard.do";
	}
	
	
}
