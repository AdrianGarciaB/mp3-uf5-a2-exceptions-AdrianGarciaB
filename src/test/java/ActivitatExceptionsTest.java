import ActivitatExceptions.Control.OperacionsBanc;
import Exceptions.BankAccountException;
import Exceptions.ClientAccountException;
import Exceptions.ExceptionMessage;
import ActivitatExceptions.Model.Client;
import ActivitatExceptions.Model.CompteEstalvi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivitatExceptionsTest {
    private Client clientA;
    private Client clientB;
    private CompteEstalvi compteEstalviA;
    private CompteEstalvi compteEstalviB;
    private static final int saldoInicial = 100;

    @Test
    void createCorrectClient() throws ClientAccountException {
        // Introducimos un cliente de manera correcta.
        Client client = new Client("Adrian", "Garcia", "12345678Z");
        assertNotNull(client);
    }

    @Test
    void createIncorrectClient() throws Exception {
        // Introducimos un cliente con un DNI invalido.
        Client client = null;
        try {client = new Client("Adrian", "Garcia", "12345678A");}
        catch (ClientAccountException ignored) {}
        finally {assertNull(client);}
    }

    void createAccounts() throws ClientAccountException, BankAccountException {
        // Inicializamos las variables para poder ser usadas.
        clientA = new Client("Adrian", "Garcia", "12345678Z");
        clientB = new Client("Sr", "Cliente", "87654321X");
        compteEstalviA = new CompteEstalvi("1234");
        compteEstalviB = new CompteEstalvi("4321");

        compteEstalviA.addUser(clientA);
        compteEstalviB.addUser(clientB);
        compteEstalviA.ingressar(saldoInicial);
        compteEstalviB.ingressar(saldoInicial);
    }

    @Test
    void addSaldoToAccount() throws Exception {
        createAccounts();
        compteEstalviA.ingressar(100);
        assertEquals(saldoInicial+100, compteEstalviA.getSaldo());
    }

    @Test
    void addNegativeSaldoToAccount() throws Exception {
        createAccounts();
        try {
            compteEstalviA.ingressar(-100);
            // En caso de que no salte la BankAccountException, el test habra fallado.
            throw new Exception("Negative values added");
        }
        catch (BankAccountException ignored){}
        finally {
            assertEquals(saldoInicial, compteEstalviA.getSaldo());
        }
    }

    @Test
    void removeSaldoToAccount() throws Exception {
        createAccounts();
        compteEstalviA.treure(100);
        assertEquals(0, compteEstalviA.getSaldo());
    }

    @Test
    void removeNegativeSaldoToAccount() throws Exception {
        createAccounts();
        try {
            compteEstalviA.ingressar(-100);
            // En caso de que no salte la BankAccountException, el test habra fallado.
            throw new Exception("Negative values added");
        }
        // En caso de que salte la excepcion, el test habra sido satisfactorio.
        catch (BankAccountException ignored){}
        finally {
            assertEquals(saldoInicial, compteEstalviA.getSaldo());
        }
    }

    @Test
    void removeLastUserfromAccount() throws Exception {
        createAccounts();
        try {
            // En caso de que no salte la BankAccountException, el test habra fallado.
            compteEstalviA.removeUser(clientA.getDNI());
            throw new Exception("The account cannot run out of users.");
        }
        // En caso de que salte la excepcion, el test habra sido satisfactorio.
        catch (BankAccountException ignored){}
        finally {
            assertEquals(1, compteEstalviA.getLlista_usuaris().size());
        }
    }

    @Test
    void removeCorrectUserfromAccount() throws Exception {
        createAccounts();
        compteEstalviA.addUser(clientB);
        try {
            compteEstalviA.removeUser(clientA.getDNI());
        }
        catch (BankAccountException ba){
            throw new Exception("The user should have been deleted.");
        }
        finally {
            assertEquals(1, compteEstalviA.getLlista_usuaris().size());
        }
    }

    @Test
    void correctTransferToUser() throws ClientAccountException, BankAccountException {
        createAccounts();
        OperacionsBanc.transferencia(compteEstalviA, compteEstalviB, 100);
        assertEquals(0, compteEstalviA.getSaldo());
        assertEquals(200, compteEstalviB.getSaldo());
    }

    @Test
    void negativeTransferToUser() throws Exception {
        createAccounts();
        try {
            OperacionsBanc.transferencia(compteEstalviA, compteEstalviB, -100);
            throw new Exception("Negative value.");
        }
        catch (BankAccountException ignored){}
        assertEquals(100, compteEstalviA.getSaldo());
        assertEquals(100, compteEstalviB.getSaldo());
    }

    @Test
    void accountNullTransfer() throws Exception {
        createAccounts();
        try {
            OperacionsBanc.transferencia(null, compteEstalviB, 10);
            throw new Exception("Null account!.");
        }
        catch (BankAccountException ignored){}
        assertEquals(100, compteEstalviA.getSaldo());
        assertEquals(100, compteEstalviB.getSaldo());
    }

    @Test
    void overdraftSaldoTransfer() throws Exception {
        createAccounts();
        try {
            OperacionsBanc.transferencia(compteEstalviA, compteEstalviB, 10000);
            throw new Exception(ExceptionMessage.ACCOUNT_OVERDRAFT);
        }
        catch (BankAccountException ignored){}
        assertEquals(100, compteEstalviA.getSaldo());
        assertEquals(100, compteEstalviB.getSaldo());
    }

    @Test
    void emptyAccountUserTransfer() throws Exception {
        createAccounts();
        //Compte sense clients.
        CompteEstalvi compteEstalviC = new CompteEstalvi("8535");

        try {
            OperacionsBanc.transferencia(compteEstalviA, compteEstalviC, 100);
            throw new Exception(ExceptionMessage.ACCOUNT_ZERO_USER);
        }
        catch (BankAccountException ignored){}
        assertEquals(100, compteEstalviA.getSaldo());
        assertEquals(0, compteEstalviC.getSaldo());
    }
}
