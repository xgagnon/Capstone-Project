package exceptions;

public class UserException extends Exception {

    private final String code = "E1001";
    public UserException(String errorMessage) {
            super(errorMessage);
        }


    public String getCode() {
        return code;
    }
}
