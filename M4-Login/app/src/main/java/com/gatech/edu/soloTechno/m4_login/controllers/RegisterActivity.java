package com.gatech.edu.soloTechno.m4_login.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.gatech.edu.soloTechno.m4_login.R;

import java.util.Arrays;
import java.util.List;


/**
 * Created by timothybaba on 2/19/17.
 */

public class RegisterActivity extends AppCompatActivity {
    private Spinner accountTypeSpinner;


    public static List<String> accounts = Arrays.asList("Manager", "Worker", "Admin", "User");




    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_register);

        final Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent saveActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(saveActivity);
            }
        });

        accountTypeSpinner = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, accounts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

    }
}
