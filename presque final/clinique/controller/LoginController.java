package clinique.controller;

import clinique.model.records.UserAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;  
import javafx.scene.Scene; 
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import clinique.controller.PatientListController;


public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;

    @FXML
public void handleLogin(ActionEvent event) {
    String username = usernameField.getText().trim();
    String password = passwordField.getText().trim();
    
    UserAuth auth = new UserAuth(username, password);
    
    if ("admin".equals(auth.username()) && "123".equals(auth.password())) {
        errorLabel.setVisible(false);
        System.out.println("✅ Connexion réussie !");
        
        try {
            // 🔍 DÉBOGAGE : Vérifier le chemin
            java.net.URL fxmlUrl = getClass().getResource("/view/PatientListView.fxml");
            System.out.println("🔍 URL du FXML : " + fxmlUrl);
            
            if (fxmlUrl == null) {
                System.err.println("❌ FICHIER INTROUVABLE : /view/PatientListView.fxml");
                System.err.println("📂 Vérifiez que le fichier existe dans src/view/");
                return;
            }
            
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            
            Stage loginStage = (Stage) loginButton.getScene().getWindow();
            Scene patientScene = new Scene(root);
            loginStage.setScene(patientScene);
            loginStage.setTitle("Theraply — Gestion des Patients");
            loginStage.centerOnScreen();
            
        } catch (IOException e) {
            System.err.println("❌ Erreur lors du chargement de PatientListView.fxml : " + e.getMessage());
            e.printStackTrace();  // 🔍 Afficher la stack trace complète
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de navigation");
            alert.setHeaderText("Impossible d'ouvrir la liste des patients");
            alert.setContentText("Erreur : " + e.getMessage());
            alert.showAndWait();
        }
    } else {
        errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect.");
        errorLabel.setVisible(true);
    }
}


    @FXML
    public void handleForgotPassword(ActionEvent event) {
        System.out.println("Mot de passe oublié cliqué !");
    }
}