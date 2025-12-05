package clinique.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Contrôleur pour la modification d'un rapport existant
 */
public class EditRapportController {

    @FXML private Label rapportIdLabel;
    @FXML private ComboBox<String> sessionCombo;
    @FXML private ComboBox<String> typeTherapieCombo;
    @FXML private TextArea resumeArea;
    @FXML private TextArea observationsArea;
    @FXML private TextArea recommandationsArea;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;

    private RapportListController.Rapport currentRapport;

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
     * Définit le rapport à modifier et remplit les champs
     */
    public void setRapport(RapportListController.Rapport rapport) {
        this.currentRapport = rapport;
        fillFields();
    }

    /**
     * Remplit les champs avec les données du rapport
     */
    private void fillFields() {
        if (currentRapport != null) {
            rapportIdLabel.setText("Rapport ID: #RAP-" + String.format("%03d", currentRapport.getId()));

            // Session (simulé)
            sessionCombo.setValue("Session #" + currentRapport.getSessionId() + " - " + 
                                 currentRapport.getPatientNom() + " (12/12/2024)");

            // Type de thérapie
            typeTherapieCombo.setValue(currentRapport.getTypeTherapie());

            // Résumé
            resumeArea.setText(currentRapport.getResume());

            // Observations (exemple)
            observationsArea.setText("État émotionnel: Le patient présente des signes d'amélioration.\n\nProgrès notables:\n- Meilleure verbalisation des émotions\n- Prise de conscience accrue");

            // Recommandations (exemple)
            recommandationsArea.setText("Pour la prochaine session:\n1. Continuer les exercices\n2. Tenir un journal\n3. Pratiquer l'exposition progressive");
        }
    }

    /**
     * Enregistre les modifications
     */
    @FXML
    public void handleSave(ActionEvent event) {
        hideMessages();

        if (!validateFields()) {
            return;
        }

        try {
            // Mettre à jour le rapport (dans une vraie app, vous mettriez à jour l'objet)
            String typeTherapie = typeTherapieCombo.getValue();
            String resume = resumeArea.getText().trim();
            String observations = observationsArea.getText().trim();
            String recommandations = recommandationsArea.getText().trim();

            // TODO: Mettre à jour l'objet currentRapport avec les nouvelles données

            showSuccess("✅ Rapport modifié avec succès !");

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
            return false;
        }

        if (typeTherapieCombo.getValue() == null) {
            showError("Le type de thérapie est obligatoire.");
            return false;
        }

        if (resumeArea.getText().trim().isEmpty()) {
            showError("Le résumé est obligatoire.");
            return false;
        }

        if (resumeArea.getText().trim().length() < 20) {
            showError("Le résumé doit contenir au moins 20 caractères.");
            return false;
        }

        if (observationsArea.getText().trim().isEmpty()) {
            showError("Les observations sont obligatoires.");
            return false;
        }

        if (observationsArea.getText().trim().length() < 20) {
            showError("Les observations doivent contenir au moins 20 caractères.");
            return false;
        }

        return true;
    }

    /**
     * Réinitialise les champs avec les données originales
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
        Stage stage = (Stage) sessionCombo.getScene().getWindow();
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