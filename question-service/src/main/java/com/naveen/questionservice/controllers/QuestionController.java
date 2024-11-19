package com.naveen.questionservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.questionservice.dtos.QuestionDto;
import com.naveen.questionservice.models.Question;
import com.naveen.questionservice.services.QuestionService;
import com.naveen.questionservice.utils.exceptions.QuestionAlreadyExistsException;
import com.naveen.questionservice.utils.exceptions.QuestionNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping
	public ResponseEntity<List<QuestionDto>> getAllQuestions(@RequestParam(required = false) String category) {
		List<QuestionDto> questionDtos = questionService.getAllQuestions(category);
		return ResponseEntity.ok(questionDtos);
	}

	@GetMapping(value = "{title}")
	public ResponseEntity<QuestionDto> getQuestion(@PathVariable String title) {
		QuestionDto questionDto = questionService.getQuestion(title);
		return ResponseEntity.ok(questionDto);
	}

	@PostMapping
	public ResponseEntity<Question> addQuestion(@RequestBody Question questiontoBeAdded) {
		Question question = questionService.addQuestion(questiontoBeAdded);
		return ResponseEntity.status(HttpStatus.CREATED).body(question);
	}

	@DeleteMapping(value = "{title}")
	public ResponseEntity<Question> deleteQuestion(@PathVariable String title) {
		Question question = questionService.deleteQuestion(title);
		return ResponseEntity.status(HttpStatus.OK).body(question);
	}

	@PutMapping
	public ResponseEntity<Question> uodateQuestion(@RequestBody Question questiontoBeUpdated) {
		Question question = questionService.updateQuestion(questiontoBeUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(question);
	}

	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(QuestionAlreadyExistsException.class)
	public String handleQuestionAlreadyExistsException(QuestionAlreadyExistsException exception) {
		return exception.getMessage();
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(QuestionNotFoundException.class)
	public String handleQuestionNotFoundException(QuestionNotFoundException exception) {
		return exception.getMessage();
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public String handleGenericExcepion(Exception exception) {
		exception.printStackTrace();
		return exception.getMessage();
	}
}
