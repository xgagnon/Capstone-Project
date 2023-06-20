package exceptions;

public class TransactionException  extends Exception{

    private final String code = "E1002";
    public TransactionException(String errorMessage) {
        super(errorMessage);
    }

    public String getCode() {
        return code;
    }
}
