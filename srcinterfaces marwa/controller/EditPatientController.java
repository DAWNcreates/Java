package clinique.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

/**
 * Contrôleur pour la modification d'un patient existant
 * Permet de mettre à jour les informations d'un patient
 * 
 * @author Monstera Clinic Team
 * @version 1.0
 */
public final class EditPatientController {
    
    // Labels d'information
    @FXML private Label patientIdLabel;
    @FXML private Label dateInscriptionLabel;
    
    // Statut
    @FXML private ToggleButton statutToggle;
    
    // Champs du formulaire
    @FXML private TextField nomField;
    @FXML private TextField ageField;
    @FXML private ComboBox<String> genreCombo;
    @FXML private TextField emailField;
    @FXML private TextField telephoneField;
    @FXML private TextArea adresseArea;
    
    // Contact d'urgence
    @FXML private TextField contactUrgenceNomField;
    @FXML private TextField contactUrgenceTelField;
    @FXML private TextField contactUrgenceRelationField;
    
    // Messages
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    
    // Données originales pour réinitialisation
    private String originalNom;
    private String originalAge;
    private String originalGenre;
    private String originalEmail;
    private String originalTelephone;
    private String originalAdresse;
    private String originalContactNom;
    private String originalContactTel;
    private String originalContactRelation;
    private boolean originalStatut;
    
