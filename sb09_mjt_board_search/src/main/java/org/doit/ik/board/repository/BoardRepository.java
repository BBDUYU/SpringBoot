package org.doit.ik.board.repository;

import java.util.List;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository{

	// [1] JPQL  연관관계 O    Board + Member 조인 쿼리
	//  Object  <-  Object [] ,   [ Board, Member ]
	@Query("SELECT b, w "
			+ "FROM Board b LEFT JOIN b.writer w "
			+ "WHERE b.bno = :bno")
	Object getBoardWithWriter(@Param("bno") Long bno);

	// [2] JPQL  연관관계 X   Board + Reply  조인 쿼리   + ON 구문 설정
	@Query("SELECT b, r "
			+ "FROM Board b LEFT JOIN Reply r ON r.board = b "
			+ "WHERE b.bno = :bno")
	List<Object[]> getBoardWithReply(@Param("bno") Long bno);

	// [3] 게시판 목록: Board + Member + Reply  조인
	@Query(
			value = "SELECT b, w, COUNT(r) "
					+ "FROM Board b LEFT JOIN Reply r ON r.board = b "
					+ "             LEFT JOIN b.writer w "
					+ "GROUP BY b "
					, countQuery = "SELECT COUNT(b) FROM Board b" )
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);

	// [4] 게시판 조회(상세보기): Board + Member + Reply  조인
	@Query(
			value = "SELECT b, w, COUNT(r) "
					+ "FROM Board b LEFT JOIN Reply r ON r.board = b "
					+ "             LEFT JOIN b.writer w "
					+ "WHERE b.bno = :bno " )
	Object getBoardByBno(@Param("bno") Long bno);

}
