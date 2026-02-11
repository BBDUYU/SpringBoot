package org.doit.ik.emp.controller;

import org.doit.ik.dept.dto.PageRequestDTO;
import org.doit.ik.emp.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmpController {

	private final EmpService empService;

	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("🐯👉 EmpController.list()... GET! 👈🐯");
		model.addAttribute("result", empService.getList(pageRequestDTO));
	}


}
