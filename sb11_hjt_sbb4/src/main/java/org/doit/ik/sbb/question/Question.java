package org.doit.ik.sbb.question;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.doit.ik.sbb.answer.Answer;
import org.doit.ik.sbb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter //엔티티를 선언할 때는 @Setter는 권장 X
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=200)
	private String subject;
	
	@Column(columnDefinition = "TEXT") //글자수를 제한할 수 없는 경우
	private String content;
	private LocalDateTime createDate;
	
	//mappedBy 속성 : 참조되고있는 속성명을 정의
	
	// cascade=CascadeType.REMOVE : 질문이 삭제되면 해당되는 답변도 모두 삭제
	@OneToMany(mappedBy="question",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
}
