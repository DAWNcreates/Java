package clinique.controller;

import clinique.model.Personnes.Patient;
import clinique.model.Sessions.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Tooltip;

public class PatientListController {

    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient, Integer> idColumn;
    @FXML private TableColumn<Patient, String> nomColumn;
    @FXML private TableColumn<Patient, String> prenomColumn;
    @FXML private TableColumn<Patient, Integer> ageColumn;
    @FXML private TableColumn<Patient, String> genreColumn;
    @FXML private TableColumn<Patient, String> emailColumn;
    @FXML private TableColumn<Patient, Integer> telephoneColumn;
    @FXML private TableColumn<Patient, String> statutColumn;
    @FXML private TableColumn<Patient, Void> actionsColumn;
    @FXML private Label totalPatientsLabel;
    @FXML private Label newPatientsLabel;
    @FXML private Label activeSessionsLabel;
    @FXML private Button addButton;
    @FXML private TextField searchField;

    private final ObservableList<Patient> patientData = FXCollections.observableArrayList();
    private final ObservableList<Patient> allPatients = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Liaison des colonnes
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        statutColumn.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        // Configuration de la colonne Actions avec boutons Edit/Delete
        setupActionsColumn();
        
        // Charger les patients
        chargerPatients();
        
        // Mettre à jour les stats
        updateStats();
        
