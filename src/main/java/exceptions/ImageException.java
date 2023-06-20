package exceptions;

public class ImageException  extends Exception{

    private final String code = "E1003";
    public ImageException(String errorMessage) {
        super(errorMessage);
    }

    public String getCode() {
        return code;
    }
}
