package org.doit.ik.guestbook.service;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

	private final GuestbookRepository guestbookRepository;
	
	@Override
	public Long register(GuestbookDTO guestbookDTO) {
		log.info("@ @ @ GuestbookServiceImpl.register()...");
		
		// GuestbookDTO -> Guestbook 변환
		Guestbook entity = this.dtoToEntity(guestbookDTO);
		
		this.guestbookRepository.save(entity);
		return entity.getGno();
	}

	@Override
	public Page<GuestbookDTO> getList() {
		log.info("@ @ @ GuestbookServiceImpl.getList()...");
		Pageable pageable = PageRequest.of(0, 10,Sort.by("gno").descending());
		Page<Guestbook> result = this.guestbookRepository.findAll(pageable);
		
		return result.map(entity -> entityToDto(entity));
	}
	
	
}
