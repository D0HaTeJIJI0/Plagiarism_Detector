package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class About {

    @FXML
    private TextArea textArea;

    private final static String TEXT = "Author: Travnichev\nIvanVersion: 1.0";

    public About() {

        textArea = new TextArea();
        textArea.setText(TEXT);
        textArea.setEditable(false);

    }

}

