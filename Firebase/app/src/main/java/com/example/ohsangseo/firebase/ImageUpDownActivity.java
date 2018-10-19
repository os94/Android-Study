package com.example.ohsangseo.firebase;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ImageUpDownActivity extends AppCompatActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    // Create a storage reference from our app
    StorageReference storageRef = storage.getReference();
    // Reference to an image file in Firebase Storage
    StorageReference storageRef2 = storageRef.child("images/3082");

    private static final int GALLERY_CODE = 1112;
    private static final int MY_PERMISSIONS = 101;
    private String[] permissons = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_up_down);

        checkPermission();
    }

    private void checkPermission() {
        int result;
        List<String> permissionList = new ArrayList<>();
        for(String pm : permissons) {
            result = ContextCompat.checkSelfPermission(this, pm);
            if(result == PackageManager.PERMISSION_DENIED) {
                permissionList.add(pm);
            }
        }
        if(!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this
                    , permissionList.toArray(new String[permissionList.size()])
                    , MY_PERMISSIONS);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS:
                if(grantResults.length>0) {
                    for(int i=0 ; i<permissions.length ; i++) {
                        if(grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    public void gallery(View view) {
        Intent iGal = new Intent(Intent.ACTION_PICK);
        iGal.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        iGal.setType("image/*");

        startActivityForResult(iGal, GALLERY_CODE);
    }

    // onActivityResult로 intent의 결과값을 처리하고, data.getData로 사진URI를 가져온다
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Uri file = data.getData();
            StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
            UploadTask uploadTask = riversRef.putFile(file);

            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    Toast.makeText(getBaseContext(), "Send !", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void get(View view) {
        ImageView imageView = (ImageView)findViewById(R.id.imageview);
        Glide.with(this /* context */)
                .using(new FirebaseImageLoader())
                .load(storageRef2)
                .into(imageView);
        Toast.makeText(this, "Get !", Toast.LENGTH_SHORT).show();
    }


}
