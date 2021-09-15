package com.basic.chatter_v05a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        mAuth = FirebaseAuth.getInstance();
        final EditText mEmail = (EditText) findViewById(R.id.reset_email_input);


        Button mPasswordResetBtn = (Button) findViewById(R.id.password_reset_btn);
        mPasswordResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString().trim();
                System.out.println("Email is " + email);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(PasswordReset.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(PasswordReset.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}