package ActivitatExceptions.Model;

import Exceptions.BankAccountException;
import Exceptions.ClientAccountException;
import Exceptions.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

public class CompteEstalvi {
    private String numCompte;
    private double saldo;
    private List<Client> llista_usuaris;

    public CompteEstalvi(String numCompte) {
        this.numCompte = numCompte;
        saldo = 0;
        this.llista_usuaris = new ArrayList<>();
    }

    /**
        Afegeix un usuari d'aquest compte
        @param client
        @return quantitat d'usuaris que té el compte

     **/
    public int addUser(Client client) throws ClientAccountException {
        for (Client clientaux: llista_usuaris) {
            if (clientaux.getDNI().equals(client.getDNI())){
                // DNI Duplicat.
                throw new ClientAccountException(ExceptionMessage.DUPLICATE_DNI);
            }
        }
        llista_usuaris.add(client);
        return llista_usuaris.size();
    }

    /**
     Elimina un usuari d'aquest compte
     @param dni
     @return quantitat d'usuaris que té el compte
     @throws BankAccountException
     **/
    public int removeUser(String dni) throws BankAccountException {
        if ((llista_usuaris.size()-1) <=0) throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);
        llista_usuaris.removeIf(u -> dni.equals(u.getDNI()));
        return llista_usuaris.size();
    }

    /**
     * Afegeix m diners al saldo
     * @param m
     */
    public void ingressar(double m) throws BankAccountException {
        if (m <= 0) throw new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
        saldo += m;
    }

    /**
     * Treu m diners del compte si n'hi han suficient
     * @param m
     * @throws BankAccountException
     */
    public void treure(double m) throws BankAccountException {
        if (m <= 0) throw new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
        else if (getSaldo() - m < 0)  throw new BankAccountException(ExceptionMessage.ACCOUNT_OVERDRAFT);
        saldo -= m;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Client> getLlista_usuaris() {
        return llista_usuaris;
    }
}
