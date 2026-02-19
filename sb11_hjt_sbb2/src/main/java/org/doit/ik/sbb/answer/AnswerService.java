package org.doit.ik.sbb.answer;

import java.time.LocalDateTime;

import org.doit.ik.sbb.question.Question;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

   private final AnswerRepository answerRepository;
   
   // [1] 답변 등록
   public void create(Question question, String content) {
      Answer answer = new Answer();
      answer.setContent(content);
      answer.setCreateDate(LocalDateTime.now());
      answer.setQuestion(question);
      this.answerRepository.save(answer);
   }
   
}
