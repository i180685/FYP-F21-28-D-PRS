package android1_app.android2;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.util.Patterns;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android1_app.android2.R;
import android1_app.android2.RegisterActivity;
import android1_app.android2.dashboard;
import android1_app.android2.forgetpassword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth ;
    EditText email,pass;
    Button signin;
    TextView register,forgotpass;
    private String Value;
    SharedPreferences getPref;
    SharedPreferences.Editor editor;
    String TAG;
    FirebaseFirestore fStore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass  = findViewById(R.id.pass);
        signin = findViewById(R.id.login);
        register = findViewById(R.id.register);
        forgotpass = findViewById(R.id.forget);
        fStore = FirebaseFirestore.getInstance();

        getPref=this.getSharedPreferences("Login",MODE_PRIVATE);
        editor=getPref.edit();
        String prefemail = getPref.getString("email","null");
        String prefpassword = getPref.getString("password","null");
        Log.d(TAG, "\n\nprefemail => "+prefemail);
        if (prefemail != null) {
            mAuth.signInWithEmailAndPassword(prefemail, prefpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        fStore.collection("users")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Log.d(TAG, document.getId() + " => " + document.getData() + " spot#" + document.getString("spot"));
                                                String spot = document.getString("spot");
                                                Log.d(TAG, ">" + document.getString("email") + "--" + prefemail + "<");
                                                if (prefemail.equals(document.getString("email"))) {
                                                    PRS user = (PRS) getApplicationContext();
                                                    user.setUsername(document.getString("name"));
                                                    user.setEmail(document.getString("email"));
                                                    user.setCar(document.getString("carName"));
                                                    user.setCompany(document.getString("carComp"));
                                                    user.setMobileno(document.getString("mobileNo"));
                                                    user.setPass(document.getString("password"));
                                                    user.setRollNo(document.getString("rollNo"));
                                                    user.setSpotNo(document.getString("spot"));
                                                    user.setNoPlate(document.getString("carNoplate"));
                                                    user.setStatus(document.getLong("status"));
                                                    user.setDocID(document.getId());
                                                }
                                            }
                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }
                                    }
                                });
                        startActivity(new Intent(LoginActivity.this, dashboard.class));
                    }
                }
            });
        }
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this,dashboard.class));

                String uemail= email.getText().toString().trim();
                String upass= pass.getText().toString().trim();

                if(uemail.isEmpty()){
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(uemail).matches()){
                    email.setError("Please Provide Valid Email Address!");
                    email.requestFocus();
                    return;
                }

                if(upass.isEmpty()){
                    pass.setError("Password is required!");
                    pass.requestFocus();
                    return;
                }
                if(upass.length()<6)
                {
                    pass.setError("Min Password length should be 6 charachters!");
                    pass.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(uemail,upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            editor.putString("email",uemail);
                            editor.putString("password",upass);
                            editor.commit();
                            startActivity(new Intent(LoginActivity.this, dashboard.class));
                            Toast.makeText(LoginActivity.this, "Sign In Successful", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Failed To Login! Please Check Your Credentials ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, forgetpassword.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (LoginActivity.this, RegisterActivity.class));
            }
        });
    }




}
