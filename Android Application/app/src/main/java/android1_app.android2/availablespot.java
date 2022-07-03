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

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

import java.util.Locale;


import android.annotation.TargetApi;
import java.text.SimpleDateFormat;
import java.util.Date;







import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
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

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    Button timeButton1, timeButton2;
    int hour1, minute1;
    int hour2, minute2;

    int cday,cmonth,cyear;
    int sday,smonth,syear;

    SimpleDateFormat sdf1, sdf2;
    String currentDate;
    String currentTime;


    Button back , book;
    Map<String,String> spots = new HashMap<>();
    FirebaseFirestore fStore ;
    FirebaseAuth fAuth;
    String userID;
    private String TAG ;

    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablespot);
        initDatePicker();

//
//        Calendar cal = Calendar.getInstance();
//        cyear = cal.get(Calendar.YEAR);
//        cmonth = cal.get(Calendar.MONTH);
//        cmonth = cmonth + 1;
//        cday = cal.get(Calendar.DAY_OF_MONTH);

//        if(sday==cday && smonth==cmonth && syear==cyear){
//            Toast.makeText(availablespot.this, "Dates are Equal", Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Toast.makeText(availablespot.this, "nahhhhhh", Toast.LENGTH_LONG).show();
//        }

        //current date and time
//        sdf1 = new SimpleDateFormat("MM.dd.yyyy");
//        currentDate= sdf1.format(new Date());
//        System.out.println(sdf1+"/n");
//
//        sdf2 = new SimpleDateFormat("HH:mm");
//        currentTime= sdf2.format(new Date());
//        System.out.println(sdf1);

        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        timeButton1 = findViewById(R.id.timeButton1);
        timeButton2 = findViewById(R.id.timeButton2);


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
        if(user.getStatus() != null && user.getStatus() != 1) {
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
        if(pr.getStatus() != null && pr.getStatus() == 1) Toast.makeText(availablespot.this, "Cannot Book another spot Vehicle parked already", Toast.LENGTH_LONG).show();

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pr.getStatus() != null && pr.getStatus() == 1) {
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
                }else if (pr.getStatus() != null && pr.getStatus() != 1){
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
                            if (user.getStatus() != null && user.getStatus() == 1) spotheading.setText("You cannot book another spot");
                            else if (user.getStatus() != null && user.getStatus() == 2) spotheading.setText("You can change your booked spot");
                            //here onwards changing color to black from purple version final
                            if(user.getSpotNo() != null) {
                                if (user.getSpotNo().equals("1"))
                                    spot1.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("2"))
                                    spot2.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("3"))
                                    spot3.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("4"))
                                    spot4.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("5"))
                                    spot5.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("6"))
                                    spot6.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("7"))
                                    spot7.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("8"))
                                    spot8.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("9"))
                                    spot9.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("10"))
                                    spot10.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("11"))
                                    spot11.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("12"))
                                    spot12.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("13"))
                                    spot13.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("14"))
                                    spot14.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("15"))
                                    spot15.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("16"))
                                    spot16.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("17"))
                                    spot17.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("18"))
                                    spot18.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("19"))
                                    spot19.setBackgroundResource(R.color.red);
                                if (user.getSpotNo().equals("20"))
                                    spot20.setBackgroundResource(R.color.red);
                            }
                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });
    }



    //Date and Time Picker implementation






    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        sday=day;
        smonth=month;
        syear=year;
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }


    //Timer functions below
    public void popTimePicker1(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour1 = selectedHour;
                minute1 = selectedMinute;
                timeButton1.setText(String.format(Locale.getDefault(), "%02d:%02d", hour1, minute1));
            }
        };


        // int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog1 = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour1, minute1, true);
        timePickerDialog1.setTitle("Select Time");
        timePickerDialog1.show();
    }



    public void popTimePicker2 (View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour2 = selectedHour;
                minute2 = selectedMinute;
                timeButton2.setText(String.format(Locale.getDefault(), "%02d:%02d", hour2, minute2));
            }
        };

        TimePickerDialog timePickerDialog2 = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour2, minute2, true);
        timePickerDialog2.setTitle("Select Time");
        timePickerDialog2.show();
    }





}