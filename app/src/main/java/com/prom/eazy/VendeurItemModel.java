package com.prom.eazy;

public class VendeurItemModel {
    private int code_agent;
    private String nom,prenom,matricule,code_sec;
    private char type_agent;

    public VendeurItemModel(int code_agent, String nom, String prenom, String matricule, String code_sec, char type_agent) {
        this.code_agent = code_agent;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.code_sec = code_sec;
        this.type_agent = type_agent;
    }

    public int getCode_agent() {
        return code_agent;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getCode_sec() {
        return code_sec;
    }

    public char getType_agent() {
        return type_agent;
    }






}
