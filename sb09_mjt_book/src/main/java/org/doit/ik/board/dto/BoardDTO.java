package org.doit.ik.board.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	// Board
	private Long bno;
	private String title;
	private String content;
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private LocalDateTime regDate;
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private LocalDateTime modDate;
	
	// Member
	private String writerEmail;
	private String writerName;
	
	// Reply
	private int replyCount;
	
}
