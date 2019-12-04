package Control;

import Exceptions.BankAccountException;
import Exceptions.ExceptionMessage;
import Model.CompteEstalvi;

public class OperacionsBanc {


    public static boolean verifyDNI(String dni) throws BankAccountException {
        //TODO implementar fòrnula de verificació del DNI
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int nDNI = Integer.parseInt(dni.substring(0, dni.length()-1));
        String letra = dni.substring(dni.length()-1);

        if (letra.equals(asignacionLetra[(nDNI % 23)])){
            return true;
        }
        else {
            throw new BankAccountException(ExceptionMessage.WRONG_DNI);
        }
    }

    public static void transferencia(CompteEstalvi font, CompteEstalvi desti, double suma) {
        //TODO implementar transferència
    }
}
