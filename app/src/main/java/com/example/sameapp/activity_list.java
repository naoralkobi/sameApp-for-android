package com.example.sameapp;

import static com.example.sameapp.Register.MyPREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.sameapp.api.ContactsApi;
import com.example.sameapp.api.apiContact;
import com.example.sameapp.dao.ContactDao;
import com.example.sameapp.models.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_list extends AppCompatActivity {

    private ContactAppDB db;
    private ContactDao contactDao;
    private ArrayList<Contact> contacts;

    ListView listView;
    CustomListAdapter adapter;
    ContactsApi contactsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db= Room.databaseBuilder(getApplicationContext(),ContactAppDB.class,"ContactsDB")
                .allowMainThreadQueries().build();

        contactDao = db.contactDao();

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String server = sharedpreferences.getString("SERVER", "");

        contactsApi = new ContactsApi(getApplicationContext(), contactDao, server);


        String owner = (sharedpreferences.getString("USERNAME", ""));

        // add new contact:
        FloatingActionButton addContact = findViewById(R.id.addContactButton);

        addContact.setOnClickListener(view ->{
            Intent i = new Intent(this,ContactFormActivity.class);
            startActivity(i);
        });

        contacts = new ArrayList<>();

        listView = findViewById(R.id.list_view);
        adapter = new CustomListAdapter(getApplicationContext(), contacts);

        listView.setAdapter(adapter);
        listView.setClickable(true);

        Call<List<apiContact>> call = contactsApi.getWebServiceAPI().getContacts(owner);
        call.enqueue(new Callback<List<apiContact>>(){

            @Override
            public void onResponse(Call<List<apiContact>> call, Response<List<apiContact>> response) {
                List<apiContact> contactsList = response.body();
                if (contactsList != null){

                    contactDao.clear(owner);

                    for (apiContact c : contactsList) {

                        Contact temp = contactDao.isDataExist(c.getUserNameOwner(), c.getId());
                        if (temp == null){
                            Contact contact = new Contact(c.getId(), c.getLast(), c.getLastDate(), c.getUserNameOwner());

                            //contacts.add(contact);
                            contactDao.insert(contact);
                        }
                    }
                }
                contacts.clear();
                contacts.addAll(contactDao.index(owner));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<apiContact>> call, Throwable t) {

            }
        });



        listView.setOnItemLongClickListener((adapterView, view, i, l)-> {
            //contacts.remove(i);
            Contact contact = contacts.remove(i);
            contactDao.delete(contact);
            adapter.notifyDataSetChanged();
            return true;
        });

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), UserActivity.class);

            intent.putExtra("userName", contacts.get(i).getContactID());
         //   intent.putExtra("profilePicture", contacts.get(i).getProfilePicture());

            startActivity(intent);
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        contacts.clear();
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String owner = (sharedpreferences.getString("USERNAME", ""));
        contacts.addAll(contactDao.index(owner));
        adapter.notifyDataSetChanged();

    }
}