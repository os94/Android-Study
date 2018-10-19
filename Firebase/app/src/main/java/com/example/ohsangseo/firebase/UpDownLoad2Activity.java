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

import java.util.HashMap;
import java.util.Map;

public class UpDownLoad2Activity extends AppCompatActivity {
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("col1/doc1"); //.collection("col1").document("doc1");
    private static final String TAG = "##### upload:";
    private static final String TAG2 = "##### download:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_down_load2);
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


        mDocRef.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "save success!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, e);
            }
        });
        Toast.makeText(this, "Send !", Toast.LENGTH_SHORT).show();
    }

    public void download(View view) {
        final TextView textView = (TextView)findViewById(R.id.textViewGet);

        mDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG2, document.getId() + " => " + document.getData());
                        textView.setText(document.getId() + " => " + document.getData());
                    } else {
                        Log.d(TAG2, "No such document");
                    }
                } else {
                    Log.d(TAG2, "get failed with ", task.getException());
                }
            }
        });
        Toast.makeText(this, "Send !", Toast.LENGTH_SHORT).show();
    }

}
