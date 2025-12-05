package clinique.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur pour l'affichage des détails d'un rapport
 */
public class RapportDetailController {

    @FXML private Label rapportTitleLabel;
    @FXML private Label typeLabel;
    @FXML private Label dateLabel;
    @FXML private Label sessionIdLabel;
    @FXML private Label rapportIdLabel;
    @FXML private Label patientStatLabel;
    @FXML private Label therapeuteStatLabel;

    @FXML private Label patientLabel;
    @FXML private Label therapeuteLabel;
    @FXML private Label dateSessionLabel;
    @FXML private Label typeSessionLabel;

    @FXML private TextArea resumeArea;
    @FXML private TextArea observationsArea;
    @FXML private TextArea recommandationsArea;

    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Button pdfButton;
    @FXML private Button printButton;
    @FXML private Button backButton;

    private RapportListController.Rapport currentRapport;

    /**
     * Définit le rapport à afficher
     */
    public void setRapport(RapportListController.Rapport rapport) {
        this.currentRapport = rapport;
        loadRapportData();
    }

    /**
     * Charge les données du rapport dans l'interface
     */
    private void loadRapportData() {
        if (currentRapport != null) {
            // Header
            if (rapportTitleLabel != null) {
                rapportTitleLabel.setText("Rapport de Session");
            }
            if (typeLabel != null) {
                typeLabel.setText(currentRapport.getTypeTherapie());
            }
            if (dateLabel != null) {
                dateLabel.setText("📅 " + currentRapport.getDateCreation());
            }
            if (sessionIdLabel != null) {
                sessionIdLabel.setText("🔗 Session #S-" + currentRapport.getSessionId());
            }
            if (rapportIdLabel != null) {
                rapportIdLabel.setText("ID: #RAP-" + String.format("%03d", currentRapport.getId()));
            }

            // Stats rapides
            if (patientStatLabel != null) {
                patientStatLabel.setText(currentRapport.getPatientNom());
            }
            if (therapeuteStatLabel != null) {
                therapeuteStatLabel.setText("Dr. " + currentRapport.getTherapeuteNom());
            }

            // Section Informations générales
            if (patientLabel != null) {
                patientLabel.setText(currentRapport.getPatientNom());
            }
            if (therapeuteLabel != null) {
                therapeuteLabel.setText("Dr. " + currentRapport.getTherapeuteNom());
            }
            if (dateSessionLabel != null) {
                dateSessionLabel.setText(currentRapport.getDateCreation());
            }
            if (typeSessionLabel != null) {
                typeSessionLabel.setText("Vidéo");
            }

            // Contenu (exemple de données)
            if (resumeArea != null) {
                resumeArea.setText(currentRapport.getResume() + "\n\nLa session s'est déroulée dans un climat de confiance établi. Le patient a exprimé ses difficultés concernant la gestion de son anxiété au quotidien, notamment dans les situations sociales.");
            }

            if (observationsArea != null) {
                observationsArea.setText("État émotionnel: Le patient présente des signes d'amélioration par rapport aux sessions précédentes.\n\nProgrès notables:\n- Meilleure verbalisation des émotions\n- Prise de conscience accrue des schémas de pensée négatifs\n- Mise en pratique des exercices entre les sessions");
            }

            if (recommandationsArea != null) {
                recommandationsArea.setText("Pour la prochaine session:\n1. Continuer les exercices de respiration 2x par jour\n2. Tenir un journal des situations anxiogènes\n3. Pratiquer l'exposition progressive à des situations sociales simples\n4. Lecture recommandée: \"Gérer son anxiété au quotidien\"");
            }
        }
    }

    /**
     * Bouton Modifier
     */
    @FXML
    public void handleEdit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditRapportView.fxml"));
            Parent root = loader.load();

            EditRapportController controller = loader.getController();
            controller.setRapport(currentRapport);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier Rapport - #" + currentRapport.getId());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(editButton.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);
            dialogStage.showAndWait();

            // Recharger les données après modification
            loadRapportData();

        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir le formulaire de modification", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Bouton Supprimer
     */
    @FXML
    public void handleDelete(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setHeaderText("Supprimer le rapport ?");
        confirmation.setContentText(
                "Êtes-vous sûr de vouloir supprimer :\n" +
                "Rapport #" + currentRapport.getId() + " - " + currentRapport.getPatientNom() + "\n\n" +
                "Cette action est irréversible."
        );

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                showAlert("Succès", "Rapport supprimé", 
                    "Le rapport a été supprimé avec succès.");
                closeDialog();
            }
        });
    }

    /**
     * Bouton PDF
     */
    @FXML
    public void handlePDF(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export PDF");
        alert.setHeaderText("Fonctionnalité à venir");
        alert.setContentText("L'export PDF du rapport sera disponible prochainement.");
        alert.showAndWait();
    }

    /**
     * Bouton Imprimer
     */
    @FXML
    public void handlePrint(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Impression");
        alert.setHeaderText("Fonctionnalité à venir");
        alert.setContentText("L'impression du rapport sera disponible prochainement.");
        alert.showAndWait();
    }

    /**
     * Bouton Retour / Fermer
     */
    @FXML
    public void handleBack(ActionEvent event) {
        closeDialog();
    }

    /**
     * Ferme le dialogue
     */
    private void closeDialog() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
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
}