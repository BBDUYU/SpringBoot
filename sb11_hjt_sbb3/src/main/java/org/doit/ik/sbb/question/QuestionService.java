package org.doit.ik.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.doit.ik.sbb.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;

	public List<Question> getList() {
		return this.questionRepository.findAll();
	}

	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);

		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("Question Not Found");
		}
	}

	// [1] 질문 등록 메서드
	public void create(String subject, String content) {
		Question q = new Question();

		// id는 자동증감
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());

		this.questionRepository.save(q);
	}

	// [2] 질문 등록
	public void create(QuestionForm questionForm) {
		Question q = new Question();
		q.setSubject(questionForm.getSubject());
		q.setContent(questionForm.getContent());
		q.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q);

	}

	   // 페이징 처리 O
	   public Page<Question> getList(int page, int size){
	                                 // 0(1번페이지)
	      Pageable pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
	      return this.questionRepository.findAll(pageable);
	   }


}
