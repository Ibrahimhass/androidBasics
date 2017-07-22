package com.example.mdibrahimhassan.helloworldfirstapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount;
    private int quizCount = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    String quizData[][] = {
            //{"Country", "Right Answer", "Choice1", "Choice3", "Choice3"}
            {"India", "New Delhi","Bangalore","Patna","Goa" },
            {"Pakistan", "Islamabad","Lahore","Karachi","Multan"},
            {"Bangladesh", "Dhaka","Cox Bazar","Rajshahi","Khulna" },
            {"Sri Lanka", "Colombo","Jaffna","Galle","Kandy" },
            {"Sri Lanka", "Colombo","Jaffna","Galle","Kandy" },
            {"America", "Washington-DC","New York","Los Angeles","San Jose" },
            {"Saudi Arabia", "Riyadh","Mecca","Madina","Taif" },
            {"China", "Beijing","Shanghai","Hong Kong","Random XYZ" }
    };
    static final private int QUIZ_COUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);
        answerBtn3 = (Button)findViewById(R.id.answerBtn3);
        answerBtn4 = (Button)findViewById(R.id.answerBtn4);

        //Create quizArray from quizData
        for (int i = 0; i < quizData.length; i ++){
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Country
            tmpArray.add(quizData[i][1]); //Right Answer
            tmpArray.add(quizData[i][2]); //Choice 1
            tmpArray.add(quizData[i][3]); //Choice 2
            tmpArray.add(quizData[i][4]); //Choice 3

            //Add tmpArray to Quiz Array
            quizArray.add(tmpArray);
        }

        showQuiz();
    }

    public void showQuiz(){
        //Update country Label
        countLabel.setText("Q" + quizCount);
        // Generate Random number between 0 and arrayCount
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());
        //Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);
        //Set question and Right Answer
        //Array Format {"Country", "Right Answer", "Choice1", "Choice3", "Choice3"}
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);
        //Remove countyr from list and shuffle choices
        quiz.remove(0);
        Collections.shuffle(quiz);
        //set choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));
        //remove this quiz from quiz array
        quizArray.remove(randomNum);
    }
    public void checkAnswer(View view){
        //Get pushed Button
        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();
        String alertTitle;
        if (btnText.equals(rightAnswer)){
            alertTitle = "Correct!";
            rightAnswerCount++;
        }
        else{
            alertTitle = "Wrong...";
        }
        //Build Alert Dialog...
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: " + rightAnswer);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if (quizCount == QUIZ_COUNT){
                    // Show result
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                }

                else{
                    quizCount++;
                    showQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
