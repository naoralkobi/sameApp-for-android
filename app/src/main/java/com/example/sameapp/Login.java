package com.example.sameapp;

import static com.example.sameapp.Register.MyPREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.sameapp.api.UsersApi;
import com.example.sameapp.api.apiUser;
import com.example.sameapp.dao.UserDao;
import com.example.sameapp.models.User;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {


    Button loginButton;
    TextView moveButton;

    private ContactAppDB db;
    private UserDao userDao;
    private SharedPreferences sharedpreferences;
    private UsersApi usersApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = Room.databaseBuilder(getApplicationContext(), ContactAppDB.class, "ContactsDB")
                .allowMainThreadQueries().build();

        userDao = db.userDao();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String server = sharedpreferences.getString("SERVER", "");

        usersApi = new UsersApi(getApplicationContext(), server);

        loginButton = findViewById(R.id.login_loginButton);
        moveButton = findViewById(R.id.move_to_register);

        // when press to login move to list of contact list.
        loginButton.setOnClickListener(view -> {
            // hide the keyboard after press.
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            EditText ItemUserName = findViewById(R.id.login_userName);
            EditText ItemPassword = findViewById(R.id.login_password);

            String userName = ItemUserName.getText().toString();
            String password = ItemPassword.getText().toString();

            //User user = userDao.get(userName);
            if (userName.length() == 0 || password.length() == 0) {
                Toast t = Toast.makeText(getApplicationContext(), "UserName or Password are not valid.", Toast.LENGTH_SHORT);
                t.show();
            }
//                else if(user == null){
//                    Toast t = Toast.makeText(getApplicationContext(), "User don't exist.", Toast.LENGTH_SHORT);
//                    t.show();
//                }
//                else if ( user != null && (!user.getPassword().equals(password))) {
//                    Toast t = Toast.makeText(getApplicationContext(), "UserName or Password Wrong.", Toast.LENGTH_SHORT);
//                    t.show();
//                }
            else {

                apiUser apiUser = new apiUser(userName, password);

                new Thread(()  -> usersApi.getWebServiceAPI().login(apiUser).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200){

//                            new Thread(() -> {
//                                User temp = userDao.isDataExist(userName);
//                                // if the user not exist in Room.
//                                if (temp == null){
//                                    User newUser = new User(userName, password, null);
//                                    userDao.insert(newUser);
//                                }
//                            }).start();


                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("USERNAME", userName);
                            editor.apply();

                            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(Login.this, instanceIdResult -> {
                                String newToken = instanceIdResult.getToken();

                                usersApi.getWebServiceAPI().sendToken(userName, newToken).enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {

                                        if (response.code() == 200){

                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });


                            });

                            Intent intent = new Intent(getApplicationContext(), activity_list.class);
                            startActivity(intent);
                        }else{
                            Toast t = Toast.makeText(getApplicationContext(), "Please sign in first.", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                })).start();

            }
        });

        moveButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });

    }
}