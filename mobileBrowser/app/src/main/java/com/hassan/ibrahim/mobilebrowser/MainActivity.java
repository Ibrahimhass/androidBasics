package com.hassan.ibrahim.mobilebrowser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView browser;
    EditText editText;
    Button goBtn, forwardBtn, backBtn,reloadBtn, clearBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        browser = (WebView)findViewById(R.id.webView_browser);
        editText = (EditText)findViewById(R.id.editUrl);
        goBtn = (Button)findViewById(R.id.search_go_btn);
        forwardBtn = (Button)findViewById(R.id.btn_forward);
        backBtn = (Button)findViewById(R.id.btn_back);
        reloadBtn = (Button)findViewById(R.id.btn_refresh);
        clearBtn = (Button)findViewById(R.id.btn_clear);
        progressBar = (ProgressBar)findViewById(R.id.progreeBarID);

        String url = "http://www.hackstories.com/";
        browser.loadUrl(url);
        //Make the browser open the sublinks in the same browser rather than any other browser
        browser.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100)
                {
                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        browser.setWebViewClient(new myClient());
        browser.getSettings().setJavaScriptEnabled(true);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                String editTextVal = editText.getText().toString();
                if (!editTextVal.startsWith("http://"))
                    editTextVal = "http://" + editTextVal;
                browser.loadUrl(editTextVal);
            }
        });
        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (browser.canGoForward())
                        browser.goForward();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (browser.canGoBack())
                    browser.goBack();
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.clearHistory();
            }
        });
        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.reload();
            }
        });

    }
}
