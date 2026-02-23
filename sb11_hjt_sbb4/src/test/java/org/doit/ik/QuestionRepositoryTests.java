package org.doit.ik;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.doit.ik.sbb.question.Question;
import org.doit.ik.sbb.question.QuestionRepository;
import org.doit.ik.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class QuestionRepositoryTests {

   @Autowired
   private QuestionRepository questionRepository;
   
   // 문제) 2번 Question을 제목과 내용을 수정하는 단위테스트
   /*
   @Test
   void testJpa() {
	   // 제목 : 수정 제목
	   // 내용 : 수정 내용
	   Optional<Question> op = this.questionRepository.findById(2);
	   if(op.isPresent()) {
		   Question q = op.get();
		   q.setSubject("수정 제목");
		   q.setContent("수정 내용");   
		   
		   this.questionRepository.save(q);
	   	
	   }  
	}
   */
   /*
   @Test
   void testJpa() {
	   // 질문1번 삭제
	   //[1]
	   //this.questionRepository.deleteById(1);
	   //[2]
	   Optional<Question> op = this.questionRepository.findById(1);
	   if(op.isPresent()) {
		   Question q = op.get();
		   this.questionRepository.delete(q);
	   }  
   }
   */
   // 문제) 질문의 제목 또는 내용에 sbb가 들어있는 질문을 조회하는 커스텀 쿼리 메서드를 작성하고 단위테스트 진행
   /*
   @Test
   void testJpa() {
	   String keyword="sbb";
	   List<Question> list = this.questionRepository.findBySubjectContainingOrContentContaining(keyword, keyword);
	   		for (Question question : list) {
				log.info("@ "+question.getSubject());
			}
   }
   */
   /*
   @Test
   void testJpa() {
	   Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		   log.info("@ "+q.getContent());
   }
   */
   /*
   @Test
   void testJpa() {
	   Optional<Question> op = this.questionRepository.findById(1);
	   if(op.isPresent()) {
		   Question q = op.get();
		   log.info("@ "+q.getSubject());
	   }
   }
   */
   /*
   @Test
   void testJpa() {
	   List<Question> list = this.questionRepository.findAll();
	   assertEquals(2, list.size());
	   
	   Question q = list.get(0);
	   assertEquals("sbb가 무엇인가요?", q.getSubject());
   }
   */
   /*
   @Test
   void testJpa() {
      
      Question q1 = new Question();
      q1.setSubject("sbb가 무엇인가요?");
      q1.setContent("sbb에 대해서 알고 싶습니다.");
      q1.setCreateDate(LocalDateTime.now());
      this.questionRepository.save(q1);
      
      Question q2 = new Question();
      q2.setSubject("스프링부트 모델 질문입니다");
      q2.setContent("id는 자동으로 생성 되나요?");
      q2.setCreateDate(LocalDateTime.now());
      this.questionRepository.save(q2);
      
      
      
   }
*/
   @Autowired
   private QuestionService questionService;
   
   @Test
   void testJpa() {
      for (int i = 1; i <= 300; i++) {
         String subject = String.format("테스트 에디터입니다: [%03d]", i);
         String content = "냉무";
         this.questionService.create(subject, content,null);
      }
   }
   
}
