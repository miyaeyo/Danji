package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.nhnnext.android.miyaeyo.danji.R;


/**
 * webView
 *
 */
public class SearchWebview extends AppCompatActivity {
    private String searchQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_webview);
        String search="http://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ie=utf8&query=";

        WebView webView = (WebView)findViewById(R.id.webview);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            searchQuery = (String) bundle.get("Query");
            webView.loadUrl(search + searchQuery);

        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
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
        Toolbar mToolbar = (Toolbar)findViewById(R.id.search_infobar);
        mToolbar.setTitle(" ");
        setSupportActionBar(mToolbar);

        TextView referenceSearchQuery = (TextView)findViewById(R.id.reference_search_query);
        referenceSearchQuery.setText(getResources().getString(R.string.search_keyword));


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.clear_btn){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
