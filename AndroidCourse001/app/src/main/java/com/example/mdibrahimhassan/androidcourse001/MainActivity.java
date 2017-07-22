package com.example.mdibrahimhassan.androidcourse001;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id._101);
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFunction();
            }
        });*/
        btn.setOnClickListener(this);


    }

    public void clickFunction() {
        System.out.print(12);
    }

    @Override
    public void onClick(View v) {
        if(v == btn ) {
            clickFunction();
        }
    }
}
