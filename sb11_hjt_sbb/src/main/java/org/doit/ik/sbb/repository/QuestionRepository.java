package org.doit.ik.sbb.repository;

import java.util.List;

import org.doit.ik.sbb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	// 제목으로 검색할 수 있는 쿼리메서드 선언
	Question findBySubject(String subject);
	
	// 제목또는 내용으로 검색
	List<Question> findBySubjectContainingOrContentContaining(String subject, String content);
	
	// 제목또는 내용으로 검색 @Query 어노테이션
	@Query("SELECT q "
			+ "FROM Question q "
			+ "WHERE q.subject "
			+ "LIKE '%:keyword%' OR q.content LIKE '%:keyword%'")
	List<Question> searchSubjectContent(@Param("keyword") String keyword);
}
