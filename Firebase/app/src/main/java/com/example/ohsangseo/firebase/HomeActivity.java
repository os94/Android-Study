package com.example.ohsangseo.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.upload).setOnClickListener(this);
        findViewById(R.id.download).setOnClickListener(this);
        findViewById(R.id.info).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload:
                startActivity(new Intent(getBaseContext(), UploadActivity.class));
                break;
            case R.id.download:
                startActivity(new Intent(getBaseContext(), DownloadActivity.class));
                break;
            case R.id.info:
                startActivity(new Intent(getBaseContext(), InfoActivity.class));
                break;
        }
    }

}
