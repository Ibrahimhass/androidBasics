package com.example.mdibrahimhassan.helloworldfirstapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        int score = getIntent().getIntExtra("PLAY_AGAIN", 0);
        Button myButton = (Button)findViewById(R.id.play);
        if (score == 1){
            myButton.setText("Play Again");
        }

    }
    public void startQuiz(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
