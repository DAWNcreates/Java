package clinique.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Contrôleur pour la création d'un nouveau rapport
 */
public class CreateRapportController {

    @FXML private ComboBox<String> sessionCombo;
    @FXML private ComboBox<String> typeTherapieCombo;
    @FXML private TextArea resumeArea;
    @FXML private TextArea observationsArea;
    @FXML private TextArea recommandationsArea;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;

    private RapportListController.Rapport createdRapport;
    private int nextRapportId = 4;

    @FXML
    public void initialize() {
        // Initialiser le ComboBox des sessions
        if (sessionCombo != null) {
            sessionCombo.getItems().addAll(
                "Session #248 - Marie Dubois (12/12/2024)",
                "Session #247 - Jean Dupont (10/12/2024)",
                "Session #246 - Pierre Martin (08/12/2024)"
            );
        }

        // Initialiser le ComboBox des types de thérapie
        if (typeTherapieCombo != null) {
            typeTherapieCombo.getItems().addAll(
                "TCC",
                "Psychanalyse",
                "Humaniste",
                "Systémique",
                "EMDR"
            );
        }
    }

    /**
     * Enregistre un nouveau rapport
     */
    @FXML
    public void handleSave(ActionEvent event) {
        hideMessages();

        if (!validateFields()) {
            return;
        }

        try {
            String sessionStr = sessionCombo.getValue();
            String typeTherapie = typeTherapieCombo.getValue();
            String resume = resumeArea.getText().trim();
            String observations = observationsArea.getText().trim();
            String recommandations = recommandationsArea.getText().trim();

            // Extraire l'ID de la session et le nom du patient
            int sessionId = Integer.parseInt(sessionStr.split("#")[1].split(" ")[0]);
            String patientNom = sessionStr.split(" - ")[1].split(" \\(")[0];

            // Créer le rapport (exemple simplifié)
            createdRapport = new RapportListController.Rapport(
                nextRapportId++,
                "12/12/2024",
                sessionId,
                patientNom,
                "Martin",
                typeTherapie,
                resume
            );

            showSuccess("✅ Rapport créé avec succès ! ID: #RAP-" + String.format("%03d", createdRapport.getId()));

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
            showError("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }

    /**
     * Valide les champs obligatoires
     */
    private boolean validateFields() {
        if (sessionCombo.getValue() == null) {
            showError("La session est obligatoire.");
            sessionCombo.requestFocus();
            return false;
        }

        if (typeTherapieCombo.getValue() == null) {
            showError("Le type de thérapie est obligatoire.");
            typeTherapieCombo.requestFocus();
            return false;
        }

        if (resumeArea.getText().trim().isEmpty()) {
            showError("Le résumé est obligatoire.");
            resumeArea.requestFocus();
            return false;
        }

        if (resumeArea.getText().trim().length() < 20) {
            showError("Le résumé doit contenir au moins 20 caractères.");
            resumeArea.requestFocus();
            return false;
        }

        if (observationsArea.getText().trim().isEmpty()) {
            showError("Les observations sont obligatoires.");
            observationsArea.requestFocus();
            return false;
        }

        if (observationsArea.getText().trim().length() < 20) {
            showError("Les observations doivent contenir au moins 20 caractères.");
            observationsArea.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Réinitialise tous les champs
     */
    @FXML
    public void handleReset(ActionEvent event) {
        sessionCombo.setValue(null);
        typeTherapieCombo.setValue(null);
        resumeArea.clear();
        observationsArea.clear();
        recommandationsArea.clear();
        hideMessages();
        sessionCombo.requestFocus();
    }

    /**
     * Annule et ferme le dialogue
     */
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

    /**
     * Ferme le dialogue
     */
    private void closeDialog() {
        Stage stage = (Stage) sessionCombo.getScene().getWindow();
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
     * Récupère le rapport créé
     */
    public RapportListController.Rapport getCreatedRapport() {
        return createdRapport;
    }
}