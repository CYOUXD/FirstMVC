package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.command.BoardVO;
import org.zerock.service.BoardService;
import org.zerock.service.BoardServiceImpl;

@Controller //= 컴포넌트의 일종. 컴포넌트를 쓰면 빈에 등록이 된다.
@RequestMapping("/service/*")
public class BoardController {
	
	//BoardService 인터페이스 객체 자동 연결 방법
	//1. new 사용
//	BoardService boardService = new BoardServiceImpl();
	
	//2. xml에 bean 등록 처리
	@Autowired
	private BoardService boardService;
	//servlet-context.xml에 빈 등록
	//<beans:bean  id="BoardService" class="org.zerock.service.BoardServiceImpl" />
	
	//게시판 등록 화면 처리
	@RequestMapping("/boardRegister")
	public String boardRegister() {
		return "service/boardRegister";
	}
	
	//게시글 등록 요청 처리
	@RequestMapping(value="/boardForm", method=RequestMethod.POST)
	public String boardForm(
			@RequestParam("name") String name,
			@RequestParam("title") String title,
			@RequestParam("content") String content
			) {
		//boardService
		boardService.register(name, title, content);
		
		return "service/boardResult";
	}
	
	//게시글 리스트 보기 요청 처리
	@RequestMapping("/boardList")
	public String boardList(Model model) { //Model model로 값을 넘겨줌
		List<BoardVO> DB = boardService.getList();
		
		//전달 받은 DB를 boardList 이름으로 저장
		model.addAttribute("boardList", DB);
		
		return "service/boardList";
	}
	
	//게시글 삭제 요청 처리
	@RequestMapping("/boardDelete")
	public String boardDelete(@RequestParam("num") String num) {
		boardService.delete(num);
		//System.out.println("삭제할 index : "+num);
		
		return "redirect:/service/boardList";
	}
	
}
