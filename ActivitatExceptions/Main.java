import Exceptions.BankAccountException;
import Model.Client;

public class Main {
    public static void main(String[] args) {
        try {
            Client client = new Client("SrNull", "Exception", "53337136K");
        } catch (BankAccountException e) {
            e.printStackTrace();
        }

    }
}
