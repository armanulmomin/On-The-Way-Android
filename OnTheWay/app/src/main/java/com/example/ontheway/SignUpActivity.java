package com.example.ontheway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText username,email,phonenumber,pass;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=findViewById(R.id.usernameid);
        pass=findViewById(R.id.passid);
        phonenumber=findViewById(R.id.mobilenoid);
        email=findViewById(R.id.emailid);
        progressBar=(ProgressBar)findViewById(R.id.progressBarid);
        mAuth = FirebaseAuth.getInstance();




    }
    public void signupButtonclicked(View v)
    {
        String textusername= username.getText().toString().trim();
        String textpass= pass.getText().toString().trim();
        String textphone= phonenumber.getText().toString().trim();
        String textemail= email.getText().toString().trim();

        if(textusername.isEmpty())
        {
            username.setError("Please enter username");
            username.requestFocus();
        }

        if(textpass.isEmpty())
        {
            pass.setError("Please enter Password");
            pass.requestFocus();
        }

        if(textphone.isEmpty())
        {
            phonenumber.setError("Please enter Phone Number");
            phonenumber.requestFocus();
        }

        if(textemail.isEmpty())
        {
            email.setError("Please enter Email");
            email.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(textemail,textpass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            user user=new user(textusername, textpass, textphone, textemail);
                            FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull  Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(SignUpActivity.this,"user Registered Successfully",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else
                                    {
                                        Toast.makeText(SignUpActivity.this,"user failed Registered Successfully",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });


                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"user failed Registered Successfully",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });



    }
}