    /**
     * Initialisation du contrôleur
     * Appelée automatiquement après le chargement du FXML
     */
    @FXML
    public void initialize() {
        System.out.println("EditPatientController initialisé");
        
        // Cacher les messages au démarrage
        errorLabel.setVisible(false);
        successLabel.setVisible(false);
        
        // Validation de l'âge (seulement des chiffres)
        ageField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                ageField.setText(oldVal);
            }
        });
        
        // Gérer le changement de statut
        statutToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            updateStatutToggleStyle(newVal);
        });
        
        // Masquer les messages lors de la saisie
        nomField.textProperty().addListener((obs, oldVal, newVal) -> hideMessages());
        emailField.textProperty().addListener((obs, oldVal, newVal) -> hideMessages());
        
        // Charger les données du patient (exemple)
        loadPatientData();
    }
    
    /**
     * Charge les données du patient à modifier
     * TODO: Remplacer par les vraies données du patient sélectionné
     */
    private void loadPatientData() {
        // Exemple de données - À remplacer par les vraies données
        patientIdLabel.setText("Patient ID: #PAT-001");
        dateInscriptionLabel.setText("12/05/2024");
        
        nomField.setText("Jean Dupont");
        ageField.setText("35");
        genreCombo.setValue("Homme");
        emailField.setText("jean.dupont@email.com");
        telephoneField.setText("+216 20 123 456");
        adresseArea.setText("15 Avenue Habib Bourguiba, Tunis");
        contactUrgenceNomField.setText("Marie Dupont");
        contactUrgenceTelField.setText("+216 20 654 321");
        contactUrgenceRelationField.setText("Épouse");
        statutToggle.setSelected(true);
        
        // Sauvegarder les valeurs originales
        saveOriginalValues();
    }
    
    /**
     * Sauvegarde les valeurs originales pour la réinitialisation
     */
    private void saveOriginalValues() {
        originalNom = nomField.getText();
        originalAge = ageField.getText();
        originalGenre = genreCombo.getValue();
        originalEmail = emailField.getText();
        originalTelephone = telephoneField.getText();
        originalAdresse = adresseArea.getText();
        originalContactNom = contactUrgenceNomField.getText();
        originalContactTel = contactUrgenceTelField.getText();
        originalContactRelation = contactUrgenceRelationField.getText();
        originalStatut = statutToggle.isSelected();
    }
    
    /**
     * Met à jour le style du bouton de statut
     */
    private void updateStatutToggleStyle(boolean isActive) {
        if (isActive) {
            statutToggle.setText("Actif");
            statutToggle.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand;");
        } else {
            statutToggle.setText("Inactif");
            statutToggle.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand;");
        }
    }
    
    /**
     * Gère l'enregistrement des modifications
     */
    @FXML
    private void handleSave() {
        if (validateFields()) {
            try {
                // TODO: Mettre à jour le patient dans le service
                System.out.println("Enregistrement des modifications...");
                System.out.println("Nom: " + nomField.getText());
                System.out.println("Statut: " + (statutToggle.isSelected() ? "Actif" : "Inactif"));
                
                showSuccess("Modifications enregistrées avec succès !");
                
                // Mettre à jour les valeurs originales
                saveOriginalValues();
                
            } catch (Exception e) {
                showError("Erreur lors de l'enregistrement: " + e.getMessage());
            }
        }
    }
    
    /**
     * Gère la réinitialisation du formulaire
     */
    @FXML
    private void handleReset() {
        // Confirmer avant de réinitialiser
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Réinitialiser les modifications");
        confirmAlert.setContentText("Êtes-vous sûr de vouloir annuler toutes les modifications et revenir aux valeurs originales ?");
        
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                resetToOriginalValues();
                showSuccess("Formulaire réinitialisé aux valeurs originales");
            }
        });
    }
    
    /**
     * Réinitialise tous les champs aux valeurs originales
     */
    private void resetToOriginalValues() {
        nomField.setText(originalNom);
        ageField.setText(originalAge);
        genreCombo.setValue(originalGenre);
        emailField.setText(originalEmail);
        telephoneField.setText(originalTelephone);
        adresseArea.setText(originalAdresse);
        contactUrgenceNomField.setText(originalContactNom);
        contactUrgenceTelField.setText(originalContactTel);
        contactUrgenceRelationField.setText(originalContactRelation);
        statutToggle.setSelected(originalStatut);
        
        hideMessages();
    }
    
    /**
     * Gère l'annulation et le retour
     */
    @FXML
    private void handleCancel() {
        // Vérifier si des modifications ont été faites
        if (hasChanges()) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmation");
            confirmAlert.setHeaderText("Modifications non enregistrées");
            confirmAlert.setContentText("Vous avez des modifications non enregistrées. Voulez-vous vraiment quitter ?");
            
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    navigateBack();
                }
            });
        } else {
            navigateBack();
        }
    }
    
    /**
     * Gère le retour
     */
    @FXML
    private void handleBack() {
        handleCancel(); // Même comportement que Annuler
    }
    
    /**
     * Vérifie si des modifications ont été faites
     */
    private boolean hasChanges() {
        return !nomField.getText().equals(originalNom) ||
               !ageField.getText().equals(originalAge) ||
               !genreCombo.getValue().equals(originalGenre) ||
               !emailField.getText().equals(originalEmail) ||
               !telephoneField.getText().equals(originalTelephone) ||
               !adresseArea.getText().equals(originalAdresse) ||
               !contactUrgenceNomField.getText().equals(originalContactNom) ||
               !contactUrgenceTelField.getText().equals(originalContactTel) ||
               !contactUrgenceRelationField.getText().equals(originalContactRelation) ||
               statutToggle.isSelected() != originalStatut;
    }
    
    /**
     * Valide tous les champs du formulaire
     */
    private boolean validateFields() {
        if (nomField.getText().trim().isEmpty()) {
            showError("Le nom est obligatoire");
            nomField.requestFocus();
            return false;
        }
        
        if (ageField.getText().trim().isEmpty()) {
            showError("L'âge est obligatoire");
            ageField.requestFocus();
            return false;
        }
        
        int age;
        try {
            age = Integer.parseInt(ageField.getText());
            if (age < 0 || age > 120) {
                showError("L'âge doit être entre 0 et 120");
                ageField.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showError("L'âge doit être un nombre valide");
            ageField.requestFocus();
            return false;
        }
        
        if (genreCombo.getValue() == null) {
            showError("Le genre est obligatoire");
            genreCombo.requestFocus();
            return false;
        }
        
        if (emailField.getText().trim().isEmpty()) {
            showError("L'email est obligatoire");
            emailField.requestFocus();
            return false;
        }
        
        if (!isValidEmail(emailField.getText())) {
            showError("Email invalide");
            emailField.requestFocus();
            return false;
        }
        
        if (telephoneField.getText().trim().isEmpty()) {
            showError("Le téléphone est obligatoire");
            telephoneField.requestFocus();
            return false;
        }
        
        if (contactUrgenceNomField.getText().trim().isEmpty()) {
            showError("Le nom du contact d'urgence est obligatoire");
            contactUrgenceNomField.requestFocus();
            return false;
        }
        
        if (contactUrgenceTelField.getText().trim().isEmpty()) {
            showError("Le téléphone du contact d'urgence est obligatoire");
            contactUrgenceTelField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Valide le format d'un email
     */
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
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
     * Masque tous les messages
     */
    private void hideMessages() {
        errorLabel.setVisible(false);
        successLabel.setVisible(false);
    }
    
    /**
     * Navigation vers la page précédente
     */
    private void navigateBack() {
        System.out.println("Retour à la liste des patients");
        // TODO: Implémenter la navigation vers PatientListView
    }
}