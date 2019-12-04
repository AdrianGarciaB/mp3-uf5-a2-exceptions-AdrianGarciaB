package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Control.OperacionsBanc;

public class Client {
    private String Nom;
    private String Cognoms;
    private String DNI;

    public Client(String nom, String cognoms, String DNI) {
        Nom = nom;
        Cognoms = cognoms;
        if(OperacionsBanc.verifyDNI(DNI)) this.DNI = DNI;

    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognoms() {
        return Cognoms;
    }

    public void setCognoms(String cognoms) {
        Cognoms = cognoms;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }



}
