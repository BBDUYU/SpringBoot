package org.doit.ik;

import java.util.Arrays;

import org.doit.ik.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	void testReadWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10,Sort.by("bno").descending());
		
		Page<Object []>result= this.boardRepository.getBoardWithReplyCount(pageable);
		System.out.println("=".repeat(50));
		for (Object[] arr:result) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println("=".repeat(50));	   
	}
	
	/*
	@Test
	void testReadWithReply() {
		List<Object[]> result = this.boardRepository.getBoardWithReply(100L);
		System.out.println("=".repeat(50));
		for (Object[] arr:result) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println("=".repeat(50));	   
	}
	 */
	/*
   @Test
   void testReadWithWriter() {
	   Object result = this.boardRepository.getBoardWithWriter(100L);
	   Object arr[] = (Object []) result;
	   System.out.println("=".repeat(50));
	   System.out.println(Arrays.toString(arr));
	   System.out.println("=".repeat(50));	   
   }
	 */
	/*
   @Transactional
   @Test
   void testRead1() {

      // 글 번호 100번 게시글 정보 조회
      Optional<Board> result = this.boardRepository.findById(100L);
      Board board = result.get();

      System.out.println(board);
      System.out.println(board.getWriter());



   }
	 */
	/*
   @Test
   void insertBoards() {

      IntStream.rangeClosed(1, 100).forEach(i -> {
         Member member = Member.builder()
                          .email("user"+i+"@naver.com")
                          .build();
         // Board 엔티티 생성
         Board board = Board.builder()
                        .title("title..."+i)
                        .content("content..."+i)
                        .writer(member) // member 엔티티 객체
                        .build();
         this.boardRepository.save(board);
      });
   }
	 */


}
