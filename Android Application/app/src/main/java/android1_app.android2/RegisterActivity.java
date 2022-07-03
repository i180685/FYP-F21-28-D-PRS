package android1_app.android2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.util.Patterns;

import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;


import android1_app.android2.LoginActivity;
import android1_app.android2.PRS;
import android1_app.android2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;  //for firebase Authentication
    FirebaseUser mUser;
    EditText username, email, mobileno, pass;
    Button register;
    ImageView show_password;
    TextView login;
    DatabaseReference reff;
//    android1_app.android2.PRS prss;
    PRS prss;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        show_password=findViewById(R.id.show_pass_btn);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        mobileno = findViewById(R.id.mobileno);
        pass = findViewById(R.id.pass);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        prss = new PRS();
        reff = FirebaseDatabase.getInstance().getReference().child("PRS"); //"PRS" will be the name of tree in firebase storage


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = username.getText().toString().trim();
                String uemail = email.getText().toString().trim();
                String uphn = mobileno.getText().toString().trim();
                String upass = pass.getText().toString().trim();

                //Input Validation checks
                if (uname.isEmpty()) {
                    username.setError("Username is required!");
                    username.requestFocus();
                    return;
                }

                if (uemail.isEmpty()) {
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {
                    email.setError("Please Provide Valid Email Address!");
                    email.requestFocus();
                    return;
                }

                if (uphn.isEmpty()) {
                    mobileno.setError("Mobile No is required!");
                    mobileno.requestFocus();
                    return;
                }

                if (upass.isEmpty()) {
                    pass.setError("Password is required!");
                    pass.requestFocus();
                    return;
                }
                if (upass.length() < 6) {
                    pass.setError("Min Password length should be 6 charachters!");
                    pass.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(uemail, upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String phn = mobileno.getText().toString().trim() ;

                            prss.setUsername(username.getText().toString().trim());
                            prss.setEmail(email.getText().toString().trim());
                            prss.setMobileno(phn);

                            // prss.setPass(pass.getText().toString().trim());   //we don't want to save user password in DB (Against user privacy) just for testing

                            reff.child(String.valueOf(maxid + 1)).setValue(prss);

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.sendEmailVerification();  //to send verification email to the user upon signup

                            Toast.makeText(RegisterActivity.this, "Check Your Email To verify your Account", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(RegisterActivity.this, android1_app.android2.LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });




        //function to increase database userdata counter in storage tree
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }


    //To change status bar color
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//              window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }


    }

    //For Hiding and unhiding password
    public void ShowHidePass(View view) {
        if(view.getId()==R.id.show_pass_btn){

            if(pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hidden);

                //Show Password
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.eye);

                //Hide Password
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}
