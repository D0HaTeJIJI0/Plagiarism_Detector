package alg;

import com.sun.javaws.WinOperaSupport;
import lib.tokenizing.TokenTypeJava;

import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;

public class Fingerprints implements Algorithm {

    private final static int SUB_LENGTH = 2;    // length of substring to hash
    private final static int MIN_LENGTH = 4;    // minimum length of substring that can overlap
    private final static int WINDOW_SIZE = MIN_LENGTH - SUB_LENGTH + 1; // width of window

    @Override
    public double detect(ArrayList<TokenTypeJava> code1, ArrayList<TokenTypeJava> code2) {

        int koef = (int) (Math.random() * code1.size());
        int x = (int) (Math.random() * code2.size());
        ArrayList<Integer> hash1 = hash(code1, koef, x);
        ArrayList<Integer> hash2 = hash(code2, koef, x);
        ArrayList<Integer> marks1 = mark(hash1);
        ArrayList<Integer> marks2 = mark(hash2);
        Heskel alg = new Heskel();
        return alg.detectFinger(marks1, marks2);

    }

    private ArrayList<Integer> hash(ArrayList<TokenTypeJava> code1, int koef, int x) {

        ArrayList<Integer> hash1 = new ArrayList<>();
        int size = code1.size();
        int sum = 0;
        for (int i = 0; i < WINDOW_SIZE; i++) {
            sum += code1.get(i).getHash() * ((int) Math.pow(x, WINDOW_SIZE - i));
        }
        hash1.add(sum % koef);
        for (int i = WINDOW_SIZE; i < size; i++) {
            hash1.add(((hash1.get(i - WINDOW_SIZE) - code1.get(i - 1).getHash() * ((int) Math.pow(x, WINDOW_SIZE - 1))) *
                    x + code1.get(i + WINDOW_SIZE).getHash()) % koef);
        }
        return hash1;

    }

    private ArrayList<Integer> mark(ArrayList<Integer> hash1) {

        ArrayList<Integer> marks = new ArrayList<>();
        int min = -1, minIndex = -1, h = 0;
        for (int i = 0; i < WINDOW_SIZE; i++) {
            h = hash1.get(i);
            if (min == -1 || min > h) {
                min = h;
                minIndex = i;
            }
        }
        marks.add(min);
        for (int i = WINDOW_SIZE; i < hash1.size(); i++) {
            h = hash1.get(i);
            if (min > h) {
                min = h;
                minIndex = i;
                marks.add(min);
            }
            else if (minIndex <= i - WINDOW_SIZE) {
                min = -1;
                for (int j = WINDOW_SIZE - 1; j > -1; j--) {
                    h = hash1.get(i - j);
                    if (min == -1 || min > h) {
                        min = h;
                        minIndex = i - j;
                    }
                }
                marks.add(min);
            }
        }
        return marks;

    }

}
