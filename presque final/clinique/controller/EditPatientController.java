package clinique.controller;

import clinique.model.Personnes.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

public class EditPatientController {

    @FXML private Label patientIdLabel;
    @FXML private TextField nomField;
    @FXML private TextField ageField;
    @FXML private ComboBox<String> genreCombo;
    @FXML private TextField emailField;
    @FXML private TextField telephoneField;
    @FXML private TextArea adresseArea;
    @FXML private TextField contactUrgenceNomField;
    @FXML private TextField contactUrgenceTelField;
    @FXML private TextField contactUrgenceRelationField;
    @FXML private Label dateInscriptionLabel;
    @FXML private ToggleButton statutToggle;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;

    private Patient currentPatient;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    
    
    @FXML
public void initialize() {
    // Initialiser le ComboBox des genres
    if (genreCombo != null) {
        genreCombo.getItems().addAll("Homme", "Femme", "Autre");
    }
    
    // Configurer le ToggleButton pour le statut
    if (statutToggle != null) {
        statutToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                statutToggle.setText("Actif");
            } else {
                statutToggle.setText("Inactif");
            }
        });
    }
}

    /**
     * Définit le patient à modifier et remplit les champs
     */
    public void setPatient(Patient patient) {
        this.currentPatient = patient;
        fillFields();
    }

    /**
     * Remplit les champs avec les données du patient
     */
    private void fillFields() {
        if (currentPatient != null) {
            patientIdLabel.setText("Patient ID: #PAT-" + String.format("%03d", currentPatient.getId()));
            nomField.setText(currentPatient.getNom() + " " + currentPatient.getPrenom());
            ageField.setText(String.valueOf(currentPatient.getAge()));
            genreCombo.setValue(currentPatient.getGenre());
            emailField.setText(currentPatient.getEmail());
            telephoneField.setText(String.valueOf(currentPatient.getNumTel()));
            
            // Date d'inscription
            if (currentPatient.getDateInscription() != null) {
                dateInscriptionLabel.setText(currentPatient.getDateInscription().format(dateFormatter));
            }
            
            // Statut
            statutToggle.setSelected("Actif".equals(currentPatient.getStatut()));
            
            // TODO: Remplir les autres champs (adresse, contact d'urgence, etc.)
            // Ces champs nécessitent des modifications dans la classe Patient
        }
    }

    /**
     * Enregistre les modifications
     */
    @FXML
    public void handleSave(ActionEvent event) {
        hideMessages();
        
        // Validation des champs
        if (!validateFields()) {
            return;
        }
        
        try {
            // Extraire nom et prénom
            String fullName = nomField.getText().trim();
            String[] nameParts = fullName.split(" ", 2);
            String nom = nameParts.length > 0 ? nameParts[0] : "";
            String prenom = nameParts.length > 1 ? nameParts[1] : "";
            
            // Mettre à jour le patient
            currentPatient.setNom(nom);
            currentPatient.setPrenom(prenom);
            currentPatient.setAge(Integer.parseInt(ageField.getText().trim()));
            currentPatient.setGenre(genreCombo.getValue());
            currentPatient.setEmail(emailField.getText().trim());
            currentPatient.setNumTel(Integer.parseInt(telephoneField.getText().trim()));
            currentPatient.setStatut(statutToggle.isSelected() ? "Actif" : "Inactif");
            
            // TODO: Mettre à jour les autres champs
            
            showSuccess("Patient modifié avec succès !");
            
            // Fermer le dialogue après 1.5 secondes
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    javafx.application.Platform.runLater(() -> closeDialog());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            
        } catch (NumberFormatException e) {
            showError("Erreur : Âge et téléphone doivent être des nombres valides.");
        } catch (Exception e) {
            showError("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }

    /**
     * Valide les champs obligatoires
     */
    private boolean validateFields() {
        if (nomField.getText().trim().isEmpty()) {
            showError("Le nom complet est obligatoire.");
            return false;
        }
        if (ageField.getText().trim().isEmpty()) {
            showError("L'âge est obligatoire.");
            return false;
        }
        if (genreCombo.getValue() == null) {
            showError("Le genre est obligatoire.");
            return false;
        }
        if (emailField.getText().trim().isEmpty()) {
            showError("L'email est obligatoire.");
            return false;
        }
        if (telephoneField.getText().trim().isEmpty()) {
            showError("Le téléphone est obligatoire.");
            return false;
        }
        
        // Validation de l'email
        if (!emailField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showError("Format d'email invalide.");
            return false;
        }
        
        // Validation de l'âge
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            if (age < 0 || age > 150) {
                showError("L'âge doit être entre 0 et 150.");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("L'âge doit être un nombre valide.");
            return false;
        }
        
        return true;
    }

    /**
     * Réinitialise les champs
     */
    @FXML
    public void handleReset(ActionEvent event) {
        fillFields();
        hideMessages();
        showSuccess("Champs réinitialisés !");
    }

    /**
     * Annule et ferme le dialogue
     */
    @FXML
    public void handleCancel(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Annuler les modifications ?");
        confirmation.setContentText("Les modifications non enregistrées seront perdues.");
        
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                closeDialog();
            }
        });
    }

    /**
     * Retour à la liste
     */
    @FXML
    public void handleBack(ActionEvent event) {
        handleCancel(event);
    }

    /**
     * Ferme le dialogue
     */
    private void closeDialog() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }

    /**
     * Affiche un message d'erreur
     */
    private void showError(String message) {
        errorLabel.setText("❌ " + message);
        errorLabel.setVisible(true);
        successLabel.setVisible(false);
    }

    /**
     * Affiche un message de succès
     */
    private void showSuccess(String message) {
        successLabel.setText("✅ " + message);
        successLabel.setVisible(true);
        errorLabel.setVisible(false);
    }

    /**
     * Cache les messages
     */
    private void hideMessages() {
        errorLabel.setVisible(false);
        successLabel.setVisible(false);
    }
}