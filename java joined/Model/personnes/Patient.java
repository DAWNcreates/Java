package model.personnes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Interfaces.Affichable;
import model.sessions.Session;

public class Patient extends Personne implements Affichable {
    private List<String> objectifsTherapie;
    private List<Session> historiqueSessions;
    private String modeCommunicationPrefere;
    private int noteSatisfaction;

    public Patient(int id, String nom, String prenom, 
        String email,int numTel,LocalDateTime dateInscription,
        List<String> objectifsTherapie, List<Session> historiqueSessions, 
        String modeCommunicationPrefere, int noteSatisfaction ) {
        super(id, nom, prenom, email, numTel, dateInscription);
        this.objectifsTherapie = objectifsTherapie;
        this.historiqueSessions = historiqueSessions;
        this.modeCommunicationPrefere = modeCommunicationPrefere;
        this.noteSatisfaction = noteSatisfaction;
    }

    public List<String> getObjectifsTherapie() {
        return objectifsTherapie;
    }

    public List<Session> getHistoriqueSessions() {
        return historiqueSessions;
    }

    public String getModeCommunicationPrefere() {
        return modeCommunicationPrefere;
    }

    public int getNoteSatisfaction() {
        return noteSatisfaction;
    }

    public void setObjectifsTherapie(List<String> objectifsTherapie) {
        this.objectifsTherapie = objectifsTherapie;
    }

    public void setHistoriqueSessions(List<Session> historiqueSessions) {
        this.historiqueSessions = historiqueSessions;
    }

    public void setModeCommunicationPrefere(String modeCommunicationPrefere) {
        this.modeCommunicationPrefere = modeCommunicationPrefere;
    }

    public void setNoteSatisfaction(int noteSatisfaction) {
        this.noteSatisfaction = noteSatisfaction;
    }

    public void ajouterSession(Session s) {
        if (s != null) {
            historiqueSessions.add(s);
        }
    }
    
    public void supprimerSession(Session s) {
        historiqueSessions.remove(s);
    }
    
    public void modifierObjectifs(List<String> nouveauxObjectifs) {
        this.objectifsTherapie = nouveauxObjectifs != null ? new ArrayList<>(nouveauxObjectifs) : new ArrayList<>();
    }
    
    public void trierSessionsParDate() {
    historiqueSessions.sort(Comparator.comparingInt(Session::getDateDebut));
    }
    
    @Override
    public String toString() {
        return super.toString() +"Patient{" +
           "objectifsTherapie=" + objectifsTherapie +
           ", historiqueSessions=" + historiqueSessions.size() + " sessions" +
           ", modeCommunicationPrefere='" + modeCommunicationPrefere + '\'' +
           ", noteSatisfaction=" + noteSatisfaction +
           "} "  ;
    }

    @Override
    public void afficher() {
        System.out.println("Je suis un patient : " + nom);
    }
    
}
