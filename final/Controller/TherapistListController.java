package Controller;

import Model.personnes.Therapist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TherapistListController implements Initializable {

    @FXML private TableView<Therapist> patientTable;
    @FXML private TableColumn<Therapist, Integer> colId;
    @FXML private TableColumn<Therapist, String> colName;
    @FXML private TableColumn<Therapist, Integer> colAge;
    @FXML private TableColumn<Therapist, String> colPhone;
    @FXML private TableColumn<Therapist, String> colEmail;
    @FXML private TableColumn<Therapist, String> colStatus;
    @FXML private Button addPatientBtn;
    @FXML private Button editPatientBtn;
    @FXML private Button deletePatientBtn;
    @FXML private Button refreshBtn;

    private ObservableList<Therapist> therapistList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("\n========================================");
        System.out.println("INITIALIZE METHOD CALLED!");
        System.out.println("========================================\n");
        
        // Check nulls
        if (patientTable == null) {
            System.err.println("❌ ERROR: patientTable is NULL!");
            showError("Erreur critique", "La TableView n'est pas injectée!");
            return;
        }
        
        if (colId == null || colName == null) {
            System.err.println("❌ ERROR: Table columns are NULL!");
            showError("Erreur critique", "Les colonnes ne sont pas injectées!");
            return;
        }
        
        System.out.println("✅ All FXML elements injected successfully");
        
        // Configure columns
        try {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            System.out.println("✅ Columns configured");
        } catch (Exception e) {
            System.err.println("❌ Error configuring columns: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Load data
        try {
            loadTherapists();
            System.out.println("✅ Data loaded: " + therapistList.size() + " therapists");
            System.out.println("✅ Table now has: " + patientTable.getItems().size() + " items");
            
            // Print first therapist to verify data
            if (!therapistList.isEmpty()) {
                Therapist first = therapistList.get(0);
                System.out.println("First therapist: " + first);
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error loading data: " + e.getMessage());
            e.printStackTrace();
            showError("Erreur", "Impossible de charger les données: " + e.getMessage());
            return;
        }

        // Setup buttons
        try {
            if (addPatientBtn != null) {
                addPatientBtn.setOnAction(e -> handleAdd());
                editPatientBtn.setOnAction(e -> handleEdit());
                deletePatientBtn.setOnAction(e -> handleDelete());
                refreshBtn.setOnAction(e -> handleRefresh());
                System.out.println("✅ Buttons configured");
            } else {
                System.err.println("⚠️ Warning: Buttons are null");
            }
        } catch (Exception e) {
            System.err.println("❌ Error configuring buttons: " + e.getMessage());
        }
        
        System.out.println("\n========================================");
        System.out.println("INITIALIZATION COMPLETE!");
        System.out.println("========================================\n");
    }

    private void loadTherapists() {
        // Check if there are therapists in the shared list from AddTherapistController
        java.util.List<Therapist> sharedList = AddTherapistController.getSharedTherapistList();
        
        if (sharedList.isEmpty()) {
            // Load default therapists if shared list is empty
            therapistList = FXCollections.observableArrayList(
                new Therapist(1, "Dr. Ahmed Ben Ali", 45, "98765432", "ahmed.benali@theraply.tn", "Actif"),
                new Therapist(2, "Dr. Fatma Trabelsi", 38, "97654321", "fatma.trabelsi@theraply.tn", "Actif"),
                new Therapist(3, "Dr. Mohamed Karray", 52, "96543210", "mohamed.karray@theraply.tn", "Actif"),
                new Therapist(4, "Dr. Salma Jlassi", 41, "95432109", "salma.jlassi@theraply.tn", "Inactif"),
                new Therapist(5, "Dr. Karim Mansour", 36, "94321098", "karim.mansour@theraply.tn", "Actif"),
                new Therapist(6, "Dr. Leila Hamdi", 43, "93210987", "leila.hamdi@theraply.tn", "Actif"),
                new Therapist(7, "Dr. Youssef Touati", 39, "92109876", "youssef.touati@theraply.tn", "Actif"),
                new Therapist(8, "Dr. Amira Sfar", 47, "91098765", "amira.sfar@theraply.tn", "Inactif")
            );
            
            // Copy to shared list for persistence
            sharedList.addAll(therapistList);
        } else {
            // Use the shared list
            therapistList = FXCollections.observableArrayList(sharedList);
        }

        System.out.println("Created list with " + therapistList.size() + " items");
        patientTable.setItems(therapistList);
        System.out.println("Items set to table");
    }
    
    // Method to receive updated therapist list from AddTherapistController
    public void setTherapistList(java.util.List<Therapist> list) {
        System.out.println("Received therapist list with " + list.size() + " items");
        therapistList = FXCollections.observableArrayList(list);
        patientTable.setItems(therapistList);
        patientTable.refresh();
    }

    private void handleAdd() {
        try {
            System.out.println("Navigating to AddTherapist view...");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddTherapist.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) addPatientBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Theraply - Ajouter Thérapeute");
            stage.show();
            
            System.out.println("Successfully loaded AddTherapist view");
            
        } catch (IOException e) {
            System.err.println("Error loading AddTherapist view: " + e.getMessage());
            e.printStackTrace();
            showError("Erreur de navigation", 
                "Impossible de charger la page Ajouter Thérapeute.\n" +
                "Assurez-vous que AddTherapist.fxml existe dans le dossier View.\n" +
                "Erreur: " + e.getMessage());
        }
    }

    private void handleEdit() {
        Therapist selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showInfo("Modifier Thérapeute", "Édition de: " + selected.getName());
        } else {
            showWarning("Aucune sélection", "Veuillez sélectionner un thérapeute à modifier");
        }
    }

    private void handleDelete() {
        Therapist selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            therapistList.remove(selected);
            showInfo("Suppression", "Thérapeute supprimé: " + selected.getName());
        } else {
            showWarning("Aucune sélection", "Veuillez sélectionner un thérapeute à supprimer");
        }
    }

    private void handleRefresh() {
        loadTherapists();
        showInfo("Actualisation", "Liste des thérapeutes actualisée");
    }

    private void showInfo(String title, String message) {
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
}