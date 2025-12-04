package validation2.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

public class DashboardController {

    @FXML
    private Button gestionPatientsBtn;

    @FXML
    private Button gestionTherapeutesBtn;

    @FXML
    private Button gestionSessionsBtn;

    @FXML
    private Button gestionRapportsBtn;

    @FXML
    private Button gestionPaiementsBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private VBox centerContent;

    @FXML
    public void initialize() {
        gestionPatientsBtn.setOnAction(this::handleGestionPatients);
        gestionTherapeutesBtn.setOnAction(this::handleGestionTherapeutes);
        gestionSessionsBtn.setOnAction(this::handleGestionSessions);
        gestionRapportsBtn.setOnAction(this::handleGestionRapports);
        gestionPaiementsBtn.setOnAction(this::handleGestionPaiements);
        dashboardBtn.setOnAction(this::handleDashboard);
        logoutBtn.setOnAction(this::handleLogout);
    }

    @FXML
    private void handleGestionPatients(ActionEvent event) {
        loadPage("EditPatientView.fxml");
    }

    @FXML
    private void handleGestionTherapeutes(ActionEvent event) {
        loadPage("AddTherapist.fxml");
    }

    @FXML
    private void handleGestionSessions(ActionEvent event) {
        loadPage("GestionSessions.fxml");
    }

    @FXML
    private void handleGestionRapports(ActionEvent event) {
        loadPage("GestionRapports.fxml");
    }

    @FXML
    private void handleGestionPaiements(ActionEvent event) {
        loadPage("PaymentProcessingView.fxml");
    }

    @FXML
    private void handleDashboard(ActionEvent event) {
        loadPage("dashboard.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        loadPage("Login.fxml");
    }

    private void loadPage(String fxmlFile) {
        try {
            System.out.println("Attempting to load: " + fxmlFile);
            
            URL fxmlUrl = null;
            
            fxmlUrl = getClass().getResource(fxmlFile);
            
            if (fxmlUrl == null) {
                fxmlUrl = getClass().getResource("/validation2/View/" + fxmlFile);
            }
            
            if (fxmlUrl == null) {
                fxmlUrl = getClass().getResource("/" + fxmlFile);
            }
            
            if (fxmlUrl == null) {
                fxmlUrl = getClass().getResource("../View/" + fxmlFile);
            }
            
            if (fxmlUrl == null) {
                System.err.println("ERROR: Could not find " + fxmlFile);
                System.err.println("Tried locations:");
                System.err.println("  - Same package: " + getClass().getResource(""));
                System.err.println("  - /validation2/View/" + fxmlFile);
                System.err.println("  - /" + fxmlFile);
                System.err.println("  - ../View/" + fxmlFile);
                showErrorDialog("Fichier introuvable", 
                    "Le fichier " + fxmlFile + " n'existe pas.\n" +
                    "Veuillez créer ce fichier dans le dossier View.");
                return;
            }
            
            System.out.println("Found FXML at: " + fxmlUrl);
            
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            
            Stage stage = (Stage) gestionPatientsBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Theraply - " + fxmlFile.replace(".fxml", ""));
            stage.show();
            
            System.out.println("Successfully loaded: " + fxmlFile);
            
        } catch (IOException e) {
            System.err.println("Error loading page " + fxmlFile + ": " + e.getMessage());
            e.printStackTrace();
            showErrorDialog("Erreur de chargement", 
                "Impossible de charger la page: " + fxmlFile + "\n" +
                "Erreur: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            showErrorDialog("Erreur", "Une erreur inattendue s'est produite: " + e.getMessage());
        }
    }

    private void showErrorDialog(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}