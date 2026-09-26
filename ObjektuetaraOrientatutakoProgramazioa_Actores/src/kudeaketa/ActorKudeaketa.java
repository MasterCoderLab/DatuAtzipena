package kudeaketa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import eredua.Actor;

/**
 * Aktoreen kudeaketarekin lotutako eragiketak
 * egiteko metodoak biltzen dituen klasea.
 *
 * Klase honek aktoreak zerrendatu, bilatu, gehitu,
 * ezabatu, editatu, daten arabera iragazi eta
 * ordenatzeko funtzionaltasuna eskaintzen du.
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
     * Formatu okerra sartuz gero, data berriro eskatzen du.
     *
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     * @param mezua erabiltzaileari erakutsiko zaion mezua
     * @return erabiltzaileak sartutako data zuzena
     */
    public static LocalDate dataIrakurri(
            Scanner sc,
            String mezua) {

        LocalDate data = null;
        boolean dataZuzena = false;

        do {

            System.out.print(mezua);
            String testua = sc.nextLine();

            try {

                data = LocalDate.parse(testua);
                dataZuzena = true;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Errorea: data YYYY-MM-DD formatuan sartu behar duzu.");
            }

        } while (!dataZuzena);

        return data;
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

        boolean erantzunZuzena = false;
        boolean emaitza = false;

        do {

            System.out.print(mezua);
            String erantzuna = sc.nextLine().trim();

            if (erantzuna.equalsIgnoreCase("B")) {

                emaitza = true;
                erantzunZuzena = true;

            } else if (erantzuna.equalsIgnoreCase("E")) {

                emaitza = false;
                erantzunZuzena = true;

            } else {

                System.out.println(
                        "Errorea: B (bai) edo E (ez) sartu behar duzu.");
            }

        } while (!erantzunZuzena);

        return emaitza;
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

        // Aktore berria ArrayList-aren amaieran gehitzen du
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

            // ArrayList-etik objektua ezabatzen du
            actores.remove(ezabatzekoAktorea);

            System.out.println(
                    "Aktorea behar bezala ezabatu da.");

        } else {

            System.out.println(
                    "Ezabatzea bertan behera utzi da.");
        }
    }

    /**
     * Aktoreen zerrenda izenaren arabera alfabetikoki ordenatzen du.
     *
     * Ordenazioa Bubble Sort algoritmoaren bidez egiten da,
     * ArrayList-eko size(), get() eta set() metodoak erabiliz.
     *
     * @param actores ordenatuko den aktoreen zerrenda
     */
    public static void aktoreakOrdenatu(
            ArrayList<Actor> actores) {

        // Bubble Sort algoritmoa
        for (int i = 0; i < actores.size() - 1; i++) {

            for (int j = 0;
                    j < actores.size() - 1 - i;
                    j++) {

                Actor lehenengoa = actores.get(j);
                Actor hurrengoa = actores.get(j + 1);

                // Bi aktoreen izenak alfabetikoki konparatzen ditu
                if (lehenengoa.getNombre()
                        .compareToIgnoreCase(
                                hurrengoa.getNombre()) > 0) {

                    // Bi aktoreen posizioak trukatzen ditu
                    actores.set(j, hurrengoa);
                    actores.set(j + 1, lehenengoa);
                }
            }
        }

        System.out.println();
        System.out.println(
                "Aktoreak izenaren arabera ordenatu dira:");

        aktoreakZerrendatu(actores);
    }

    /**
     * Kode baten bidez aktore bat bilatzen du eta haren
     * datuak editatzeko aukera ematen du.
     *
     * Aldaketak Actor klaseko setter metodoen bidez egiten dira.
     * Aktorearen kodea aldatzean, kode berria errepikatuta
     * ez dagoela egiaztatzen da.
     *
     * @param actores aktorea bilatzeko eta aldatzeko erabiliko den zerrenda
     * @param sc teklatuko datuak irakurtzeko Scanner objektua
     */
    public static void aktoreaEditatu(
            ArrayList<Actor> actores,
            Scanner sc) {

        int kodea =
                zenbakiOsoaIrakurri(
                        sc,
                        "Sartu editatu nahi duzun aktorearen kodea: ");

        Actor editatzekoAktorea = null;

        // Kodearen bidez aktorea bilatzen du
        for (Actor actor : actores) {

            if (actor.getCodigo() == kodea) {

                editatzekoAktorea = actor;
                break;
            }
        }

        if (editatzekoAktorea == null) {

            System.out.println(
                    "Ez da kode hori duen aktorerik aurkitu.");

            return;
        }

        System.out.println();
        System.out.println("Editatuko den aktorea:");
        System.out.println(editatzekoAktorea);

        int aukera;

        do {

            System.out.println();
            System.out.println("===== AKTOREA EDITATU =====");
            System.out.println("1. Kodea aldatu");
            System.out.println("2. Izena aldatu");
            System.out.println("3. Jaiotze-data aldatu");
            System.out.println("4. Jaioterria aldatu");
            System.out.println("5. Nazionalitatea aldatu");
            System.out.println("6. Bizirik egoera aldatu");
            System.out.println("0. Amaitu");

            aukera =
                    zenbakiOsoaIrakurri(
                            sc,
                            "Aukeratu editatu nahi duzun eremua: ");

            switch (aukera) {

                case 1:

                    int kodeBerria =
                            zenbakiOsoaIrakurri(
                                    sc,
                                    "Sartu kode berria: ");

                    boolean kodeErrepikatua = false;

                    // Kode berria beste aktore batek duen egiaztatzen du
                    for (Actor actor : actores) {

                        if (actor != editatzekoAktorea
                                && actor.getCodigo() == kodeBerria) {

                            kodeErrepikatua = true;
                            break;
                        }
                    }

                    if (kodeErrepikatua) {

                        System.out.println(
                                "Errorea: kode hori duen aktore bat existitzen da.");

                    } else {

                        // Setter metodoaren bidez kodea aldatzen du
                        editatzekoAktorea.setCodigo(kodeBerria);

                        System.out.println(
                                "Kodea behar bezala aldatu da.");
                    }

                    break;

                case 2:

                    System.out.print("Sartu izen berria: ");
                    String izenBerria = sc.nextLine();

                    // Setter metodoaren bidez izena aldatzen du
                    editatzekoAktorea.setNombre(izenBerria);

                    System.out.println(
                            "Izena behar bezala aldatu da.");

                    break;

                case 3:

                    LocalDate jaiotzeDataBerria =
                            dataIrakurri(
                                    sc,
                                    "Sartu jaiotze-data berria (YYYY-MM-DD): ");

                    if (editatzekoAktorea.getFechaMuerte() != null
                            && jaiotzeDataBerria.isAfter(
                                    editatzekoAktorea.getFechaMuerte())) {

                        System.out.println(
                                "Errorea: jaiotze-data ezin da heriotza-data baino geroagokoa izan.");

                    } else {

                        editatzekoAktorea.setFechaNacimiento(
                                jaiotzeDataBerria);

                        System.out.println(
                                "Jaiotze-data behar bezala aldatu da.");
                    }

                    break;

                case 4:

                    System.out.print("Sartu jaioterri berria: ");
                    String jaioterriBerria = sc.nextLine();

                    editatzekoAktorea.setLugarNacimiento(
                            jaioterriBerria);

                    System.out.println(
                            "Jaioterria behar bezala aldatu da.");

                    break;

                case 5:

                    System.out.print(
                            "Sartu nazionalitate berria: ");

                    String nazionalitateBerria =
                            sc.nextLine();

                    editatzekoAktorea.setNacionalidad(
                            nazionalitateBerria);

                    System.out.println(
                            "Nazionalitatea behar bezala aldatu da.");

                    break;

                case 6:

                    boolean bizirik =
                            baiEzIrakurri(
                                    sc,
                                    "Aktorea bizirik dago? (B/E): ");

                    if (bizirik) {

                        // Egoera eta heriotzaren datuak setter bidez aldatzen ditu
                        editatzekoAktorea.setVivo(true);
                        editatzekoAktorea.setFechaMuerte(null);
                        editatzekoAktorea.setLugarMuerte("");

                        System.out.println(
                                "Aktorearen egoera behar bezala aldatu da.");

                    } else {

                        LocalDate heriotzaData =
                                dataIrakurri(
                                        sc,
                                        "Sartu heriotza-data (YYYY-MM-DD): ");

                        if (heriotzaData.isBefore(
                                editatzekoAktorea.getFechaNacimiento())) {

                            System.out.println(
                                    "Errorea: heriotza-data ezin da jaiotze-data baino lehenagokoa izan.");

                        } else {

                            System.out.print(
                                    "Sartu heriotza-lekua: ");

                            String heriotzaLekua =
                                    sc.nextLine();

                            // Datuak baliozkoak direnean bakarrik aldatzen ditu
                            editatzekoAktorea.setVivo(false);
                            editatzekoAktorea.setFechaMuerte(
                                    heriotzaData);
                            editatzekoAktorea.setLugarMuerte(
                                    heriotzaLekua);

                            System.out.println(
                                    "Aktorearen egoera behar bezala aldatu da.");
                        }
                    }

                    break;

                case 0:

                    System.out.println();
                    System.out.println(
                            "Aktorearen azken informazioa:");

                    System.out.println(editatzekoAktorea);

                    break;

                default:

                    System.out.println(
                            "Aukera ez da zuzena.");

                    break;
            }

        } while (aukera != 0);
    }
}