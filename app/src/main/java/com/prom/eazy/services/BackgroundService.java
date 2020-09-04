package com.prom.eazy.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.prom.eazy.Api;
import com.prom.eazy.ApiAgent;
import com.prom.eazy.Model;
import com.prom.eazy.SharedPref;
import com.prom.eazy.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BackgroundService extends JobIntentService {

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, BackgroundService.class, 123, work);
        Log.d("khraa", "enqueueWork");


    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("khraa", "onCreate");
    }


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Api api = ApiAgent.getAgent().create(Api.class);
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        Call<Model> login = api.login(username,password);
        try {Log.d("khraa","service is try");
            Response<Model> response = login.execute();
            if (response.body().getIsSuccess() == 1) {
                //get username
                Log.d("khraa","responce==1");
                //storing the user in shared preferences
                SharedPref.getInstance(LoginActivity.getAppContext()).storeUserName(response.body().getUsername());
                Log.d("khraa","service is try SharedPref");
            }

        }catch (Exception e){
            Log.d("khraa","service is catch");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("khraa", "onDestroy");
    }
    @Override
    public boolean onStopCurrentWork() {
        Log.d("khraa", "onStopCurrentWork");
        return super.onStopCurrentWork();
    }
}
