package android1_app.android2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Window;
import android.view.WindowManager;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class dashboard extends AppCompatActivity {

    private String TAG;
    private String username , userspot;
    private Long userstatus;

    CardView available;
    CardView profile;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore ;
    Button unbook;
    LinearLayout unbookbutton;

    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;

    TextView currspot, curruser, currstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fStore = FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();

        available = findViewById(R.id.available);
        profile = findViewById(R.id.profile);
        unbook = findViewById(R.id.unbook);
        unbookbutton = findViewById(R.id.unbookbutton);
        currspot = findViewById(R.id.currspot);


        mPreferences=this.getSharedPreferences("Login",MODE_PRIVATE);
        editor=mPreferences.edit();
        String prefemail = mPreferences.getString("email",null);

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
//                                Toast.makeText(dashboard.this, "PREF AND EMAIL SAME ", Toast.LENGTH_LONG).show();
                                    username = document.getString("name");
                                    userspot = document.getString("spot");
                                    userstatus = document.getLong("status");
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
                            PRS user = (PRS) getApplicationContext();
                            if (user.getSpotNo().equals("0")) unbookbutton.setVisibility(View.GONE);
                            currspot = findViewById(R.id.currspot);
                            if(user.getSpotNo().equals("0")) currspot.setText("-");
                            else currspot.setText(user.getSpotNo());
                            curruser = findViewById(R.id.curruser);
                            curruser.setText(user.getUsername());
                            currstatus = findViewById(R.id.currstatus);
                            if(user.getStatus() == 1) currstatus.setText("Parked");
                            else if (user.getStatus() == 0 && !user.getSpotNo().equals("0")) currstatus.setText(("On my Way"));
                            else if (user.getStatus() == 0 && user.getSpotNo().equals("0")) currstatus.setText(("Book your Spot"));
                            else if (user.getStatus() == 2) currstatus.setText("In Parking Lot");
                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, profilepage.class));
            }
        });

        currspot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getIntent());
            }
        });
        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, availablespot.class));
            }
        });

        unbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRS pr = (PRS) getApplicationContext();
                fStore.collection("users").document(pr.getDocID()).update("spot","0");
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
                startActivity(new Intent(dashboard.this, dashboard.class));
            }
        });
    }
}