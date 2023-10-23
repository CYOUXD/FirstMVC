package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.command.JoinVO;
import org.zerock.service.JoinService;
import org.zerock.service.JoinServiceImpl;

@Controller
@RequestMapping("/service/*") // "/*" 생략 가능
public class ServiceController {
	
	@Autowired
	JoinService joinService;
	//servlet-context.xml에서 연결 영역을 설정한 컴포넌트 스캔이 연결해 줌
	//JoinService를 상속 받은 클래스가 JoinServiceImpl밖에 없어서 자동으로 연결됨.
	//????? 상속받으면 타입이 같다는 뜻??
	//답 : 부모로부터 상속 받았으니까 같음. JoinService는 부모 생성할 JoinServiceImpl은 자식. 부모를 상속 받았으니 타입이 같다고 할 수 있다.
	//JoinServiceImpl에 @Service("joinService")라고 지정해주면 오류 없이 연결된다.

	// /service/ 이후에 맵핑 정보가 없는 경우 보여줄 부분
	@RequestMapping("")
	public String basic() {
		return "home"; //home으로 이동. 404 에러가 뜨지 않게 처리한 것
	}
	
	//화면 처리 부분 - 뷰
	@RequestMapping(value="/member_ex01", method=RequestMethod.GET)
//	@GetMapping("/member_ex01")
	public String member_ex01() {
		return "service/member_ex01";
	}
	
	@PostMapping("/join")
	public String join(JoinVO vo) {
		//JoinVO로 전달 받은 값을 처리
		joinService.insertMember(vo);
		
		return "service/member_ex02";
	}
	
	//로그인 화면으로 이동
	@RequestMapping("/member_ex03")
	public String member_ex03() {
		return "service/member_ex03";
	}
	
	//로그인 처리
	@RequestMapping("/login")
	public String login(JoinVO vo, Model model, RedirectAttributes RA) {
		//로그인 유효성 검사(서비스에서 id 검사)
		int result = joinService.memberCheck(vo);
		if(result == 1) {
			//로그인 성공 -> member_mypage 이동
			model.addAttribute("memberInfo", vo);
			return "service/member_mypage"; //마이페이지로 이동
		}else {
			//로그인 실패
			RA.addFlashAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
			//RA - 리다이렉트
			//addFlashAttribute는 리다이렉트를 해야 실행 됨
			return "redirect:/service/member_ex03";
		}
	}
	
	
	
	
	
	
	
	

}
