package android1_app.android2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android1_app.android2.LoginActivity;
import android1_app.android2.R;


public class MainActivity extends AppCompatActivity {

    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;
    private static int SPLASH_TIME_OUT = 3000;
    private String Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences=getSharedPreferences("Login",MODE_PRIVATE);
        editor=mPreferences.edit();
        editor.putString("email","i160123@nu.edu.pk");
        editor.putString("password","saad123");
        editor.commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        }, SPLASH_TIME_OUT);

    }
}