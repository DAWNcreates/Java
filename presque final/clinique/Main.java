package clinique;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import clinique.controller.LoginController;
import clinique.controller.AddPatientController;
import clinique.controller.PatientListController;
import clinique.controller.EditPatientController;
import clinique.controller.PatientDossierController;
import clinique.controller.SessionListController;
import clinique.controller.CreateSessionController;
import clinique.controller.SessionDetailsController;
import clinique.controller.CreateRapportController;
import clinique.controller.EditRapportController;
import clinique.controller.RapportDetailController;
import clinique.controller.RapportListController;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Le fichier FXML est dans le package "view" (pas "clinique.view")
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RapportListView.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Theraply App - Connexion");
            primaryStage.show();
            
        } catch (Exception e) {
            System.out.println("❌ ERREUR lors du chargement :");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}