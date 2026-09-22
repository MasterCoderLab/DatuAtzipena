import java.time.LocalDate;
import java.util.Scanner;

/**
 * Aktoreen datuak kudeatzeko programa.
 * Programak aktoreak zerrendatu, kodearen bidez bilatu
 * eta hainbat kalkulu egiteko aukera ematen du.
 */
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

    // Teklatutik datuak irakurtzeko Scanner objektua
    static Scanner sc = new Scanner(System.in);

    /**
     * Programaren abiapuntua.
     *
     * @param args komando-lerroko argumentuak
     */
    public static void main(String[] args) {

        // Programaren menu nagusiari deitzen dio
        menu();

        // Scanner objektua ixten du programa amaitzean
        sc.close();
    }

    /**
     * Programaren menu nagusia erakutsi eta erabiltzailearen
     * aukerak kudeatzen ditu.
     */
    static void menu() {

        int aukera;

        do {

            System.out.println();
            System.out.println("===== AKTOREEN MENUA =====");
            System.out.println("1. Aktore guztiak zerrendatu");
            System.out.println("2. Aktore bat kodearen bidez bilatu");
            System.out.println("3. Jaiotze-urteen batez-bestekoa kalkulatu");
            System.out.println("4. Jaiotze-urte handiena duen aktorea erakutsi");
            System.out.println("5. Jaiotze-data zaharrena duen aktorea erakutsi");
            System.out.println("6. Izen luzeena duen aktorea erakutsi");
            System.out.println("0. Irten");

            aukera = zenbakiOsoaIrakurri("Aukeratu aukera bat: ");

            switch (aukera) {

                case 1:
                    aktoreakZerrendatu();
                    break;

                case 2:
                    aktoreaKodezBilatu();
                    break;

                case 3:
                    jaiotzeUrteenBatezBestekoa();
                    break;

                case 4:
                    jaiotzeUrteHandiena();
                    break;

                case 5:
                    jaiotzeDataZaharrena();
                    break;

                case 6:
                    izenLuzeena();
                    break;

                case 0:
                    System.out.println("Programa amaitu da.");
                    break;

                default:
                    System.out.println("Aukera ez da zuzena.");
                    break;
            }

        } while (aukera != 0);
    }

    /**
     * Teklatutik zenbaki oso bat modu seguruan irakurtzen du.
     *
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return erabiltzaileak sartutako zenbaki osoa
     */
    static int zenbakiOsoaIrakurri(String mezua) {

        System.out.print(mezua);

        // Erabiltzaileak zenbaki oso bat sartu duen egiaztatzen du
        while (!sc.hasNextInt()) {

            System.out.println("Errorea: zenbaki bat sartu behar duzu.");

            // Scanner-aren bufferra garbitzen du
            sc.nextLine();

            System.out.print(mezua);
        }

        int zenbakia = sc.nextInt();

        // Scanner-aren bufferra garbitzen du
        sc.nextLine();

        return zenbakia;
    }

    /**
     * Adierazitako posizioan dagoen aktorearen datu guztiak
     * pantailan erakusten ditu.
     *
     * @param i aktorearen posizioa tauletan
     */
    static void aktoreaErakutsi(int i) {

        System.out.println("------------------------------");
        System.out.println("Kodea: " + codigo[i]);
        System.out.println("Izena: " + nombre[i]);
        System.out.println("Jaiotze-data: " + fechaNacimiento[i]);

        if (lugarNacimiento[i].isEmpty()) {
            System.out.println("Jaioterria: -");
        } else {
            System.out.println("Jaioterria: " + lugarNacimiento[i]);
        }

        System.out.println("Nazionalitatea: " + nacionalidad[i]);

        if (fechaMuerte[i] == null) {
            System.out.println("Heriotza-data: -");
        } else {
            System.out.println("Heriotza-data: " + fechaMuerte[i]);
        }

        if (lugarMuerte[i].isEmpty()) {
            System.out.println("Heriotza-lekua: -");
        } else {
            System.out.println("Heriotza-lekua: " + lugarMuerte[i]);
        }

        System.out.println("Jaiotze-urtea: " + anioNacimiento[i]);
    }

    /**
     * Aktore guztien datuak pantailan erakusten ditu.
     */
    static void aktoreakZerrendatu() {

        for (int i = 0; i < codigo.length; i++) {
            aktoreaErakutsi(i);
        }
    }

    /**
     * Erabiltzaileak adierazitako kodea duen aktorea bilatu
     * eta haren datuak erakusten ditu.
     */
    static void aktoreaKodezBilatu() {

        int bilatutakoKodea =
                zenbakiOsoaIrakurri("Sartu aktorearen kodea: ");

        boolean aurkituta = false;

        for (int i = 0; i < codigo.length; i++) {

            if (codigo[i] == bilatutakoKodea) {

                aktoreaErakutsi(i);

                aurkituta = true;
                break;
            }
        }

        if (!aurkituta) {
            System.out.println(
                    "Ez da kode hori duen aktorerik aurkitu.");
        }
    }

    /**
     * Aktoreen jaiotze-urteen batez-bestekoa kalkulatu
     * eta pantailan erakusten du.
     */
    static void jaiotzeUrteenBatezBestekoa() {

        int batura = 0;

        // Jaiotze-urte guztiak batzen ditu
        for (int i = 0; i < anioNacimiento.length; i++) {
            batura = batura + anioNacimiento[i];
        }

        // Batura aktore kopuruarekin zatitzen du
        double batezBestekoa =
                (double) batura / anioNacimiento.length;

        System.out.println(
                "Jaiotze-urteen batez-bestekoa: "
                        + batezBestekoa);
    }

    /**
     * Jaiotze-urte handiena bilatu eta urte hori duten
     * aktore guztiak erakusten ditu.
     */
    static void jaiotzeUrteHandiena() {

        int handiena = anioNacimiento[0];

        // Jaiotze-urte handiena bilatzen du
        for (int i = 1; i < anioNacimiento.length; i++) {

            if (anioNacimiento[i] > handiena) {
                handiena = anioNacimiento[i];
            }
        }

        System.out.println(
                "Jaiotze-urte handiena: " + handiena);

        // Urte handiena duten aktore guztiak erakusten ditu
        for (int i = 0; i < anioNacimiento.length; i++) {

            if (anioNacimiento[i] == handiena) {
                aktoreaErakutsi(i);
            }
        }
    }

    /**
     * Jaiotze-data zaharrena bilatu eta data hori duen
     * aktorea erakusten du.
     */
    static void jaiotzeDataZaharrena() {

        LocalDate dataZaharrena = fechaNacimiento[0];
        int posizioa = 0;

        // Jaiotze-data guztiak alderatzen ditu
        for (int i = 1; i < fechaNacimiento.length; i++) {

            if (fechaNacimiento[i].isBefore(dataZaharrena)) {

                dataZaharrena = fechaNacimiento[i];
                posizioa = i;
            }
        }

        System.out.println(
                "Jaiotze-data zaharrena: " + dataZaharrena);

        aktoreaErakutsi(posizioa);
    }

    /**
     * Karaktere gehien dituen izena bilatu eta luzera hori
     * duten aktore guztiak erakusten ditu.
     */
    static void izenLuzeena() {

        int luzeraHandiena = nombre[0].length();

        // Izenen luzera handiena bilatzen du
        for (int i = 1; i < nombre.length; i++) {

            if (nombre[i].length() > luzeraHandiena) {
                luzeraHandiena = nombre[i].length();
            }
        }

        System.out.println(
                "Izen luzeenaren karaktere kopurua: "
                        + luzeraHandiena);

        // Luzera handiena duten aktore guztiak erakusten ditu
        for (int i = 0; i < nombre.length; i++) {

            if (nombre[i].length() == luzeraHandiena) {
                aktoreaErakutsi(i);
            }
        }
    }
}