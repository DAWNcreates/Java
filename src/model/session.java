package model;

public abstract class session {
    protected int id;
    protected patient patient;        
    protected therapeute therapeute;   
    protected int dateDebut;           
    protected int dureeMinutes;

    public session(int id, patient patient, therapeute therapeute,
                   int dateDebut, int dureeMinutes) {
        this.id = id;
        this.patient = patient;
        this.therapeute = therapeute;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }


    public patient getPatient() { return patient; }
    public therapeute getTherapeute() { return therapeute; }
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
    
    
    
}