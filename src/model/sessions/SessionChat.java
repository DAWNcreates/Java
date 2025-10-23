package model.sessions;


public final class SessionChat extends Session {
    private String transcriptId;

    public SessionChat(int id, model.personnes.Patient patient, 
    model.personnes.Therapeute therapeute, int dateDebut, 
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
