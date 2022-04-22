package android1_app.android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class profilepage extends AppCompatActivity {

    ImageView back;
    Button logout;
    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;
    TextView name,emaill,rollNo,status,spotNo,password,companyCar,noPlate,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);

        mPreferences=getSharedPreferences("Login",MODE_PRIVATE);
        editor=mPreferences.edit();

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
        if(pr.getStatus() == 0 )
            status.setText("Not Parked");
        else if(pr.getStatus() == 1)
            status.setText("Parked");

        back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profilepage.this, dashboard.class));
            }
        });
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
}