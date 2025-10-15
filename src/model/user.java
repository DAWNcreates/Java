package model;
import java.time.LocalDateTime;

public abstract class user{
    protected int id;
    protected String nom; 
    protected String prenom;
    protected String email;
    protected int numTel;
    protected LocalDateTime dateInscription;

    public user(int id, String nom, String prenom, String email, int numTel, LocalDateTime dateInscription) {
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
    
    
    
    
}
