package org.doit.ik.mreview.repository;

import java.util.List;

import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	// 쿼리메서드 : 특정영화의 모든 리뷰 정보 조회
	List<Review> findByMovie(Movie movie);
}
