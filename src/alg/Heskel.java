package alg;

import lib.tokenizing.TokenTypeJava;
import java.util.ArrayList;
import static java.lang.Math.min;

public class Heskel {

    private static final int K_GRAMM = 3;
    private static boolean flags_code1[], flags_code2[];

    public static double detect(ArrayList<TokenTypeJava> code1, ArrayList<TokenTypeJava> code2){

        double result;
        ArrayList<Integer> position1 = uniq(code1);
        ArrayList<Integer> position2 = uniq(code2);
        boolean f;
        flags_code1 = new boolean[code1.size()];
        flags_code2 = new boolean[code2.size()];
        for (int i = 0; i < code1.size(); i++) {
            flags_code1[i] = false;
        }
        for (int i = 0; i < code2.size(); i++) {
            flags_code2[i] = false;
        }
        int maxLen = 0, len, ind1, ind2;
        for (int i = 0; i < position1.size(); i++){
            for (int j = 0; j < position2.size(); j++){
                ind2 = position2.get(j);
                if (flags_code2[ind2]) continue;
                ind1 = position1.get(i);
                f = true;
                for (int k = 0; k < K_GRAMM; k++){
                    if (!code1.get(ind1 + k).equals(code2.get(ind2 + k))){
                        f = false;
                        break;
                    }
                }
                if (f){
                    flags_code1[ind1] = true;
                    flags_code2[ind2] = true;
                    if (!flags_code2[ind2 + K_GRAMM - 1] || !flags_code1[ind1 + K_GRAMM - 1]) {
                        len = expand(code1, code2, ind1, ind2);
                        maxLen += len;
                    }
                    break;
                }
            }
        }
        if (maxLen == 0){
            return 0;
        }
        result = (((double)maxLen) / min(code2.size(), code1.size())) * 100;
        return result;

    }

    private static int expand(ArrayList<TokenTypeJava> code1, ArrayList<TokenTypeJava> code2, int ind1, int ind2) {

        int dec;
        for (dec = 1; (dec < ind1) && (dec < ind2); dec++){
            if (!code1.get(ind1 - dec).equals(code2.get(ind2 - dec)) || flags_code2[ind2 - dec] || flags_code1[ind1 - dec]){
               // stop = true;
                break;
            }
            else{
                flags_code1[ind1 - dec] = true;
                flags_code2[ind2 - dec] = true;
            }
        }
        dec--;
        int end1 = ind1 + K_GRAMM - 1,
            end2 = ind2 + K_GRAMM - 1,
            inc;
        for (inc = 1; (inc < code1.size() - end1) && (inc < code2.size() - end2); inc++){
            if (!code1.get(end1 + inc).equals(code2.get(end2 + inc)) || flags_code1[end1 + inc] || flags_code2[end2 + inc]){
                break;
            }
            else {
                flags_code1[end1 + inc] = true;
                flags_code2[end2 + inc] = true;
            }
        }
        inc--;
        return (dec + K_GRAMM + inc);

    }

    private static ArrayList<Integer> uniq(ArrayList<TokenTypeJava> code){

        ArrayList<Integer> pos = new ArrayList<>();
        int size = code.size();
        boolean flags[] = new boolean[size];
        for (int i = 0; i < size; i++){
            flags[i] = false;
        }
        int ind = 0;
        boolean f;
        for (int i = 0; i < size - K_GRAMM; i++){
            if (flags[i]) continue;
            for (int j = i + 1; j < size - K_GRAMM + 1; j++){
                f = true;
                for (int k = 0; k < K_GRAMM; k++) {
                    if (!code.get(i + k).equals(code.get(j + k))) {
                        f = false;
                        break;
                    }
                }
                if (f){
                    flags[j] = true;
                    flags[i] = true;
                }
            }
            if (!flags[i]){
                pos.add(i);
            }
        }
        return pos;

    }

}
