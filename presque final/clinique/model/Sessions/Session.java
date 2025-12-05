/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinique.model.Sessions;

/**
 *
 * @author marwa
 */
import clinique.model.Personnes.Patient;
import clinique.model.Personnes.Therapeute;


public sealed abstract class Session 
permits SessionChat, SessionVideo,SessionIA{
    protected int id;
    protected Patient patient;        
    protected Therapeute therapeute;   
    protected int dateDebut;           
    protected int dureeMinutes;

    public Session(int id, Patient patient, Therapeute therapeute,
                   int dateDebut, int dureeMinutes) {
        this.id = id;
        this.patient = patient;
        this.therapeute = therapeute;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }


    public Patient getPatient() { return patient; }
    public Therapeute getTherapeute() { return therapeute; }
    public int getId() { return id; }
    public int getDateDebut() { return dateDebut; }
    public int getDureeMinutes() { return dureeMinutes; }
    
    public boolean estRecente() {
        int maintenant = (int) (System.currentTimeMillis() / 1000);
        return (maintenant - dateDebut) < 7 * 24 * 3600; 
    }
    
    public String getResume() {
        return "Session #" + id + " | Patient: " + patient.getNom() + 
               " | Thérapeute: " + therapeute.getNom();
    }
    
    public boolean estLongue() {
        return dureeMinutes > 60;
    }
    
    @Override
    public String toString() {
        return "Session{" +
           "id=" + id +
           ", patient=" + (patient != null ? patient.getNom() + " " + patient.getPrenom() : "null") +
           ", therapeute=" + (therapeute != null ? therapeute.getNom() + " " + therapeute.getPrenom() : "null") +
           ", dateDebut=" + dateDebut +
           ", dureeMinutes=" + dureeMinutes +
           '}';
    }
    
}