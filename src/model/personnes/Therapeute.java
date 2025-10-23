package model.personnes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Affichable;

public class Therapeute extends Personne implements Affichable{
    private String licenceUrl;
    private List<String> specialites;
    private String statut;
    private double tarifHoraire;
    private Map<Integer, Patient> patientsSuivis;

    public Therapeute(int id, String nom, String prenom, String email, int numTel,
                      LocalDateTime dateInscription, String licenceUrl,
                      List<String> specialites, String statut, double tarifHoraire) {
        super(id, nom, prenom, email, numTel, dateInscription);
        this.licenceUrl = licenceUrl;
        this.specialites = specialites;
        this.statut = statut;
        this.tarifHoraire = tarifHoraire;
        this.patientsSuivis = new HashMap<>(); 
    }


    public String getLicenceUrl() { return licenceUrl; }
    public void setLicenceUrl(String licenceUrl) { this.licenceUrl = licenceUrl; }

    public List<String> getSpecialites() { return specialites; }
    public void setSpecialites(List<String> specialites) { this.specialites = specialites; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public double getTarifHoraire() { return tarifHoraire; }
    public void setTarifHoraire(double tarifHoraire) { this.tarifHoraire = tarifHoraire; }


    public Map<Integer, Patient> getPatientsSuivis() {
        return new HashMap<>(patientsSuivis); // copie pour sécurité
    }



    public void ajouterPatient(Patient p) {
        if (p != null) {
            patientsSuivis.put(p.getId(), p);
        }
    }


    public void supprimerPatient(int patientId) {
        patientsSuivis.remove(patientId);
    }


    public boolean suitPatient(int patientId) {
        return patientsSuivis.containsKey(patientId);
    }
    
    @Override
    public String toString() {
        return  super.toString()+ "Therapeute{" +
           "licenceUrl='" + licenceUrl + '\'' +
           ", specialites=" + specialites +
           ", statut='" + statut + '\'' +
           ", tarifHoraire=" + tarifHoraire +
           ", patientsSuivis=" + patientsSuivis.size() + " patients" +
           "} " ;
    }
    
    public void afficher (){
        System.out.println("Je suis un therapeute :" +nom);
    }
}
