package com.naveen.questionservice.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.naveen.questionservice.daos.QuestionDao;
import com.naveen.questionservice.dtos.QuestionDto;
import com.naveen.questionservice.models.Question;
import com.naveen.questionservice.utils.exceptions.QuestionAlreadyExistsException;
import com.naveen.questionservice.utils.exceptions.QuestionNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
	private final ModelMapper modelMapper;
	private final QuestionDao questionDao;
	private static final String QUESTION_NOT_FOUND = "Question with the title doesn't exist";

	@Override
	public List<QuestionDto> getAllQuestions(String category) {
		List<Question> questions = questionDao.findByCategoryOrAll(category);
		return questions.stream().map(question -> modelMapper.map(question, QuestionDto.class)).toList();
	}

	@Override
	public Question addQuestion(Question questionToBeAdded) {
		Optional<Question> optionalQuestion = questionDao.findByTitle(questionToBeAdded.getTitle());
		if (optionalQuestion.isPresent()) {
			throw new QuestionAlreadyExistsException("Question with the same title already exists");
		}
		return questionDao.save(questionToBeAdded);
	}

	@Override
	public Question deleteQuestion(String title) {
		Optional<Question> optionalQuestion = questionDao.findByTitle(title);
		if (optionalQuestion.isEmpty()) {
			throw new QuestionNotFoundException(QUESTION_NOT_FOUND);
		}
		questionDao.delete(optionalQuestion.get());
		return optionalQuestion.get();
	}

	@Override
	public Question updateQuestion(Question questiontoBeUpdated) {
		Optional<Question> optionalQuestion = questionDao.findByTitle(questiontoBeUpdated.getTitle());
		if (optionalQuestion.isEmpty()) {
			throw new QuestionNotFoundException(QUESTION_NOT_FOUND);
		}
		Question question = optionalQuestion.get();
		question.update(questiontoBeUpdated);
		return question;
	}

	@Override
	public QuestionDto getQuestion(String title) {
		Optional<Question> optionalQuestion = questionDao.findByTitle(title);
		if (optionalQuestion.isEmpty()) {
			throw new QuestionNotFoundException(QUESTION_NOT_FOUND);
		}
		return modelMapper.map(optionalQuestion.get(), QuestionDto.class);
	}

}
