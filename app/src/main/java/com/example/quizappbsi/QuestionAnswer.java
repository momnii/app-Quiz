package com.example.quizappbsi;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswer {
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "Welcher Planet ist der größte in unserem Sonnensystem?",
                new String[]{"a) Jupiter", "b) Saturn", "c) Venus", "d) Mars"},
                "Jupiter",
                R.drawable.image1
        ));
        questions.add(new Question(
                "Welcher Fluss ist der längste, der ausschließlich durch Deutschland fließt?",
                new String[]{"a) Rhein", "b) Weser", "c) Elbe", "d) Donau"},
                "Elbe",
                R.drawable.image2
        ));
        questions.add(new Question(
                "Welches Bundesland hat die meisten Einwohner in Deutschland?",
                new String[]{"a) Bayern", "b) Sachsen", "c) Hessen", "d) NRW"},
                "NRW",
                R.drawable.image4
        ));
        questions.add(new Question(
                "Welches Land hat die meisten offiziellen Sprachen?",
                new String[]{"a) Kanada", "b) Südafrika", "c) Belgien", "d) Schweiz"},
                "Südafrika",
                R.drawable.image3
        ));

        return questions;
    }
}