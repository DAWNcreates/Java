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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import clinique.model.Interfaces.Affichable;
import clinique.model.Sessions.Session;

public class Patient extends Personne implements Affichable {
    private List<String> objectifsTherapie;
    private List<Session> historiqueSessions;
    private String modeCommunicationPrefere;
    private int noteSatisfaction;
    private int age;
    private String genre;
    private String statut;

    public Patient(int id, String nom, String prenom, String email, int numTel,
               LocalDateTime dateInscription, List<String> objectifsTherapie,
               List<Session> historiqueSessions, String modeCommunicationPrefere,
               int noteSatisfaction, int age, String genre) {
    super(id, nom, prenom, email, numTel, dateInscription);
    this.objectifsTherapie = objectifsTherapie;
    this.historiqueSessions = historiqueSessions;
    this.modeCommunicationPrefere = modeCommunicationPrefere;
    this.noteSatisfaction = noteSatisfaction;
    this.age = age;
    this.genre = genre;
}

    public void setAge(int age) {
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getNumTel() {
        return numTel;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    

    @Override
    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    @Override
    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }
    

    public String getStatut() { return "Actif"; }
    
}

