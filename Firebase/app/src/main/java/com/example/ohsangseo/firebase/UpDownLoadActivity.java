package com.example.ohsangseo.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UpDownLoadActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "##### upload:";
    private static final String TAG2 = "##### download:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_down_load);
    }

    public void upload(View view) {
        EditText nameView = (EditText) findViewById(R.id.editTextName);
        EditText contentView = (EditText) findViewById(R.id.editTextContent);
        String name = nameView.getText().toString();
        String content = contentView.getText().toString();

        if (content.isEmpty() || name.isEmpty()) {
            return;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("content", content);

        db.collection("col2").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
        Toast.makeText(this, "Send !", Toast.LENGTH_SHORT).show();
    }

    public void download(View view) {
        final TextView textView = (TextView)findViewById(R.id.textViewGet);

        db.collection("col2")
                .whereEqualTo("name", "tom") //주석시 Col2 전부 가져옴,document에 차례로
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG2, document.getId() + " => " + document.getData());
                                textView.setText(document.getId() + " => " + document.getData()); //show final value
                            }
                        } else {
                            Log.d(TAG2, "Error getting documents: ", task.getException());
                        }
                    }
                });
        Toast.makeText(this, "Get !", Toast.LENGTH_SHORT).show();
    }
}
