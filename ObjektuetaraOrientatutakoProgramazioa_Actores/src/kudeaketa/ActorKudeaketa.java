package kudeaketa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import eredua.Actor;

/**
 * Aktoreen kudeaketarekin lotutako eragiketak
 * egiteko metodoak biltzen dituen klasea.
 *
 * Klase honek aktoreak zerrendatu, bilatu, gehitu,
 * ezabatu, daten arabera iragazi eta ordenatzeko
 * funtzionaltasuna eskaintzen du.
 */
public class ActorKudeaketa {

    /**
     * Teklatutik zenbaki oso bat modu seguruan irakurtzen du.
     *
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return erabiltzaileak sartutako zenbaki osoa
     */
    public static int zenbakiOsoaIrakurri(
            Scanner sc,
            String mezua) {

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
     *
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return erabiltzaileak sartutako data
     */
    public static LocalDate dataIrakurri(
            Scanner sc,
            String mezua) {

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
     *
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return true B sartzen bada; false E sartzen bada
     */
    public static boolean baiEzIrakurri(
            Scanner sc,
            String mezua) {

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
     * Aktoreen taularen goiburua erakusten du.
     */
    private static void taulaGoiburuaErakutsi() {

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
    private static void aktoreaLerroanErakutsi(Actor actor) {

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
     * Aktore guztiak taula formatuan erakusten ditu.
     *
     * @param actores erakutsiko diren aktoreen zerrenda
     */
    public static void aktoreakZerrendatu(
            ArrayList<Actor> actores) {

        taulaGoiburuaErakutsi();

        for (Actor actor : actores) {
            aktoreaLerroanErakutsi(actor);
        }
    }

    /**
     * Kode baten bidez aktore bat bilatzen du.
     *
     * @param actores bilaketa egiteko aktoreen zerrenda
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     */
    public static void aktoreaKodezBilatu(
            ArrayList<Actor> actores,
            Scanner sc) {

        int bilatutakoKodea =
                zenbakiOsoaIrakurri(
                        sc,
                        "Sartu aktorearen kodea: ");

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
     * @param actores bilaketa egiteko aktoreen zerrenda
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     */
    public static void aktoreakDatenArteanZerrendatu(
            ArrayList<Actor> actores,
            Scanner sc) {

        LocalDate hasierakoData =
                dataIrakurri(
                        sc,
                        "Sartu hasierako data (YYYY-MM-DD): ");

        LocalDate amaierakoData =
                dataIrakurri(
                        sc,
                        "Sartu amaierako data (YYYY-MM-DD): ");

        // Daten ordena egokia dela egiaztatzen du
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
     * Aktore berri bat zerrendan gehitzen du.
     *
     * Aktorearen kodea bakarra dela egiaztatzen du.
     *
     * @param actores aktorea gehituko den zerrenda
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     */
    public static void aktoreaGehitu(
            ArrayList<Actor> actores,
            Scanner sc) {

        int kodea =
                zenbakiOsoaIrakurri(
                        sc,
                        "Sartu aktorearen kodea: ");

        // Kode errepikaturik ez dagoela egiaztatzen du
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
                        sc,
                        "Sartu jaiotze-data (YYYY-MM-DD): ");

        System.out.print("Sartu jaioterria: ");
        String jaioterria = sc.nextLine();

        System.out.print("Sartu nazionalitatea: ");
        String nazionalitatea = sc.nextLine();

        boolean bizirik =
                baiEzIrakurri(
                        sc,
                        "Aktorea bizirik dago? (B/E): ");

        LocalDate heriotzaData = null;
        String heriotzaLekua = "";

        if (!bizirik) {

            heriotzaData =
                    dataIrakurri(
                            sc,
                            "Sartu heriotza-data (YYYY-MM-DD): ");

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
     * Kode baten bidez aktore bat bilatu eta ezabatzen du.
     *
     * Ezabatu aurretik aktorearen informazioa erakusten du
     * eta erabiltzaileari berrespena eskatzen dio.
     *
     * @param actores aktorea ezabatuko den zerrenda
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     */
    public static void aktoreaEzabatu(
            ArrayList<Actor> actores,
            Scanner sc) {

        int kodea =
                zenbakiOsoaIrakurri(
                        sc,
                        "Sartu ezabatu nahi duzun aktorearen kodea: ");

        Actor ezabatzekoAktorea = null;

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
                        sc,
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
     * Aktoreen zerrenda izenaren arabera ordenatzen du
     * eta emaitza taula formatuan erakusten du.
     *
     * @param actores ordenatuko den aktoreen zerrenda
     */
    public static void aktoreakOrdenatu(
            ArrayList<Actor> actores) {

        // ArrayList bera ordenatzen du
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