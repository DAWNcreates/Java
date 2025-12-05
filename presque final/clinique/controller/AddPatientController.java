package clinique.controller;

import clinique.model.Personnes.Patient;
import clinique.model.Sessions.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddPatientController {

    @FXML private TextField nomField;
    @FXML private TextField ageField;
    @FXML private ComboBox<String> genreCombo;
    @FXML private TextField emailField;
    @FXML private TextField telephoneField;
    @FXML private TextArea adresseArea;
    @FXML private TextField contactUrgenceNomField;
    @FXML private TextField contactUrgenceTelField;
    @FXML private TextField contactUrgenceRelationField;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;

    private Patient createdPatient;
    private int nextPatientId = 4;

    @FXML
    public void initialize() {
        // Initialiser le ComboBox des genres
        if (genreCombo != null) {
            genreCombo.getItems().addAll("Homme", "Femme", "Autre");
        }
    }

    /**
     * Enregistre un nouveau patient
     */
    @FXML
    public void handleSave(ActionEvent event) {
        hideMessages();

        if (!validateFields()) {
            return;
        }

        try {
            String fullName = nomField.getText().trim();
            String[] nameParts = fullName.split(" ", 2);
            String nom = nameParts.length > 0 ? nameParts[0] : "";
            String prenom = nameParts.length > 1 ? nameParts[1] : "";

            createdPatient = new Patient(
                nextPatientId++,
                nom,
                prenom,
                emailField.getText().trim(),
                Integer.parseInt(telephoneField.getText().trim()),
                LocalDateTime.now(),
                List.of("Non spécifié"),
                new ArrayList<Session>(),
                "Email",
                0,
                Integer.parseInt(ageField.getText().trim()),
                genreCombo.getValue()
            );

            showSuccess("✅ Patient ajouté avec succès ! ID: #PAT-" + String.format("%03d", createdPatient.getId()));

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
            nomField.requestFocus();
            return false;
        }

        if (ageField.getText().trim().isEmpty()) {
            showError("L'âge est obligatoire.");
            ageField.requestFocus();
            return false;
        }

        if (genreCombo.getValue() == null) {
            showError("Le genre est obligatoire.");
            genreCombo.requestFocus();
            return false;
        }

        if (emailField.getText().trim().isEmpty()) {
            showError("L'email est obligatoire.");
            emailField.requestFocus();
            return false;
        }

        if (telephoneField.getText().trim().isEmpty()) {
            showError("Le téléphone est obligatoire.");
            telephoneField.requestFocus();
            return false;
        }

        if (contactUrgenceNomField.getText().trim().isEmpty()) {
            showError("Le nom du contact d'urgence est obligatoire.");
            contactUrgenceNomField.requestFocus();
            return false;
        }

        if (contactUrgenceTelField.getText().trim().isEmpty()) {
            showError("Le téléphone du contact d'urgence est obligatoire.");
            contactUrgenceTelField.requestFocus();
            return false;
        }

        // Validation email
        if (!emailField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showError("Format d'email invalide.");
            emailField.requestFocus();
            return false;
        }

        // Validation âge
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            if (age < 0 || age > 150) {
                showError("L'âge doit être entre 0 et 150.");
                ageField.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showError("L'âge doit être un nombre valide.");
            ageField.requestFocus();
            return false;
        }

        // Validation téléphone
        try {
            Integer.parseInt(telephoneField.getText().trim());
        } catch (NumberFormatException e) {
            showError("Le téléphone doit être un nombre valide.");
            telephoneField.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Réinitialise tous les champs
     */
    @FXML
    public void handleReset(ActionEvent event) {
        nomField.clear();
        ageField.clear();
        genreCombo.setValue(null);
        emailField.clear();
        telephoneField.clear();
        adresseArea.clear();
        contactUrgenceNomField.clear();
        contactUrgenceTelField.clear();
        contactUrgenceRelationField.clear();
        hideMessages();
        nomField.requestFocus();
    }

    /**
     * Annule et ferme le dialogue
     */
    @FXML
    public void handleCancel(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Annuler l'ajout ?");
        confirmation.setContentText("Les données saisies seront perdues.");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                closeDialog();
            }
        });
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
        if (errorLabel != null) {
            errorLabel.setText("❌ " + message);
            errorLabel.setVisible(true);
        }
        if (successLabel != null) {
            successLabel.setVisible(false);
        }
    }

    /**
     * Affiche un message de succès
     */
    private void showSuccess(String message) {
        if (successLabel != null) {
            successLabel.setText(message);
            successLabel.setVisible(true);
        }
        if (errorLabel != null) {
            errorLabel.setVisible(false);
        }
    }

    /**
     * Cache les messages
     */
    private void hideMessages() {
        if (errorLabel != null) {
            errorLabel.setVisible(false);
        }
        if (successLabel != null) {
            successLabel.setVisible(false);
        }
    }

    /**
     * Récupère le patient créé
     */
    public Patient getCreatedPatient() {
        return createdPatient;
    }
}