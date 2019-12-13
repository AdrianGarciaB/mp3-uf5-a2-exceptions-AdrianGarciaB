package Exceptions;

public class ClientAccountException extends Exception {
    public ClientAccountException(String messageError) {
        super(messageError);
    }
}
