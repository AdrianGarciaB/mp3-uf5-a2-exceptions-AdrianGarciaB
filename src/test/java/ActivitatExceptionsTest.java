import Control.OperacionsBanc;
import Exceptions.BankAccountException;
import Model.Client;
import Model.CompteEstalvi;
import org.junit.jupiter.api.Test;

class ActivitatExceptionsTest {

    @Test
    void verifyUser1() throws Exception {
        new Client("Adrian", "Garcia", "12345678A");
    }
}
