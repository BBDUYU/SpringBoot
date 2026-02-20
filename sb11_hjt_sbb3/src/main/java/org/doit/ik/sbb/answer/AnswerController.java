package org.doit.ik.sbb.answer;

import org.doit.ik.sbb.question.Question;
import org.doit.ik.sbb.question.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
	private final AnswerService answerService;
	private final QuestionService questionService;
	
	/*
	@PostMapping("/create/{id}")
	public String createAnswer(Model model,@PathVariable("id") Integer id,@RequestParam("content") String content) {
		
		Question question = this.questionService.getQuestion(id);
		this.answerService.create(question, content);
		return String.format("redirect:/question/detail/%s", id);
	}
	*/
	@PostMapping("/create/{id}")
	public String createAnswer(Model model,@PathVariable("id") Integer id,@Valid AnswerForm answerForm,BindingResult bindingResult) {
		Question question = this.questionService.getQuestion(id);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question",question);
			return "/question/detail";
		}
		this.answerService.create(question,answerForm.getContent());
		return String.format("redirect:/question/detail/%s", id);
	}
}
