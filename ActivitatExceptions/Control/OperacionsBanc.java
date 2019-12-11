package Control;

import Exceptions.BankAccountException;
import Exceptions.ExceptionMessage;
import Model.CompteEstalvi;

public class OperacionsBanc {


    public static boolean verifyDNI(String dni) throws BankAccountException {
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int nDNI = Integer.parseInt(dni.substring(0, dni.length()-1));
        String letra = dni.substring(dni.length()-1);
        if (letra.equals(asignacionLetra[(nDNI % 23)]) && dni.length() == 9){
            return true;
        }
        return false;
    }

    public static void transferencia(CompteEstalvi font, CompteEstalvi desti, double suma) throws BankAccountException {
        if (font == null && desti == null) {
            throw new BankAccountException(ExceptionMessage.ACCOUNT_NOT_FOUND);
        }
        else if (font.getLlista_usuaris().size() <= 0 && desti.getLlista_usuaris().size() <= 0){
            throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);
        }
        else {
            font.treure(suma);
            desti.ingressar(suma);
        }
    }
}
