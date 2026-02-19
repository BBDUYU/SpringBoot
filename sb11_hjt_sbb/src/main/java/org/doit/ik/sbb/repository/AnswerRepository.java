package org.doit.ik.sbb.repository;

import java.util.List;

import org.doit.ik.sbb.entity.Answer;
import org.doit.ik.sbb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	// [1] 커스텀 쿼리메서드
	List<Answer> findByQuestionId(Integer questionId);
	List<Answer> findByQuestion(Question entity);
	
	// [2] JPQL
	@Query("select a from Answer a where a.question.id = :qid")
	List<Answer> findByQId(@Param("qid") Integer questionId);
}
