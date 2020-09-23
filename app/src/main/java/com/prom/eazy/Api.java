package com.prom.eazy;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
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

    @POST("sign.php")
    @FormUrlEncoded
    Call<ModelSign> sign(@Field("username") String username, @Field("mdp") String mdp, @Field("phone") String phone
            , @Field("name") String name, @Field("secondName") String secondName);

    @POST("ifUsernameExist.php")
    @FormUrlEncoded
    Call<ModelUsername> ifExist(@Field("username") String username);

    @POST("typeEtAutorisation.php")
    @FormUrlEncoded
    Call<ModelTypeAgent> type(@Field("username") String username);

    @POST("signPt.php")
    @FormUrlEncoded
    Call<ModelSign> signPt(@Field("username") String username, @Field("mdp") String mdp, @Field("phone") String phone
            , @Field("name") String name, @Field("secondName") String secondName);

    @POST("getAgents.php")
    @FormUrlEncoded
    Call<ModelListAgents> getAgents(@Field("username") String username);

    @POST("insertBonDeRetour.php")
    @FormUrlEncoded
    Call<ModelIsSuccess> insertBonDeRetour(@Field("username") String username, @Field("code_vehicule") int code_vehicule, @Field("code_ag") int code_ag, @Field("sec") int sec);

    @POST("insertQteBonDeRetour.php")
    @FormUrlEncoded
    Call<ModelIsSuccess> insertQteBonDeRetour(@Field("code_pr") int code_pr, @Field("qte") int qte, @Field("code_bp") int code_bp);

    @POST("deleteQteBonDeRetour.php")
    @FormUrlEncoded
    Call<ModelIsSuccess> deleteQteBonDeRetour(@Field("code_bp") int code_bp);

    @POST("getListeVendeursPointes.php")
    @FormUrlEncoded
    Call<ModelListeVendeursPointes> getListeVendeursPointes(@Field("username") String username);

    @POST("getListeVendeursPointesSortie.php")
    @FormUrlEncoded
    Call<ModelListeVendeursPointes> getListeVendeursPointesSortie(@Field("username") String username);

    @POST("getAgentsSortie.php")
    @FormUrlEncoded
    Call<ModelListAgents> getAgentsSortie(@Field("username") String username);

    @POST("getListProducts.php")
    @FormUrlEncoded
    Call<ModelListProduct> getListProducts(@Field("username") String username);

    @POST("insertBonDeSortie.php")
    @FormUrlEncoded
    Call<ModelIsSuccess> insertBonDeSortie(@Field("username") String username, @Field("code_vehicule") int code_vehicule, @Field("code_ag") int code_ag, @Field("sec") int sec);

    @POST("insertQteBonDeRetour.php")
    @FormUrlEncoded
    Call<ModelIsSuccess> insertQteBonDeSortie(@Field("code_pr") int code_pr, @Field("qte") int qte, @Field("code_bp") int code_bp);

    @POST("deleteQteBonDeRetour.php")
    @FormUrlEncoded
    Call<ModelIsSuccess> deleteQteBonDeSortie(@Field("code_bp") int code_bp);

    @POST("insertBonDeSortieHashmaped.php")
    @FormUrlEncoded
    Call<ModelIsSuccess> insertBonDeSortieHashmaped(@Field("code_bp") int code_bp, @Field("data") String data);

    @POST("getListBonsPointage.php")
    @FormUrlEncoded
    Call<ModelListBonsPointage> getListBonsPointage(@Field("username") String username);

}

