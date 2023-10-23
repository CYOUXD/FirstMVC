package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.member.model.SampleDTO;
import org.zerock.member.model.SampleDTOList;

@Controller
@RequestMapping("/sample/*")	//매핑할 내용을 지정하는 어노테이션. /*는 생략 가능
public class SampleController {
	
	//경로가 sample로 기본 경로 설정
	@RequestMapping("")	// 디렉토리 내부에 있는 값을 의미 URL에서 접근 할 때 sample뒤에 /까지 입력해야 한다.
	public void basic() {
		System.out.println("basic!");
	}
	
	//경로 sample/basic인 경우
	@RequestMapping("/basic")
	public void basicGet() {
		System.out.println("basic get!");
	}
	
	@GetMapping("/basicOnlyGet")
	//localhost:8080/sample/basicOnlyGet에 Post 방식으로 보내면 basicGet()이 실행된다.
	//get일 때만 실행된다.
	public void basicGet2() {
		System.out.println("basic only get!");
	}
	//포스트맨에서 여기 주소를  get 방식으로 바꾸면 접속되지 않고 basic()의 내용이 뜨는 이유는 
	//컨트롤러의 @RequestMapping("/sample/*")이기 때문에 여기에 접속 되지 않으면 적용 할 수 있는건
	//@RequestMapping("")이 비어있는 basic() 밖에 없다.
		
	
	//SampleDTO[name, age]를 데이터로 가지는 클래스
	//URI에서 get 방식으로 정보 전달
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		System.out.println(dto);
		
		return "ex01";
	}
	
	
	// /sample/ex02의 개별적 파라미터 처리 어노테이션
	@GetMapping("/ex02")
	public String ex01(
			@RequestParam("name") String name,
			@RequestParam("age") String age
			) {
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
		return "ex02";
	}
	
	
	// "/sample/ex02List"에 리스트 값을 처리하는 어노테이션
	//동일한 값으로 여러개의 값이 전달되는 경우 처리 방식
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		System.out.println("ids : "+ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		System.out.println("ids : "+Arrays.toString(ids));
		return "ex02Array";
	}
	
	
	//sampleDTO 리스트는 sampleDTOList 클래스를 고민해야 합니다.	
	//실행 안됨 나중에 다시 확인
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		System.out.println("list dtos :"+list);
		return "ex02Bean";
	}
	
	
	//@@InitBinder를 사용하여 데이터를 변환 - Controller에서 데이터 변환
	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) { SimpleDateFormat
	 * dataformat = new SimpleDateFormat("yyyy-MM-dd");
	 * binder.registerCustomEditor(java.util.Date.class, new
	 * CustomDateEditor(dataformat, false)); }
	 */
	
	//int page 값이 있으면 page로 넘김 
	//DTO는 값이 없어도 되는데 page 값은 @ModelAttribute의 값이기 때문에 반드시 있어야 한다.
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		System.out.println("dto :" +dto);
		System.out.println("page :" +page);
		
		return "sample/ex04";
	}
	
	
	/*
	 * RedirectAttribute : Model 타입과 더불어 스프링 MC가 자동으로 전달해 주는 타입 중 
	 * RedirectAttribute가 존재함
	 * 
	 * JSP에서 response.sendRedirect("/home?name=aaa&page10");과 유사한 경우
	 * 
	 * Spring에서는 
	 * 	rttr.addFlashAttribute("name", "aaa");
	 * 	rttr.addFlashAttribute("age", 10);
	 * 
	 * return "redirect:/home";
	 * **리다이렉트 시 값을 전달하지만 일회성 데이터를 전달함
	 */
	
	@GetMapping("/home2")
	public String home2(RedirectAttributes rttr) {
		rttr.addFlashAttribute("name", "aaa");
		rttr.addFlashAttribute("age", 10);
		return "redirect:/";
	}
	
	//json 사용
	@GetMapping("/ex05")
	@ResponseBody	//데이터로 처리할 때 사용. 뷰 리졸브를 거치지 않고 처리됨
	public SampleDTO ex05() {
		System.out.println("/ex05 --- VO 또는 DTO와 같은 복합데이터가 있는 객체 타입");
		SampleDTO dto = new SampleDTO();
		dto.setName("홍길동");
		dto.setAge("200");
		
		return dto;
	}
	
	
	//json 사용
	@GetMapping("/ex051")
	   @ResponseBody 
	   public List<SampleDTO> ex051() {
	      System.out.println("/ex051 vo 또는 DTO와 같은 복합데이터가 있는 객체 타입");
	      List<SampleDTO> list = new ArrayList<>();
	      
	      SampleDTO dto = new SampleDTO();
	      dto.setName("홍길동");
	      dto.setAge("200");
	      list.add(dto);
	      
	      SampleDTO dto2 = new SampleDTO();
	      dto2.setName("홍길자");
	      dto2.setAge("100");
	      list.add(dto2);
	      
	      return list;
	   }
	  
	
	
	//ResponseEntity 타입 : 웹 프로그램을 다룰 때에 HTTP 프로토콜 헤더를 다루는 경우에 사용
	/*
	 * 스프링은 기본적으로 HttpServletRequest나 HttpServletResponse를 직접 핸들링하지 않음
	 * 이 때 사용하는 것이 ResponseEntity이다.
	 */
	@GetMapping("/ex06")
	public ResponseEntity<String> ex06() {
		System.out.println("/ex06 동작!");
		
		//{"name" : "홍길동"}
		String msg = "{\"name\":\"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders(); //HttpHeaders : spring으로 선택
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	
	//파일 업로드 처리
	/*
	 * servlet이 2.5인 경우에는 commons의 파일 업로드를 사용하던지 cos.jar 파일을 불러와서 사용했음.
	 * servler 3.0버전 이후에는 Tomcat 자체에서 지원하고 있기 때문에 별도의 라이브러리를 불러올 필요 없음
	 * 현재는 2.5가 기본으로 생성되기 때문에 테스트용으로 확인
	 * 
	 * - pom.xml에서 common-fileupload 라이브러리를 추가
	 * - servlet-context.xml에서 일부를 추가 지정
	 */
	@GetMapping("/exUpload")
	public void exUpload() {
		System.out.println("/exUpload");
	}
	
	@PostMapping("/sample/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) { 
		//가져올 데이터의 name과 같은 이름이어야 제대로 읽어온다.
		files.forEach(file -> {
			System.out.println("-------------------------------------");
			System.out.println("name : " + file.getOriginalFilename());
			System.out.println("size : " + file.getSize());
			
//			file.getInputStream();

		});
	}
	
	
}
