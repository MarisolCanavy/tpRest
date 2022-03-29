package com.tprest.tp;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Contacts {
    List<Contact> contacts;

    public Contacts() {
        this.contacts = new ArrayList<Contact>();
    }

    public Contacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void getInfosListe(){
        if(!this.contacts.isEmpty()) {
            for (Contact contact1 : contacts) {
                String infos = " Nom : " + contact1.nom + ", prénom : " + contact1.prenom + ", id : " + contact1.id + ",annee : " + contact1.annee;
                System.out.println(infos);
            }
        }
    }
}
