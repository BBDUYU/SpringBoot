package org.doit.ik;

import java.util.stream.IntStream;

import org.doit.ik.board.entity.Member;
import org.doit.ik.board.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTests {
	
	@Autowired
	private MemberRepository memberRepository;

	// 1. 100명의 회원 추가
	
	@Test
	void insertMembers() {
		
		IntStream.rangeClosed(1, 100).forEach(i-> {
			// 1) Member 엔티티 * 100명
			Member member = Member.builder()
					.email("user"+i+"@naver.com")
					.name("USER"+i)
					.password("1234")
					.build();
			// 2) memberRepository.save(Member 엔티티)	
			this.memberRepository.save(member);
		});
		/*
		for (int i = 1; i <= 100; i++) {
			
		}
		*/
		
	}

}



