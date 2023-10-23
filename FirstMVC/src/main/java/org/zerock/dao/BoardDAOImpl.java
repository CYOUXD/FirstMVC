package org.zerock.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.zerock.command.BoardVO;

//DAO에 객체 주입은 일반적으로 Component나 Repository 어노테이션 사용
//Component-scan에 꼭 추가해야 한다. scan 영역 안에 있어야 인식한다.
@Repository
public class BoardDAOImpl implements BoardDAO {

	//DB로 가정할 리스트
	List<BoardVO> DB = new ArrayList<>();

	@Override
	public void boardInsert(String name, String title, String content) {
		System.out.println("-------- DAO 계층 --------");
		System.out.println(name);
		System.out.println(title);
		System.out.println(content);
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setTitle(title);
		vo.setContent(content);
		
		DB.add(vo);
		//System.out.println("현재 게시글 수 : "+DB.size());
	}

	@Override
	public List<BoardVO> boartSelect() { //getList()에서 실행될 예정 모든 DB 불러와서 반환
		return DB;
	}

	@Override
	public void boardDelete(String num) {
		DB.remove(Integer.parseInt(num));
	}

}
