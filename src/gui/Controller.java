package gui;

import alg.Algorithm;
import alg.Heskel;
import alg.StringAlignment;
import alg.StringTilling;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lib.AlgorithmType;
import lib.database.DAO;
import lib.tokenizing.TokenTypeJava;
import lib.tokenizing.Tokenizer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    @FXML
    private TextArea textArea1;
    @FXML
    private TextArea textArea2;
    @FXML
    private TextArea resultOneVsOne;
    @FXML
    private TextArea resultsOneVsMany;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem OpenFileOrigItem;
    @FXML
    private MenuItem OpenFileSuspItem;
    @FXML
    private CheckMenuItem oneVsOneItem;
    @FXML
    private CheckMenuItem oneVsManyItem;
    @FXML
    private CheckMenuItem heskelItem;
    @FXML
    private CheckMenuItem stringTilingItem;
    @FXML
    private CheckMenuItem stringAlignmentItem;
    @FXML
    private CheckMenuItem fingerprintsItem;
    @FXML
    private SplitPane splitPane;
    private AlgorithmType algorithmType;
    private Algorithm algorithm = new Heskel();
    private boolean mode = true;
    private static final int MAX_LEN = 1000;
    private static final String ENDING = "% plagiarism detected.\n";
    private static final String DELIMITER = ": ";
    private static final String NEW_LINE = "\n";

    public Controller() {

        textArea1 = new TextArea();
        textArea2 = new TextArea();
        menuBar = new MenuBar();
        oneVsManyItem = new CheckMenuItem();
        oneVsOneItem = new CheckMenuItem();
        splitPane = new SplitPane();
        resultOneVsOne = new TextArea();
        resultsOneVsMany = new TextArea();
        heskelItem = new CheckMenuItem();
        stringTilingItem = new CheckMenuItem();
        stringAlignmentItem = new CheckMenuItem();

    }

    @FXML
    private void OpenFileOrig() {

        JFileChooser fileopen = new JFileChooser();
        int res = fileopen.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION){

            File file = fileopen.getSelectedFile();
            try {
                FileReader fr = new FileReader(file);
                int c;
                String str = "";

                while(((c = fr.read()) != -1)){
                    str += (char)c;
                }

                textArea1.setText(str);

                fr.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }

    @FXML
    private void about() {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("about.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(new Scene(root, 300, 200));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Author: Travnichev/nIvanVersion: 1.0

    }

    @FXML
    private void close() {

        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void OpenFileSusp() {

        JFileChooser fileopen = new JFileChooser();
        int res = fileopen.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION){

            File file = fileopen.getSelectedFile();
            try {
                FileReader fr = new FileReader(file);
                int c;
                String str = "";

                while(((c = fr.read()) != -1)){
                    str += (char)c;
                }

                textArea2.setText(str);

                fr.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }

    @FXML
    private void oneVsOneMode() {

        oneVsOneItem.setSelected(true);
        oneVsManyItem.setSelected(false);
        if (!mode) {
            setOneVsOneMode();
            mode = true;
        }

    }

    @FXML
    private void oneVsManyMode() {

        oneVsManyItem.setSelected(true);
        oneVsOneItem.setSelected(false);
        if (mode) {
            setOneVsManyMode();
            mode = false;
        }

    }

    @FXML
    private void setHeskelAlgorithm() {

        heskelItem.setSelected(true);
        stringTilingItem.setSelected(false);
        stringAlignmentItem.setSelected(false);
        fingerprintsItem.setSelected(false);
        if (algorithmType != AlgorithmType.HESKEL) {
            algorithmType = AlgorithmType.HESKEL;
            algorithm = new Heskel();
        }

    }

    @FXML
    private void setStringTilingAlgorithm() {

        heskelItem.setSelected(false);
        stringTilingItem.setSelected(true);
        stringAlignmentItem.setSelected(false);
        fingerprintsItem.setSelected(false);
        if (algorithmType != AlgorithmType.STRING_TILING) {
            algorithmType = AlgorithmType.STRING_TILING;
            algorithm = new StringTilling();
        }

    }

    @FXML
    private void setStringAlignmentAlgorithm() {

        heskelItem.setSelected(false);
        stringTilingItem.setSelected(false);
        stringAlignmentItem.setSelected(true);
        fingerprintsItem.setSelected(false);
        if (algorithmType != AlgorithmType.STRING_ALIGNMENT) {
            algorithmType = AlgorithmType.STRING_ALIGNMENT;
            algorithm = new StringAlignment();
        }

    }

    @FXML
    private void setFingerprintsAlgorithm() {

        heskelItem.setSelected(false);
        stringTilingItem.setSelected(false);
        stringAlignmentItem.setSelected(false);
        fingerprintsItem.setSelected(true);
        if (algorithmType != AlgorithmType.STRING_ALIGNMENT) {
            algorithmType = AlgorithmType.STRING_ALIGNMENT;
            algorithm = new StringAlignment();
        }

    }

    private void setOneVsOneMode() {

        textArea2.setVisible(true);
        splitPane.setVisible(true);
        splitPane.setDividerPosition(0, 0.5);
        textArea1.setPrefSize(296, 398);

    }

    private void setOneVsManyMode() {

        textArea2.setVisible(false);
        splitPane.setDividerPosition(0, 1);
        textArea1.setPrefSize(600, 400);

    }

    @FXML
    private void analyze(){

        if (mode) {
            double resPercent;
            String text1 = textArea1.getText(),
                    text2 = textArea2.getText();
            if (text1.length() > text2.length()) {
                resPercent = analyzeOneVsOne(text2, text1);
            }
            else {
                resPercent = analyzeOneVsOne(text1, text2);
            }
            resultOneVsOne.setText(resPercent + ENDING);
        }
        else {
            analyzeOneVsMany();
        }

    }



    private void analyzeOneVsMany() {

        String inputCode = textArea1.getText();
        StringBuffer baseCode;
        DAO dao = new DAO();
        ArrayList<String> baseCodes = (ArrayList) dao.getFiles();
        StringBuffer resultBuffer = new StringBuffer();
        double resPercent;
        int pos;
        for (String file : baseCodes) {
            baseCode = decompose(file);
            resPercent = analyzeOneVsOne(inputCode, baseCode.toString());
            pos = file.lastIndexOf('\\');
            resultBuffer.append(file.substring(++pos, file.length()) +
                                                    DELIMITER +
                                                    resPercent +
                                                    ENDING);
        }
        resultsOneVsMany.setText(resultBuffer.toString());
    }

    private StringBuffer decompose(String file) {

        Scanner sc;
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            sc = new Scanner(fileReader);
            StringBuffer result = new StringBuffer();
            while (sc.hasNextLine()) {
                result.append(sc.nextLine() + NEW_LINE);
            }
            sc.close();
            fileReader.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private double analyzeOneVsOne(String originalCode, String suspiciousCode) {

        String subOrig, subSusp;
        double result = 0, maxRes, totalLength = 0;
        for (int beginIndexOrig = 0; beginIndexOrig < originalCode.length();) {
            if (beginIndexOrig + MAX_LEN * 2 > originalCode.length()) {
                subOrig = originalCode.substring(beginIndexOrig, originalCode.length());
                beginIndexOrig = originalCode.length();
            }
            else {
                subOrig = originalCode.substring(beginIndexOrig, beginIndexOrig + MAX_LEN);
                beginIndexOrig += MAX_LEN;
            }
            Tokenizer tokenizerInput = new Tokenizer(subOrig);
            ArrayList<TokenTypeJava> tokenListOrig  = tokenizerInput.getTokenizedList();
            totalLength += tokenListOrig.size();
            maxRes = 0;
            for (int beginIndexSusp = 0; beginIndexSusp < suspiciousCode.length();) {
                if (beginIndexSusp + MAX_LEN * 2 > suspiciousCode.length()) {
                    subSusp = suspiciousCode.substring(beginIndexSusp, suspiciousCode.length());
                    beginIndexSusp = suspiciousCode.length();
                }
                else {
                    subSusp = suspiciousCode.substring(beginIndexSusp, beginIndexSusp + MAX_LEN);
                    beginIndexSusp += MAX_LEN;
                }
                Tokenizer tokenizerSusp = new Tokenizer(subSusp);
                ArrayList<TokenTypeJava> tokenListSusp = tokenizerSusp.getTokenizedList();
                maxRes = Math.max(algorithm.detect(tokenListOrig, tokenListSusp), maxRes);
            }
            result += maxRes;
        }
        double resPercent = (result / totalLength) * 100;
        return resPercent;

    }

}
