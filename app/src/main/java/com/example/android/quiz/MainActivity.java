package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int NUMBER_OF_QUESTIONS = 8;
    private final int CHECKBOX_QUESTION_NUMBER = 3;
    private final int EDITTEXT_QUESTION_NUMBER = 7;
    private int CHECKBOX_WRONG_ANSWERS = 0;
    private int CHECKBOX_CORRECT_ANSWERS = 0;
    int points[] = new int[NUMBER_OF_QUESTIONS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setArray();
    }

    /*
     * Method that reads inserted name.
     */
    String getName() {
        return ((EditText) findViewById(R.id.name_box)).getText().toString();
    }

    /*
     * Method invoked on RadioButton click. Checks if answer is correct and distributes points.
     */
    public void getAnswer(View view) {
        RadioGroup rg = (RadioGroup) view.getParent();
        String rgTag = rg.getTag().toString();
        int questionNumber = Integer.parseInt(rgTag);

        RadioButton selectedAnswer = (RadioButton) view;
        if (selectedAnswer.getTag().toString().equals(getString(R.string.tag_answer_correct)))
            points[questionNumber - 1] = 1;
        else
            points[questionNumber - 1] = 0;
    }

    /*
     * Method for CheckBox. Gives 1 point if all correct answers are checked.
     */
    public void getAnswerCheckbox(View view) {

        CheckBox used_checkbox = (CheckBox) view;
        String correct = getString(R.string.tag_answer_correct);
        int result = 0;

        if (used_checkbox.getTag().toString() == correct) {
            if (used_checkbox.isChecked())
                CHECKBOX_CORRECT_ANSWERS++;
            else
                CHECKBOX_CORRECT_ANSWERS--;
        } else {
            if (used_checkbox.isChecked())
                CHECKBOX_WRONG_ANSWERS++;
            else
                CHECKBOX_WRONG_ANSWERS--;
        }

        if (CHECKBOX_WRONG_ANSWERS > 0)
            result = 0;
        else if (CHECKBOX_CORRECT_ANSWERS < 2)
            result = 0;
        else
            result = 1;

        points[CHECKBOX_QUESTION_NUMBER - 1] = result;
    }

    /*
     * Method to check answer in question 7 with EditText.
     */
    private void getAnswerText() {
        EditText answer = findViewById(R.id.question_7_answer_box);

        if (answer.getText().toString().equals(getString(R.string.question_7_correct_answer)))
            points[EDITTEXT_QUESTION_NUMBER - 1] = 1;
        else
            points[EDITTEXT_QUESTION_NUMBER - 1] = 0;
    }

    /*
     * Method to sum up all the points and display end message.
     */
    public void quizSummary(View view) {
        int total = 0;
        getAnswerText();
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++)
            total += points[i];

        String name = getName();
        String result = getString(R.string.summary_message, name, total, NUMBER_OF_QUESTIONS);

        String advice;
        if (total == 0)
            advice = getString(R.string.advice_1);
        else if (total < NUMBER_OF_QUESTIONS / 2)
            advice = getString(R.string.advice_2);
        else if (total < NUMBER_OF_QUESTIONS)
            advice = getString(R.string.advice_3);
        else
            advice = getString(R.string.advice_4);
        result += "\n" + advice;

        Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
        toast.show();
    }

    /*
     * Array initialization with all values as 0.
     */
    private void setArray() {
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++)
            points[i] = 0;
    }
}