package Exceptions;

public class BankAccountException extends Exception {
    public BankAccountException(String messageError) {
        super(messageError);
    }
}
