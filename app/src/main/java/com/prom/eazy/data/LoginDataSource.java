package com.prom.eazy.data;


import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prom.eazy.Api;
import com.prom.eazy.ApiAgent;
import com.prom.eazy.Exceptions.LoginException;
import com.prom.eazy.Model;
import com.prom.eazy.SharedPref;
import com.prom.eazy.data.model.LoggedInUser;
import com.prom.eazy.services.BackgroundService;
import com.prom.eazy.ui.login.LoginActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource { MutableLiveData<Boolean> bool = new MutableLiveData<>();

    public LoginDataSource(){

    }

    public Result<LoggedInUser> login(String username, String password) {

        try {

            // TODO: handle loggedInUser authentication
                //getting logged in user name
               fun(username,password);
               Thread.sleep(5000);

                    if (SharedPref.getInstance(LoginActivity.getAppContext()).isLoggedIn()) {
                        Log.d("khraa", " try if 1");
                        return new Result.Success<>(new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                SharedPref.getInstance(LoginActivity.getAppContext()).LoggedInUser()));

                    } else {
                        Log.d("khraa", " try if 2");
                            return new Result.Error(new LoginException("incorrect username or password "));
                    }




        } catch (Exception e) { Log.d("khraa"," catch");
                return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    public LiveData<Boolean> getBool(){
        return bool;
    }

    public void fun(String username,String password){
        /*Api api = ApiAgent.getAgent().create(Api.class);
        Call<Model> login = api.login(username,password);
        login.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.body().getIsSuccess() == 1) {
                    //get username
                    //storing the user in shared preferences
                    SharedPref.getInstance(LoginActivity.getAppContext()).storeUserName(response.body().getUsername());
                    Log.d("khraa","onResponse");
                    bool.setValue(new Boolean(true));
                    Log.d("khraa","onResponse bool = "+ bool.getValue().toString());
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("khraa","onFailure");
            }


        });
        try {
            Response<Model> response = login.execute();
            if(response.body().getIsSuccess() == 1){
                SharedPref.getInstance(LoginActivity.getAppContext()).storeUserName(response.body().getUsername());

            }
        }catch (Exception e){

        }*/
        Intent serviceIntent = new Intent(LoginActivity.getAppContext(), com.prom.eazy.services.BackgroundService.class);
        serviceIntent.putExtra("username", username);
        serviceIntent.putExtra("password", password);

          com.prom.eazy.services.BackgroundService.enqueueWork(LoginActivity.getAppContext(), serviceIntent);
    }




}
