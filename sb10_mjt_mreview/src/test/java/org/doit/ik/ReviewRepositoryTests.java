package org.doit.ik;

import java.util.List;
import java.util.stream.IntStream;

import org.doit.ik.mreview.entity.Member;
import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.Review;
import org.doit.ik.mreview.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class ReviewRepositoryTests {


	@Autowired
	private  ReviewRepository reviewRepository;

	// 200개의 영화리뷰 insert
	/*
	@Test
	void insertMovieReviews() {
		IntStream.rangeClosed(1, 200).forEach(i->{
			log.info("@ ReviewRepositoryTests.insertMovieReviews()...");
			// 영화번호 랜덤하게 발생... 1 ~ 100 임의의 수
			Long mno = (long)(Math.random()*100)+1;
			// 리뷰를 적은 고객
			Long mid = (long)(Math.random()*100)+1;
			Review review=Review.builder()
					.grade((int)(Math.random()*5)+1)
					.text("리뷰 테스트"+i)
					.movie(Movie.builder().mno(mno).build())
					.member(Member.builder().mid(mid).build())
					.build();

			this.reviewRepository.save(review);

		});
	}
	 */

	@Test
	void testFindByMovie() {
		Movie movie = Movie.builder()
				.mno(93L)
				.build();
		List<Review> result = this.reviewRepository.findByMovie(movie);
		
		result.forEach(movieReviw->{
	         System.out.println("🎶" +movieReviw.getReviewnum());
	         System.out.println("\t"+movieReviw.getGrade());
	         System.out.println("\t"+movieReviw.getText());
	         System.out.println("\t"+movieReviw.getMember().getEmail());
	         System.out.println("-".repeat(70));
	      });
	}
}

