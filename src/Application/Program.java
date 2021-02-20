/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author W-E-R
 */
public class Program extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Program.stage = stage;
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/FXMLMain.fxml"));
            ScrollPane scrollPane = loader.load();
            Scene mainScene = new Scene(scrollPane);
            
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Sample JavaFX application");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
