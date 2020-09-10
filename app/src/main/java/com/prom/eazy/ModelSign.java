package com.prom.eazy;

public class ModelSign {

    private String username;
    private String name;
    private String sencondName;
    private String phone;
    //@SerializedName("mdp")
    private String mdp;

    private int isSuccess;
    private String message;

    public ModelSign(String username, String name, String sencondName, String phone, String mdp, int isSuccess, String message) {
        this.username = username;
        this.name = name;
        this.sencondName = sencondName;
        this.phone = phone;
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

      public String getName() {
           return this.name;
       }

       public void setName(String name) {
           this.name = name;
       }

    public String getSecondName() {
        return this.sencondName;
    }

    public void setSecondName(String sencondName) {
        this.name = sencondName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
