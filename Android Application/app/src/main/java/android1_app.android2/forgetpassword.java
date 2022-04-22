package android1_app.android2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android1_app.android2.LoginActivity;
import android1_app.android2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        mAuth= FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uemail= email.getText().toString().trim();

                //Validation Checks on User Input
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
                mAuth.sendPasswordResetEmail(uemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgetpassword.this, "Check Your Email To Reset Your Password!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(forgetpassword.this, "Try Again! Something wrong happened! ", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                startActivity(new Intent(forgetpassword.this, LoginActivity.class));
            }
        });

    }
}