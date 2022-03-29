package com.tprest.tp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {
    String nom;
    String prenom;
    int annee;
    int id;

    public Contact() {
    }

    public Contact(String nom, String prenom, int annee, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.annee = annee;
        this.id = id;
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

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfos(){
        String infos = " Nom : " + nom + ", prénom : " + prenom + ", id : " + id + ",annee : " + annee ;
        return infos;
    }
}
