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

    private int hash = -1;

    public boolean equals(TokenTypeJava ttj){

        return this.toString().equals(ttj.toString());

    }


    public int getHash() {

        if (hash == -1) {
            switch (this) {
                case ACCESS_CONTROL_MODIFIER:
                    hash = 1;
                    break;
                case NON_ACCESS_MODIFIER:
                    hash = 2;
                    break;
                case BASIC_TYPE:
                    hash = 3;
                    break;
                case CYCLE:
                    hash = 4;
                    break;
                case BIN_OP:
                    hash = 5;
                    break;
                case UN_OP:
                    hash = 6;
                    break;
                case COMPARISON_OP:
                    hash = 7;
                    break;
                case CONDIT:
                    hash = 8;
                    break;
                case LOG_OP:
                    hash = 9;
                    break;
                case NUMBER:
                    hash = 10;
                    break;
            }
        }
        return hash;

    }
}
