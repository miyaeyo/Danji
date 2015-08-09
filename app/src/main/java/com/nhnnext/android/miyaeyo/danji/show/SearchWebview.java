package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nhnnext.android.miyaeyo.danji.R;

/**
 * Created by miyaeyo on 2015. 8. 9..
 */
public class SearchWebview extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_webview);
        String search="http://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ie=utf8&query=";
        WebView webView = (WebView)findViewById(R.id.webview);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String searchQuery = (String) bundle.get("Query");
            webView.loadUrl(search + searchQuery);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
