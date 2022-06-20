package com.example.sameapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.sameapp.Register.MyPREFERENCES;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.sameapp.api.ContactsApi;
import com.example.sameapp.api.apiContact;
import com.example.sameapp.api.apiInvitation;
import com.example.sameapp.dao.ContactDao;
import com.example.sameapp.dao.UserDao;
import com.example.sameapp.models.Contact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactFormActivity extends AppCompatActivity {

    private ContactAppDB db;
    private ContactDao contactDao;
    private UserDao userDao;
    private ContactsApi contactsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);


        db = Room.databaseBuilder(getApplicationContext(),ContactAppDB.class,"ContactsDB")
                .allowMainThreadQueries().build();

        contactDao = db.contactDao();
        userDao = db.userDao();

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String localServer = sharedpreferences.getString("SERVER", "");

        contactsApi = new ContactsApi(getApplicationContext(), contactDao, localServer);


        Button btnAdd = findViewById(R.id.contact_btnAdd);

        btnAdd.setOnClickListener(view->{

            EditText userName = findViewById(R.id.contact_userName);
            EditText nickname = findViewById(R.id.contact_nickname);
            EditText server = findViewById(R.id.contact_server);

            //TODO need to edit what we create, just for now.

            Time today = new Time(Time.getCurrentTimezone());
            today.setToNow();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String strDate = sdf.format(c.getTime());


            String owner = (sharedpreferences.getString("USERNAME", ""));

            Contact contact = new Contact(userName.getText().toString(), "", strDate, owner);

            apiContact apiContact = new apiContact(userName.getText().toString(), owner,
                    nickname.getText().toString(), server.getText().toString(), "" ,strDate);


            contactsApi.getWebServiceAPI().createContact(apiContact).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200){

                        contactDao.insert(contact);
                        contactDao.update(contact);

                        apiInvitation apiInvitation = new apiInvitation(contact.getUserNameOwner(), contact.getContactID(), server.getText().toString());
                        contactsApi.getWebServiceAPI().invitationUser(apiInvitation).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.code() == 200){

                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });

                    }
                    else{
                        Toast t = Toast.makeText(getApplicationContext(), "you have to add a real user name", Toast.LENGTH_SHORT);
                        t.show();
                    }
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    finish();
                }
            });

        });
    }
}