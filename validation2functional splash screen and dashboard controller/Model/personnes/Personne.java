package model.personnes;

import java.time.LocalDateTime;
import Interfaces.Affichable;


public abstract class Personne implements Affichable{
    protected int id;
    protected String nom; 
    protected String prenom;
    protected String email;
    protected int numTel;
    protected LocalDateTime dateInscription;

    public Personne (int id, String nom, String prenom, String email, int numTel, LocalDateTime dateInscription) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numTel = numTel;
        this.dateInscription = dateInscription;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public int getNumTel() {
        return numTel;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }
    
    @Override
    public String toString() {
        return "Personne{" +
           "id=" + id +
           ", nom='" + nom + '\'' +
           ", prenom='" + prenom + '\'' +
           ", email='" + email + '\'' +
           ", numTel=" + numTel +
           ", dateInscription=" + dateInscription +
           '}';
    }
    
    
    
}
