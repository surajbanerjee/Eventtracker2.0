package info.EventTracker.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addnew extends AppCompatActivity {

    private Button logout;
    private TextView textView;
    private EditText EventName,CollegeName,Description;
    private EditText date;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);

        logout = findViewById(R.id.logout);
        textView = findViewById(R.id.textView);
        EventName = findViewById(R.id.EventName);
        CollegeName = findViewById(R.id.CollegeName);
        date = findViewById(R.id.date);
        Description =findViewById(R.id.Description);
        add = findViewById(R.id.add);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(addnew.this, "Logout SucessFully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(addnew.this , MainActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String,Object> map = new HashMap<>();
                map.put("EventName",EventName.getText().toString());
                map.put("CollegeName",CollegeName.getText().toString());
                map.put("Date",date.getText().toString());
                map.put("Description",Description.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("Events").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("ooo", "onComplete: ");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("ooo", "onFailure: "+e.toString());
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.i("ooo", "onSuccess: ");
                            }
                        });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt_EventName = EventName.getText().toString();
                        String txt_CollegeName = CollegeName.getText().toString();
                        String txt_date = date.getText().toString();
                        String txt_Description = Description.getText().toString();

                        if (TextUtils.isEmpty(txt_EventName) || TextUtils.isEmpty(txt_CollegeName) || TextUtils.isEmpty(txt_date) || TextUtils.isEmpty(txt_Description)){
                            Toast.makeText(addnew.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                        }else{
                            adds(txt_EventName,txt_CollegeName,txt_date,txt_Description);
                        }
                    }
                });

            }
        });
    }

    public void adds(String EventName,String CollegeName,String date, String Description) {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(addnew.this, "Data added !", Toast.LENGTH_LONG).show();
                startActivity(new Intent(addnew.this, events.class));
                finish();
            }
        });
    }
}
