package alg;

import lib.tokenizing.TokenTypeJava;

import java.util.ArrayList;

public class StringAlignment implements Algorithm {

    public final static int EQUAL = 1;
    private final static int DIFFERENT = -1;
    private final static int GAP = -2;
    private int score[][];

    private void initScore(int size1, int size2) {

        score = new int[size1 + 1][size2 + 1];
        for (int i = 0; i <= size1; i++) {
            score[i][0] = 0;
        }
        for (int j = 0; j <= size2; j++) {
            score[0][j] = 0;
        }

    }

    @Override
    public double detect(ArrayList<TokenTypeJava> code1, ArrayList<TokenTypeJava> code2) {

        initScore(code1.size(), code2.size());
        for (int i = 1; i <= code1.size(); i++) {
            for (int j = 1; j <= code2.size(); j++) {
                score[i][j] = Math.max(
                                        score[i - 1][j - 1] + check(code1.get(i - 1), code2.get(j - 1)),
                                        Math.max(
                                                score[i - 1][j] + GAP,
                                                Math.max(
                                                        score[i][j - 1] + GAP,
                                                        0
                                                        )
                                                )
                                        );

            }
        }
        return score[code1.size()][code2.size()];

    }

    private int check(TokenTypeJava t1, TokenTypeJava t2) {

        return (t1 == t2)? EQUAL : DIFFERENT;

    }


}
