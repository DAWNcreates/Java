package model.sessions;


public final class SessionVideo extends Session{
     private String urlEnregistrement;

    public SessionVideo(int id, model.personnes.Patient patient, model.personnes.Therapeute therapeute, int dateDebut, int dureeMinutes,String urlEnregistrement ) {
        super(id, patient, therapeute, dateDebut, dureeMinutes);
        this.urlEnregistrement = urlEnregistrement;
    }

    public String getUrlEnregistrement() {
        return urlEnregistrement;
    }

    public void setUrlEnregistrement(String urlEnregistrement) {
        this.urlEnregistrement = urlEnregistrement;
    }
    
    @Override
    public String toString() {
        return   super.toString()+ "SessionVideo{" +
           "urlEnregistrement='" + urlEnregistrement + '\'' +
           "} " ;
    }
}
