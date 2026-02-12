package org.doit.ik.board.controller;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.dto.PageResultDTO;
import org.doit.ik.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/register")
	public void register() {
		log.info("👌 BoardController.register()... GET" );
	}
	
	@PostMapping("/register")
	public String register(BoardDTO boardDTO, RedirectAttributes rttr) {
		log.info("👌 BoardController.register()... POST" );
		Long bno = this.boardService.register(boardDTO);
		rttr.addFlashAttribute("msg", bno);
		return "redirect:/board/list";
	}
	
	// http://localhost/board/list
	// http://localhost/board/list?page=2&size=10&type=t&keyword=길동
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("👌 BoardController.list()... GET" );
		PageResultDTO<BoardDTO, Object[]> result = this.boardService.getList(pageRequestDTO);
		model.addAttribute("result", result);
	}
	
	// http://localhost/board/read?bno=100&page=1&size=10&type=&keyword=
	// http://localhost/board/modify?bno=101&page=1&type=&keyword=
	@GetMapping({"/read", "modify"})
	public void readAndmodify(
			@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO
			, @RequestParam("bno") Long bno
			, Model model
			) {		
		log.info("👌 BoardController.read/modify()... GET" );
		BoardDTO dto = this.boardService.get(bno);
		model.addAttribute("dto", dto);		
	}
	
	// http://localhost/board/modify  + POST
	@PostMapping("/modify")
	public String modify(BoardDTO dto
			, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO
			, RedirectAttributes rttr) {
		log.info("😍 BoardController.modify()... POST");
		this.boardService.modify(dto);
		
		// /board/read?bno=10&page=3&size=10
		rttr.addAttribute("bno", dto.getBno());
		rttr.addAttribute("page", pageRequestDTO.getPage());
		rttr.addAttribute("size", pageRequestDTO.getSize());
		rttr.addAttribute("type", pageRequestDTO.getType());
		rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
		return "redirect:/board/read";
	}
	
	// http://localhost/board/remove  + POST
	@PostMapping("/remove")
	public String remove(
			@RequestParam("bno") Long bno 
			, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO
			, RedirectAttributes rttr) {
		log.info("😍 BoardController.remove()... POST");
		
		this.boardService.removeWithReplies(bno);
		rttr.addFlashAttribute("msg", bno);
		return "redirect:/board/list";
	}
	
}











