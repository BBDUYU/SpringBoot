package org.doit.ik.guestbook.controller;

import java.util.List;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.service.GuestbookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {

	private final GuestbookService guestbookService;

	/* // [1] 페이징 처리  X
	@GetMapping("/list")
	public void list(Model model) {
		log.info("😍 GuestbookController.list()...");		
		Page<GuestbookDTO> pageList = this.guestbookService.getList();		
		List<GuestbookDTO> gblist =  pageList.getContent();		
		model.addAttribute("gblist", gblist);
	}
	 */
	// [2] 페이징 처리 O
	// localhost/guestbook/list?page=1&size=10&type=t&keyword=xxx
	// localhost/guestbook/list
	@GetMapping("/list")
	public void list(Model model, PageRequestDTO pageRequestDTO ) {
		log.info("😍 GuestbookController.list()... [2]");		
		PageResultDTO<GuestbookDTO, Guestbook> result = this.guestbookService.getList(pageRequestDTO);
		model.addAttribute("result", result);
	}

	//  /guestbook/register
	@GetMapping("/register")
	public void register() {
		log.info("😍 GuestbookController.register()... GET");
	}

	@PostMapping("/register")
	public String register(GuestbookDTO dto, RedirectAttributes rttr) {
		log.info("😍 GuestbookController.register()... POST");
		Long gno = this.guestbookService.register(dto);
		rttr.addFlashAttribute("msg", gno);  // 일회성 파라미터
		return "redirect:/guestbook/list";
	} 

	// http://localhost/guestbook/read?gno=302&page=1&size=10
	// http://localhost/guestbook/modify?gno=221&page=9&size=10
	@GetMapping({"/read", "/modify"})
	public void readAndmodify(@RequestParam("gno") Long gno
			,@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO
			, Model model) {
		log.info("😍 GuestbookController.read()... ");
		GuestbookDTO dto = this.guestbookService.read(gno);
		model.addAttribute("dto", dto); 
	}

	/* 
	// http://localhost/guestbook/modify?gno=221&page=9&size=10
	@GetMapping("/modify")
	public void modify(@RequestParam("gno") Long gno
			,@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO
			, Model model) {
		log.info("😍 GuestbookController.modify()... ");
		GuestbookDTO dto = this.guestbookService.read(gno);
		model.addAttribute("dto", dto); 
	}
	*/

	@PostMapping("/modify")
	public String modify(GuestbookDTO dto
			, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO
			, RedirectAttributes rttr) {
		log.info("😍 GuestbookController.modify()... POST");
		this.guestbookService.modify(dto);
		
		// /guestbook/read?gno=10&page=3&size=10
		rttr.addAttribute("gno", dto.getGno());
		rttr.addAttribute("page", pageRequestDTO.getPage());
		rttr.addAttribute("size", pageRequestDTO.getSize());
		rttr.addAttribute("type", pageRequestDTO.getType());
		rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
		return "redirect:/guestbook/read";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("gno") Long gno
			, RedirectAttributes rttr) {
		log.info("😍 GuestbookController.remove()... POST");
		this.guestbookService.remove(gno);
		 
		rttr.addFlashAttribute("msg", gno);
		return "redirect:/guestbook/list";
	}

}









