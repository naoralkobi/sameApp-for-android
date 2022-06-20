package com.example.sameapp.api;

import android.content.Context;

import com.example.sameapp.MyApplication;
import com.example.sameapp.R;
import com.google.gson.GsonBuilder;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MessagesApi {
    //private MutableLiveData<List<Contact>> contactsListData;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    Context context;

    public MessagesApi(Context context, String server) {
        String myUrl = "http://10.0.2.2:" + server + "/";
        retrofit = new Retrofit.Builder()
                .baseUrl(myUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.context = context;

    }

    public WebServiceAPI getWebServiceAPI() {
        return webServiceAPI;
    }


    // get all contacts from api.
    public void get(String contactId, String username) {
        Call<List<apiMessage>> call = webServiceAPI.getMessages(contactId, username);
        call.enqueue(new Callback<List<apiMessage>> (){

            @Override
            public void onResponse(Call<List<apiMessage>> call, Response<List<apiMessage>> response) {
                List<apiMessage> messages = (List<apiMessage>) response.body();
                for (apiMessage c : messages) {
                    String id = c.getContactId();
                    String last = c.getContent();
                }
                //response.body();
            }

            @Override
            public void onFailure(Call<List<apiMessage>> call, Throwable t) {

            }
        });
    }
}