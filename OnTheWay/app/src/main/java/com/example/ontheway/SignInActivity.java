package com.example.ontheway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class SignInActivity extends AppCompatActivity {

    EditText username, pass;
    TextView forgotpass, register;
    Button signin;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username = findViewById(R.id.signinnameid);
        pass = findViewById(R.id.signinpassid);
        forgotpass = findViewById(R.id.forgotpassid);
        register = findViewById(R.id.registerid);
        progressBar = findViewById(R.id.progressBarsignin);
        mAuth = FirebaseAuth.getInstance();


    }

    public void txtforgotpass(View view) {
        Intent intent=new Intent(this,ForgotPassActivity.class);
        startActivity(intent);

    }

    public void txtregister(View view) {
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);

    }

    public void signIn(View view) {
        String un = username.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(un).matches()) {
            username.setError("Please Enter a valid email");
            username.requestFocus();
        }

        if (password.length() < 3) {
            username.setError("Please Enter a valid pass");
            username.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(un, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "User has successfully singed in", Toast.LENGTH_LONG).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "User faild to sing in", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}