package org.doit.ik.guestbook.controller;

import java.util.List;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.service.GuestbookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
	
	private final GuestbookService guestbookService;
	/*
	@GetMapping("/list")
	public void index() {
		log.info("😘😘 GuestbookController.index()...");
	}
	*/
	// /guestbook/register
	@GetMapping("/register")
	public void register() {
		log.info("😘😘 GuestbookController.register()...GET");
	}
	
	@PostMapping("/register")
	public String register(GuestbookDTO dto,RedirectAttributes rttr) {
		log.info("😘😘 GuestbookController.register()...POST");
		Long gno = this.guestbookService.register(dto);
		rttr.addFlashAttribute("msg",gno);
		return "redirect:/guestbook/list";
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("😘😘 GuestbookController.list()...");
		Page<GuestbookDTO> pageList = this.guestbookService.getList();
		List<GuestbookDTO> gblist =  pageList.getContent();
		model.addAttribute("gblist",gblist);
	}
	
}
