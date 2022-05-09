package com.example.ontheway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {
    EditText editTextemail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        editTextemail=findViewById(R.id.resetpassid);
        mAuth=FirebaseAuth.getInstance();
    }
    public void forgotpassresetbtn(View view)
    {
        resetpasswor();

    }

    private void resetpasswor() {

        String txtemail=editTextemail.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(txtemail).matches())
        {
            editTextemail.setError("Please enter valid email");
            editTextemail.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(txtemail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ForgotPassActivity.this,"Please check your email to reset password",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(ForgotPassActivity.this,SignInActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(ForgotPassActivity.this,"Failed to reset password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}