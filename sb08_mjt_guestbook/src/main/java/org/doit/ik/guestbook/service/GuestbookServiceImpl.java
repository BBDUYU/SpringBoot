package org.doit.ik.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.entity.QGuestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

	private final GuestbookRepository guestbookRepository;

	@Override
	public Long register(GuestbookDTO guestbookDTO) {
		log.info("🤩🤩🤩 GuestbookServiceImpl.register()...");

		// GuestbookDTO -> Guestbook 변환
		Guestbook entity = this.dtoToEntity(guestbookDTO);		
		this.guestbookRepository.save(entity);
		return entity.getGno();
	}



	/* [1] 방명록 목록
	@Override
	public Page<GuestbookDTO> getList() {
		log.info("🤩🤩🤩 GuestbookServiceImpl.getList()...");		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending() );		
		Page<Guestbook> result =  this.guestbookRepository.findAll( pageable); 		
		// Page<Guestbook> -> Page<GuestbookDTO> 변환
		return result.map(entity -> entityToDto(entity));		 
	}
	 */

	// [2] 방명록 목록
	/*
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
		log.info("🤩🤩🤩 GuestbookServiceImpl.getList()... [2]");	
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("gno").descending());
		Page<Guestbook> result =  this.guestbookRepository.findAll( pageable);		
		// Page<Guestbook> -> PageResultDTO<GuestbookDTO, Guestbook> 변환 코딩.
		Function<Guestbook, GuestbookDTO> fn = entity -> entityToDto(entity);
		return new PageResultDTO<>(result, fn);
	}
	*/

	@Override
	public GuestbookDTO read(Long gno) {
		log.info("🤩🤩🤩 GuestbookServiceImpl.read()... ");
		Optional<Guestbook> result = this.guestbookRepository.findById(gno);
		GuestbookDTO dto = null;
		if (result.isPresent()) {
			Guestbook  entity = result.get();
			dto = entityToDto(entity);
		}
		return dto;
	}


	@Override
	public void modify(GuestbookDTO guestbookDTO) {
		log.info("🤩🤩🤩 GuestbookServiceImpl.modify()... ");
		Optional<Guestbook> result = this.guestbookRepository.findById(guestbookDTO.getGno());
		GuestbookDTO dto = null;
		if (result.isPresent()) {
			Guestbook entity = result.get();
			entity.changeTitle(guestbookDTO.getTitle());      // 수정 제목
			entity.changeContent(guestbookDTO.getContent());  // 수정 내용
			this.guestbookRepository.save(entity); // update
		}
	}
 
	@Override
	public void remove(Long gno) {		
		log.info("🤩🤩🤩 GuestbookServiceImpl.remove()... ");
		this.guestbookRepository.deleteById(gno);
	}
	
	// 검색 처리                                  검색조건, 검색어
	private BooleanBuilder getSearchPredicate(  PageRequestDTO pageRequestDTO ) {
		String type = pageRequestDTO.getType();			// 검색조건
		String keyword = pageRequestDTO.getKeyword();   // 검색어
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		// 자바 코딩으로 쿼리 작성: Querydsl
		//                        WHERE gno > 0
		BooleanExpression expr1 = qGuestbook.gno.gt(0L); 
		booleanBuilder.and(expr1);
		
		if ( type==null || type.trim().length() ==0 ) {
			return booleanBuilder;
		}
		
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		
		String [] typeArr = type.split("");  // type:  "t"  "tc"  "tcw"
		for (String t : typeArr) {
			if (t.equals("t")) {              //   title LIKE '%키워드%'
				BooleanExpression exprTitle = qGuestbook.title.contains(keyword); 
				conditionBuilder.or(exprTitle);
			}else if (t.equals("w")) {        //   writer LIKE '%키워드%'
				BooleanExpression exprWriter = qGuestbook.writer.contains(keyword); 
				conditionBuilder.or(exprWriter);
			}else if (t.equals("c")) {        //   content LIKE '%키워드%' 
				conditionBuilder.or( qGuestbook.content.contains(keyword) );
			}			
		}
		
		// title LIKE '%키워드%' OR writer LIKE '%키워드%' OR content LIKE '%키워드%'		
		booleanBuilder.and(conditionBuilder);
		
		return booleanBuilder;
	}

	// [3] 방명록 목록 + 검색 기능 추가	 
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
		log.info("🤩🤩🤩 GuestbookServiceImpl.getList()... [3]");	
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("gno").descending());
		BooleanBuilder booleanBuilder = this.getSearchPredicate(pageRequestDTO);
		Page<Guestbook> result =  this.guestbookRepository.findAll(booleanBuilder, pageable);		
		// Page<Guestbook> -> PageResultDTO<GuestbookDTO, Guestbook> 변환 코딩.
		Function<Guestbook, GuestbookDTO> fn = entity -> entityToDto(entity);
		return new PageResultDTO<>(result, fn);
	}
}







