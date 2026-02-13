package org.doit.ik;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.MovieImage;
import org.doit.ik.mreview.repository.MovieImageRepository;
import org.doit.ik.mreview.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@RequiredArgsConstructor
@SpringBootTest
class MovieRepositoryTests {
	@Autowired
	private  MovieImageRepository movieImageRepository;
	@Autowired
	private  MovieRepository movieRepository;
	
	// 100개의 영화 insert

	@Test
	void insertMovies() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			// Movie 엔티티 생성 + save(엔티티)
			//			ㄴ MovieImage 엔티티 객체 생성 * 임의의 수 N개
			log.info("@ MovieRepositoryTests.insertMovies()...");
			Movie movie=Movie.builder()
					.title("movie title..."+i)
					.build();
			this.movieRepository.save(movie);
			//
			int count = (int)(Math.random()*5)+1; //1~5 임의의 수
			for (int j = 0; j < count; j++) {
				MovieImage movieImage = MovieImage.builder()
						.movie(movie)
						.uuid(UUID.randomUUID().toString())
						.imgName("test"+j+".jpg")
						.build();
				this.movieImageRepository.save(movieImage);
			}
		});
	}
	
	/*
	@Test
	void testGetListPage() {
		//Page<Object[]> getListPage(Pageable pageable);
		Pageable pageable = PageRequest.of(0, 10,Sort.by("mno").descending());
		Page<Object[]> result=this.movieRepository.getListPage(pageable);
		
		// List<Object[]> objectArr=result.getContent();
		for (Object[] objects : result.getContent()) {
			System.out.println("@ "+Arrays.toString(objects));
		}
	}
	*/
	/*
	@Test
	void testGetMovieWithAll() {
		Long mno = 1L;
		List<Object[]> result = this.movieRepository.getMovieWithAll(mno);
		System.out.println("@ @ @"+result);
		result.forEach(movie->{
			System.out.println("$ $"+movie[0]+" / "+movie[1]);
		});
	}
	*/
}
