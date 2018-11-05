package main.messages;

/**
 *
 * @author saban
 */
public enum EXIT_CODE {
    
    COMANDO_INESISTENTE(0),
    PARAMETRI_INSUFFICENTI(1),
    NON_UN_COMANDO(2),
    SINTASSI_ERRATA(3),
    OK(4);
    
    private int numVal;

    EXIT_CODE(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

}

