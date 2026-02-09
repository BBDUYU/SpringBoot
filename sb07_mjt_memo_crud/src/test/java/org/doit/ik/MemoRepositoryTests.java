package org.doit.ik;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.doit.ik.memo.Memo;
import org.doit.ik.memo.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class MemoRepositoryTests {

   @Autowired
   private MemoRepository memoRepository;
   
   // [1] memo 테이블에 더미 데이터를 insert
   /*
   @Test
   void testInsertDummies() {
      
      IntStream.rangeClosed(1, 100)
      .forEach(i -> {
         Memo memo = Memo.builder()
               .memoText("Sample Memo" + i)
               .build();
      this.memoRepository.save(memo);
      });
   }
*/
   // [2] mno = 10 메모 내용을 조회 Select
   /*
   @Test
   void testSelectOne() {
	  long mno = 10;
      Optional<Memo> result = this.memoRepository.findById(mno);
      result.ifPresent(memo->{
    	  System.out.print("@ @ 메모테스트 : "+memo.getMemoText());
      });
   }
   // [3] mno=1 메모내용 수정
   @Test
   void testUpdateMemo() {
	  long mno = 1;
	  Memo memo = Memo.builder()
              .memoText("[수정] Sample Memo.."+mno)
              .build();
	  memo=this.memoRepository.save(memo); //1번 메모는 존재하기때문에 수정
	  System.out.println("@ @"+memo);
   }
   */
   
   // [4]
   /*
   @Test
   void testDeleteMemo() {
	  long mno = 1;
	  this.memoRepository.deleteById(mno);
   }
   */
   // [5] 페이징처리 + 목록조회 Select
   @Test
   void testSelectPageMemo() {
	   Sort sort = Sort.by("mno").descending();
	   int pageNumber=1;
	   int pageSize=15;
	   Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
	   Page<Memo> memoPage =  this.memoRepository.findAll(pageable);
	   
	   List<Memo> list = memoPage.getContent();
	   System.out.println("@ @".repeat(50));
	   list.forEach(memo->{
		   System.out.println("$ "+memo);
	   });
	   System.out.println("=".repeat(50));
       System.out.println("총 페이수: " + memoPage.getTotalPages());   // 
       System.out.println("총 레코드수: " + memoPage.getTotalElements());
       System.out.println("현재 페이지: " + memoPage.getNumber());
       System.out.println("이전 페이지: " + memoPage.hasPrevious());
       System.out.println("다음 페이지: " + memoPage.hasNext());
       System.out.println(memoPage.isFirst());
    System.out.println("=".repeat(50));
   }
   
}
