package clinique.controller;

import clinique.model.Personnes.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PatientDossierController {

    @FXML private Label patientNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label idLabel;
    @FXML private Label sessionsCount;
    @FXML private Label reportsCount;
    @FXML private TableView<?> sessionsTable;
    @FXML private TableView<?> documentsTable;

    private Patient currentPatient;

    /**
     * Définit le patient dont on affiche le dossier
     */
    public void setPatient(Patient patient) {
        this.currentPatient = patient;
        loadPatientData();
    }

    /**
     * Charge les données du patient
     */
    private void loadPatientData() {
        if (currentPatient != null) {
            patientNameLabel.setText(currentPatient.getNom() + " " + currentPatient.getPrenom());
            emailLabel.setText("📧 " + currentPatient.getEmail());
            phoneLabel.setText("📱 +" + currentPatient.getNumTel());
            idLabel.setText("ID: #PAT-" + String.format("%03d", currentPatient.getId()));

            // Charger les compteurs
            if (sessionsCount != null) {
                sessionsCount.setText(String.valueOf(currentPatient.getHistoriqueSessions().size()));
            }
            if (reportsCount != null) {
                reportsCount.setText("0");
            }
        }
    }

    /**
     * Ferme le dialogue et retourne à PatientListView
     */
    @FXML
    public void handleClose(ActionEvent event) {
        // Fermer la fenêtre modale
        Stage stage = (Stage) patientNameLabel.getScene().getWindow();
        stage.close();
    }
}