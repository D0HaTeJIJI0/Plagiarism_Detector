package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lib.tokenizing.Normalizer;
import lib.tokenizing.TokenTypeJava;
import lib.tokenizing.Tokenizer;

import java.util.ArrayList;

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
        Tokenizer tokenizer = new Tokenizer(inputCode);
        ArrayList<TokenTypeJava> tokenList  = tokenizer.getTokenizedText();
        StringBuffer outputCode = new StringBuffer();
        for (TokenTypeJava t : tokenList){
            outputCode.append(t + "\n");
        }
        textArea2.setText(outputCode.toString());

    }

}
