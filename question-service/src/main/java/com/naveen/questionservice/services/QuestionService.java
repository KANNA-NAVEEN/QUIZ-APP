package com.naveen.questionservice.services;

import java.util.List;

import com.naveen.questionservice.dtos.QuestionDto;
import com.naveen.questionservice.models.Question;

public interface QuestionService {

	List<QuestionDto> getAllQuestions(String category);

	Question addQuestion(Question question);
	
	Question deleteQuestion(String title);
	Question updateQuestion(Question question);

	QuestionDto getQuestion(String title);

}
