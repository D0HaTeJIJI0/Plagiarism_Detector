package gui;

import alg.Heskel;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import lib.tokenizing.TokenTypeJava;
import lib.tokenizing.Tokenizer;

import java.util.ArrayList;

public class Controller {

    @FXML
    private TextArea textArea1;
    @FXML
    private TextArea textArea2;
    @FXML
    private TextArea resultArea;
    @FXML
    private MenuBar menuBar;

    public Controller() {

        textArea1 = new TextArea();
        textArea2 = new TextArea();
        menuBar = new MenuBar();

    }

    @FXML
    private void analyze(){

        String inputCode = textArea1.getText();
        String suspiciousCode = textArea2.getText();
//        String inputCode = "if (a>=b) {\n" +
//                "         // Comment1’\n" +
//                "c=d+b;\n" +
//                "        d=d+1;}\n" +
//                "else  // Comment2’\n" +
//                "c=d-a;\n";
//        String suspiciousCode = "if (a >= b) {\n" +
//                "    c = d + b; // Comment1\n" +
//                "    d = d + 1;}\n" +
//                "else\n" +
//                "    c = d - a; //Comment2\n";
        Tokenizer tokenizerInput = new Tokenizer(inputCode),
                    tokenizerSusp = new Tokenizer(suspiciousCode);
        ArrayList<TokenTypeJava> tokenListInput  = tokenizerInput.getTokenizedList(),
                                    tokenListSusp = tokenizerSusp.getTokenizedList();
        StringBuffer s = new StringBuffer();
        for (TokenTypeJava t : tokenListInput){
            s.append(t + "\n");
        }
        textArea1.setText(s.toString());
       s.delete(0, s.length());
        for (TokenTypeJava t : tokenListSusp){
            s.append(t + "\n");
        }
        textArea2.setText(s.toString());
        double resPercent = Heskel.detect(tokenListInput, tokenListSusp);
        resultArea.setText(resPercent + "% plagiarism detected.");
//        StringBuffer s = new StringBuffer();
//        for (TokenTypeJava t : tokenListInput){
//            s.append(t + "\n");
//        }
//        textArea2.setText(s.toString());

    }

}
