package com.sos.ohsangseo.gem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sos.ohsangseo.gem.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int gem[] = {
            R.drawable.g1, R.drawable.g2, R.drawable.g3,
            R.drawable.g4, R.drawable.g5, R.drawable.g6,
            R.drawable.g7
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageResource(gem[0]);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int i = random.nextInt(7);
                iv.setImageResource(gem[i]);
            }
        });

    }
}
