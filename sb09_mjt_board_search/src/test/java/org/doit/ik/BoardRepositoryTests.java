package org.doit.ik;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Member;
import org.doit.ik.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.transaction.Transactional;

@SpringBootTest
class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	void testRead3() {
		Object result = this.boardRepository.getBoardByBno(11L);
		Object[] arr = (Object[])result;		
		System.out.println("=".repeat(50));
		System.out.println(Arrays.toString(arr));
		System.out.println("=".repeat(50));
	}
	
	/*
	@Test
	void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object []> result = this.boardRepository.getBoardWithReplyCount(pageable);
		
		System.out.println("=".repeat(50));
		for (Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		} 
		System.out.println("=".repeat(50));
	}
	*/
	
	/*
	@Test
	void testReadWithReply() {
		
		List<Object[]> result = this.boardRepository.getBoardWithReply(100L);		
		System.out.println("=".repeat(50));
		for (Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		} 
 
		System.out.println("=".repeat(50));
	}
	*/
	
	/*
	@Test
	void testReadWithWriter() {
		
		Object result = this.boardRepository.getBoardWithWriter(100L);
		Object [] arr = (Object []) result;
		System.out.println("=".repeat(50));
		System.out.println(Arrays.toString(arr));
//		[
//		   Board(bno=100, title=title...100, content=content...100)
//		 , Member(email=user100@naver.com, name=USER100, password=1234)
//		]
		System.out.println("=".repeat(50));
	}
	*/
	
	/*
	@Transactional
	@Test
	void testRead1() {
		// 글번호 100번 게시글 정보를 조회
        Optional<Board>	result = this.boardRepository.findById(100L);
        Board board = result.get();
        System.out.println(  board );	
        System.out.println(  board.getWriter() );	
        
        // Board(
        //   bno=100, title=title...100, content=content...100, 
        //   writer=Member(email=user100@naver.com, name=USER100, password=1234)        
        //) 
	}
    */

	/*
	@Test
	void insertBoards() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			Member member = Member.builder()
					.email("user"+i+"@naver.com")
					.build();
			
			// Board 엔티티 생성
			Board board= Board.builder()
					.title("title..."+i)
					.content("content..."+i)
					.writer(member) // member 엔티티 객체
					.build();
			this.boardRepository.save(board); // 
		});		

	}
	*/

}


