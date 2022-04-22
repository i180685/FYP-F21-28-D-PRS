package android1_app.android2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import android1_app.android2.R;

public class availablespot extends AppCompatActivity {
    
    CardView spot1,spot2,spot3,spot4,spot5,spot6,spot7,spot8,spot9,spot10,spot11,spot12,spot13,spot14,spot15,spot16,spot17,spot18,spot19,spot20;
    TextView spotheading;

    Button back , book;
    Map<String,String> spots = new HashMap<>();
    FirebaseFirestore fStore ;
    FirebaseAuth fAuth;
    String userID;
    private String TAG ;

    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablespot);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        spots.put("1","0");
        spots.put("2","0");
        spots.put("3","0");
        spots.put("4","0");
        spots.put("5","0");
        spots.put("6","0");
        spots.put("7","0");
        spots.put("8","0");
        spots.put("9","0");
        spots.put("10","0");
        spots.put("11","0");
        spots.put("12","0");
        spots.put("13","0");
        spots.put("14","0");
        spots.put("15","0");
        spots.put("16","0");
        spots.put("17","0");
        spots.put("18","0");
        spots.put("19","0");
        spots.put("20","0");

        back= findViewById(R.id.back_button);
        book = findViewById(R.id.bookSpot);

        spot1 = findViewById(R.id.spot1);
        spot2 = findViewById(R.id.spot2);
        spot3 = findViewById(R.id.spot3);
        spot4 = findViewById(R.id.spot4);
        spot5 = findViewById(R.id.spot5);
        spot6 = findViewById(R.id.spot6);
        spot7 = findViewById(R.id.spot7);
        spot8 = findViewById(R.id.spot8);
        spot9 = findViewById(R.id.spot9);
        spot10 = findViewById(R.id.spot10);
        spot11 = findViewById(R.id.spot11);
        spot12 = findViewById(R.id.spot12);
        spot13 = findViewById(R.id.spot13);
        spot14 = findViewById(R.id.spot14);
        spot15 = findViewById(R.id.spot15);
        spot16 = findViewById(R.id.spot16);
        spot17 = findViewById(R.id.spot17);
        spot18 = findViewById(R.id.spot18);
        spot19 = findViewById(R.id.spot19);
        spot20 = findViewById(R.id.spot20);
        spotheading = findViewById(R.id.spotheading);
        fetchData();

        PRS chosenSpot = (PRS) getApplicationContext();
        if( chosenSpot.getChosenspot() == "1") spot1.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "2") spot2.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "3") spot3.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "4") spot4.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "5") spot5.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "6") spot6.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "7") spot7.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "8") spot8.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "9") spot9.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "10") spot10.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "11") spot11.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "12") spot12.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "13") spot13.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "14") spot14.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "15") spot15.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "16") spot16.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "17") spot17.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "18") spot18.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "19") spot19.setBackgroundResource(R.color.orange);
        if( chosenSpot.getChosenspot() == "20") spot20.setBackgroundResource(R.color.orange);

        PRS user = (PRS) getApplicationContext();
        if(user.getStatus() != 1) {
            spot1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("1") == "0") {
                        spot1.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("1");
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("2") == "0") {
                        spot2.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("2");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("3") == "0") {
                        spot3.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("3");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("4") == "0") {
                        spot4.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("4");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("5") == "0") {
                        spot5.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("5");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("6") == "0") {
                        spot6.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("6");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("7") == "0") {
                        spot7.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("7");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("8") == "0") {
                        spot8.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("8");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("9") == "0") {
                        spot9.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("9");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("10") == "0") {
                        spot10.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("10");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("11") == "0") {
                        spot11.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("11");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("12") == "0") {
                        spot12.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("12");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("13") == "0") {
                        spot13.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("13");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("14") == "0") {
                        spot14.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("14");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("15") == "0") {
                        spot15.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("15");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("16") == "0") {
                        spot16.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("16");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("17") == "0") {
                        spot17.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("17");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("18") == "0") {
                        spot18.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("18");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("19") == "0") {
                        spot19.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("19");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("20") == "0") spot20.setBackgroundResource(R.color.spot);
                    }
                }
            });
            spot20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spots.get("20") == "0") {
                        spot20.setBackgroundResource(R.color.orange);
                        chosenSpot.setChosenspot("20");
                        if (spots.get("1") == "0") spot1.setBackgroundResource(R.color.spot);
                        if (spots.get("2") == "0") spot2.setBackgroundResource(R.color.spot);
                        if (spots.get("3") == "0") spot3.setBackgroundResource(R.color.spot);
                        if (spots.get("4") == "0") spot4.setBackgroundResource(R.color.spot);
                        if (spots.get("5") == "0") spot5.setBackgroundResource(R.color.spot);
                        if (spots.get("6") == "0") spot6.setBackgroundResource(R.color.spot);
                        if (spots.get("7") == "0") spot7.setBackgroundResource(R.color.spot);
                        if (spots.get("8") == "0") spot8.setBackgroundResource(R.color.spot);
                        if (spots.get("9") == "0") spot9.setBackgroundResource(R.color.spot);
                        if (spots.get("10") == "0") spot10.setBackgroundResource(R.color.spot);
                        if (spots.get("11") == "0") spot11.setBackgroundResource(R.color.spot);
                        if (spots.get("12") == "0") spot12.setBackgroundResource(R.color.spot);
                        if (spots.get("13") == "0") spot13.setBackgroundResource(R.color.spot);
                        if (spots.get("14") == "0") spot14.setBackgroundResource(R.color.spot);
                        if (spots.get("15") == "0") spot15.setBackgroundResource(R.color.spot);
                        if (spots.get("16") == "0") spot16.setBackgroundResource(R.color.spot);
                        if (spots.get("17") == "0") spot17.setBackgroundResource(R.color.spot);
                        if (spots.get("18") == "0") spot18.setBackgroundResource(R.color.spot);
                        if (spots.get("19") == "0") spot19.setBackgroundResource(R.color.spot);
                    }
                }
            });
        }

        mPreferences=this.getSharedPreferences("Login",MODE_PRIVATE);
        editor=mPreferences.edit();
        String prefemail = mPreferences.getString("email",null);

        PRS pr = (PRS) getApplicationContext();
        if(pr.getStatus() == 1) Toast.makeText(availablespot.this, "Cannot Book another spot Vehicle parked already", Toast.LENGTH_LONG).show();

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pr.getStatus() == 1) {
                    Toast.makeText(availablespot.this, "Cannot Book another spot Vehicle parked already", Toast.LENGTH_LONG).show();

                    fStore.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()) {
                                        for( QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d(TAG, document.getId() + " => " + document.getData() + " spot#" + document.getString("spot"));
                                            String spot = document.getString("spot");
                                            Log.d(TAG, ">"+document.getString("email") + "--" + prefemail + "<");
                                            if( prefemail.equals(document.getString("email"))) {
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
                                    }else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                }else if (pr.getStatus() != 1){
                    fStore.collection("users").document(pr.getDocID()).update("spot",chosenSpot.getChosenspot());
                    fStore.collection("users").document(pr.getDocID()).update("status",0);
                    fStore.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()) {
                                        for( QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d(TAG, document.getId() + " => " + document.getData() + " spot#" + document.getString("spot"));
                                            String spot = document.getString("spot");
                                            Log.d(TAG, ">"+document.getString("email") + "--" + prefemail + "<");
                                            if( prefemail.equals(document.getString("email"))) {
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
                                    }else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                    startActivity(new Intent(availablespot.this, dashboard.class));
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(availablespot.this, dashboard.class));
            }
        });
    }
    public void fetchData(){
             fStore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for( QueryDocumentSnapshot document : task.getResult()){
                                Log.d(TAG, document.getId() + " => " + document.getData() + " spot#" + document.getString("spot"));
                                String spot = document.getString("spot");
                                Long status = document.getLong("status");
                                if ((spots.get(spot)) == "0" && status == 1) {
                                    spots.replace(spot, "1");
                                    Log.d(TAG, spot +"   " + spots.get(spot));
                                }
                            }

                            if (spots.get("1")=="1") spot1.setBackgroundResource(R.color.red);
                            if (spots.get("2")=="1") spot2.setBackgroundResource(R.color.red);
                            if (spots.get("3")=="1") spot3.setBackgroundResource(R.color.red);
                            if (spots.get("4")=="1") spot4.setBackgroundResource(R.color.red);
                            if (spots.get("5")=="1") spot5.setBackgroundResource(R.color.red);
                            if (spots.get("6")=="1") spot6.setBackgroundResource(R.color.red);
                            if (spots.get("7")=="1") spot7.setBackgroundResource(R.color.red);
                            if (spots.get("8")=="1") spot8.setBackgroundResource(R.color.red);
                            if (spots.get("9")=="1") spot9.setBackgroundResource(R.color.red);
                            if (spots.get("10")=="1") spot10.setBackgroundResource(R.color.red);
                            if (spots.get("11")=="1") spot11.setBackgroundResource(R.color.red);
                            if (spots.get("12")=="1") spot12.setBackgroundResource(R.color.red);
                            if (spots.get("13")=="1") spot13.setBackgroundResource(R.color.red);
                            if (spots.get("14")=="1") spot14.setBackgroundResource(R.color.red);
                            if (spots.get("15")=="1") spot15.setBackgroundResource(R.color.red);
                            if (spots.get("16")=="1") spot16.setBackgroundResource(R.color.red);
                            if (spots.get("17")=="1") spot17.setBackgroundResource(R.color.red);
                            if (spots.get("18")=="1") spot18.setBackgroundResource(R.color.red);
                            if (spots.get("19")=="1") spot19.setBackgroundResource(R.color.red);
                            if (spots.get("20")=="1") spot20.setBackgroundResource(R.color.red);
                            PRS  user = (PRS) getApplicationContext();
                            if (user.getStatus() == 1) spotheading.setText("You cannot book another spot");
                            else if (user.getStatus() == 2) spotheading.setText("You can change your booked spot");
                            if (user.getSpotNo().equals("1")) spot1.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("2")) spot2.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("3")) spot3.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("4")) spot4.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("5")) spot5.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("6")) spot6.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("7")) spot7.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("8")) spot8.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("9")) spot9.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("10")) spot10.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("11")) spot11.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("12")) spot12.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("13")) spot13.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("14")) spot14.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("15")) spot15.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("16")) spot16.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("17")) spot17.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("18")) spot18.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("19")) spot19.setBackgroundResource(R.color.purple);
                            if (user.getSpotNo().equals("20")) spot20.setBackgroundResource(R.color.purple);
                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });
    }
}