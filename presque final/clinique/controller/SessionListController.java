package clinique.controller;

import clinique.model.Personnes.Patient;
import clinique.model.Personnes.Therapeute;
import clinique.model.Sessions.Session;
import clinique.model.Sessions.SessionChat;
import clinique.model.Sessions.SessionVideo;
import clinique.model.Sessions.SessionIA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class SessionListController {
    @FXML private TableView<Session> sessionTable;
    @FXML private TableColumn<Session, Integer> idColumn;
    @FXML private TableColumn<Session, String> dateColumn;
    @FXML private TableColumn<Session, String> heureColumn;
    @FXML private TableColumn<Session, String> patientColumn;
    @FXML private TableColumn<Session, String> therapeuteColumn;
    @FXML private TableColumn<Session, String> typeColumn;
    @FXML private TableColumn<Session, String> statutColumn;
    @FXML private TableColumn<Session, Void> actionColumn;
    @FXML private TextField searchField;
    @FXML private DatePicker dateDebutFilter;
    @FXML private Label totalSessionsLabel;
    @FXML private Label monthSessionsLabel;
    @FXML private Label activeSessionsLabel;
    @FXML private Label completedSessionsLabel;

    private final ObservableList<Session> sessionData = FXCollections.observableArrayList();
    private final ObservableList<Session> allSessions = FXCollections.observableArrayList();

    // Données de test - Patients variés
    private Patient patient1 = new Patient(1, "Benali", "Ahmed", "ahmed.benali@email.com", 98123456,
            LocalDateTime.now(), new ArrayList<>(), new ArrayList<>(), "Email", 4, 35, "Homme");
    private Patient patient2 = new Patient(2, "Trabelsi", "Leila", "leila.trabelsi@email.com", 22345678,
            LocalDateTime.now(), new ArrayList<>(), new ArrayList<>(), "Téléphone", 5, 28, "Femme");
    private Patient patient3 = new Patient(3, "Mansour", "Karim", "karim.mansour@email.com", 55678901,
            LocalDateTime.now(), new ArrayList<>(), new ArrayList<>(), "Email", 3, 42, "Homme");

    // Données de test - Thérapeutes variés
    private Therapeute therapeute1 = new Therapeute(1, "Martin", "Sophie", "sophie.martin@theraply.tn", 71234567,
            LocalDateTime.now(), "http://licence.tn/001", new ArrayList<>(), "Actif", 85.0);
    private Therapeute therapeute2 = new Therapeute(2, "Benali", "Ahmed", "ahmed.benali@theraply.tn", 71345678,
            LocalDateTime.now(), "http://licence.tn/002", new ArrayList<>(), "Actif", 90.0);
    private Therapeute therapeute3 = new Therapeute(3, "Leroy", "Claire", "claire.leroy@theraply.tn", 71456789,
            LocalDateTime.now(), "http://licence.tn/003", new ArrayList<>(), "Actif", 80.0);

    @FXML
    public void initialize() {
        setupTableColumns();
        setupActionsColumn();
        chargerSessions();
        updateStats();
        setupSearch();
    }

    private void setupTableColumns() {
        idColumn.setCellFactory(col -> new TableCell<Session, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Session session = getTableRow().getItem();
                    setText(String.valueOf(session.getId()));
                }
            }
        });

        dateColumn.setCellFactory(col -> new TableCell<Session, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Session session = getTableRow().getItem();
                    // Calcul de la date basé sur l'ID de session pour varier
                    int daysOffset = (session.getId() - 1) * -1; // -0, -1, -2 jours
                    java.time.LocalDate date = java.time.LocalDate.now().plusDays(daysOffset);
                    setText(String.format("%02d/%02d/%d", 
                        date.getDayOfMonth(), 
                        date.getMonthValue(), 
                        date.getYear()));
                }
            }
        });

        heureColumn.setCellFactory(col -> new TableCell<Session, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Session session = getTableRow().getItem();
                    // Heures variées selon l'ID
                    String[] heures = {"09:00", "14:30", "16:45", "10:15", "11:30"};
                    int index = (session.getId() - 1) % heures.length;
                    setText(heures[index]);
                }
            }
        });

        patientColumn.setCellFactory(col -> new TableCell<Session, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Session session = getTableRow().getItem();
                    setText(session.getPatient().getNom() + " " + session.getPatient().getPrenom());
                }
            }
        });

        therapeuteColumn.setCellFactory(col -> new TableCell<Session, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Session session = getTableRow().getItem();
                    setText("Dr. " + session.getTherapeute().getNom());
                }
            }
        });

        typeColumn.setCellFactory(col -> new TableCell<Session, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Session session = getTableRow().getItem();
                    if (session instanceof SessionVideo) {
                        setText("📹 Vidéo");
                    } else if (session instanceof SessionChat) {
                        setText("💬 Chat");
                    } else if (session instanceof SessionIA) {
                        setText("🤖 IA");
                    }
                }
            }
        });

        statutColumn.setCellFactory(col -> new TableCell<Session, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText("Planifiée");
                    setStyle("-fx-text-fill: #9D71E8; -fx-font-weight: bold;");
                }
            }
        });
    }

    private void setupActionsColumn() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Label detailsIcon = new Label("👁");
            private final Label deleteIcon = new Label("🗑");
            private final Button detailsBtn = new Button();
            private final Button deleteBtn = new Button();
            private final HBox pane = new HBox(10);

            {
                // Configuration des icônes
                detailsIcon.setStyle("-fx-font-size: 22px;");
                deleteIcon.setStyle("-fx-font-size: 22px;");
                
                // Configuration des boutons
                detailsBtn.setGraphic(detailsIcon);
                deleteBtn.setGraphic(deleteIcon);
                
                detailsBtn.getStyleClass().add("action-view-btn");
                deleteBtn.getStyleClass().add("action-delete-btn");
                
                // Taille fixe
                detailsBtn.setMinSize(48, 48);
                detailsBtn.setPrefSize(48, 48);
                detailsBtn.setMaxSize(48, 48);
                
                deleteBtn.setMinSize(48, 48);
                deleteBtn.setPrefSize(48, 48);
                deleteBtn.setMaxSize(48, 48);
                
                detailsBtn.setTooltip(new Tooltip("Voir détails"));
                deleteBtn.setTooltip(new Tooltip("Supprimer"));

                detailsBtn.setOnAction(event -> {
                    Session session = getTableView().getItems().get(getIndex());
                    openSessionDetails(session);
                });

                deleteBtn.setOnAction(event -> {
                    Session session = getTableView().getItems().get(getIndex());
                    deleteSession(session);
                });

                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(detailsBtn, deleteBtn);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });
    }

    private void setupSearch() {
        if (searchField != null) {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterSessions(newValue);
            });
        }
    }

    private void filterSessions(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            sessionTable.setItems(allSessions);
            return;
        }

        String lowerCaseFilter = searchText.toLowerCase();
        ObservableList<Session> filteredData = FXCollections.observableArrayList();

        for (Session session : allSessions) {
            if (session.getPatient().getNom().toLowerCase().contains(lowerCaseFilter) ||
                session.getPatient().getPrenom().toLowerCase().contains(lowerCaseFilter) ||
                session.getTherapeute().getNom().toLowerCase().contains(lowerCaseFilter) ||
                String.valueOf(session.getId()).contains(lowerCaseFilter)) {
                filteredData.add(session);
            }
        }

        sessionTable.setItems(filteredData);
    }

    private void chargerSessions() {
        if (sessionData.isEmpty()) {
            int now = (int) (System.currentTimeMillis() / 1000);
            
            // Sessions avec des dates et heures VARIÉES
            // Session 1 : 10/12/2024 à 09:00
            int date1 = now - (2 * 24 * 3600) - (5 * 3600); // Il y a 2 jours à 9h
            sessionData.add(new SessionVideo(1, patient1, therapeute1, date1, 60, "http://video.com/rec1"));
            
            // Session 2 : 11/12/2024 à 14:30
            int date2 = now - (1 * 24 * 3600) - (1800); // Il y a 1 jour à 14h30
            sessionData.add(new SessionChat(2, patient2, therapeute2, date2, 45, "transcript_001"));
            
            // Session 3 : 12/12/2024 à 16:45
            int date3 = now + (2 * 3600) + (2700); // Aujourd'hui à 16h45
            sessionData.add(new SessionIA(3, patient3, therapeute3, date3, 30, "Faible", "Résumé IA"));
        }

        allSessions.setAll(sessionData);
        sessionTable.setItems(sessionData);
    }

    private void updateStats() {
        if (totalSessionsLabel != null) {
            totalSessionsLabel.setText(String.valueOf(allSessions.size()));
        }
        if (monthSessionsLabel != null) {
            monthSessionsLabel.setText("0");
        }
        if (activeSessionsLabel != null) {
            activeSessionsLabel.setText("0");
        }
        if (completedSessionsLabel != null) {
            completedSessionsLabel.setText("0");
        }
    }

    @FXML
    public void handleAddSession(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateSessionView.fxml"));
            Parent root = loader.load();
            
            CreateSessionController controller = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Créer une Session");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(sessionTable.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);

            dialogStage.showAndWait();
            
            // Récupérer la session créée et l'ajouter à la liste
            Session newSession = controller.getCreatedSession();
            if (newSession != null) {
                sessionData.add(newSession);
                allSessions.add(newSession);
                updateStats();
                showAlert("Succès", "Session créée", 
                    "La session #" + newSession.getId() + " a été créée avec succès.");
            }

        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir le dialogue", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSessionDoubleClick(javafx.scene.input.MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            Session selectedSession = sessionTable.getSelectionModel().getSelectedItem();
            if (selectedSession != null) {
                openSessionDetails(selectedSession);
            }
        }
    }

    private void openSessionDetails(Session session) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SessionDetailsView.fxml"));
            Parent root = loader.load();

            SessionDetailsController controller = loader.getController();
            controller.setSession(session);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Détails Session #" + session.getId());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(sessionTable.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);

            dialogStage.showAndWait();

            sessionTable.refresh();
            updateStats();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir les détails", e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteSession(Session session) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setHeaderText("Supprimer la session ?");
        confirmation.setContentText(
            "Êtes-vous sûr de vouloir supprimer :\n" +
            "Session #" + session.getId() + " - " + session.getPatient().getNom() +
            "\n\nCette action est irréversible."
        );

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            sessionData.remove(session);
            allSessions.remove(session);
            updateStats();
            showAlert("Succès", "Session supprimée", 
                "La session a été supprimée avec succès.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}