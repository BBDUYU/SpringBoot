package org.doit.ik.mreview.repository;

import java.util.List;

import org.doit.ik.mreview.entity.Member;
import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	// 쿼리메서드 : 특정영화의 모든 리뷰 정보 조회
	// Member 회원정보 X : Lazy 지연로딩으로 fetch 설정 - 
	@EntityGraph(attributePaths = {"member"},type=EntityGraphType.FETCH)
	List<Review> findByMovie(Movie movie);

	
	// 회원 탈퇴하면 회원이 작성한 리뷰도 모두 삭제할 때 사용할 쿼리 메서드
	// update, delete -> @Modifying 어노테이션 붙이기
	@Modifying
	@Query("delete from Review mr where mr.member = :member")
	void deleteByMember(@Param("member") Member member);
}
