package android1_app.android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class ResponseActivity extends AppCompatActivity {
    TextView responseText;
    JazzCashResponse jazzCashResponse;
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        responseText = findViewById(R.id.responseText);

        if (getIntent() != null) {

            jazzCashResponse = (JazzCashResponse) getIntent().getSerializableExtra(Constants.jazzCashResponse);

            responseText.setText(jazzCashResponse.getPpResponseMessage()); //to show message received from jazzcash
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ResponseActivity.this, dashboard.class));

            }
        }, SPLASH_TIME_OUT);


    }
}

