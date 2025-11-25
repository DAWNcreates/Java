package model.sessions;

public final class SessionIA extends Session {
    private String niveauRisque;
    private String resumeIA;

    public SessionIA(int id, model.personnes.Patient patient, 
    model.personnes.Therapeute therapeute, int dateDebut, 
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
    
    @Override
    public String toString() {
        return  super.toString() +"SessionIA{" +
           "niveauRisque='" + niveauRisque + '\'' +
           ", resumeIA='" + resumeIA + '\'' +
           "} " ;
    }
}
