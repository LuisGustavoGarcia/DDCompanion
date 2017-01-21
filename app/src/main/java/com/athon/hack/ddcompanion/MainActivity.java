package com.athon.hack.ddcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button signInButton;
    private TextView registerButton;
    private EditText email;
    private EditText password;
    private FirebaseAuth authentication;

    private String emailStr;
    private String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = (Button)findViewById(R.id.signInButtonID);
        email = (EditText)findViewById(R.id.emailInputID);
        password = (EditText)findViewById(R.id.passwordInputID);
        registerButton = (TextView) findViewById(R.id.createAccountButton);
        authentication = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                emailStr = email.getText().toString();
                passwordStr = password.getText().toString();

                authentication.signInWithEmailAndPassword(emailStr,passwordStr)
                        .addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(),"Log in successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,DrawerActivity.class));
                            }
                        });
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

    }
}
