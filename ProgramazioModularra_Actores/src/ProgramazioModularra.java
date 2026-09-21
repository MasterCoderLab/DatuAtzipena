import java.time.LocalDate;

public class ProgramazioModularra {

    // Aktoreen datuak gordetzeko taulak

    // Aktoreen kodeak gordetzeko taula
    static int[] codigo = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
    };

    // Aktoreen izenak gordetzeko taula
    static String[] nombre = {
            "Kevin Costner",
            "Héctor Alterio",
            "Ricardo Darín",
            "Viggo Mortensen",
            "Liv Tyler",
            "Orlando Bloom",
            "Javier Bardem",
            "Belén Rueda",
            "Lola Dueñas",
            "Humphrey Bogart",
            "Ingrid Bergman"
    };

    // Aktoreen jaiotze-datak gordetzeko taula
    static LocalDate[] fechaNacimiento = {
            LocalDate.of(1955, 1, 18),
            LocalDate.of(1929, 10, 21),
            LocalDate.of(1957, 1, 16),
            LocalDate.of(1958, 10, 20),
            LocalDate.of(1977, 7, 1),
            LocalDate.of(1977, 1, 13),
            LocalDate.of(1969, 3, 1),
            LocalDate.of(1965, 3, 16),
            LocalDate.of(1929, 4, 16),
            LocalDate.of(1899, 12, 25),
            LocalDate.of(1915, 8, 29)
    };

    // Aktoreen jaioterriak gordetzeko taula
    static String[] lugarNacimiento = {
            "Lywood, California",
            "Buenos Aires",
            "Buenos Aires",
            "Manhattan, New York",
            "Portland, Maine",
            "Canterbury, Kent",
            "Las Palmas de Gran Canaria",
            "Madrid",
            "",
            "New York, New York",
            "Stockholm"
    };

    // Aktoreen nazionalitateak gordetzeko taula
    static String[] nacionalidad = {
            "Estados Unidos",
            "Argentina",
            "Argentina",
            "Estados Unidos",
            "Estados Unidos",
            "Gran Bretaña",
            "España",
            "España",
            "España",
            "Estados Unidos",
            "Suecia"
    };

    // Aktoreen heriotza-datak gordetzeko taula
    static LocalDate[] fechaMuerte = {
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            LocalDate.of(1957, 1, 14),
            LocalDate.of(1982, 8, 28)
    };

    // Aktoreen heriotza-lekuak gordetzeko taula
    static String[] lugarMuerte = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "Los Angeles, C",
            "Londres"
    };

    // Zenbakizko eremua: aktoreen jaiotze-urtea
    static int[] anioNacimiento = {
            1955,
            1929,
            1957,
            1958,
            1977,
            1977,
            1969,
            1965,
            1929,
            1899,
            1915
    };


    public static void main(String[] args) {

        // Aktore guztiak erakusten dituen metodoari deitzen dio
        aktoreakZerrendatu();
    }


    // Aktore guztien datuak pantailan erakusten ditu
    static void aktoreakZerrendatu() {

        for (int i = 0; i < codigo.length; i++) {

            System.out.println("------------------------------");

            System.out.println("Kodea: " + codigo[i]);
            System.out.println("Izena: " + nombre[i]);
            System.out.println("Jaiotze-data: " + fechaNacimiento[i]);
            System.out.println("Jaioterria: " + lugarNacimiento[i]);
            System.out.println("Nazionalitatea: " + nacionalidad[i]);
            System.out.println("Heriotza-data: " + fechaMuerte[i]);
            System.out.println("Heriotza-lekua: " + lugarMuerte[i]);
            System.out.println("Jaiotze-urtea: " + anioNacimiento[i]);
        }
    }
}