package info.EventTracker.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText emaillog;
    private EditText password;
    private Button login;
    private TextView signlink;
    private ProgressBar progressBars;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emaillog = findViewById(R.id.emaillog);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signlink =findViewById(R.id.signlink);
        progressBars =findViewById(R.id.progressBars);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emaillog.getText().toString().trim();
                String Password = password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emaillog.setError("Email is Required !!!");
                    return;
                }

                if (TextUtils.isEmpty(Password)){
                    password.setError("Password is required !!!");
                    return;
                }

                if(Password.length()<6){
                    password.setError("password must be >= 6 characters");
                    return;
                }

                progressBars.setVisibility(View.VISIBLE);

                //authentic the user

                auth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(login.this,"Logged in SucessFully!! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),addnew.class));
                        }else{
                            Toast.makeText(login.this,"ERROR!!" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBars.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        signlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), signup.class));
            }
        });

    }
}
