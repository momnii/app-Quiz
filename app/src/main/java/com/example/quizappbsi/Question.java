package com.example.quizappbsi;

public class Question {
    private String question;
    private String[] choices;
    private String correctAnswer;
    private int imageResource;

    public Question(String question, String[] choices, String correctAnswer, int imageResource) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.imageResource = imageResource;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;

}
    public int getImageResource() {
        return imageResource;
    }
}
