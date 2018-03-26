package lib.tokenizing;

public class RegExTokenizer {

    public static final int NUM_OF_TOKENS = 10;
    public static final String REG_ACCESS_CONTROL_MODIFIER = "private|public|protected";
    public static final String REG_NON_ACCESS_MODIFIER = "static|final|abstract|synchronized";
    public static final String REG_BASIC_TYPE = "byte|short|int|long|float|double|char|boolean|void";
    public static final String REG_CYCLE = "for|while|do|goto";
    public static final String REG_BIN_OP = "\\+=|-=|\\/=|%=|\\*=|&=|\\^=|\\+|-|\\*|\\/|%|=|(\\|)|&|\\^";
    public static final String REG_UN_OP = "\\+\\+|--|~";
    public static final String REG_COMPARISON_OP = "==|>|<|=>|<=|!=";
    public static final String REG_CONDIT = "if|else if|else";
    public static final String REG_LOG_OP = "!|(\\|\\|)|&&";
    public static final String REG_NUMBER = "-?[0-9]+\\.?[0-9]*";
    
}
