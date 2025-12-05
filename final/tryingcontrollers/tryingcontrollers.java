package tryingcontrollers;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class tryingcontrollers extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // Try multiple paths to find splash screen
        Parent root = null;
        String[] paths = {"/View/splashscreen.fxml", "/view/splashscreen.fxml", "splashscreen.fxml"};
        
        for (String path : paths) {
            try {
                System.out.println("Trying path: " + path);
                root = FXMLLoader.load(getClass().getResource(path));
                System.out.println("SUCCESS! Found at: " + path);
                break;
            } catch (Exception e) {
                System.out.println("Failed: " + path);
            }
        }
        
        if (root == null) {
            System.err.println("ERROR: Could not find splashscreen.fxml in any location!");
            System.err.println("Class location: " + getClass().getResource(""));
            throw new Exception("splashscreen.fxml not found");
        }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        // Fade in animation
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        
        // Fade out animation
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(2));
        
        // Chain animations
        fadeIn.setOnFinished(e -> fadeOut.play());
        
        // Load dashboard after fade out
        fadeOut.setOnFinished(e -> {
            try {
                Parent mainRoot = FXMLLoader.load(getClass().getResource("/View/dashboard.fxml"));
                stage.setScene(new Scene(mainRoot));
                stage.setResizable(true);
                stage.show();
            } catch (IOException ex) {
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