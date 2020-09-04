package com.prom.eazy;

import android.util.Log;

import com.prom.eazy.ui.login.LoginActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAgent {

    //base url 127.0.0.1

    public static final String BASE_URL = "http://192.168.59.2:8088/PFE/sidebar/retrofit/";
    private static Retrofit retrofit = null;

    public static Retrofit getAgent() {

        if (retrofit == null) {
            if(!SharedPref.getInstance(LoginActivity.getAppContext()).isServerOn()) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.d("khraa","Rani f ApiAgent makach SharedPred");
            }else{
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.59.2:808"+SharedPref.getInstance(LoginActivity.getAppContext()).serverOn()+"/PFE/sidebar/retrofit/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.d("khraa","Rani f ApiAgent kayen SharedPred");

            }
        }
        return retrofit;
    }
}
