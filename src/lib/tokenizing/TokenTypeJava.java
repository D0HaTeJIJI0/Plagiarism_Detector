package lib.tokenizing;

public enum TokenTypeJava {

    ACCESS_CONTROL_MODIFIER,
    NON_ACCESS_MODIFIER,
    BASIC_TYPE,
    CYCLE,
    BIN_OP,
    UN_OP,
    COMPARISON_OP,
    CONDIT,
    LOG_OP,
    NUMBER;

    public boolean equals(TokenTypeJava ttj){

        return this.toString().equals(ttj.toString());

    }


}
