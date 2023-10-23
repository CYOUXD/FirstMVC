package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.command.BoardVO;
import org.zerock.dao.BoardDAO;

//@Component
//@Component("boardService") - boardService : 빈에 등록 될 이름(id)

@Service("boardService") //= 컴포넌트의 일종. 빈 그래프에 등록이 됨
//3. 어노테이션을 이용한 빈 등록 - component-scan
public class BoardServiceImpl implements BoardService {
	
	//BoardDAO 인터페이스 객체 자동 연결 방법 3가지
	//1. new
//	BoardDAO boardDAO = new BoardDAOImpl();
	
	//2. xml에 bean 등록 처리
	@Autowired
	BoardDAO boardDAO;
	//servlet-context.xml에 빈 등록
	//<beans:bean  id="boardDAO" class="org.zerock.service.BoardDAOImpl" />
	//id의 첫글자를 대문자로하면 클래스를 의미하게 되므로 오류가 남
	//id 첫글자를 대문자로 쓰면 연결할 게 두 개라고 뜨는데 소문자로 하면 제대로 잘 연결 된다.
	//id="BoardDAO" 라고 쓰면 연결 할 게 2개라고 뜨는데 그 중 boardDAOImpl은 BoardDAOImple.java를 의미한다. 첫글자 소문자로 쓸 것.

	//3. 어노테이션 사용하여 빈에 등록
	//2.를 놔두고 sevlet-context.xml 에 추가 했던건 주석처리하고 위에 @Service("boardService") 추가해 줌
	
	
	@Override
	public void register(String name, String title, String content) {
		//boardRegister.jsp 로 부터 전달 받은 값 확인
		System.out.println("----------- service 계층 ------------");
		System.out.println(name);
		System.out.println(title);
		System.out.println(content);
		
		boardDAO.boardInsert(name, title, content);
		
	}

	@Override
	public List<BoardVO> getList() {
		List<BoardVO> List = boardDAO.boartSelect();
		return List;
	}

	@Override
	public void delete(String num) {
		//System.out.println("삭제 index : "+num);
		boardDAO.boardDelete(num);

	}

}
