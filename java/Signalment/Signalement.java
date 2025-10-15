
package Signalment;

import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import java.util.Date;


public class Signalement {
    
    private String idSignalement;     
    private String description;        
    private LocalDate dateSignal;  
    private boolean resolu;           
    private String auteur;            

    public Signalement(String idSignalement, String description, String auteur) {
        this.idSignalement = idSignalement;
        this.description = description;
        this.auteur = auteur;
        this.dateSignal = LocalDate.now();
        this.resolu = false;
    }
    
        public void marquerCommeResolu() {
        this.resolu = true;
        System.out.println("Signalement " + idSignalement + " marqué comme résolu.");
    }

    public void ecrireSignalement() {
        System.out.println("Écriture du signalement " + idSignalement + " : " + description);
    }

    public void afficherSignalement() {
        System.out.println("Signalement");
        System.out.println("ID : " + idSignalement);
        System.out.println("Auteur : " + auteur);
        System.out.println("Date : " + dateSignal);
        System.out.println("Description : " + description);
        System.out.println("Résolu : " + (resolu ? "Oui" : "Non"));
    }
    public String getIdSignalement() { return idSignalement; }
    public void setIdSignalement(String idSignalement) { this.idSignalement = idSignalement; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDateSignal() { return dateSignal; }

    public void setDateSignal(LocalDate dateSignal) {
        this.dateSignal = dateSignal;
    }

    public boolean isResolu() { return resolu; }
    public void setResolu(boolean resolu) { this.resolu = resolu; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
}
