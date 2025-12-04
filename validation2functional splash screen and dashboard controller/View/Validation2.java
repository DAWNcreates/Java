package validation2.View;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Validation2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
Parent root = FXMLLoader.load(getClass().getResource("/validation2/View/splashscreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(2));

        fadeIn.setOnFinished(e -> fadeOut.play());

        fadeOut.setOnFinished(e -> {
            try {
Parent mainRoot = FXMLLoader.load(getClass().getResource("/validation2/View/dashboard.fxml"));                stage.setScene(new Scene(mainRoot));
                stage.setResizable(true); 
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace(); 
                System.err.println("Failed to load dashboard.fxml");
            }
        });

        fadeIn.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}