package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class patient extends user {
    private List<String> objectifsTherapie;
    private List<session> historiqueSessions;
    private String modeCommunicationPrefere;
    private int noteSatisfaction;

    public patient(int id, String nom, String prenom, 
        String email,int numTel,LocalDateTime dateInscription,
        List<String> objectifsTherapie, List<session> historiqueSessions, 
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

    public List<session> getHistoriqueSessions() {
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

    public void setHistoriqueSessions(List<session> historiqueSessions) {
        this.historiqueSessions = historiqueSessions;
    }

    public void setModeCommunicationPrefere(String modeCommunicationPrefere) {
        this.modeCommunicationPrefere = modeCommunicationPrefere;
    }

    public void setNoteSatisfaction(int noteSatisfaction) {
        this.noteSatisfaction = noteSatisfaction;
    }

    public void ajouterSession(session s) {
        if (s != null) {
            historiqueSessions.add(s);
        }
    }
    
    public void supprimerSession(session s) {
        historiqueSessions.remove(s);
    }
    
    public void modifierObjectifs(List<String> nouveauxObjectifs) {
        this.objectifsTherapie = nouveauxObjectifs != null ? new ArrayList<>(nouveauxObjectifs) : new ArrayList<>();
    }
    
    public void trierSessionsParDate() {
    historiqueSessions.sort((s1, s2) -> Integer.compare(s1.getDateDebut(), s2.getDateDebut()));
    }
}
