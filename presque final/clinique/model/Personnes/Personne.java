/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinique.model.Personnes;

/**
 *
 * @author marwa
 */
import java.time.LocalDateTime;
import clinique.model.Interfaces.Affichable;


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

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }
    
    
    
    
    
}
