package com.example.ohsangseo.modulelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient()); //클릭시 새창 안뜨게
        mWebView.setWebChromeClient(new WebChromeClient()); //alert경고창 사용

        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true); //자바스크립트 사용허용

        mWebView.loadUrl("http://www.naver.com");
    }

    @Override //웹뷰내 뒤로가기 동작
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( (keyCode==KeyEvent.KEYCODE_BACK)&&mWebView.canGoBack() ) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
