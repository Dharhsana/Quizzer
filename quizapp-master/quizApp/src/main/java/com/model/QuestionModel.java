package com.model;

import java.util.List;

public class QuestionModel extends BaseModel{

	private long id;
	private String text;
	private int weight;
	private int display_order;
	private String label;
	private List<AnswerModel> answerList;
	private AnswerModel correctAnswer;
	private TutorModel tutor;
	private List<QuizModel> quizList;
	
	public QuestionModel() {
		super();
	}
	
	public QuestionModel(long id, String text, int weight, int display_order, String label,
			List<AnswerModel> answerList, AnswerModel correctAnswer, TutorModel tutor, List<QuizModel> quizList) {
		super();
		this.id = id;
		this.text = text;
		this.weight = weight;
		this.display_order = display_order;
		this.label = label;
		this.answerList = answerList;
		this.correctAnswer = correctAnswer;
		this.tutor = tutor;
		this.quizList = quizList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDisplayOrder() {
		return display_order;
	}

	public void setDisplayOrder(int display_order) {
		this.display_order = display_order;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<AnswerModel> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerModel> answerList) {
		this.answerList = answerList;
	}

	public AnswerModel getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(AnswerModel correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public TutorModel getTutor() {
		return tutor;
	}

	public void setTutor(TutorModel tutor) {
		this.tutor = tutor;
	}

	public List<QuizModel> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<QuizModel> quizList) {
		this.quizList = quizList;
	}

	@Override
	public String toString() {
		return "QuestionModel [id=" + id + ", text=" + text + ", weight=" + weight + ", display_order=" + display_order
				+ ", label=" + label + ", answerList=" + answerList + ", correctAnswer=" + correctAnswer + ", tutor="
				+ tutor + ", quizList=" + quizList + "]";
	}

	
	
	

	
	
	
}
