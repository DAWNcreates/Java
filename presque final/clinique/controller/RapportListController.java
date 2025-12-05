package clinique.controller;

import clinique.model.Personnes.Patient;
import clinique.model.Personnes.Therapeute;
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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Contrôleur pour la liste des rapports thérapeutiques
 */
public class RapportListController {

    @FXML private TableView<Rapport> rapportTable;
    @FXML private TableColumn<Rapport, Integer> idColumn;
    @FXML private TableColumn<Rapport, String> dateColumn;
    @FXML private TableColumn<Rapport, String> sessionColumn;
    @FXML private TableColumn<Rapport, String> patientColumn;
    @FXML private TableColumn<Rapport, String> therapeuteColumn;
    @FXML private TableColumn<Rapport, String> typeColumn;
    @FXML private TableColumn<Rapport, String> resumeColumn;
    @FXML private TableColumn<Rapport, Void> actionColumn;

    @FXML private TextField searchField;
    @FXML private DatePicker dateDebutFilter;
    @FXML private DatePicker dateFinFilter;
    @FXML private ComboBox<String> patientFilter;

    @FXML private Label totalRapportsLabel;
    @FXML private Label moisLabel;
    @FXML private Label urgentLabel;
    @FXML private Label efficaciteLabel;

    @FXML private Button viewButton;

