package org.doit.ik;

import java.util.Optional;

import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuestbookRepositoryTests {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	/* 
	@Test
	void insertDummies() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			
			Guestbook entity = Guestbook.builder()
					.title("title..."+i)
					.content("content..."+i)
					.writer("user"+(i%10))
					.build();
			
			System.out.println("😘 " + this.guestbookRepository.save(entity) );
			
		});
	}
	*/ 
	
	 
	// 
	/*
	@Test
	void updateTest() {
		
		Optional<Guestbook> result = this.guestbookRepository.findById(300L);
		
		if (result.isPresent()) {
			// entity  gno=300L
			Guestbook guestbook = result.get();
			guestbook.changeTitle("Changed title...");
			guestbook.changeContent("Changed Content...");
			this.guestbookRepository.save(guestbook);
		}
	}
	*/
 
	
	/*
	// *** Querydsl 사용해서 처리... : 방명록 검색 구현
	// 1. 단일 항목 검색 : 제목/내용/작성자
	// 2. 다중 항목 검색 : 제목+내용/내용+작성자/제목+작성자
	//                  제목+내용+작성자
	@Test
	void querydslTest() {
		
		// Q도메인클래스 객체 생성
		QGuestbook qGuestbook = QGuestbook.guestbook;
		// 1) BooleanBuilder 객체 생성
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		// 2) 검색 조건  BooleanExperssion 객체 생성 
//		String type = "tcw";
		String keyword = "1";
		
		// title LIKE '%1%' or content LIKE '%1%'
		BooleanExpression exprTitle = qGuestbook.title.contains(keyword);		
		BooleanExpression exprContent = qGuestbook.content.contains(keyword);		
		BooleanExpression exprAll =  exprTitle.or(exprContent);
		
		booleanBuilder.and(exprAll);
		
		// 
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending() );
		Page<Guestbook> result =  this.guestbookRepository.findAll( booleanBuilder, pageable);
		
		result.stream().forEach(guestbook->{
			System.out.println("🤩👍 " + guestbook);
		});
	}
	*/

}











