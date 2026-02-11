package org.doit.ik.board.service;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.dto.PageResultDTO;
import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Member;

public interface BoardService {
	
	
	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder()
				.email(dto.getWriterEmail())
				.build();
		Board entity=Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		return entity;
	}
	
	default BoardDTO entityToDto(Board board, Member member,Long replyCount) {
		BoardDTO dto = BoardDTO.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())
				.build();
				return dto;
	}
	// [1] 게시글 등록
	Long register(BoardDTO boardDTO);
	// [2] 게시글 목록
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	// [3] 게시글 조회(상세보기)
	BoardDTO get(Long bno);

	// [4] 게시글 수정
	void modify(BoardDTO dto);
	
	
}
