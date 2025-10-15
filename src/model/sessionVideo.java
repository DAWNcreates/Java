package model;

public class sessionVideo extends session{
    private String urlEnregistrement;

    public sessionVideo(int id, model.patient patient, model.therapeute therapeute, int dateDebut, int dureeMinutes,String urlEnregistrement ) {
        super(id, patient, therapeute, dateDebut, dureeMinutes);
        this.urlEnregistrement = urlEnregistrement;
    }

    public String getUrlEnregistrement() {
        return urlEnregistrement;
    }

    public void setUrlEnregistrement(String urlEnregistrement) {
        this.urlEnregistrement = urlEnregistrement;
    }

    
    
    
    
}
