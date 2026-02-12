package org.doit.ik;

import java.util.stream.IntStream;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;
import org.doit.ik.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReplyRepositoryTests {
	
	@Autowired
	private ReplyRepository replyRepository;

	@Test
	void insertReply() {
		IntStream.rangeClosed(1, 300).forEach(i->{
			long bno = (long)(Math.random()*99)+2;  // 게시글 번호 임의의 수
			Board board= Board.builder()  // Board 엔티티 생성
					.bno(bno)
					.build();
			Reply reply = Reply.builder()
					.text("reply..."+i)
					.board(board)         // Board 엔티티
					.replyer("guest")
					.build();
			this.replyRepository.save(reply);
		});	
	}

}
