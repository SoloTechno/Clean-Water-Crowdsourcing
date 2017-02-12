package com.example.timothybaba.m4_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joshua on 2/12/2017.
 */

public class MainActivity extends AppCompatActivity {
    private Button mLogoutButton;

    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.logout_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent logoutActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(logoutActivity);
            }
        });
    }

}
