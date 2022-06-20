package com.example.sameapp;


import static com.example.sameapp.Register.MyPREFERENCES;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.sameapp.api.MessagesApi;
import com.example.sameapp.api.apiMessage;
import com.example.sameapp.api.apiTransfer;
import com.example.sameapp.dao.ContactDao;
import com.example.sameapp.dao.MessageDao;
import com.example.sameapp.dao.UserDao;
import com.example.sameapp.models.Message;
import com.example.sameapp.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserActivity extends AppCompatActivity {

    ImageView profilePictureView;
    TextView userNameView;
    Button sendButton;

    private ContactAppDB db;
    private MessageDao messageDao;
    private ContactDao contactDao;
    private UserDao userDao;
    private MessagesApi messageApi;

    private ArrayList<Message> messages;

    RecyclerView listView;
    MessageListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        db = Room.databaseBuilder(getApplicationContext(), ContactAppDB.class, "ContactsDB")
                .allowMainThreadQueries().build();

        messageDao = db.messageDao();
        contactDao = db.contactDao();
        userDao = db.userDao();

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String server = sharedpreferences.getString("SERVER", "");

        messageApi = new MessagesApi(getApplicationContext(), server);


        profilePictureView = findViewById(R.id.user_image_profile_image);
        userNameView = findViewById(R.id.user_text_user_name);
        sendButton = findViewById(R.id.button_gchat_send);

        Intent activityIntent = getIntent();

        if (activityIntent != null) {
            String receiver = activityIntent.getStringExtra("userName");
            int profilePicture = activityIntent.getIntExtra("profilePicture", R.drawable.profile);

            // get the user to restore his profile picture:
            User user = userDao.get(receiver);
            String profileImage = user.getPictureId();
            Uri profileUri = Uri.parse(profileImage);

            if (!Objects.equals(profileImage, "")){
                byte[] profileImageByte = Base64.decode(profileImage, Base64.DEFAULT);
                Bitmap image = BitmapFactory.decodeByteArray(profileImageByte,0,profileImageByte.length);
                profilePictureView.setImageBitmap(image);
            }
            else{
                profilePictureView.setImageResource(R.drawable.profile);
            }

            userNameView.setText(receiver);
        }


        messages = new ArrayList<Message>();

        listView = findViewById(R.id.recycler_gchat);
        adapter = new MessageListAdapter(getApplicationContext());

        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setMessages(messages);

        //messageApi.get(receiver, sender);
        String sender = (sharedpreferences.getString("USERNAME", ""));
        String receiver = activityIntent.getStringExtra("userName");

        Call<List<apiMessage>> call = messageApi.getWebServiceAPI().getMessages(receiver, sender);
        call.enqueue(new Callback<List<apiMessage>> (){

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<apiMessage>> call, Response<List<apiMessage>> response) {
                List<apiMessage> messagesList = response.body();

                if (messagesList != null){

                    messageDao.clear(sender, receiver);


                    for (apiMessage m : messagesList) {

                        Message temp = messageDao.isDataExist(m.getId());
                        if (temp == null){
                            Message message = new Message(m.getCreated(), m.getSent(), m.getUserId(), m.getContent(), m.getContactId());

                            //contacts.add(contact);
                            messageDao.insert(message);
                            contactDao.update(m.getContactId(), m.getContent(), m.getCreated());
                        }
                    }
                }

                messages.clear();
                TextView textViewReceiver = findViewById(R.id.user_text_user_name);
                String receiver = textViewReceiver.getText().toString();
                messages.addAll(messageDao.get(receiver, sender));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<apiMessage>> call, Throwable t) {

            }
        });





        sendButton.setOnClickListener(view -> {

            EditText mEdit = findViewById(R.id.edit_gchat_message);

            String messageContent = mEdit.getText().toString();

            if (messageContent.length() > 0) {
                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String strDate = sdf.format(c.getTime());

                String receiver1 = activityIntent.getStringExtra("userName");

                SharedPreferences sharedpreferences1 = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String sender1 = (sharedpreferences1.getString("USERNAME", ""));

                Message message = new Message(strDate, true, sender1, messageContent, receiver1);

                apiMessage apiMessage = new apiMessage(message.getMessageId(),messageContent,strDate,true, sender1, receiver1);

                //messageApi.get(receiver, sender);

                messageApi.getWebServiceAPI().createMessage(apiMessage, receiver1).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call1, Response<Void> response) {
                        if (response.code() == 200){
                            //messageDao.clear(sender, receiver);
                            contactDao.update(receiver1,messageContent,strDate);
                            messageDao.insert(message);
                            messages.clear();
                            messages.addAll(messageDao.get(receiver1, sender1));
                            adapter.notifyDataSetChanged();
                            mEdit.setText("");

                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call1, Throwable t) {
                    }
                });

                apiTransfer apiTransfer = new apiTransfer(sender1, receiver1, messageContent);

                messageApi.getWebServiceAPI().transferMessage(apiTransfer).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call1, Response<Void> response) {
                        if (response.code() == 200){

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call1, Throwable t) {

                    }
                });


            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume(){
        super.onResume();
        messages.clear();
        TextView textViewReceiver = findViewById(R.id.user_text_user_name);
        String receiver = textViewReceiver.getText().toString();
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String sender = (sharedpreferences.getString("USERNAME", ""));
        messages.addAll(messageDao.get(receiver,sender));
        adapter.notifyDataSetChanged();
    }
}