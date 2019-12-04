package excepcions.Exemple;

import java.time.LocalDate;

public class MainExceptionOne {

    public static void main(String[] args) throws PersonNotBornException {
        Person p = new Person("Pep", Person.Genere.HOME, LocalDate.of(2020,10,10));
    }
}
