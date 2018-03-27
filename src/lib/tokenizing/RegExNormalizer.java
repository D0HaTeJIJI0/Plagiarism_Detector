package lib.tokenizing;

public class RegExNormalizer {

    public static final String TAB = "\t";
    public static final String BIG_LETTERS = "[A-Z]";
    public static final String NEW_LINE = "\n";
    public static final String MANY_SPACES = " {2,}";
    public static final String COMMENT1 = "\\/\\/(.)*";
    public static final String COMMENT2 = "\\/\\*(.|\\n)*\\*\\/";

    public static final String REPLACEMENT = " ";

}
