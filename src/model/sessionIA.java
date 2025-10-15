package model;


public class sessionIA extends session {
    private String niveauRisque;
    private String resumeIA;

    public sessionIA(int id, model.patient patient, 
    model.therapeute therapeute, int dateDebut, 
    int dureeMinutes,String niveauRisque, String resumeIA ) {
        super(id, patient, therapeute, dateDebut, dureeMinutes);
        this.niveauRisque = niveauRisque;
        this.resumeIA = resumeIA;
    }

    public String getNiveauRisque() {
        return niveauRisque;
    }

    public String getResumeIA() {
        return resumeIA;
    }

    public void setNiveauRisque(String niveauRisque) {
        this.niveauRisque = niveauRisque;
    }

    public void setResumeIA(String resumeIA) {
        this.resumeIA = resumeIA;
    }

    
    
    
    
}
