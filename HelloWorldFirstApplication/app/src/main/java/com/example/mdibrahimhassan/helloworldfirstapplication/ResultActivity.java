package com.example.mdibrahimhassan.helloworldfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = (TextView)findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView)findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences settings = getSharedPreferences("IbrahimHassan", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);
        totalScore += score;

        resultLabel.setText(score + " / 5");
        totalScoreLabel.setText("Total Score is : " + totalScore);

        //Update Score
        SharedPreferences.Editor edit = settings.edit();
        edit.putInt("totalScore", totalScore);
        edit.commit();
    }
    public void returnPressed(View view){
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        intent.putExtra("PLAY_AGAIN", 1);
        startActivity(intent);
    }
}
