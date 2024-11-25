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
import com.naveen.questionservice.utils.ApiResponse;
import com.naveen.questionservice.utils.ResponseBuilder;
import com.naveen.questionservice.utils.exceptions.QuestionAlreadyExistsException;
import com.naveen.questionservice.utils.exceptions.QuestionNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping
	public ResponseEntity<ApiResponse> getAllQuestions(@RequestParam(required = false) String category,
			HttpServletRequest request) {
		List<QuestionDto> questionDtos = questionService.getAllQuestions(category);
		return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(HttpStatus.OK.toString(), "Request is processed",
				questionDtos, request));
	}

	@GetMapping(value = "{title}")
	public ResponseEntity<ApiResponse> getQuestion(@PathVariable String title, HttpServletRequest request) {
		QuestionDto questionDto = questionService.getQuestion(title);
		return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(HttpStatus.OK.toString(),
				"Question data is fetched", questionDto, request));
	}

	@PostMapping
	public ResponseEntity<ApiResponse> addQuestion(@RequestBody Question questiontoBeAdded,
			HttpServletRequest request) {
		Question question = questionService.addQuestion(questiontoBeAdded);
		return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(HttpStatus.CREATED.toString(),
				"Question entity is created", question, request));
	}

	@DeleteMapping(value = "{title}")
	public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable String title, HttpServletRequest request) {
		Question question = questionService.deleteQuestion(title);
		return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(HttpStatus.OK.toString(),
				"Question entity is deleted", question, request));
	}

	@PutMapping
	public ResponseEntity<ApiResponse> uodateQuestion(@RequestBody Question questiontoBeUpdated,
			HttpServletRequest request) {
		Question question = questionService.updateQuestion(questiontoBeUpdated);
		return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(HttpStatus.OK.toString(),
				"Question details are updated", question, request));
	}

	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(QuestionAlreadyExistsException.class)
	public ApiResponse handleQuestionAlreadyExistsException(QuestionAlreadyExistsException exception,
			HttpServletRequest request) {
		return ResponseBuilder.buildErrorResponse(HttpStatus.CONFLICT.toString(), "Already exists",
				exception.getMessage(), request);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(QuestionNotFoundException.class)
	public ApiResponse handleQuestionNotFoundException(QuestionNotFoundException exception,
			HttpServletRequest request) {
		return ResponseBuilder.buildErrorResponse(HttpStatus.NOT_FOUND.toString(), "Question does not exist",
				exception.getMessage(), request);
	}

}
