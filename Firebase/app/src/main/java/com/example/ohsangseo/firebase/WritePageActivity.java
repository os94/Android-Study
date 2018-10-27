package com.example.ohsangseo.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class WritePageActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "##### WritePage Log:";
    private EditText editTextTitle, editTextContent;
    private Spinner spinner;
    private SpinnerAdapter sAdapter;
    private String[] category;
    private Button submit;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_page);

        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextContent = (EditText) findViewById(R.id.editTextContent);
        spinner = (Spinner) findViewById(R.id.spinner);
        category = getResources().getStringArray(R.array.category);
        submit = (Button) findViewById(R.id.buttonSubmit);

        sAdapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                data.put("category", category[i]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(WritePageActivity.this, "select Category!", Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        data.put("title", editTextTitle.getText().toString());
        data.put("content", editTextContent.getText().toString());

        db.collection("Doc Board").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                Toast.makeText(getBaseContext(), "Send!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
                Toast.makeText(getBaseContext(), "Failed.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}
