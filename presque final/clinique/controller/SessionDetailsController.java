package clinique.controller;

import clinique.model.Sessions.Session;
import clinique.model.Sessions.SessionChat;
import clinique.model.Sessions.SessionVideo;
import clinique.model.Sessions.SessionIA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SessionDetailsController {

    @FXML private Label typeLabel;
    @FXML private Label statutLabel;
    @FXML private Label idLabel;
    @FXML private Label patientLabel;
    @FXML private Label therapeuteLabel;
    @FXML private Label dateHeureLabel;
    @FXML private Label dureeLabel;
    @FXML private Label paiementLabel;
    @FXML private TextArea motifArea;
    @FXML private TextArea notesArea;

    private Session currentSession;

    public void setSession(Session session) {
        this.currentSession = session;
        loadSessionData();
    }

    private void loadSessionData() {
        if (currentSession != null) {
            if (idLabel != null) {
                idLabel.setText("#S-" + String.format("%03d", currentSession.getId()));
            }

            if (typeLabel != null) {
                if (currentSession instanceof SessionVideo) {
                    typeLabel.setText("📹 Session Vidéo");
                } else if (currentSession instanceof SessionChat) {
                    typeLabel.setText("💬 Session Chat");
                } else if (currentSession instanceof SessionIA) {
                    typeLabel.setText("🤖 Session IA");
                }
            }

            if (statutLabel != null) {
                statutLabel.setText("Planifiée");
            }

            if (patientLabel != null) {
                patientLabel.setText(currentSession.getPatient().getNom() + " " + 
                                   currentSession.getPatient().getPrenom());
            }

            if (therapeuteLabel != null) {
                therapeuteLabel.setText("Dr. " + currentSession.getTherapeute().getNom());
            }

            if (dateHeureLabel != null) {
                dateHeureLabel.setText("12/12/2024 à 14:30");
            }

            if (dureeLabel != null) {
                dureeLabel.setText(currentSession.getDureeMinutes() + " minutes");
            }

            if (paiementLabel != null) {
                paiementLabel.setText("Payé");
            }

            if (motifArea != null) {
                motifArea.setText("Consultation de routine pour suivi thérapeutique.");
            }

            if (notesArea != null) {
                notesArea.setText("Patient en bonne progression. Continue les exercices recommandés.");
            }
        }
    }

    @FXML
    public void handleClose(ActionEvent event) {
        closeDialog();
    }

    @FXML
    public void handleBack(ActionEvent event) {
        closeDialog();
    }

    @FXML
    public void handleMarkCompleted(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Session Terminée");
        alert.setHeaderText("Session marquée comme terminée");
        alert.setContentText("La session #" + currentSession.getId() + " a été marquée comme terminée.");
        alert.showAndWait();
        closeDialog();
    }

    @FXML
    public void handleEdit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification");
        alert.setHeaderText("Fonctionnalité à venir");
        alert.setContentText("La modification de session sera disponible prochainement.");
        alert.showAndWait();
    }

    @FXML
    public void handleAddReport(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rapport");
        alert.setHeaderText("Ajouter un rapport");
        alert.setContentText("Fonctionnalité d'ajout de rapport à venir.");
        alert.showAndWait();
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Annuler la session ?");
        confirmation.setContentText("Êtes-vous sûr de vouloir annuler cette session ?\nCette action est irréversible.");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Session Annulée");
                success.setHeaderText("La session a été annulée");
                success.setContentText("La session #" + currentSession.getId() + " a été annulée avec succès.");
                success.showAndWait();
                closeDialog();
            }
        });
    }

    private void closeDialog() {
        Stage stage = (Stage) idLabel.getScene().getWindow();
        stage.close();
    }
}