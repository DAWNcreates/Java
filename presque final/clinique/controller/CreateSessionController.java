package clinique.controller;

import clinique.model.Personnes.Patient;
import clinique.model.Personnes.Therapeute;
import clinique.model.Sessions.Session;
import clinique.model.Sessions.SessionChat;
import clinique.model.Sessions.SessionVideo;
import clinique.model.Sessions.SessionIA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateSessionController {
    @FXML private ToggleButton chatToggle;
    @FXML private ToggleButton videoToggle;
    @FXML private ToggleButton iaToggle;
    @FXML private ToggleGroup typeGroup;
    @FXML private ComboBox<String> patientCombo;
    @FXML private ComboBox<String> therapeuteCombo;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> heureCombo;
    @FXML private RadioButton duree30;
    @FXML private RadioButton duree45;
    @FXML private RadioButton duree60;
    @FXML private RadioButton duree90;
    @FXML private TextArea motifArea;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    
    private Session createdSession;
    private int nextSessionId = 4;
    
    // Données de test - Patients
    private Patient patient1 = new Patient(1, "Benali", "Ahmed", "ahmed.benali@email.com", 98123456,
            LocalDateTime.now(), new ArrayList<>(), new ArrayList<>(), "Email", 4, 35, "Homme");
    private Patient patient2 = new Patient(2, "Trabelsi", "Leila", "leila.trabelsi@email.com", 22345678,
            LocalDateTime.now(), new ArrayList<>(), new ArrayList<>(), "Téléphone", 5, 28, "Femme");
    private Patient patient3 = new Patient(3, "Mansour", "Karim", "karim.mansour@email.com", 55678901,
            LocalDateTime.now(), new ArrayList<>(), new ArrayList<>(), "Email", 3, 42, "Homme");
    
    // Données de test - Thérapeutes
    private Therapeute therapeute1 = new Therapeute(1, "Martin", "Sophie", "sophie.martin@theraply.tn", 71234567,
            LocalDateTime.now(), "http://licence.tn/001", new ArrayList<>(), "Actif", 85.0);
    private Therapeute therapeute2 = new Therapeute(2, "Benali", "Ahmed", "ahmed.benali@theraply.tn", 71345678,
            LocalDateTime.now(), "http://licence.tn/002", new ArrayList<>(), "Actif", 90.0);
    private Therapeute therapeute3 = new Therapeute(3, "Leroy", "Claire", "claire.leroy@theraply.tn", 71456789,
            LocalDateTime.now(), "http://licence.tn/003", new ArrayList<>(), "Actif", 80.0);

    @FXML
    public void initialize() {
        if (patientCombo != null) {
            patientCombo.getItems().addAll(
                "Ahmed Benali (#1)", 
                "Leila Trabelsi (#2)", 
                "Karim Mansour (#3)"
            );
        }
        
        if (therapeuteCombo != null) {
            therapeuteCombo.getItems().addAll(
                "Dr. Sophie Martin", 
                "Dr. Ahmed Benali", 
                "Dr. Claire Leroy"
            );
        }
        
        if (heureCombo != null) {
            for (int h = 8; h <= 18; h++) {
                heureCombo.getItems().add(String.format("%02d:00", h));
                heureCombo.getItems().add(String.format("%02d:30", h));
            }
        }
        
        if (videoToggle != null) {
            videoToggle.setSelected(true);
        }
        
        if (duree30 != null) {
            duree30.setSelected(true);
        }
    }

    @FXML
    public void handleCreate(ActionEvent event) {
        hideMessages();
        
        if (!validateFields()) {
            return;
        }

        try {
            // Récupérer le patient sélectionné
            Patient selectedPatient = getSelectedPatient();
            Therapeute selectedTherapeute = getSelectedTherapeute();
            
            // Récupérer la durée sélectionnée
            int duree = getDureeSelectionnee();
            
            // Créer un timestamp (simulation de la date/heure)
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            
            // Créer la session selon le type
            if (videoToggle.isSelected()) {
                createdSession = new SessionVideo(
                    nextSessionId++,
                    selectedPatient,
                    selectedTherapeute,
                    timestamp,
                    duree,
                    "http://video.theraply.tn/session" + nextSessionId
                );
            } else if (chatToggle.isSelected()) {
                createdSession = new SessionChat(
                    nextSessionId++,
                    selectedPatient,
                    selectedTherapeute,
                    timestamp,
                    duree,
                    "transcript_" + String.format("%03d", nextSessionId)
                );
            } else if (iaToggle.isSelected()) {
                createdSession = new SessionIA(
                    nextSessionId++,
                    selectedPatient,
                    selectedTherapeute,
                    timestamp,
                    duree,
                    "Faible",
                    "Session IA - Analyse en cours"
                );
            }
            
            showSuccess("✅ Session créée avec succès ! ID: #S-" + String.format("%03d", createdSession.getId()));
            
            // Fermer le dialogue après 1.5 secondes
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    javafx.application.Platform.runLater(() -> closeDialog());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            
        } catch (Exception e) {
            showError("Erreur lors de la création : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private Patient getSelectedPatient() {
        String selection = patientCombo.getValue();
        if (selection.contains("#1")) return patient1;
        if (selection.contains("#2")) return patient2;
        if (selection.contains("#3")) return patient3;
        return patient1;
    }
    
    private Therapeute getSelectedTherapeute() {
        String selection = therapeuteCombo.getValue();
        if (selection.contains("Sophie")) return therapeute1;
        if (selection.contains("Ahmed")) return therapeute2;
        if (selection.contains("Claire")) return therapeute3;
        return therapeute1;
    }
    
    private int getDureeSelectionnee() {
        if (duree30.isSelected()) return 30;
        if (duree45.isSelected()) return 45;
        if (duree60.isSelected()) return 60;
        if (duree90.isSelected()) return 90;
        return 60;
    }

    private boolean validateFields() {
        if (patientCombo.getValue() == null) {
            showError("Le patient est obligatoire.");
            return false;
        }
        if (therapeuteCombo.getValue() == null) {
            showError("Le thérapeute est obligatoire.");
            return false;
        }
        if (datePicker.getValue() == null) {
            showError("La date est obligatoire.");
            return false;
        }
        if (heureCombo.getValue() == null) {
            showError("L'heure est obligatoire.");
            return false;
        }
        return true;
    }

    @FXML
    public void handleReset(ActionEvent event) {
        patientCombo.setValue(null);
        therapeuteCombo.setValue(null);
        datePicker.setValue(null);
        heureCombo.setValue(null);
        motifArea.clear();
        duree30.setSelected(true);
        videoToggle.setSelected(true);
        hideMessages();
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Annuler la création ?");
        confirmation.setContentText("Les données saisies seront perdues.");
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                closeDialog();
            }
        });
    }

    @FXML
    public void handleClose(ActionEvent event) {
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) patientCombo.getScene().getWindow();
        stage.close();
    }

    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText("❌ " + message);
            errorLabel.setVisible(true);
        }
        if (successLabel != null) {
            successLabel.setVisible(false);
        }
    }

    private void showSuccess(String message) {
        if (successLabel != null) {
            successLabel.setText(message);
            successLabel.setVisible(true);
        }
        if (errorLabel != null) {
            errorLabel.setVisible(false);
        }
    }

    private void hideMessages() {
        if (errorLabel != null) {
            errorLabel.setVisible(false);
        }
        if (successLabel != null) {
            successLabel.setVisible(false);
        }
    }
    
    // Méthode pour récupérer la session créée
    public Session getCreatedSession() {
        return createdSession;
    }
}