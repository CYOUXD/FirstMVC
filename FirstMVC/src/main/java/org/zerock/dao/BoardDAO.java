package org.zerock.dao;

import java.util.List;

import org.zerock.command.BoardVO;

public interface BoardDAO {
	//추상 메서드
	//추가
	public void boardInsert(String name, String title, String content);
	//전체 불러오기
	public List<BoardVO> boartSelect();
	//삭제
	public void boardDelete(String num);	
}
