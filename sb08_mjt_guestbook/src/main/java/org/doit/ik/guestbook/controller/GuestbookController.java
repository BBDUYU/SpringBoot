package org.doit.ik.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/guestbook")
public class GuestbookController {
	@GetMapping("/list")
	   public void index() {
	      log.info("😘😘 GuestbookController.index()...");
	   }
}
