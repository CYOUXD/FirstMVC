package org.zerock.service;

import java.util.List;

import org.zerock.command.BoardVO;

public interface BoardService {
	//board 등록 메서드(이름, 제목, 내용)
	public void register(String name, String title, String content);
	//모든 게시물 가져오는 메서드
	public List<BoardVO> getList();
	//게시글 삭제 메서드(게시글 번호=리스트의 인덱스번호)
	public void delete(String num); //전달 값은 int로 받을 수 없음
}
