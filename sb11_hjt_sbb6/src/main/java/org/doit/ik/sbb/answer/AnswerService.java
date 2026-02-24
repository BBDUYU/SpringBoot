package org.doit.ik.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.doit.ik.sbb.exception.DataNotFoundException;
import org.doit.ik.sbb.question.Question;
import org.doit.ik.sbb.user.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

   private final AnswerRepository answerRepository;
   
   // [1] 답변 등록
   /*
   public void create(Question question, String content,SiteUser author) {
      Answer answer = new Answer();
      answer.setContent(content);
      answer.setCreateDate(LocalDateTime.now());
      answer.setQuestion(question);
      answer.setAuthor(author);
      this.answerRepository.save(answer);
   }
   */
   // [2] 답변등록 + 앵커기능
   public Answer create(Question question, String content,SiteUser author) {
	      Answer answer = new Answer();
	      answer.setContent(content);
	      answer.setCreateDate(LocalDateTime.now());
	      answer.setQuestion(question);
	      answer.setAuthor(author);
	      this.answerRepository.save(answer);
	      return answer;
	   }
   public Answer getAnswer(Integer id) {
       Optional<Answer> answer = this.answerRepository.findById(id);
       if (answer.isPresent()) {
           return answer.get();
       } else {
           throw new DataNotFoundException("answer not found");
       }
   }

   public void modify(Answer answer, String content) {
       answer.setContent(content);
       answer.setModifyDate(LocalDateTime.now());
       this.answerRepository.save(answer);
   }
   
   public void delete(Answer answer) {
       this.answerRepository.delete(answer);
   }
   public void vote(Answer answer,SiteUser siteUser) {
	   answer.getVoter().add(siteUser);
		this.answerRepository.save(answer);
	}
   
}