        // Recherche en temps réel
        setupSearch();
    }
    
    /**
     * Configure la colonne Actions avec les boutons Edit, Delete et Dossier
     */
    private void setupActionsColumn() {
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button dossierBtn = new Button("📋");
            private final Button editBtn = new Button("✏️");
            private final Button deleteBtn = new Button("🗑️");
            private final HBox pane = new HBox(6);

            {
                // Appliquer les classes CSS
                dossierBtn.getStyleClass().add("action-dossier-btn");
                editBtn.getStyleClass().add("action-edit-btn");
                deleteBtn.getStyleClass().add("action-delete-btn");
                
                // Tooltips
                dossierBtn.setTooltip(new Tooltip("Voir le dossier"));
                editBtn.setTooltip(new Tooltip("Modifier"));
                deleteBtn.setTooltip(new Tooltip("Supprimer"));
                
                // Action du bouton Dossier
                dossierBtn.setOnAction(event -> {
                    Patient patient = getTableView().getItems().get(getIndex());
                    openPatientDossier(patient);
                });
                
                // Action du bouton Edit
                editBtn.setOnAction(event -> {
                    Patient patient = getTableView().getItems().get(getIndex());
                    openEditPatientDialog(patient);
                });
                
                // Action du bouton Delete
                deleteBtn.setOnAction(event -> {
                    Patient patient = getTableView().getItems().get(getIndex());
                    deletePatient(patient);
                });
                
                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(dossierBtn, editBtn, deleteBtn);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });
    }
    
    /**
     * Configure la recherche en temps réel
     */
    private void setupSearch() {
        if (searchField != null) {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterPatients(newValue);
            });
        }
    }
    
    /**
     * Filtre les patients selon la recherche
     */
    private void filterPatients(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            patientTable.setItems(allPatients);
            return;
        }
        
        String lowerCaseFilter = searchText.toLowerCase();
        ObservableList<Patient> filteredData = FXCollections.observableArrayList();
        
        for (Patient patient : allPatients) {
            if (patient.getNom().toLowerCase().contains(lowerCaseFilter) ||
                patient.getPrenom().toLowerCase().contains(lowerCaseFilter) ||
                patient.getEmail().toLowerCase().contains(lowerCaseFilter) ||
                String.valueOf(patient.getId()).contains(lowerCaseFilter)) {
                filteredData.add(patient);
            }
        }
        
        patientTable.setItems(filteredData);
    }
    
    /**
     * Charge les données des patients (données de test)
     */
    private void chargerPatients() {
        if (patientData.isEmpty()) {
            patientData.add(new Patient(1, "Jean", "Dupont", "jean@email.com", 12345678,
                    LocalDateTime.now(), List.of("Anxiété"), new ArrayList<Session>(),
                    "Email", 5, 32, "Homme"));
            
            patientData.add(new Patient(2, "Marie", "Dubois", "marie@email.com", 98765432,
                    LocalDateTime.now(), List.of("Dépression"), new ArrayList<Session>(),
                    "Téléphone", 4, 28, "Femme"));
            
            patientData.add(new Patient(3, "Pierre", "Martin", "pierre@email.com", 55555555,
                    LocalDateTime.now(), List.of("Stress"), new ArrayList<Session>(),
                    "Email", 3, 45, "Homme"));
        }
        allPatients.setAll(patientData);
        patientTable.setItems(patientData);
    }
    
    /**
     * Met à jour les statistiques affichées
     */
    private void updateStats() {
        if (totalPatientsLabel != null) {
            totalPatientsLabel.setText(String.valueOf(allPatients.size()));
        }
        if (newPatientsLabel != null) {
            newPatientsLabel.setText("0");
        }
        if (activeSessionsLabel != null) {
            activeSessionsLabel.setText("0");
        }
    }
    
    /**
     * Ouvre le dialogue d'ajout de patient
     */
    @FXML
    public void handleAddPatient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPatientView.fxml"));
            Parent root = loader.load();
            
            AddPatientController controller = loader.getController();
            
            // Créer une fenêtre modale
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter un Patient");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(patientTable.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);
            
            // Attendre la fermeture du dialogue
            dialogStage.showAndWait();
            
            // Récupérer le patient créé et l'ajouter à la liste
            Patient newPatient = controller.getCreatedPatient();
            if (newPatient != null) {
                patientData.add(newPatient);
                allPatients.add(newPatient);
                updateStats();
                showAlert("Succès", "Patient ajouté", 
                    "Le patient " + newPatient.getNom() + " " + newPatient.getPrenom() + " a été ajouté avec succès.");
            }
            
        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir le dialogue", e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Ouvre le dialogue d'édition de patient
     */
    private void openEditPatientDialog(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditPatientView.fxml"));
            Parent root = loader.load();
            
            // Récupérer le contrôleur et passer le patient
            EditPatientController controller = loader.getController();
            controller.setPatient(patient);
            
            // Créer une fenêtre modale
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier Patient - " + patient.getNom() + " " + patient.getPrenom());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(patientTable.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);
            
            // Attendre la fermeture du dialogue
            dialogStage.showAndWait();
            
            // Rafraîchir la table après modification
            patientTable.refresh();
            updateStats();
            
        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir le dialogue", e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Supprime un patient après confirmation
     */
    private void deletePatient(Patient patient) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setHeaderText("Supprimer le patient ?");
        confirmation.setContentText(
            "Êtes-vous sûr de vouloir supprimer :\n" +
            patient.getNom() + " " + patient.getPrenom() + " (ID: " + patient.getId() + ") ?\n\n" +
            "Cette action est irréversible."
        );
        
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            patientData.remove(patient);
            allPatients.remove(patient);
            updateStats();
            showAlert("Succès", "Patient supprimé", 
                "Le patient " + patient.getNom() + " " + patient.getPrenom() + " a été supprimé avec succès.");
        }
    }
    
    /**
     * Gère le double-clic sur un patient pour ouvrir son dossier
     */
    @FXML
    public void handlePatientDoubleClick(javafx.scene.input.MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
            if (selectedPatient != null) {
                openPatientDossier(selectedPatient);
            }
        }
    }
    
    /**
     * Ouvre le dossier complet du patient
     */
    private void openPatientDossier(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PatientDossierView.fxml"));
            Parent root = loader.load();
            
            Stage currentStage = (Stage) patientTable.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Theraply - Dossier de " + patient.getNom() + " " + patient.getPrenom());
            
        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir le dossier", e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Déconnexion
     */
    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
            Parent root = loader.load();
            
            Stage currentStage = (Stage) patientTable.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Theraply App - Connexion");
            
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de se déconnecter", e.getMessage());
        }
    }
    
    /**
     * Affiche une boîte de dialogue d'alerte
     */
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

