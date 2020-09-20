package com.prom.eazy;

import java.io.Serializable;

public class VendeurItemModel implements Serializable {
    private int code_agent;
    private String nom,prenom,matricule,code_sec,code_vehicule;
    private char type_agent;

    public String getChauffeur() {
        return chauffeur;
    }

    private String chauffeur;

    public VendeurItemModel(int code_agent, String nom, String prenom, String matricule, String code_sec, char type_agent,String code_vehicule, String chauffeur) {
        this.code_agent = code_agent;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.code_sec = code_sec;
        this.type_agent = type_agent;
        this.code_vehicule = code_vehicule;
        this.chauffeur = chauffeur;
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

    public String getCode_vehicule(){return code_vehicule;}


}
