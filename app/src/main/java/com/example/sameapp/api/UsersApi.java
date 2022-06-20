package com.example.sameapp.api;

import static com.example.sameapp.Register.MyPREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sameapp.MyApplication;
import com.example.sameapp.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersApi {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    public Context context;
    private SharedPreferences sharedpreferences;


    public UsersApi(Context context, String server) {
        String myUrl = "http://10.0.2.2:" + server + "/";

        retrofit = new Retrofit.Builder()
                .baseUrl(myUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.context = context;
    }

    public WebServiceAPI getWebServiceAPI() {
        return webServiceAPI;
    }

}
