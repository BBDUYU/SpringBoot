package org.doit.ik.memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemoController {

   private final MemoRepository memoRepository;
   @GetMapping("/memo/list/{pageNumber}")
   public String memoList(@PathVariable("pageNumber") int page,Model model) {
	   log.info("@ MemoController.memoList()...page : "+page);
	   
	   Sort sort = Sort.by("mno").descending();
	   int pageSize=10;
	   Pageable pageable = PageRequest.of(page-1, pageSize, sort);
	   Page<Memo> memoPage =  this.memoRepository.findAll(pageable);
	   
	   int totalPages=memoPage.getTotalPages();
	   int blockSize = 5; // 1 2 3 4 5 >
	   int currentBlock = (int) Math.ceil((double) page / blockSize);
       int startPage = (currentBlock - 1) * blockSize + 1;
       int endPage = Math.min(startPage + blockSize - 1, totalPages);
       
       model.addAttribute("memoPage",memoPage);
       model.addAttribute("totalPages",totalPages);
       model.addAttribute("currentPage",page);
       model.addAttribute("startPage",startPage);
       model.addAttribute("endPage",endPage);
       
	   return "/memo/list";
   }
}