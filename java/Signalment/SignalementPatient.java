/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Signalment;

import java.time.LocalDateTime;

public class SignalementPatient extends Signalement {

    private String idPatient;        
    private String typeSignalement;  

    public SignalementPatient(String idSignalement, String description, String auteur,
                              String idPatient, String typeSignalement) {
        super(idSignalement, description, auteur);
        this.idPatient = idPatient;
        this.typeSignalement = typeSignalement;
    }

    public void envoyerNotification() {
        System.out.println("Notification envoyée pour le signalement du patient " + idPatient);
    }

    public void repondreAuPatient(String reponse) {
        System.out.println("Réponse envoyée au patient " + idPatient + " : " + reponse);
    }

    @Override
    public void afficherSignalement() {
        super.afficherSignalement();  
        System.out.println("ID Patient : " + idPatient);
        System.out.println("Type de signalement : " + typeSignalement);
        System.out.println("-------------------");
    }

    public String getIdPatient() { return idPatient; }
    public void setIdPatient(String idPatient) { this.idPatient = idPatient; }

    public String getTypeSignalement() { return typeSignalement; }
    public void setTypeSignalement(String typeSignalement) { this.typeSignalement = typeSignalement; }
}
