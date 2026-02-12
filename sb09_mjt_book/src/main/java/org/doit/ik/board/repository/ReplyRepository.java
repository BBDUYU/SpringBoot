package org.doit.ik.board.repository;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	// update, delete JPQL을 사용할 때는 @Modifying 어노테이션을 붙인다 (중요)
	@Modifying
	@Query("DELETE FROM Reply r "
			+ "WHERE r.board.bno = :bno")
	void deleteByBno(@Param("bno") Long bno);
}
