package com.prom.eazy;

public class BonPointageItemModel {

    private int code_bp;
    private String nom;
    private String prenom;
    private String matricule;
    private String code_sec;
    private String code_vehicule;
    private String chauffeur;

    public String getType_bp() {
        return type_bp;
    }

    private String type_bp;

    public String getDate_pt() {
        return date_pt;
    }

    private String date_pt;


    public BonPointageItemModel(int code_bp, String nom, String prenom, String matricule, String code_sec, String code_vehicule, String chauffeur, String date_pt,String type_bp) {
        this.code_bp = code_bp;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.code_sec = code_sec;
        this.code_vehicule = code_vehicule;
        this.chauffeur = chauffeur;
        this.date_pt = date_pt;
        this.type_bp = type_bp;
    }

    public int getCode_bp() {
        return code_bp;
    }

    public void setCode_bp(int code_bp) {
        this.code_bp = code_bp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCode_sec() {
        return code_sec;
    }

    public void setCode_sec(String code_sec) {
        this.code_sec = code_sec;
    }

    public String getCode_vehicule() {
        return code_vehicule;
    }

    public void setCode_vehicule(String code_vehicule) {
        this.code_vehicule = code_vehicule;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }
}
