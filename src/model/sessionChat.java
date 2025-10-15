package model;


public class sessionChat extends session {
    private String transcriptId;

    public sessionChat(int id, model.patient patient, 
    model.therapeute therapeute, int dateDebut, 
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

    

    
    
    
    
}
