package com.model;


public class AnswerModel extends BaseModel{

	private long id;
	private String label;
	private String text;
	private QuestionModel question;
	
	public AnswerModel() {
		super();
	}

	public AnswerModel(long id, String label, String text, QuestionModel question) {
		super();
		this.id = id;
		this.label = label;
		this.text = text;
		this.question = question;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public QuestionModel getQuestion() {
		return question;
	}

	public void setQuestion(QuestionModel question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "AnswerModel [id=" + id + ", label=" + label + ", text=" + text + ", question=" + question + "]";
	}
	
	
	
}
