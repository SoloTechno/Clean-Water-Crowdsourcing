package com.gatech.edu.soloTechno.m4_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import static android.R.attr.name;


/**
 * Created by timothybaba on 2/19/17.
 */

public class RegisterActivity extends AppCompatActivity {
    private Spinner accountTypeSpinner;
    private EditText firstName_text;
    private EditText lastName_text;
    private EditText email_text;
    private EditText password_text;
    private FirebaseAuth auth;
    public static final String TAG = RegisterActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String accountType;
    private String email;
    private String password;
    private String firstName;
    private String lastName;



    public static List<String> accounts = Arrays.asList("Manager", "Worker", "Admin", "User");


    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        accountTypeSpinner = (Spinner) findViewById(R.id.spinner4);
        firstName_text = (EditText) findViewById(R.id.first_Name);
        lastName_text = (EditText) findViewById(R.id.last_Name);
        email_text = (EditText) findViewById(R.id.email);
        password_text = (EditText) findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();
        final Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submitForm();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, accounts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        createAuthStateListener();
    }

    /**
     * Private helper method to register the user through Firebase authentication. After user
     * submits information, method reads in email and password, calls the Firebase instance to
     * add new users to the system.
     */
    private void submitForm() {

        accountType = accountTypeSpinner.getSelectedItem().toString().trim();
        email = email_text.getText().toString().trim();
        password = password_text.getText().toString().trim();
        firstName = firstName_text.getText().toString().trim();
        lastName = lastName_text.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            //startActivity(intent);
                            Log.d(TAG, "Authentication successful");

                        }
                    }
                });
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference firstNameRef =  myRootRef.child("First Name");
                    firstNameRef.setValue(firstName);
                    DatabaseReference lastNameRef =  myRootRef.child("Last Name");
                    lastNameRef.setValue(lastName);
                    DatabaseReference accountTypeRef =  myRootRef.child("Account Type");
                    accountTypeRef.setValue(accountType);


                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            auth.removeAuthStateListener(mAuthListener);
        }
    }
}