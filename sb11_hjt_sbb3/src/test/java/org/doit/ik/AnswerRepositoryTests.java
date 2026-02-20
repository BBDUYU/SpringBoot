package org.doit.ik;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.doit.ik.sbb.answer.Answer;
import org.doit.ik.sbb.answer.AnswerRepository;
import org.doit.ik.sbb.question.Question;
import org.doit.ik.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class AnswerRepositoryTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	// [1] 답변작성
	/*
	@Test
	void testJpa() {
		Optional<Question> op = this.questionRepository.findById(2);
		if(op.isPresent()) {
			Question q = op.get();
			Answer a=new Answer();
			a.setContent("두번째 답변");
			a.setQuestion(q);
			a.setCreateDate(LocalDateTime.now());
			this.answerRepository.save(a);
		}  
	}
	*/
	
	// 문제) 질문 2의 답변이 2개
	// 질문2의 답변정보를 출력하는 단위테스트
	/*
	@Test
	@Transactional
	void testJpa() {
		Optional<Question> op = this.questionRepository.findById(2);
		if(op.isPresent()) {
			Question q = op.get();
			List<Answer> list=q.getAnswerList();
			for (Answer answer : list) {
				System.out.println(answer.getContent());
			}
		}  
	}
	*/
	@Test
	@Transactional
	void testJpa() {
		Optional<Question> op = this.questionRepository.findById(2);
		if(op.isPresent()) {
			Question q =op.get();
			List<Answer> answerList = q.getAnswerList();
			log.info("> "+answerList.size());
		}
	}
}
