package com.example.sameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.sameapp.Register.MyPREFERENCES;


public class SettingsActivity extends AppCompatActivity {

    Button saveButton;
    Button cancelButton;

    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        saveButton = findViewById(R.id.set_server_button);
        cancelButton = findViewById(R.id.back_button);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        saveButton.setOnClickListener(v -> {

            // hide the keyboard after press.
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            EditText ItemServer = findViewById(R.id.set_server_input);
            String server= ItemServer.getText().toString();

            if (server.length() == 0){
                Toast t = Toast.makeText(getApplicationContext(), "Please insert server", Toast.LENGTH_SHORT);
                t.show();
            }


            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("SERVER", server);
            editor.commit();

            finish();

        });

        cancelButton.setOnClickListener(v -> {

            // hide the keyboard after press.
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            finish();
        });



    }
}