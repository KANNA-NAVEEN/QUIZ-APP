package com.naveen.questionservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "category", nullable = false)
	private String category;
	
	@Column(name = "title", unique = true, nullable = false)
	private String title;
	
	@Column(name = "difficulty_level", nullable = false)
	private String difficultyLevel;
	
	@Column(name = "option_a", nullable = false)
	private String optionA;
	
	@Column(name = "option_b", nullable = false)
	private String optionB;
	
	@Column(name = "option_c", nullable = false)
	private String optionC;
	
	@Column(name = "option_d", nullable = false)
	private String optionD;
	
	@Column(name = "answer", nullable = false)
	private Character answer;

	public void update(Question question) {
		this.category=question.getCategory();
		this.difficultyLevel=question.getDifficultyLevel();
		this.optionA=question.getOptionA();
		this.optionB=question.getOptionB();
		this.optionC=question.getOptionC();
		this.optionD=question.getOptionD();
		this.answer=question.getAnswer();
	}
}
