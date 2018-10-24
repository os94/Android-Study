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

        findViewById(R.id.updown).setOnClickListener(this);
        findViewById(R.id.updown2).setOnClickListener(this);
        findViewById(R.id.info).setOnClickListener(this);
        findViewById(R.id.img).setOnClickListener(this);
        findViewById(R.id.doc).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updown:
                startActivity(new Intent(getBaseContext(), UpDownLoadActivity.class));
                break;
            case R.id.updown2:
                startActivity(new Intent(getBaseContext(), UpDownLoad2Activity.class));
                break;
            case R.id.info:
                startActivity(new Intent(getBaseContext(), InfoActivity.class));
                break;
            case R.id.img:
                startActivity(new Intent(getBaseContext(), ImageUpDownActivity.class));
                break;
            case R.id.doc:
                startActivity(new Intent(getBaseContext(), DocBoardActivity.class));
                break;
        }
    }

}
