package Controller;

import Model.personnes.Therapist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddTherapistController implements Initializable {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<String> specialtyComboBox;
    @FXML private DatePicker hireDatePicker;
    @FXML private TextField licenseNumberField;
    @FXML private TextField experienceField;
    @FXML private TextArea bioTextArea;
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private Button backBtn;

    // Static list to store therapists (shared across controllers)
    private static java.util.List<Therapist> sharedTherapistList = new java.util.ArrayList<>();
    private static int nextId = 9; // Start from 9 since we have 8 therapists already

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AddTherapistController initialized");
        
        // Populate specialty ComboBox
        specialtyComboBox.getItems().addAll(
            "Psychologue clinicien",
            "Psychothérapeute",
            "Psychiatre",
            "Thérapeute familial",
            "Thérapeute comportemental",
            "Conseiller en santé mentale",
            "Art-thérapeute",
            "Musicothérapeute"
        );
        
        // Set button actions
        saveBtn.setOnAction(e -> handleSave());
        cancelBtn.setOnAction(e -> handleCancel());
        if (backBtn != null) {
            backBtn.setOnAction(e -> handleCancel());
        }
    }

    private void handleSave() {
        System.out.println("Save button clicked");
        
        // Validate required fields
        if (!validateFields()) {
            return;
        }
        
        try {
            // Get form data
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String fullName = "Dr. " + firstName + " " + lastName;
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String specialty = specialtyComboBox.getValue();
            LocalDate hireDate = hireDatePicker.getValue();
            String licenseNumber = licenseNumberField.getText().trim();
            String experience = experienceField.getText().trim();
            String bio = bioTextArea.getText().trim();
            
            // Calculate age (dummy value for now, you can add a date of birth field)
            int age = 35; // Default age
            
            // Create new therapist
            Therapist newTherapist = new Therapist(
                nextId++,
                fullName,
                age,
                phone,
                email,
                "Actif" // Default status
            );
            
            // Add to shared list
            sharedTherapistList.add(newTherapist);
            
            System.out.println("New therapist created: " + newTherapist);
            System.out.println("Total therapists in shared list: " + sharedTherapistList.size());
            
            // Show success message
            showSuccess("Succès", "Le thérapeute " + fullName + " a été ajouté avec succès!");
            
            // Navigate back to TherapistListView
            navigateToTherapistList();
            
        } catch (Exception e) {
            System.err.println("Error saving therapist: " + e.getMessage());
            e.printStackTrace();
            showError("Erreur", "Une erreur s'est produite lors de l'enregistrement: " + e.getMessage());
        }
    }

    private boolean validateFields() {
        StringBuilder errors = new StringBuilder();
        
        if (firstNameField.getText().trim().isEmpty()) {
            errors.append("• Le prénom est obligatoire\n");
        }
        
        if (lastNameField.getText().trim().isEmpty()) {
            errors.append("• Le nom est obligatoire\n");
        }
        
        if (emailField.getText().trim().isEmpty()) {
            errors.append("• L'email est obligatoire\n");
        } else if (!isValidEmail(emailField.getText().trim())) {
            errors.append("• L'email n'est pas valide\n");
        }
        
        if (phoneField.getText().trim().isEmpty()) {
            errors.append("• Le téléphone est obligatoire\n");
        }
        
        if (specialtyComboBox.getValue() == null) {
            errors.append("• La spécialité est obligatoire\n");
        }
        
        if (hireDatePicker.getValue() == null) {
            errors.append("• La date d'embauche est obligatoire\n");
        }
        
        if (errors.length() > 0) {
            showWarning("Champs obligatoires manquants", errors.toString());
            return false;
        }
        
        return true;
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private void handleCancel() {
        System.out.println("Cancel button clicked");
        
        // Show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Annuler l'ajout");
        alert.setContentText("Êtes-vous sûr de vouloir annuler? Toutes les données seront perdues.");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                navigateToTherapistList();
            }
        });
    }

    private void navigateToTherapistList() {
        try {
            System.out.println("Navigating back to TherapistListView...");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TherapistListView.fxml"));
            Parent root = loader.load();
            
            // Get the controller and pass the shared list
            TherapistListController controller = loader.getController();
            controller.setTherapistList(sharedTherapistList);
            
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Theraply - Liste des Thérapeutes");
            stage.show();
            
            System.out.println("Successfully navigated to TherapistListView");
            
        } catch (IOException e) {
            System.err.println("Error navigating to TherapistListView: " + e.getMessage());
            e.printStackTrace();
            showError("Erreur de navigation", 
                "Impossible de retourner à la liste des thérapeutes.\n" +
                "Erreur: " + e.getMessage());
        }
    }

    private void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Getter for shared list
    public static java.util.List<Therapist> getSharedTherapistList() {
        return sharedTherapistList;
    }
}