package android1_app.android2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class fineamount extends AppCompatActivity {
    Button back;
    Button BuyNow;
    TextView Fine;
    String price;
    Integer val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fineamount);

        back=findViewById(R.id.back_button);
        BuyNow = findViewById(R.id.buyNow);
        Fine = findViewById(R.id.fine);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fineamount.this, dashboard.class));
            }
        });

//        BuyNow.setOnClickListener(view -> {
//            Intent intent = new Intent(fineamount.this, PaymentActivity.class);
//            intent.putExtra("price", Fine.getText().toString());
//            startActivity(intent);
//        });
        val=0;
        price=Fine.getText().toString();
        val=Integer.parseInt(price);

        if(val>0)
        {
            BuyNow.setOnClickListener(view -> {
                Intent intent = new Intent(fineamount.this, PaymentActivity.class);
                intent.putExtra("price", Fine.getText().toString());
                startActivity(intent);
            });
        }
        else
        {
            Toast.makeText(fineamount.this, "No Fine Available", Toast.LENGTH_SHORT).show();
        }


    }
}