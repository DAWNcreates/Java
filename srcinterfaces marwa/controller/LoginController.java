/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
/**
 *
 * @author marwa
 */
public class LoginController {

    // === Champs du formulaire ===
    @FXML private TextField nomField;
    @FXML private TextField ageField;
    @FXML private ComboBox<String> genreCombo;
    @FXML private TextField emailField;
    @FXML private TextField telephoneField;
    @FXML private TextArea adresseArea;
    @FXML private TextField contactUrgenceNomField;
    @FXML private TextField contactUrgenceTelField;
    @FXML private TextField contactUrgenceRelationField;

    // === Messages ===
    @FXML private Label errorLabel;
    @FXML private Label successLabel;

    // === Actions ===

    @FXML
    public void handleSave() {
        // Masquer les messages précédents
        hideMessages();

        // Validation des champs obligatoires
        if (isEmpty(nomField) ||
            isEmpty(ageField) ||
            genreCombo.getValue() == null ||
            isEmpty(emailField) ||
            isEmpty(telephoneField) ||
            isEmpty(contactUrgenceNomField) ||
            isEmpty(contactUrgenceTelField)) {
            
            showError("Veuillez remplir tous les champs marqués par *.");
            return;
        }

        // Validation de l'âge
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            if (age < 0 || age > 150) {
                showError("L'âge doit être entre 0 et 150 ans.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("L'âge doit être un nombre entier valide.");
            return;
        }

        // ✅ Tout est valide → enregistrer (pour l’instant, juste afficher un message)
        // TODO: Plus tard → sauvegarder dans une ObservableList<Patient> ou en BDD

        String nom = nomField.getText().trim();
        String genre = genreCombo.getValue();

        showSuccess("✅ Le patient **" + nom + "** (" + genre + ") a été ajouté avec succès !");

        // Option : Réinitialiser après succès
        // clearForm();
    }

    @FXML
    public void handleReset() {
        clearForm();
        hideMessages();
    }

    @FXML
    public void handleCancel() {
        // Fermer la fenêtre actuelle
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
        
        // 🔁 Option : revenir au dashboard (si vous voulez)
        // goToDashboard();
    }

    // === Méthodes utilitaires ===

    private boolean isEmpty(TextField field) {
        return field.getText() == null || field.getText().trim().isEmpty();
    }

    private boolean isEmpty (TextArea area) {
        return area.getText() == null || area.getText().trim().isEmpty();
    }

    private void clearForm() {
        nomField.clear();
        ageField.clear();
        genreCombo.setValue(null);
        emailField.clear();
        telephoneField.clear();
        adresseArea.clear();
        contactUrgenceNomField.clear();
        contactUrgenceTelField.clear();
        contactUrgenceRelationField.clear();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
        successLabel.setVisible(false);
    }

    private void showSuccess(String message) {
        successLabel.setText(message);
        successLabel.setVisible(true);
        errorLabel.setVisible(false);
    }

    private void hideMessages() {
        errorLabel.setVisible(false);
        successLabel.setVisible(false);
    }

    // === Option : Retour au Dashboard ===
    /*
    private void goToDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/DashboardView.fxml")
            );
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Theraply — Dashboard");
            stage.show();

            // Fermer la fenêtre actuelle
            ((Stage) nomField.getScene().getWindow()).close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ouvrir le dashboard");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    */
}