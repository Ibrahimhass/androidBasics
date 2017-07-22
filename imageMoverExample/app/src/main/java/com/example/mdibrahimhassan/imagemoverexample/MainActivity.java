package com.example.mdibrahimhassan.imagemoverexample;

import android.graphics.Point;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Screen Size
    private int screenHeight;
    private int screenWidth;

    //ImageViews
    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView arrowLeft;
    private ImageView arrowRight;

    //Position
    private float arrowUpX;
    private float arrowUpY;

    private float arrowDownX;
    private float arrowDownY;

    private float arrowRightX;
    private float arrowRightY;

    private float arrowLeftX;
    private float arrowLeftY;

    //Initialise class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrowUp = (ImageView) findViewById(R.id.arrowUp);
        arrowDown = (ImageView) findViewById(R.id.arrowdown);
        arrowLeft = (ImageView) findViewById(R.id.arrowleft);
        arrowRight = (ImageView) findViewById(R.id.arrowright);

        //Get ScreenSize
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        //Move to out of screen
        arrowUp.setX(-80.0f);
        arrowUp.setY(-80.0f);

        arrowDown.setX(-80.0f);
        arrowDown.setY(screenHeight + 80.0f);

        arrowRight.setX(screenWidth + 80.0f);
        arrowRight.setY(-80.0f);

        arrowLeft.setX(-80.0f);
        arrowLeft.setY(-80.0f);

        //Start timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        },0, 20);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void changePos() {
        arrowUpY -= 10;
        if (arrowUp.getY() + arrowUp.getHeight() < 0) {
            arrowUpX = (float) Math.floor(Math.random() * (screenWidth - arrowUp.getWidth()));
            arrowUpY = screenHeight + 100.0f;
        }
        arrowUp.setX(arrowUpX);
        arrowUp.setY(arrowUpY);
        //ArrowDown
        arrowDownY += 10;
        if (arrowDown.getY() > screenHeight) {
            arrowDownX = (float) Math.floor(Math.random() * (screenWidth - arrowDown.getWidth()));
            arrowDownY = -100.0f;
        }
        arrowDown.setX(arrowDownX);
        arrowDown.setY(arrowDownY);
        //ArrowRight
        arrowRightX += 10;
        if(arrowRight.getX() > screenWidth){
            arrowRightX = - 100.0f;
            arrowRightY = (float) Math.floor(Math.random() * (screenHeight - arrowRight.getHeight()));
        }
        arrowRight.setY(arrowRightY);
        arrowRight.setX(arrowRightX);
        //ArrowLeft
        arrowLeftX -= 10;
        if (arrowLeft.getX() + arrowLeft.getWidth() < 0){
            arrowLeftX = screenWidth + 100.0f;
            arrowLeftY = (float) Math.floor(Math.random() * (screenHeight - arrowLeft.getHeight()));

        }
        arrowLeft.setX(arrowLeftX);
        arrowLeft.setY(arrowLeftY);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
