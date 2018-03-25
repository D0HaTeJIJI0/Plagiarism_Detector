package lib.tokenizing;

import java.util.regex.*;

import static lib.tokenizing.RegExNormalizer.*;

public class Normalizer {

    private String inputText;
    Pattern pTab, pBigLetters, pManySpaces, pNewLine;

    private final int OFFSET = ((int) 'a') - ((int) 'A');

    public Normalizer(){

        inputText = new String();
        initPattern();

    }

    public Normalizer(String inputText) {

        this.inputText = inputText;
        initPattern();

    }

    public void initPattern(){

        pTab = Pattern.compile(TAB);
        pBigLetters = Pattern.compile(BIG_LETTERS);
        pManySpaces = Pattern.compile(MANY_SPACES);
        pNewLine = Pattern.compile(NEW_LINE);

    }

    public void setInputText(String inputText) {

        this.inputText = inputText;

    }

    public String getInputText() {

        return inputText;

    }

    public String getNormalizedText(){

        StringBuffer outputText;
        outputText = normalize(pTab, inputText, REPLACEMENT);
        outputText = normalize(pManySpaces, outputText.toString(), REPLACEMENT);
        outputText = normalize(pNewLine, outputText.toString(), REPLACEMENT);
        Matcher mBigLetters = pBigLetters.matcher(outputText);
        StringBuffer result = new StringBuffer();
        char letter[];
        int letCode;
        while (mBigLetters.find()){
            letter = mBigLetters.group().toCharArray();
            letCode = (int) letter[0];
            letCode += OFFSET;
            letter[0] = (char) letCode;
            mBigLetters.appendReplacement(result, String.valueOf(letter[0]));
        }
        mBigLetters.appendTail(result);
        return result.toString();

    }

    private StringBuffer normalize(Pattern p, String inputText, String replacement) {

        Matcher matcher = p.matcher(inputText);
        StringBuffer outputText = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(outputText, replacement);
        }
        matcher.appendTail(outputText);
        return outputText;

    }

}
