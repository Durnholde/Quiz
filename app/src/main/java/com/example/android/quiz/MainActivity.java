package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int NUMBER_OF_QUESTIONS = 8;
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
    String getName()
    {
        return ((EditText) findViewById(R.id.name_box)).getText().toString();
    }

    /*
     * Method invoked on RadioButton click. Checks if answer is correct and distributes points.
     */
    void getAnswer(View view)
    {
        RadioGroup rg = (RadioGroup) view.getParent();
        String rgTag = rg.getTag().toString();
        int questionNumber = Integer.parseInt(rgTag);

        RadioButton selectedAnswer = (RadioButton) view;
        if(selectedAnswer.getTag().toString().equals(getString(R.string.tag_answer_correct)))
            points[questionNumber - 1] = 1;
        else
            points[questionNumber - 1] = 0;
    }

    /*
     * Method to sum up all the points and display end message.
     */
    void quizSummary(View view)
    {
        int total = 0;
        for(int i=0; i<NUMBER_OF_QUESTIONS; i++)
            total += points[i];

        String name = getName();
        String result = getString(R.string.summary_message, name, total, NUMBER_OF_QUESTIONS);

        String advice;
        if(total == 0)
            advice = getString(R.string.advice_1);
        else if(total < NUMBER_OF_QUESTIONS/2)
            advice = getString(R.string.advice_2);
        else if(total < NUMBER_OF_QUESTIONS)
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
    void setArray()
    {
        for(int i = 0; i<NUMBER_OF_QUESTIONS; i++)
            points[i]=0;
    }
}