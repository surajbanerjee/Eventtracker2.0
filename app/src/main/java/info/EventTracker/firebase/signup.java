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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.PasswordAuthentication;

public class signup extends AppCompatActivity {
    private EditText emailid;
    private EditText pasword;
    private Button register;
    private Button loginlink;
    private ProgressBar progressBar;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailid = findViewById(R.id.emailid);
        pasword = findViewById(R.id.pasword);
        register = findViewById(R.id.register);
        loginlink =findViewById(R.id.loginlink);
        progressBar =findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),addnew.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString().trim();
                String Password = pasword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailid.setError("Email is Required !!!");
                    return;
                }

                if (TextUtils.isEmpty(Password)){
                    pasword.setError("Password is required !!!");
                    return;
                }

                if(Password.length()<6){
                    pasword.setError("password must be >= 6 characters");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                // register user in firebase
                auth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(signup.this,"user Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),addnew.class));
                        }else{
                            Toast.makeText(signup.this,"ERROR!!" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signup.this, login.class);
                startActivity(i);
            }
        });

    }

}
