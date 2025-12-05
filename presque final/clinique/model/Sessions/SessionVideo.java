/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinique.model.Sessions;

/**
 *
 * @author marwa
 */
public final class SessionVideo extends Session{
     private String urlEnregistrement;

    public SessionVideo(int id, clinique.model.Personnes.Patient patient, clinique.model.Personnes.Therapeute therapeute, int dateDebut, int dureeMinutes,String urlEnregistrement ) {
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
