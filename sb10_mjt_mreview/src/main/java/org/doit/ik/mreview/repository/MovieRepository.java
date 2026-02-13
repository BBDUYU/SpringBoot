package org.doit.ik.mreview.repository;

import java.util.List;

import org.doit.ik.mreview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	// 영화 + 영화이미지 + 평점
	@Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) "
			+ " from Movie m "
			+ "    left outer join Review r on r.movie = m "
			+ "    left outer join MovieImage mi on mi.movie = m "
			+ " group by m, mi ")
	Page<Object[]> getListPage(Pageable pageable);

	// 특정 영화정보와 이미지 정보
	@Query( "select m, mi "
			+ " from Movie m "
			+ " left outer join MovieImage mi on mi.movie = m "
			+ " where m.mno = :mno " )
	List<Object[]> getMovieWithAll(@Param("mno")Long mno); 
}
