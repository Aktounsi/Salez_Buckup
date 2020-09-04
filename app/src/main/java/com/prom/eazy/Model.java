package com.prom.eazy;

/**
 * Created by twonsi_ on 1/9/2020. (french date)
 */

public class Model {


    private String username;
    //private String email;
    //@SerializedName("mdp")
    private String mdp;

    private int isSuccess;
    private String message;

    public Model(String username, /*String email,*/ String mdp, int isSuccess, String message) {
        this.username = username;
        //this.email = email;
        this.mdp = mdp;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

 /*   public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
*/
    public String getPassword() {
        return mdp;
    }

    public void setPassword(String password) {
        this.mdp = password;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

