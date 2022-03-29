package com.tprest.tp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


@RestController
@RequestMapping("api")
public class Webservices {

    Contacts contacts = new Contacts();

    @GetMapping(value = "/contacts", produces = {"application/xml","application/json"})
    Contacts getContacts() {
        readFromXml();
        return contacts;
    }

    @PostMapping(value = "/contacts", consumes = {"application/xml","application/json"})
    Contacts newContact(@RequestBody Contact contact) {
        int maxValue = 0;

        if(contacts.getContacts().isEmpty()){
            maxValue = 1;
        }else{
            for(Contact contact1 : contacts.getContacts()){
                if(maxValue < contact1.getId()){
                    maxValue = contact1.getId();
                }
            }
            maxValue = maxValue+1;
        }

        contact.setId(maxValue);
        contacts.getContacts().add(contact);
        saveToXml();
        return contacts;
    }

    @GetMapping(value="/contact/{id}", produces = "application/xml")
    Contact getContact(@PathVariable int id) {
        readFromXml();
        Contact contactTrouve = null ;
        if(!contacts.getContacts().isEmpty()){
            for(Contact contact1 : contacts.getContacts()){
                if(contact1.getId() == id){
                    contactTrouve = contact1;
                }
            }
        }
        System.out.println("Le contact trouvé : " + contactTrouve.getNom());
        saveToXml();
        return contactTrouve;
    }

    @PutMapping(value="/contact/{id}", produces = "application/xml")
    Contacts editContact(@PathVariable int id, @RequestBody Contact contact) {
        readFromXml();
        Contact contactEdit = null;
        contacts.getInfosListe();
        if(!contacts.getContacts().isEmpty()){
            for(Contact contact1 : contacts.getContacts()){
                if(contact1.getId() == id){
                    //System.out.println("Le contact trouvé est : " + contact1.getInfos());
                    contactEdit = contact1;
                    //System.out.println("Le contact édité est : " + contactEdit.getInfos());
                    contact.setId(contact1.getId());
                    //System.out.println("Le contact avec l'id est : " + contact.getInfos());
                }
            }
        }
        if(contactEdit != null){
            contacts.getContacts().remove(contactEdit);
            contacts.getContacts().add(contact);
        }
        saveToXml();
        return contacts;
    }

    @DeleteMapping(value="/contact/{id}")
    Contacts deleteContact(@PathVariable int id) {
        readFromXml();
        Contact contactAsupp = null ;
        //System.out.println("Entrée : " + contacts.getContacts().toString());
        if(!contacts.getContacts().isEmpty()){
            for(Contact contact1 : contacts.getContacts()){
                if(contact1.getId() == id){
                    contactAsupp = contact1;
                    //System.out.println( "Le contact a supprimer : " + contact1.getPrenom() + " son id : " + contact1.getId());
                }
            }
        }
        contacts.getContacts().remove(contactAsupp);
        //System.out.println("Sortie : " + contacts.getContacts().toString());
        saveToXml();
        // supprimer le contact d'identifiant spécifié
        return contacts;
    }

    String path ="/Users/marisolcanavy/Desktop/ISIS/Alternance/FIA4/SOA - S2/2. TP rest/reco/tp";
    private void readFromXml() {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Contacts.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File f = new File(path+"/contacts.xml");
            contacts = (Contacts) jaxbUnmarshaller.unmarshal(f);
        } catch (Exception ex) {
            System.out.println("Erreur lecture du fichier:"+ex.getMessage());
            ex.printStackTrace();
        }
    }
    private void saveToXml() {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Contacts.class);
            Marshaller march = jaxbContext.createMarshaller();
            march.marshal(contacts, new File(path+"/contacts.xml"));
        } catch (Exception ex) {
            System.out.println("Erreur écriture du fichier:"+ex.getMessage());
            ex.printStackTrace();
        }
    }
}
