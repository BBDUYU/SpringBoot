package org.doit.ik.board.entity;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;

	private String title;
	private String content;

	// 작성자 연관관계 설정 X
	@ManyToOne(fetch=FetchType.LAZY) // 지연로딩 - 응답을 기다리는게 아님 끊어버림 이후에 추가 정보를 가져오려면 @Transactional
	private Member writer;

	public void changeTitle(String title) {   
		this.title = title;
	}
	
	public void changeContent(String content) {
		this.content = content;
	}
}
