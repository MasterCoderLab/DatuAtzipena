import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Aktoreen datuak objektuetara orientatutako programazioaren bidez
 * kudeatzeko programa nagusia.
 *
 * Programak aktoreak zerrendatzeko, kodearen bidez bilatzeko,
 * bi daten artean bilatzeko, aktore berriak gehitzeko,
 * aktoreak ezabatzeko eta izenaren arabera ordenatzeko aukera ematen du.
 */
public class Main {

    // Teklatutik datuak irakurtzeko Scanner objektua
    static Scanner sc = new Scanner(System.in);

    /**
     * Programaren abiapuntua.
     *
     * Aktoreen ArrayList-a sortzen du, hasierako datuak kargatzen ditu
     * eta menu nagusia abiarazten du.
     *
     * @param args komando-lerroko argumentuak
     */
    public static void main(String[] args) {

        // Aktore guztiak gordetzeko ArrayList-a
        ArrayList<Actor> actores = new ArrayList<>();

        // Hasierako aktoreak ArrayList-ean kargatzen ditu
        aktoreakKargatu(actores);

        // Menu nagusia abiarazten du
        menu(actores);

        // Scanner objektua ixten du
        sc.close();
    }

    /**
     * Programaren menu nagusia erakusten du eta
     * erabiltzaileak aukeratutako eragiketak kudeatzen ditu.
     *
     * @param actores kudeatuko diren aktoreen zerrenda
     */
    static void menu(ArrayList<Actor> actores) {

        int aukera;

        do {

            System.out.println();
            System.out.println("===== AKTOREEN MENUA =====");
            System.out.println("1. Aktore guztiak zerrendatu");
            System.out.println("2. Aktore bat kodearen bidez bilatu");
            System.out.println("3. Bi daten arteko aktoreak zerrendatu");
            System.out.println("4. Aktore berri bat gehitu");
            System.out.println("5. Aktore bat ezabatu");
            System.out.println("6. Aktoreak izenaren arabera ordenatu");
            System.out.println("0. Irten");

            aukera = zenbakiOsoaIrakurri("Aukeratu aukera bat: ");

            switch (aukera) {

                case 1:
                    aktoreakZerrendatu(actores);
                    break;

                case 2:
                    aktoreaKodezBilatu(actores);
                    break;

                case 3:
                    aktoreakDatenArteanZerrendatu(actores);
                    break;

                case 4:
                    aktoreaGehitu(actores);
                    break;

                case 5:
                    aktoreaEzabatu(actores);
                    break;

                case 6:
                    aktoreakOrdenatu(actores);
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
     * Balio oker bat sartuz gero, erabiltzaileari berriro eskatzen dio.
     *
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return erabiltzaileak sartutako zenbaki osoa
     */
    static int zenbakiOsoaIrakurri(String mezua) {

        System.out.print(mezua);

        while (!sc.hasNextInt()) {

            System.out.println(
                    "Errorea: zenbaki oso bat sartu behar duzu.");

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
     * Teklatutik data bat irakurtzen du YYYY-MM-DD formatuan.
     * Formatu okerra sartuz gero, erabiltzaileari berriro eskatzen dio.
     *
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return erabiltzaileak sartutako data
     */
    static LocalDate dataIrakurri(String mezua) {

        while (true) {

            System.out.print(mezua);

            String testua = sc.nextLine();

            try {

                return LocalDate.parse(testua);

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Errorea: data YYYY-MM-DD formatuan sartu behar duzu.");
            }
        }
    }

    /**
     * Teklatutik bai edo ez motako erantzuna irakurtzen du.
     * B letra bai adierazteko erabiltzen da eta E letra ez adierazteko.
     *
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return true erabiltzaileak B sartzen badu; false E sartzen badu
     */
    static boolean baiEzIrakurri(String mezua) {

        while (true) {

            System.out.print(mezua);

            String erantzuna = sc.nextLine().trim();

            if (erantzuna.equalsIgnoreCase("B")) {
                return true;
            }

            if (erantzuna.equalsIgnoreCase("E")) {
                return false;
            }

            System.out.println(
                    "Errorea: B (bai) edo E (ez) sartu behar duzu.");
        }
    }

    /**
     * Hasierako aktoreen datuak zerrendan kargatzen ditu.
     *
     * @param actores aktoreak gordeko diren zerrenda
     */
    static void aktoreakKargatu(ArrayList<Actor> actores) {

        actores.add(new Actor(
                1,
                "Kevin Costner",
                LocalDate.of(1955, 1, 18),
                "Lywood, California",
                "Estados Unidos",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                2,
                "Héctor Alterio",
                LocalDate.of(1929, 10, 21),
                "Buenos Aires",
                "Argentina",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                3,
                "Ricardo Darín",
                LocalDate.of(1957, 1, 16),
                "Buenos Aires",
                "Argentina",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                4,
                "Viggo Mortensen",
                LocalDate.of(1958, 10, 20),
                "Manhattan, New York",
                "Estados Unidos",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                5,
                "Liv Tyler",
                LocalDate.of(1977, 7, 1),
                "Portland, Maine",
                "Estados Unidos",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                6,
                "Orlando Bloom",
                LocalDate.of(1977, 1, 13),
                "Canterbury, Kent",
                "Gran Bretaña",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                7,
                "Javier Bardem",
                LocalDate.of(1969, 3, 1),
                "Las Palmas de Gran Canaria",
                "España",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                8,
                "Belén Rueda",
                LocalDate.of(1965, 3, 16),
                "Madrid",
                "España",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                9,
                "Lola Dueñas",
                LocalDate.of(1929, 4, 16),
                "",
                "España",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                10,
                "Humphrey Bogart",
                LocalDate.of(1899, 12, 25),
                "New York, New York",
                "Estados Unidos",
                LocalDate.of(1957, 1, 14),
                "Los Angeles, C",
                false
        ));

        actores.add(new Actor(
                11,
                "Ingrid Bergman",
                LocalDate.of(1915, 8, 29),
                "Stockholm",
                "Suecia",
                LocalDate.of(1982, 8, 28),
                "Londres",
                false
        ));
    }

    /**
     * Aktoreen taularen goiburua pantailan erakusten du.
     */
    static void taulaGoiburuaErakutsi() {

        System.out.printf(
                "%-6s %-20s %-12s %-28s %-18s %-14s %-20s %-7s%n",
                "Kodea",
                "Izena",
                "Jaiotza",
                "Jaioterria",
                "Nazionalitatea",
                "Heriotza",
                "Heriotza lekua",
                "Bizirik"
        );

        System.out.println("-".repeat(135));
    }

    /**
     * Aktore baten datuak taulako lerro batean erakusten ditu.
     *
     * @param actor erakutsiko den aktorea
     */
    static void aktoreaLerroanErakutsi(Actor actor) {

        String jaioterria;

        if (actor.getLugarNacimiento().isEmpty()) {
            jaioterria = "-";
        } else {
            jaioterria = actor.getLugarNacimiento();
        }

        String heriotzaData;

        if (actor.getFechaMuerte() == null) {
            heriotzaData = "-";
        } else {
            heriotzaData = actor.getFechaMuerte().toString();
        }

        String heriotzaLekua;

        if (actor.getLugarMuerte().isEmpty()) {
            heriotzaLekua = "-";
        } else {
            heriotzaLekua = actor.getLugarMuerte();
        }

        String bizirik;

        if (actor.isVivo()) {
            bizirik = "Bai";
        } else {
            bizirik = "Ez";
        }

        System.out.printf(
                "%-6d %-20s %-12s %-28s %-18s %-14s %-20s %-7s%n",
                actor.getCodigo(),
                actor.getNombre(),
                actor.getFechaNacimiento(),
                jaioterria,
                actor.getNacionalidad(),
                heriotzaData,
                heriotzaLekua,
                bizirik
        );
    }

    /**
     * Aktore guztiak taula formatuan pantailan erakusten ditu.
     *
     * @param actores erakutsiko diren aktoreen zerrenda
     */
    static void aktoreakZerrendatu(ArrayList<Actor> actores) {

        taulaGoiburuaErakutsi();

        for (Actor actor : actores) {
            aktoreaLerroanErakutsi(actor);
        }
    }

    /**
     * Kode baten bidez aktore bat bilatzen du eta,
     * aurkituz gero, haren informazioa erakusten du.
     *
     * @param actores bilaketa egiteko erabiliko den aktoreen zerrenda
     */
    static void aktoreaKodezBilatu(ArrayList<Actor> actores) {

        int bilatutakoKodea =
                zenbakiOsoaIrakurri("Sartu aktorearen kodea: ");

        boolean aurkituta = false;

        for (Actor actor : actores) {

            if (actor.getCodigo() == bilatutakoKodea) {

                System.out.println();
                System.out.println(actor);

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
     * Bi daten artean jaiotako aktoreak bilatzen ditu
     * eta taula formatuan erakusten ditu.
     *
     * Hasierako eta amaierako datak bilaketan sartzen dira.
     *
     * @param actores bilaketa egiteko erabiliko den aktoreen zerrenda
     */
    static void aktoreakDatenArteanZerrendatu(
            ArrayList<Actor> actores) {

        LocalDate hasierakoData =
                dataIrakurri(
                        "Sartu hasierako data (YYYY-MM-DD): ");

        LocalDate amaierakoData =
                dataIrakurri(
                        "Sartu amaierako data (YYYY-MM-DD): ");

        // Hasierako data amaierakoa baino geroagokoa den egiaztatzen du
        if (hasierakoData.isAfter(amaierakoData)) {

            System.out.println(
                    "Errorea: hasierako data ezin da amaierako data baino geroagokoa izan.");

            return;
        }

        boolean aurkituta = false;

        System.out.println();
        taulaGoiburuaErakutsi();

        for (Actor actor : actores) {

            LocalDate jaiotzeData =
                    actor.getFechaNacimiento();

            // Hasierako eta amaierako datak barne hartzen ditu
            if ((jaiotzeData.isEqual(hasierakoData)
                    || jaiotzeData.isAfter(hasierakoData))
                    &&
                    (jaiotzeData.isEqual(amaierakoData)
                    || jaiotzeData.isBefore(amaierakoData))) {

                aktoreaLerroanErakutsi(actor);

                aurkituta = true;
            }
        }

        if (!aurkituta) {

            System.out.println(
                    "Ez dago data horien artean jaiotako aktorerik.");
        }
    }

    /**
     * Aktore berri bat sortu eta zerrendan gehitzen du.
     *
     * Aktorearen kodea bakarra dela egiaztatzen du.
     * Aktorea bizirik badago, heriotzaren datuak ez dira eskatzen.
     * Hilda badago, heriotza-data eta heriotza-lekua eskatzen dira.
     *
     * @param actores aktore berria gehituko den zerrenda
     */
    static void aktoreaGehitu(ArrayList<Actor> actores) {

        int kodea =
                zenbakiOsoaIrakurri("Sartu aktorearen kodea: ");

        // Kode bera duen aktorerik dagoen egiaztatzen du
        for (Actor actor : actores) {

            if (actor.getCodigo() == kodea) {

                System.out.println(
                        "Errorea: kode hori duen aktore bat existitzen da.");

                return;
            }
        }

        System.out.print("Sartu aktorearen izena: ");
        String izena = sc.nextLine();

        LocalDate jaiotzeData =
                dataIrakurri(
                        "Sartu jaiotze-data (YYYY-MM-DD): ");

        System.out.print("Sartu jaioterria: ");
        String jaioterria = sc.nextLine();

        System.out.print("Sartu nazionalitatea: ");
        String nazionalitatea = sc.nextLine();

        boolean bizirik =
                baiEzIrakurri("Aktorea bizirik dago? (B/E): ");

        LocalDate heriotzaData = null;
        String heriotzaLekua = "";

        if (!bizirik) {

            heriotzaData =
                    dataIrakurri(
                            "Sartu heriotza-data (YYYY-MM-DD): ");

            // Heriotza-data jaiotze-data baino lehenagokoa ezin da izan
            if (heriotzaData.isBefore(jaiotzeData)) {

                System.out.println(
                        "Errorea: heriotza-data ezin da jaiotze-data baino lehenagokoa izan.");

                return;
            }

            System.out.print("Sartu heriotza-lekua: ");
            heriotzaLekua = sc.nextLine();
        }

        Actor aktoreBerria = new Actor(
                kodea,
                izena,
                jaiotzeData,
                jaioterria,
                nazionalitatea,
                heriotzaData,
                heriotzaLekua,
                bizirik
        );

        actores.add(aktoreBerria);

        System.out.println();
        System.out.println("Aktorea behar bezala gehitu da:");
        System.out.println(aktoreBerria);
    }

    /**
     * Erabiltzaileak adierazitako kodea duen aktorea bilatu
     * eta zerrendatik ezabatzen du.
     *
     * Ezabatu aurretik aktorearen informazioa toString metodoaren
     * bidez erakusten du eta erabiltzaileari berrespena eskatzen dio.
     *
     * @param actores aktorea bilatu eta ezabatuko den zerrenda
     */
    static void aktoreaEzabatu(ArrayList<Actor> actores) {

        int kodea =
                zenbakiOsoaIrakurri(
                        "Sartu ezabatu nahi duzun aktorearen kodea: ");

        Actor ezabatzekoAktorea = null;

        // Kodea duen aktorea bilatzen du
        for (Actor actor : actores) {

            if (actor.getCodigo() == kodea) {

                ezabatzekoAktorea = actor;
                break;
            }
        }

        if (ezabatzekoAktorea == null) {

            System.out.println(
                    "Ez da kode hori duen aktorerik aurkitu.");

            return;
        }

        System.out.println();
        System.out.println("Ezabatu nahi duzun aktorea:");
        System.out.println(ezabatzekoAktorea);

        boolean baieztatu =
                baiEzIrakurri(
                        "Ziur zaude aktorea ezabatu nahi duzula? (B/E): ");

        if (baieztatu) {

            actores.remove(ezabatzekoAktorea);

            System.out.println(
                    "Aktorea behar bezala ezabatu da.");

        } else {

            System.out.println(
                    "Ezabatzea bertan behera utzi da.");
        }
    }

    /**
     * Aktoreen zerrenda izenaren arabera ordenatzen du.
     *
     * Ordenazioa alfabetikoki eta maiuskulak edo minuskulak
     * kontuan hartu gabe egiten da. Ondoren, zerrenda ordenatua
     * taula formatuan erakusten du.
     *
     * @param actores ordenatuko den aktoreen zerrenda
     */
    static void aktoreakOrdenatu(ArrayList<Actor> actores) {

        // Aktoreak izenaren arabera alfabetikoki ordenatzen ditu
        actores.sort(
                Comparator.comparing(
                        Actor::getNombre,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        System.out.println();
        System.out.println(
                "Aktoreak izenaren arabera ordenatu dira:");

        aktoreakZerrendatu(actores);
    }
}