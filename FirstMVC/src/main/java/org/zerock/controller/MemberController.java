package org.zerock.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.member.model.MemberVo;

@Controller 
//application-config.xml 파일에서 컴포넌트 스캔으로 컨트롤러를 스캔하여 찾아서 사용한다.
@RequestMapping("/member")  //uri 경로로 구분함(views 경로)
public class MemberController {
	
//	@RequestMapping(value="/memberJoin", method = RequestMethod.POST) 
	//method = RequestMethod.GET : get 방식으로 데이터가 넘어감
	//method = RequestMethod.POST : post 방식으로 데이터가 넘어감
	@GetMapping("/memberJoin") 
	//get방식으로 데이터를 넘김. Get, Post, Put, Delete 등의 메서드 매핑 어노테이션이 존재함
	public String memJoin() {
		System.out.println("memberJoin 접근!");
		return "member/memJoin"; //view로 해당 jsp 화면을 보여줌
		//return 값에 앞, 뒤로 prefix와 suffix가 붙어서 경로가 된다.
		//servlet-context.xml 파일에 설정한 prefix: /WEB-INF/views/ suffix : .jsp
	}
	
	//값 전달
	//주소를 입력해서 들어오면 get 방식이니까 이 메서드가 실행됨
	@RequestMapping(value="/memLogin", method=RequestMethod.GET)
	public String memLogin(Model model) {
		return "member/memLogin";
	}
	//form을 post로 전달 했으니 이 메서드 실행
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public String memLogin(
			Model model, 
			//1. request 객체 이용 방법
//			HttpServletRequest request 
			//2. @RequestParam 이용 방법
			//@RequestParam의 추가 속성
			//@RequestParam(value = "파라미터 값", required=false, defaultValue="기본값")
			//인자가 하나면 파라미터 값
			//1. required : 해당 파라미터가 필수가 아닌 경우 지정. 기본값 = true. false면 필수가 아니므로 기본값 지정
			//2. defaultValue : required 지정 시 기본 값 설정
//			@RequestParam("memId") String memId, //어노테이션을 이용하는 방법
//			@RequestParam("memPw") String memPw,
//			@RequestParam(value="memName", required=false, defaultValue="tester") String name
			//true면 디폴트 값을 설정해주면 오류난다. name 값을 받아오지 못했기 때문에 오류가 남 반드시 값이 존재해야 함. 
			//false일 때는 값이 넘어오지 않고 기본값이 없어도 에러가 나지 않음
			//3. 커맨드 객체를 이용한 HTTP 정보 얻기
			//vo 객체에 동일한 setter가 있으면 자동으로 저장
			MemberVo mem
			) {
		
		//전통적 방법1
//		String memId = request.getParameter("memId");		
//		String memPw = request.getParameter("memPw");	
		
		System.out.println(mem.getMemId());
		System.out.println(mem.getMemPw());
		System.out.println(mem.getName());
		
		//4. Model
		//컨트롤러에서 뷰에 데이터를 전달하기 위해서 사용되는 객체로 Model과 ModelAndView가 있다.
		// 1) Model은 데이터만 전달
		// 2) ModelAndView 데이터와 뷰의 이름을 함께 전달
		
		//Model - addAttribute("전달이름", 값);
		model.addAttribute("memId", mem.getMemId());
		model.addAttribute("memPw", mem.getMemPw());

		model.addAttribute("mem", mem); //객체 자체를 넘기기
		
		return "member/memLogin_ok";
	}
	
	//5. ModelAndView 방식
	//뷰에서 커맨드 객체를 참조할 때 원하는 이름으로 변경 할 수 있다.
	@RequestMapping(value="/memLoginModelAndView", method=RequestMethod.POST)
	public ModelAndView memPut(Model model, MemberVo memVo) {
		//객체 생성
		ModelAndView mav = new ModelAndView();
		
		//ModelAndView에 값을 추가
		mav.addObject("memId", memVo.getMemId());
		mav.addObject("mem", memVo);
		
		//View 설정
		mav.setViewName("member/memLogin_ok");
		
		return mav;
	}
	
	
	//컨트롤러가 화면 뷰 리졸버에 리턴값을 넘겼을때 이동하는 방식 3가지
	@RequestMapping("/req_ex01") //리턴 타입 void는 맵핑 URI를 파일 이름으로 사용. return "req_ex01" 한 것과 같음
	//해당 위치에 실제 파일이 없어도 console은 찍힌다. 실행이 되었으니까. 화면에만 404 오류로 뜸
	public void req_ex01() {
		//접속 경로(URI 중 context를 뺀 경로) => /member/req_ex01
		System.out.println("req_ex01에 접근!");
	}
	
	@RequestMapping("/req_ex02")
	public String req_ex02() {
		return "member/req_ex02"; //스트링이기 때문에 view 리졸버로 바로 전달 ??
	}
	
	@RequestMapping("/req_ex03")
	public String req_03() {
		return "redirect:/member/req_ex01"; //sendRedirect와 같다.
		//리다이렉트가 뷰 리졸브로 전달되면 리다이렉트 값을 다시 컨트롤러에 요청하여 맞는 경로로 이동한다.
	}
	
	//@ResponseBody를 사용하면 리턴 값을 뷰리졸버에 경로로 전달하지 않고 바로 데이터로 보여준다
	@ResponseBody	//메서드에 리턴 값을 view 리졸버로 전달 X, 해당 메서드를 호출한 곳으로 결과를 반환. 비동기 통신에 사용
	@RequestMapping("/test")
	public String testResponseBody() {
		return "<h2>testResponseBody</h2>";
	}

}
