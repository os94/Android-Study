package com.example.ohsangseo.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocBoardActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_board);

        buttonWrite = (Button)findViewById(R.id.buttonWrite);
        buttonWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonWrite:
                startActivity(new Intent(getBaseContext(), WritePageActivity.class));
                break;
        }

    }
}
