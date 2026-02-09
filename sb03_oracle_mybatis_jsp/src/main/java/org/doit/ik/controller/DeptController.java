package org.doit.ik.controller;

import org.doit.ik.persistence.DeptMapper;
import org.doit.ik.persistence.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {
	
	
	private final DeptMapper deptMapper;
	
	@GetMapping("/list")
	public ModelAndView list() {
		log.info("@ DeptController.index()....");
//		
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/dept/list");
//		
		ModelAndView mv = new ModelAndView("/dept/list");
		try {
			mv.addObject("list",this.deptMapper.getDeptList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
		
	}
}
