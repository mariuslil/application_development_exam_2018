package no.ntnu.imt3281.weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Weather.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Weather Forecast");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main
     *
     * @param args cmd line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}