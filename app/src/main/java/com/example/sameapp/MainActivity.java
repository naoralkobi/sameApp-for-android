package com.example.sameapp;

import static com.example.sameapp.Register.MyPREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sameapp.api.ContactsApi;
import com.example.sameapp.api.MessagesApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

// this activity is for the first screen when opening the app.
public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    FloatingActionButton settingButton;

    private SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("SERVER", "7001");
        editor.commit();

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        settingButton = findViewById(R.id.SettingsButton);

        // when press to login go to login screen.
        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });

        // when press to register go to register screen.
        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });

        // when press to setting go to setting screen.
        settingButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });


    }
}