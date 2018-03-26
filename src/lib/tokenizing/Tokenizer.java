package lib.tokenizing;

import java.util.ArrayList;
import java.util.regex.*;

import static lib.tokenizing.RegExNormalizer.*;
import static lib.tokenizing.RegExTokenizer.*;
import static lib.tokenizing.TokenTypeJava.*;

public class Tokenizer {

    private String inputText;
    private Pattern patterns[];
    private TokenTypeJava tokens[];

    public Tokenizer(String inputText) {

        this.inputText = inputText;
        initPatterns();
        initTokens();

    }

    private void initTokens() {

        tokens = new TokenTypeJava[NUM_OF_TOKENS];
        tokens[0] = ACCESS_CONTROL_MODIFIER;
        tokens[1] = NON_ACCESS_MODIFIER;
        tokens[2] = BASIC_TYPE;
        tokens[3] = CYCLE;
        tokens[4] = BIN_OP;
        tokens[5] = UN_OP;
        tokens[6] = COMPARISON_OP;
        tokens[7] = CONDIT;
        tokens[8] = LOG_OP;
        tokens[9] = NUMBER;

    }

    private void initPatterns() {

        patterns = new Pattern[NUM_OF_TOKENS];
        patterns[0] = Pattern.compile(REG_ACCESS_CONTROL_MODIFIER);
        patterns[1] = Pattern.compile(REG_NON_ACCESS_MODIFIER);
        patterns[2] = Pattern.compile(REG_BASIC_TYPE);
        patterns[3] = Pattern.compile(REG_CYCLE);
        patterns[4] = Pattern.compile(REG_BIN_OP);
        patterns[5] = Pattern.compile(REG_UN_OP);
        patterns[6] = Pattern.compile(REG_COMPARISON_OP);
        patterns[7] = Pattern.compile(REG_CONDIT);
        patterns[8] = Pattern.compile(REG_LOG_OP);
        patterns[9] = Pattern.compile(REG_NUMBER);

    }

    private Matcher[] initMatchers(){

        Matcher [] matchers = new Matcher[NUM_OF_TOKENS];
       for (int i = 0; i < NUM_OF_TOKENS; i++){
           matchers[i] = patterns[i].matcher(inputText);
       }
       return matchers;

    }

    public ArrayList<TokenTypeJava> getTokenizedText(){

        Normalizer normalizer = new Normalizer(inputText);
        StringBuffer outputText = new StringBuffer(normalizer.getNormalizedText());
        boolean f;
        Matcher [] matchers = initMatchers();
        ArrayList<TokenTypeJava> tokenList = new ArrayList<>();
        int minPos, pos, minLen, len, begPos = 0;
        TokenTypeJava minTokenType;
        do{
            f = false;
            minPos = -1;
            minLen = -1;
            minTokenType = null;
            for (int i = 0; i < NUM_OF_TOKENS; i++) {
                if (matchers[i].find(begPos)) {
                    pos = matchers[i].start();
                    if (minPos > pos || minPos == -1) {
                        f = true;
                        minPos = pos;
                        minLen = matchers[i].end() - pos;
                        minTokenType = tokens[i];
                    }
                    else if (minPos == pos) {
                        len = matchers[i].end() - pos;
                        if (len > minLen) {
                            minLen = len;
                            minTokenType = tokens[i];
                        }
                    }
                }
            }
            if (f) {
                tokenList.add(minTokenType);
                begPos = minPos + minLen;
            }
        } while (f);
        return tokenList;

    }

}
