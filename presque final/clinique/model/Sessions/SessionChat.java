/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinique.model.Sessions;

/**
 *
 * @author marwa
 */
public final class SessionChat extends Session {
    private String transcriptId;

    public SessionChat(int id, clinique.model.Personnes.Patient patient, 
    clinique.model.Personnes.Therapeute therapeute, int dateDebut, 
    int dureeMinutes,String transcriptId ) {
        super(id, patient, therapeute, dateDebut, dureeMinutes);
        this.transcriptId = transcriptId;
    }

    public String getTranscriptId() {
        return transcriptId;
    }

    public void setTranscriptId(String transcriptId) {
        this.transcriptId = transcriptId;
    }
    
    @Override
    public String toString() {
        return super.toString()+ "SessionChat{" +
           "transcriptId='" + transcriptId + '\'' +
           "} " ;
    }
    
}
