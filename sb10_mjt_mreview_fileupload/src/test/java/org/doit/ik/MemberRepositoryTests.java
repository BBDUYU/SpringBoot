package org.doit.ik;

import java.util.UUID;
import java.util.stream.IntStream;

import org.doit.ik.mreview.entity.Member;
import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.MovieImage;
import org.doit.ik.mreview.repository.MemberRepository;
import org.doit.ik.mreview.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@RequiredArgsConstructor
@SpringBootTest
class MemberRepositoryTests {
	@Autowired
	private  MemberRepository memberRepository;
	@Autowired
	private  ReviewRepository reviewRepository;
	
	// 100명의 회원 insert
/*
	@Test
	void insertMembers() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			log.info("@ MemberRepositoryTests.insertMembers()...");
			Member member=Member.builder()
					.email("m"+i+"@doit.com")
					.pw("1234")
					.nickname("reviewer"+i)
					.build();
			this.memberRepository.save(member);
			//	
		});
	}
*/
	
	// 리뷰삭제 + 회원탈퇴 

	@Transactional
	@Commit
	@Test
	void testDeleteMember() {
		Long mid = 2L;
		Member member = Member.builder()
				.mid(mid)
				.build();
		this.reviewRepository.deleteByMember(member);
		this.memberRepository.delete(member);
	}

}
