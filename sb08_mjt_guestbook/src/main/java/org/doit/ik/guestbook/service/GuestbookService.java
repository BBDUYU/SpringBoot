package org.doit.ik.guestbook.service;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;

public interface GuestbookService {
	Long register(GuestbookDTO guestbookDTO);

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

	Page<GuestbookDTO> getList();
	
}
