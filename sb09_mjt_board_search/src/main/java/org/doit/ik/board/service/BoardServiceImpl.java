package org.doit.ik.board.service;


import java.util.Optional;
import java.util.function.Function;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.dto.PageResultDTO;
import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Member;
import org.doit.ik.board.repository.BoardRepository;
import org.doit.ik.board.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;

	@Override
	public Long register(BoardDTO boardDTO) {
		log.info("😝 BoardServiceImpl.register()..." + boardDTO);
		// BoardDTO -> Board 엔티티로 변환
		Board entity = this.dtoToEntity(boardDTO);
		this.boardRepository.save(entity);
		
		return entity.getBno(); // 등록된 글번호
	}

	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info("😝 BoardServiceImpl.getList()..." + pageRequestDTO);		
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("bno").descending());
		Page<Object []> result = this.boardRepository.getBoardWithReplyCount(pageable);		
		Function<Object[], BoardDTO> fn = ( en -> entityToDto( (Board)en[0], (Member)en[1], (Long)en[2] ) );	
		// Page<Object[]>  -> PageResultDTO 변환		
		return new PageResultDTO<>(result, fn); // 생성자 
	}

	@Override
	public BoardDTO get(Long bno) {
		log.info("😝 BoardServiceImpl.get()..." + bno);	
		Object result = this.boardRepository.getBoardByBno(bno);
		Object [] arr = (Object []) result;  // Board, Member, Replay
		BoardDTO dto = entityToDto((Board)arr[0], (Member)arr[1], (Long)arr[2]);
		return dto;
	}

	@Override
	public void modify(BoardDTO dto) {
		log.info("😝 BoardServiceImpl.modify()..." + dto);	
		
		// Optional<Board> result = this.boardRepository.findById(dto.getBno());		
		Board entity = this.boardRepository.getReferenceById(dto.getBno());
		if( entity !=  null) {
			entity.changeTitle(dto.getTitle());
			entity.changeContent(dto.getContent());
			// BoardDTO -> Board 엔티티 변환
			this.boardRepository.save(entity);
		}
		
		
	}

	@Transactional
	@Override
	public void removeWithReplies(Long bno) {
		log.info("😝 BoardServiceImpl.removeWithReplies()..." + bno);	
		// 1. 댓글 먼저 삭제
		// X this.replyRepository.deleteById(bno);  댓글 PK
		this.replyRepository.deleteByBno(bno);
		// 2. 게시글 삭제
		this.boardRepository.deleteById(bno);
		
	}

}
