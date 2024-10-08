package com.example.quizappbsi;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    ImageView questionImageView;


    int score = 0;
    List<Question> questions;
    int totalQuestion;
    int currentQuestionIndex = 0;

    int questionNumber = 1;
    String selectedAnswer = "";
    Button selectedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        questionImageView = findViewById(R.id.question_image);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        questions = QuestionAnswer.getQuestions();
        totalQuestion = questions.size();

        Collections.shuffle(questions);  // Shuffle the questions

        totalQuestionsTextView.setText("Question: " + questionNumber + "/" + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        resetButtonColors();

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            handleSubmission();
            if(questionNumber < 4) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questionNumber++;
                        totalQuestionsTextView.setText("Question: " + questionNumber + "/" + totalQuestion);
                    }
                }, 2000);
             }
        } else {
            handleAnswerSelection(clickedButton);
        }
    }

    private void resetButtonColors() {
        ansA.setBackgroundColor(Color.parseColor("#D3D9F6"));
        ansB.setBackgroundColor(Color.parseColor("#D3D9F6"));
        ansC.setBackgroundColor(Color.parseColor("#D3D9F6"));
        ansD.setBackgroundColor(Color.parseColor("#D3D9F6"));
    }

    private void handleSubmission() {
        String correctAnswer = questions.get(currentQuestionIndex).getCorrectAnswer();
        String formattedCorrectAnswer = getFormattedAnswer(correctAnswer);

        highlightCorrectAnswer(formattedCorrectAnswer);

        if (getFormattedAnswer(selectedAnswer).equals(formattedCorrectAnswer)) {
            score++;
        } else {
            if (selectedButton != null) {
                selectedButton.setBackgroundColor(Color.RED);
            }
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestionIndex++;
                loadNewQuestion();
            }
        }, 2000);
    }

    private void handleAnswerSelection(Button clickedButton) {
        selectedAnswer = clickedButton.getText().toString();
        selectedButton = clickedButton;
        clickedButton.setBackgroundColor(Color.parseColor("#CBC3FF"));
        submitBtn.setEnabled(true);
    }

    private void highlightCorrectAnswer(String formattedCorrectAnswer) {
        if (ansA.getText().toString().contains(formattedCorrectAnswer)) {
            ansA.setBackgroundColor(Color.GREEN);
        } else if (ansB.getText().toString().contains(formattedCorrectAnswer)) {
            ansB.setBackgroundColor(Color.GREEN);
        } else if (ansC.getText().toString().contains(formattedCorrectAnswer)) {
            ansC.setBackgroundColor(Color.GREEN);
        } else if (ansD.getText().toString().contains(formattedCorrectAnswer)) {
            ansD.setBackgroundColor(Color.GREEN);
        }
    }

    // Helper method to format the correct answer for comparison
    private String getFormattedAnswer(String answer) {
        return answer.contains(")") ? answer.split("\\)")[1].trim() : answer.trim(); // Remove "a)", "b)", etc.
    }
    void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }

        resetButtonColors();

        submitBtn.setEnabled(false);

        Question currentQuestion = questions.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestion());
        ansA.setText(currentQuestion.getChoices()[0]);
        ansB.setText(currentQuestion.getChoices()[1]);
        ansC.setText(currentQuestion.getChoices()[2]);
        ansD.setText(currentQuestion.getChoices()[3]);

        // Setze das Bild für die Frage
        questionImageView.setImageResource(currentQuestion.getImageResource());

        // Reset the selected answer
        selectedAnswer = "";
    }

    void finishQuiz() {
        String passStatus;
        if (score > totalQuestion * 0.60) {
            passStatus = "Passed";
            Intent intent = new Intent(this, EndScreen_Win.class);
            startActivity(intent);
        } else {
            passStatus = "Failed";
            Intent intent = new Intent(this, EndScreen_Lost.class);
            startActivity(intent);
        }
        restartQuiz();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        Collections.shuffle(questions);// Shuffle the questions again
        questionNumber = 1;
        totalQuestionsTextView.setText("Question: " + questionNumber + "/" + totalQuestion);
        selectedAnswer = "";
    }


}