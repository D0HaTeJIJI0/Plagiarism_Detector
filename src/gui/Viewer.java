package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Viewer extends Application {

    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{

        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Plagiarism Detector");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

    }
}
