package com.example.ohsangseo.firebase;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InfoActivity extends AppCompatActivity {
    String uid, name, email;
    Uri photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getUserProfile();
        showUserProfile();
    }

    public void getUserProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();
        }
    }

    private void showUserProfile() {
        TextView textView = (TextView) findViewById(R.id.uid);
        textView.setText(uid);
        TextView textView2 = (TextView) findViewById(R.id.name);
        textView2.setText(name);
        TextView textView3 = (TextView) findViewById(R.id.email);
        textView3.setText(email);
        // skip the showing photoUrl, use glide or etc...
    }

}
