package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lib.tokenizing.Normalizer;

public class Controller {

    @FXML
    private TextArea textArea1;
    @FXML
    private TextArea textArea2;

    public Controller() {

        textArea1 = new TextArea();
        textArea2 = new TextArea();

    }

    @FXML
    private void analyze(){

        String inputCode = textArea1.getText();
        Normalizer normalizer = new Normalizer(inputCode);
        String outputCode = normalizer.getNormalizedText();
        textArea2.setText(outputCode);

    }

}
