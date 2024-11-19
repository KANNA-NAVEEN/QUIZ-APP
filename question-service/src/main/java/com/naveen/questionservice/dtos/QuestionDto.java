package com.naveen.questionservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {
	private Integer id;
	private String category;
	private String title;
	private String difficultyLevel;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
}
