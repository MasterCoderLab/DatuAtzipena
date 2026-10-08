
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Aktoreen datuak kudeatzeko programa modularra.
 *
 * Programak aktoreak zerrendatu, kodearen bidez bilatu
 * eta zenbakizko, data eta testu eremuetan oinarritutako
 * kalkuluak egiteko aukera ematen du.
 *
 * Datuak taula paraleloetan gordetzen dira.
 */
public class ProgramazioModularra {

    // Menuaren eta datu-kutxen zabalera.
    private static final int ZABALERA = 60;

    // Aktoreen kodeak gordetzeko taula.
    private static final int[] codigo = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
    };

    // Aktoreen izenak gordetzeko taula.
    private static final String[] nombre = {
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

    // Aktoreen jaiotze-datak gordetzeko taula.
    private static final LocalDate[] fechaNacimiento = {
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

    // Aktoreen jaioterriak gordetzeko taula.
    private static final String[] lugarNacimiento = {
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

    // Aktoreen nazionalitateak gordetzeko taula.
    private static final String[] nacionalidad = {
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

    // Aktoreen heriotza-datak gordetzeko taula.
    // null balioak data ez dagoela adierazten du.
    private static final LocalDate[] fechaMuerte = {
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

    // Aktoreen heriotza-lekuak gordetzeko taula.
    private static final String[] lugarMuerte = {
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

    // Zenbakizko eremua: aktoreen jaiotze-urtea.
    // Batez bestekoa eta baliorik handiena kalkulatzeko.
    private static final int[] anioNacimiento = {
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

    /**
     * Programaren abiapuntua.
     *
     * Scanner bakarra sortu, menua abiarazi eta
     * erabiltzailearen sarrera modu seguruan kudeatzen du.
     *
     * @param args komando-lerroko argumentuak
     */
    public static void main(String[] args) {

        try (Scanner teklatua = new Scanner(System.in)) {

            menu(teklatua);

        } catch (NoSuchElementException e) {

            // Sarrera amaitzen bada, programa modu kontrolatuan amaitzen da.
            System.out.println();
            System.out.println("Teklatuaren sarrera amaitu da.");
        }
    }

    /**
     * Programaren menu nagusia kudeatzen du.
     *
     * Erabiltzailearen aukera irakurri eta dagokion
     * metodoari deitzen dio.
     *
     * @param teklatua erabiltzailearen sarrera irakurtzeko objektua
     */
    private static void menu(Scanner teklatua) {

        int aukera;

        do {

            erakutsiMenua();

            aukera = zenbakiOsoaIrakurri(
                    teklatua,
                    "Aukeratu aukera bat (0-6): ");

            switch (aukera) {

                case 1:
                    aktoreakZerrendatu();
                    break;

                case 2:
                    aktoreaKodezBilatu(teklatua);
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
                    erakutsiIzenburua("PROGRAMA AMAITUTA");
                    break;

                default:
                    System.err.println(
                            "Errorea: 0 eta 6 arteko aukera bat sartu.");
                    break;
            }

        } while (aukera != 0);
    }

    /**
     * Menu nagusia modu antolatuan erakusten du.
     *
     * Aukerak ataletan banatzen ditu eta
     * kutxaren zabalera mantentzen du.
     */
    private static void erakutsiMenua() {

        System.out.println();
        System.out.println("╔" + "═".repeat(ZABALERA + 2) + "╗");

        String titulua = "AKTOREEN KUDEAKETA";
        int hutsuneak = (ZABALERA - titulua.length()) / 2;

        kutxakoLerroa("", "║");
        kutxakoLerroa(
                " ".repeat(hutsuneak) + titulua, "║");
        kutxakoLerroa("", "║");

        System.out.println("╠" + "═".repeat(ZABALERA + 2) + "╣");

        kutxakoLerroa("KONTSULTAK", "║");
        kutxakoLerroa("", "║");

        kutxakoLerroa(
                "[1] Aktore guztiak zerrendatu", "║");
        kutxakoLerroa(
                "[2] Aktore bat kodearen bidez bilatu", "║");

        kutxakoLerroa("", "║");
        kutxakoLerroa("ESTATISTIKAK ETA KALKULUAK", "║");
        kutxakoLerroa("", "║");

        kutxakoLerroa(
                "[3] Jaiotze-urteen batez-bestekoa kalkulatu", "║");
        kutxakoLerroa(
                "[4] Jaiotze-urte handiena duen aktorea erakutsi", "║");
        kutxakoLerroa(
                "[5] Jaiotze-data zaharrena duen aktorea erakutsi", "║");
        kutxakoLerroa(
                "[6] Izen luzeena duen aktorea erakutsi", "║");

        kutxakoLerroa("", "║");

        System.out.println("╠" + "═".repeat(ZABALERA + 2) + "╣");
        kutxakoLerroa("[0] Irten", "║");
        System.out.println("╚" + "═".repeat(ZABALERA + 2) + "╝");

        System.out.println();
    }

    /**
     * Testu bat kutxaren barruan lerrokatuta erakusten du.
     *
     * Behar diren hutsuneak automatikoki gehitzen ditu.
     *
     * @param testua erakutsi beharreko testua
     * @param ertza kutxaren alboetako karakterea
     */
    private static void kutxakoLerroa(
            String testua, String ertza) {

        int hutsuneak = Math.max(0, ZABALERA - testua.length());

        System.out.println(
                ertza + " "
                        + testua
                        + " ".repeat(hutsuneak)
                        + " " + ertza);
    }

    /**
     * Emaitza baten izenburua erakusten du.
     *
     * @param titulua erakutsiko den izenburua
     */
    private static void erakutsiIzenburua(String titulua) {

        System.out.println();
        System.out.println("═".repeat(ZABALERA + 4));
        System.out.println("  " + titulua);
        System.out.println("═".repeat(ZABALERA + 4));
        System.out.println();
    }

    /**
     * Teklatutik zenbaki oso bat irakurtzen du.
     *
     * Sarrera baliogabea bada, salbuespena harrapatu
     * eta zenbakia berriro eskatzen du.
     *
     * @param teklatua sarrera irakurtzeko Scanner objektua
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return erabiltzaileak sartutako zenbaki osoa
     */
    private static int zenbakiOsoaIrakurri(
            Scanner teklatua, String mezua) {

        int zenbakia = 0;
        boolean baliozkoa = false;

        do {

            System.out.print(mezua);
            String sarrera = teklatua.nextLine().trim();

            try {

                zenbakia = Integer.parseInt(sarrera);
                baliozkoa = true;

            } catch (NumberFormatException e) {

                System.err.println(
                        "Errorea: zenbaki oso bat sartu behar duzu.");
            }

        } while (!baliozkoa);

        return zenbakia;
    }

    /**
     * Aktore baten datu-eremu bat modu lerrokatuan erakusten du.
     *
     * @param etiketa eremuaren izena
     * @param balioa eremuaren balioa
     */
    private static void datuLerroa(
            String etiketa, Object balioa) {

        String testua = String.format(
                "%-18s %s", etiketa + ":", balioa);

        kutxakoLerroa(testua, "│");
    }

    /**
     * Adierazitako posizioan dagoen aktorearen
     * datu guztiak kutxa batean erakusten ditu.
     *
     * Metodo hau beste eragiketek berrerabiltzen dute.
     *
     * @param i aktorearen posizioa tauletan
     */
    private static void aktoreaErakutsi(int i) {

        System.out.println();
        System.out.println("┌" + "─".repeat(ZABALERA + 2) + "┐");

        kutxakoLerroa(
                "AKTOREA: " + nombre[i], "│");

        System.out.println("├" + "─".repeat(ZABALERA + 2) + "┤");

        datuLerroa("Kodea", codigo[i]);
        datuLerroa("Izena", nombre[i]);
        datuLerroa("Jaiotze-data", fechaNacimiento[i]);

        if (lugarNacimiento[i].isEmpty()) {
            datuLerroa("Jaioterria", "-");
        } else {
            datuLerroa("Jaioterria", lugarNacimiento[i]);
        }

        datuLerroa("Nazionalitatea", nacionalidad[i]);

        if (fechaMuerte[i] == null) {
            datuLerroa("Heriotza-data", "-");
        } else {
            datuLerroa("Heriotza-data", fechaMuerte[i]);
        }

        if (lugarMuerte[i].isEmpty()) {
            datuLerroa("Heriotza-lekua", "-");
        } else {
            datuLerroa("Heriotza-lekua", lugarMuerte[i]);
        }

        datuLerroa("Jaiotze-urtea", anioNacimiento[i]);

        System.out.println("└" + "─".repeat(ZABALERA + 2) + "┘");
    }

    /**
     * Aktore guztiak pantailan zerrendatzen ditu.
     *
     * Erregistro bakoitza datu-kutxa batean erakusten du.
     */
    private static void aktoreakZerrendatu() {

        erakutsiIzenburua("AKTORE GUZTIEN ZERRENDA");

        for (int i = 0; i < codigo.length; i++) {

            aktoreaErakutsi(i);
        }

        System.out.println();
        System.out.println(
                "Guztira: " + codigo.length + " aktore.");
    }

    /**
     * Erabiltzaileak sartutako kodearen bidez
     * aktore bat bilatzen du.
     *
     * @param teklatua erabiltzailearen sarrera irakurtzeko objektua
     */
    private static void aktoreaKodezBilatu(Scanner teklatua) {

        erakutsiIzenburua("AKTOREA KODEAREN BIDEZ BILATU");

        int bilatutakoKodea = zenbakiOsoaIrakurri(
                teklatua, "Sartu aktorearen kodea: ");

        boolean aurkituta = false;

        for (int i = 0; i < codigo.length; i++) {

            if (codigo[i] == bilatutakoKodea) {

                aktoreaErakutsi(i);
                aurkituta = true;
                break;
            }
        }

        if (!aurkituta) {

            System.out.println();
            System.out.println(
                    "Ez da kode hori duen aktorerik aurkitu.");
        }
    }

    /**
     * Jaiotze-urteen batez bestekoa kalkulatzen du.
     *
     * Urte guztiak batu eta aktore kopuruarekin
     * zatitzen ditu.
     */
    private static void jaiotzeUrteenBatezBestekoa() {

        erakutsiIzenburua("JAIOTZE-URTEEN BATEZ BESTEKOA");

        int batura = 0;

        // Jaiotze-urte guztiak batzen ditu.
        for (int i = 0; i < anioNacimiento.length; i++) {

            batura += anioNacimiento[i];
        }

        // Zatiketa erreala egiteko double motara bihurtzen da.
        double batezBestekoa =
                (double) batura / anioNacimiento.length;

        System.out.printf(
                "  Aktore kopurua: %d%n", anioNacimiento.length);

        System.out.printf(
                "  Jaiotze-urteen batez bestekoa: %.2f%n",
                batezBestekoa);
    }

    /**
     * Jaiotze-urte handiena bilatzen du.
     *
     * Urte hori duten aktore guztiak erakusten ditu,
     * balio berdina duten erregistroak kontuan hartuz.
     */
    private static void jaiotzeUrteHandiena() {

        erakutsiIzenburua("JAIOTZE-URTE HANDIENA");

        int handiena = anioNacimiento[0];

        // Urterik handiena bilatzen du.
        for (int i = 1; i < anioNacimiento.length; i++) {

            if (anioNacimiento[i] > handiena) {
                handiena = anioNacimiento[i];
            }
        }

        System.out.println(
                "Jaiotze-urte handiena: " + handiena);

        // Urte hori duten aktore guztiak erakusten ditu.
        for (int i = 0; i < anioNacimiento.length; i++) {

            if (anioNacimiento[i] == handiena) {
                aktoreaErakutsi(i);
            }
        }
    }

    /**
     * Jaiotze-data zaharrena duen aktorea bilatzen du.
     *
     * LocalDate objektuak isBefore() erabiliz
     * alderatzen ditu.
     */
    private static void jaiotzeDataZaharrena() {

        erakutsiIzenburua("JAIOTZE-DATA ZAHARRENA");

        LocalDate dataZaharrena = fechaNacimiento[0];
        int posizioa = 0;

        // Data zaharrena eta bere posizioa gordetzen ditu.
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
     * Karaktere gehien dituen aktorearen izena bilatzen du.
     *
     * Luzera bereko izenak badaude, horiek guztiak
     * erakusten ditu.
     */
    private static void izenLuzeena() {

        erakutsiIzenburua("IZEN LUZEENA DUEN AKTOREA");

        int luzeraHandiena = nombre[0].length();

        // Izenaren gehieneko luzera bilatzen du.
        for (int i = 1; i < nombre.length; i++) {

            if (nombre[i].length() > luzeraHandiena) {

                luzeraHandiena = nombre[i].length();
            }
        }

        System.out.println(
                "Izen luzeenaren karaktere kopurua: "
                        + luzeraHandiena);

        // Luzera handiena duten aktoreak erakusten ditu.
        for (int i = 0; i < nombre.length; i++) {

            if (nombre[i].length() == luzeraHandiena) {
                aktoreaErakutsi(i);
            }
        }
    }
}
