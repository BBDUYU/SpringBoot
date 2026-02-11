package org.doit.ik;

import java.util.Optional;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.doit.ik.guestbook.service.GuestbookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuestbookServiceTests {

	@Autowired
	private GuestbookService guestbookService;

	/*
	@Test
	void registerTest() {
		GuestbookDTO dto = GuestbookDTO.builder()
				.title("새글")
				.content("새내용")
				.writer("홍길동")
				.build();
		Long gno = this.guestbookService.register(dto);	
		System.out.println("🤩🤩 " + gno + "글 등록!!!");
	}
	*/
	

}