    private final ObservableList<Rapport> rapportData = FXCollections.observableArrayList();
    private final ObservableList<Rapport> allRapports = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        setupActionsColumn();
        chargerRapports();
        updateStats();
        setupSearch();
        setupSelection();
    }

    /**
     * Configure les colonnes du tableau
     */
    private void setupTableColumns() {
        idColumn.setCellFactory(col -> new TableCell<Rapport, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Rapport rapport = getTableRow().getItem();
                    setText(String.valueOf(rapport.getId()));
                }
            }
        });

        dateColumn.setCellFactory(col -> new TableCell<Rapport, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Rapport rapport = getTableRow().getItem();
                    setText(rapport.getDateCreation());
                }
            }
        });

        sessionColumn.setCellFactory(col -> new TableCell<Rapport, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Rapport rapport = getTableRow().getItem();
                    setText("#S-" + rapport.getSessionId());
                }
            }
        });

        patientColumn.setCellFactory(col -> new TableCell<Rapport, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Rapport rapport = getTableRow().getItem();
                    setText(rapport.getPatientNom());
                }
            }
        });

        therapeuteColumn.setCellFactory(col -> new TableCell<Rapport, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Rapport rapport = getTableRow().getItem();
                    setText("Dr. " + rapport.getTherapeuteNom());
                }
            }
        });

        typeColumn.setCellFactory(col -> new TableCell<Rapport, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Rapport rapport = getTableRow().getItem();
                    setText(rapport.getTypeTherapie());
                }
            }
        });

        resumeColumn.setCellFactory(col -> new TableCell<Rapport, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Rapport rapport = getTableRow().getItem();
                    String resume = rapport.getResume();
                    // Tronquer si trop long
                    if (resume.length() > 60) {
                        resume = resume.substring(0, 57) + "...";
                    }
                    setText(resume);
                }
            }
        });
    }

    /**
     * Configure la colonne Actions avec boutons
     */
    private void setupActionsColumn() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button viewBtn = new Button("📄");
            private final Button pdfBtn = new Button("Pdf");
            private final HBox pane = new HBox(6);

            {
                viewBtn.getStyleClass().add("action-dossier-btn");
                pdfBtn.getStyleClass().add("action-edit-btn");

                viewBtn.setTooltip(new Tooltip("Voir détails"));
                pdfBtn.setTooltip(new Tooltip("Exporter PDF"));

                viewBtn.setOnAction(event -> {
                    Rapport rapport = getTableView().getItems().get(getIndex());
                    openRapportDetails(rapport);
                });

                pdfBtn.setOnAction(event -> {
                    Rapport rapport = getTableView().getItems().get(getIndex());
                    exportPDF(rapport);
                });

                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(viewBtn, pdfBtn);
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
                filterRapports(newValue);
            });
        }
    }

    /**
     * Configure la sélection dans le tableau
     */
    private void setupSelection() {
        rapportTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            viewButton.setDisable(newSelection == null);
        });
    }

    /**
     * Filtre les rapports selon la recherche
     */
    private void filterRapports(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            rapportTable.setItems(allRapports);
            return;
        }

        String lowerCaseFilter = searchText.toLowerCase();
        ObservableList<Rapport> filteredData = FXCollections.observableArrayList();

        for (Rapport rapport : allRapports) {
            if (rapport.getPatientNom().toLowerCase().contains(lowerCaseFilter) ||
                rapport.getTherapeuteNom().toLowerCase().contains(lowerCaseFilter) ||
                rapport.getTypeTherapie().toLowerCase().contains(lowerCaseFilter) ||
                String.valueOf(rapport.getId()).contains(lowerCaseFilter)) {
                filteredData.add(rapport);
            }
        }

        rapportTable.setItems(filteredData);
    }

    /**
     * Charge les données de test
     */
    private void chargerRapports() {
        if (rapportData.isEmpty()) {
            rapportData.add(new Rapport(1, "12/12/2024", 248, "Marie Dubois", "Martin", "TCC", 
                "Séance focalisée sur la gestion de l'anxiété avec techniques de respiration"));
            rapportData.add(new Rapport(2, "10/12/2024", 247, "Jean Dupont", "Sophie", "Psychanalyse", 
                "Exploration des rêves récurrents et analyse des symboles"));
            rapportData.add(new Rapport(3, "08/12/2024", 246, "Pierre Martin", "Ahmed", "Humaniste", 
                "Travail sur l'estime de soi et l'affirmation personnelle"));
        }

        allRapports.setAll(rapportData);
        rapportTable.setItems(rapportData);
    }

    /**
     * Met à jour les statistiques
     */
    private void updateStats() {
        if (totalRapportsLabel != null) {
            totalRapportsLabel.setText(String.valueOf(allRapports.size()));
        }
        if (moisLabel != null) {
            moisLabel.setText("3");
        }
        if (urgentLabel != null) {
            urgentLabel.setText("0");
        }
        if (efficaciteLabel != null) {
            efficaciteLabel.setText("4.2/5");
        }
    }

    /**
     * Ouvre les détails d'un rapport
     */
    private void openRapportDetails(Rapport rapport) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RapportDetailView.fxml"));
            Parent root = loader.load();

            RapportDetailController controller = loader.getController();
            controller.setRapport(rapport);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Détails du Rapport #" + rapport.getId());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(rapportTable.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);
            dialogStage.showAndWait();

            rapportTable.refresh();
            updateStats();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir les détails", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exporte un rapport en PDF
     */
    private void exportPDF(Rapport rapport) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export PDF");
        alert.setHeaderText("Fonctionnalité à venir");
        alert.setContentText("L'export PDF du rapport #" + rapport.getId() + " sera disponible prochainement.");
        alert.showAndWait();
    }

    /**
     * Double-clic sur un rapport pour ouvrir les détails
     */
    @FXML
    public void handleRapportDoubleClick(javafx.scene.input.MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            Rapport selectedRapport = rapportTable.getSelectionModel().getSelectedItem();
            if (selectedRapport != null) {
                openRapportDetails(selectedRapport);
            }
        }
    }

    /**
     * Crée un nouveau rapport
     */
    @FXML
    public void handleAddRapport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateRapportView.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouveau Rapport");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(rapportTable.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);
            dialogStage.showAndWait();

            // Recharger après création
            updateStats();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir le formulaire", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Bouton Voir détails
     */
    @FXML
    public void handleViewDetails(ActionEvent event) {
        Rapport selectedRapport = rapportTable.getSelectionModel().getSelectedItem();
        if (selectedRapport != null) {
            openRapportDetails(selectedRapport);
        }
    }

    /**
     * Affiche une alerte
     */
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Classe interne Rapport (en attendant le modèle complet)
     */
    public static class Rapport {
        private int id;
        private String dateCreation;
        private int sessionId;
        private String patientNom;
        private String therapeuteNom;
        private String typeTherapie;
        private String resume;

        public Rapport(int id, String dateCreation, int sessionId, String patientNom, 
                      String therapeuteNom, String typeTherapie, String resume) {
            this.id = id;
            this.dateCreation = dateCreation;
            this.sessionId = sessionId;
            this.patientNom = patientNom;
            this.therapeuteNom = therapeuteNom;
            this.typeTherapie = typeTherapie;
            this.resume = resume;
        }

        // Getters
        public int getId() { return id; }
        public String getDateCreation() { return dateCreation; }
        public int getSessionId() { return sessionId; }
        public String getPatientNom() { return patientNom; }
        public String getTherapeuteNom() { return therapeuteNom; }
        public String getTypeTherapie() { return typeTherapie; }
        public String getResume() { return resume; }
    }
}