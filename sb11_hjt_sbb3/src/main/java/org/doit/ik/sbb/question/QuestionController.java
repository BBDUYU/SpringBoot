package org.doit.ik.sbb.question;

import java.util.List;

import org.doit.ik.sbb.answer.AnswerForm;
import org.doit.ik.sbb.page.Criteria;
import org.doit.ik.sbb.page.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	/*
	private final QuestionRepository questionRepository;

	@GetMapping("/list")
	public void list(Model model) {
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList",questionList);
	}
	 */
	private final QuestionService questionService;
	// [2] 페이징 처리 O
	// http://localhost/question/list?page=0&size=10&검색조건=keyword&검색어=type ...
	@GetMapping("/list")
	public void list( Model model, 
			@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "size", defaultValue = "10") int size ) {
		Page<Question> paging = this.questionService.getList(page, size);
		model.addAttribute("paging", paging);
		
		Criteria criteria = new Criteria(page, size);
		int total= (int)paging.getTotalElements(); // 총 레코드 수
		model.addAttribute("pageMaker",new PageDTO(criteria, total));
	}

	/* [1] 페이징 X
	@GetMapping("/list")
	public void list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList",questionList);
	}
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id")Integer id,Model model,AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
		return "/question/detail";
	}

	@GetMapping("/create")
	public void questionCreate(QuestionForm questionForm) {

	}

	// [2]
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {			
			return "/question/create";
		}
		this.questionService.create(questionForm);
		return "redirect:/question/list";
	}

	/* [1]
	@PostMapping("/create")
	public String questionCreate(@RequestParam(value="subject") String subject,@RequestParam(value="content") String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list";
	}
	 */
}
