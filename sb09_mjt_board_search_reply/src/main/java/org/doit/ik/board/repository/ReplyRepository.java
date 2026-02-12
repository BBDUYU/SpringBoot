package org.doit.ik.board.repository;

import java.util.List;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	// [1] 기본 제공 메서드
	
	// [2] @Query + JPQL 메서드
	// 게시글을 삭제할때 먼저 댓글삭제
	// (기억) update, delete JPQL를 사용할 때는  @Modifying 어노테이션을 붙인다. 
	
	@Modifying
	@Query("DELETE FROM Reply r "
		+ " WHERE r.board.bno = :bno")
	void deleteByBno(@Param("bno") Long bno);
	
	// [3] 쿼리 메서드 : 게시글의 댓글 목록 조회
	List<Reply> getRepliesByBoardOrderByRno(Board board);
}
