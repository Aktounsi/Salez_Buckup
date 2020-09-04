package com.prom.eazy;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by twonsi_ on 1/9/2020.
 */

public interface Api {

/*    @POST("register.php")
    @FormUrlEncoded
    Call<model> register(@Field("username") String username, @Field("email") String email, @Field("password") String password);
*/
    @POST("login.php")
    @FormUrlEncoded
    Call<Model> login(@Field("username") String username, @Field("mdp") String mdp);



}

