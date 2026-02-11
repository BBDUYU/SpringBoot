package org.doit.ik.guestbook.service;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;

public interface GuestbookService {
	
	// 1. 방명록 쓰기
	Long register(GuestbookDTO guestbookDTO);
	
	// 2. 방명록 목록
	// [1] 페이징 처리 X 
	// Page<GuestbookDTO> getList();
	// [2] 페이징 처리 O
	PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO);

	// 3. 방명록 조회(상세보기)
	GuestbookDTO read(Long gno);
	
	// 4. 방명록 수정
	void modify(GuestbookDTO guestbookDTO);	
	
	// 5. 방명록 삭제
	void remove(Long gno);
	
	// DTO -> Entity 변환 메서드
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();		
		return entity;
	}

	// Entity -> DTO 변환 메서드
	default GuestbookDTO entityToDto(Guestbook entity) {
		GuestbookDTO dto = GuestbookDTO.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();		
		return dto;
	} 

}
