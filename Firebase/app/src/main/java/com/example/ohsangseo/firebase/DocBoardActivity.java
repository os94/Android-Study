package com.example.ohsangseo.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class DocBoardActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonWrite;
    private ListView listView;
    DocListViewAdapter adapter = new DocListViewAdapter();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "##### get:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_board);

        listView = (ListView)findViewById(R.id.listviewDoc);
        getDB();

        buttonWrite = (Button)findViewById(R.id.buttonWrite);
        buttonWrite.setOnClickListener(this);
    }

    private void getDB() {
        db.collection("Doc Board")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Map<String, Object> data = document.getData();
                                adapter.addItem((String)data.get("title"), (String)data.get("content"));
                            }
                            listView.setAdapter(adapter);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
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
