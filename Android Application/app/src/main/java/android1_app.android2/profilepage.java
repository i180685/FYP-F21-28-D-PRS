package android1_app.android2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import androidx.annotation.Nullable;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;


import com.google.firebase.auth.FirebaseAuth;

public class profilepage extends AppCompatActivity {

    ImageView back;
    Button logout;
    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;

    TextView name,emaill,rollNo,status,spotNo,password,companyCar,noPlate,mobile;
    ImageView accountimage,circleImageView;
    Integer REQUEST_CAMERA=1, SELECT_IMAGE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);

        mPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        editor = mPreferences.edit();

        PRS pr = (PRS) getApplicationContext();

        name = findViewById(R.id.name);
        name.setText(pr.getUsername());
        emaill = findViewById(R.id.emaill);
        emaill.setText(pr.getEmail());
        rollNo = findViewById(R.id.rollNo);
        rollNo.setText(pr.getRollNo());
        spotNo = findViewById(R.id.spotNo);
        spotNo.setText(pr.getSpotNo());
        password = findViewById(R.id.password);
        password.setText(pr.getPass());
        companyCar = findViewById(R.id.companyCar);
        companyCar.setText(pr.getCompany() + " " + pr.getCar());
        noPlate = findViewById(R.id.noPlate);
        noPlate.setText(pr.getNoPlate());
        mobile = findViewById(R.id.mobile);
        mobile.setText(pr.getMobileno());
        status = findViewById(R.id.status);
        if (pr.getStatus() == 0)
            status.setText("Not Parked");
        else if (pr.getStatus() == 1)
            status.setText("Parked");

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profilepage.this, dashboard.class));
            }
        });


//        accountimage = findViewById(R.id.accountimage);
//        accountimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SelectImage();
//            }
//        });
//
//        circleImageView = findViewById(R.id.circleImageView);
//        circleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SelectImage();
//            }
//        });


        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("email",null);
                editor.putString("password",null);
                editor.commit();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profilepage.this, LoginActivity.class));
            }
        });
    }
    //Select image from gallery or camera function.
//    private void SelectImage()
//    {
//        final CharSequence[] items={"Camera","Gallery","Cancel"};
//        AlertDialog.Builder builder=new AlertDialog.Builder(profilepage.this);
//        builder.setTitle("Add Image");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if(items[i].equals("Camera"))
//                {
//                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent,REQUEST_CAMERA);
//                }
//                else if(items[i].equals("Gallery"))
//                {
//                    Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("image/*");
//                    startActivityForResult(intent,SELECT_IMAGE);
//
//                } else if (items[i].equals("Cancel"))
//                {
//                    dialogInterface.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==RESULT_OK)
//        {
//            if(requestCode==REQUEST_CAMERA)
//            {
//                Bitmap bitmap=(Bitmap)data.getExtras().get("data");
//                circleImageView.setImageBitmap(bitmap);
//                accountimage.setVisibility(View.GONE);
//            }
//            else if(requestCode==SELECT_IMAGE)
//            {
//                Uri selectImage=data.getData();
//                circleImageView.setImageURI(selectImage);
//                accountimage.setVisibility(View.GONE);
//            }
//        }
//    }


}