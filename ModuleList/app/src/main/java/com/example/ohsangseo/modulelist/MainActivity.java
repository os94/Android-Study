package com.example.ohsangseo.modulelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textView2).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), CameraActivity.class);
                        startActivity(intent);
                    }
                }
        );
        findViewById(R.id.textView3).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                    }
                }
        );
        findViewById(R.id.textView4).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), WebviewActivity.class);
                        startActivity(intent);
                    }
                }
        );
        findViewById(R.id.textView5).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), HTMLParseActivity.class);
                        startActivity(intent);
                    }
                }
        );


    }
}
