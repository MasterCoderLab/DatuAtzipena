
package fitxategiak.aplikazioa;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import fitxategiak.eredua.Erregistroa;
import fitxategiak.kudeaketa.FitxategiKudeatzailea;

/**
 * Programaren kontsolako menua kudeatzen duen klasea.
 *
 * Erabiltzailearen sarrera eta programaren irteera
 * klase honetan kudeatzen dira.
 *
 * Fitxategien eragiketak FitxategiKudeatzailea
 * klaseari delegatzen zaizkio.
 */
public class Menua {

    private final Scanner teklatua;
    private final FitxategiKudeatzailea kudeatzailea;
    private final Erregistroa[] datuBasea;

    /**
     * Menuaren objektua sortzen du.
     *
     * @param teklatua erabiltzailearen datuak irakurtzeko Scanner objektua
     * @param kudeatzailea fitxategiak kudeatzeko objektua
     * @param datuBasea programak erabiliko dituen erregistroak
     */
    public Menua(
            Scanner teklatua,
            FitxategiKudeatzailea kudeatzailea,
            Erregistroa[] datuBasea) {

        this.teklatua = teklatua;
        this.kudeatzailea = kudeatzailea;
        this.datuBasea = datuBasea;
    }

    /**
     * Menu nagusia abiarazten du eta erabiltzailearen
     * aukerak kudeatzen ditu.
     *
     * Sarrera ixten bada, programa modu kontrolatuan amaitzen da.
     */
    public void exekutatu() {

        try {
            int aukera;

            do {
                erakutsiMenua();
                aukera = irakurriAukera();

                switch (aukera) {

                    case 1:
                        aldatuDirektorioa();
                        break;

                    case 2:
                        zerrendatuDirektorioa();
                        break;

                    case 3:
                        sortuDirektorioa();
                        break;

                    case 4:
                        sortuAadFitxategiak();
                        break;

                    case 5:
                        ezabatuAadFitxategiak();
                        break;

                    case 0:
                        break;
                }

            } while (aukera != 0);

        } catch (NoSuchElementException e) {

            // Sarrera ixten bada, ez da salbuespenaren trazarik erakusten.
            erakutsiMezua("Erabiltzailearen sarrera itxi da.");
        }

        erakutsiAmaiera();
    }

    /**
     * Programaren menu nagusia erakusten du.
     */
    private void erakutsiMenua() {

        String bidea = moztuTestua(
                kudeatzailea.getLanDirektorioa().getAbsolutePath(),
                58);

        System.out.println();
        System.out.println(
                "╔══════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                  FITXATEGI KUDEATZAILEA                      ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════╣");

        System.out.printf(
                "║ %-60s ║%n",
                "Uneko lan-direktorioa:");

        System.out.printf(
                "║ %-60s ║%n",
                bidea);

        System.out.println(
                "╠══════════════════════════════════════════════════════════════╣");

        System.out.printf(
                "║ %-60s ║%n",
                "[1] Lan-direktorioa aldatu");

        System.out.printf(
                "║ %-60s ║%n",
                "[2] Fitxategiak eta direktorioak zerrendatu");

        System.out.printf(
                "║ %-60s ║%n",
                "[3] Direktorio berria sortu");

        System.out.printf(
                "║ %-60s ║%n",
                "[4] .aad fitxategiak sortu");

        System.out.printf(
                "║ %-60s ║%n",
                "[5] .aad fitxategiak ezabatu");

        System.out.printf(
                "║ %-60s ║%n",
                "[0] Irten");

        System.out.println(
                "╚══════════════════════════════════════════════════════════════╝");

        System.out.println();
    }

    /**
     * Galdera bat erakusten du eta erabiltzailearen
     * erantzuna irakurtzen du.
     *
     * @param galdera erabiltzaileari erakutsiko zaion mezua
     * @return irakurritako testua, kanpoko zuriunerik gabe
     */
    private String irakurriLerroa(String galdera) {

        System.out.print(galdera);
        return teklatua.nextLine().trim();
    }

    /**
     * Menuaren aukera irakurtzen eta baliozkotzen du.
     *
     * Aukera baliogabea bada, berriro eskatzen da.
     *
     * @return erabiltzaileak aukeratutako zenbakia
     */
    private int irakurriAukera() {

        int aukera = -1;

        do {
            String testua = irakurriLerroa("┌─ Aukera (0-5): ");

            try {
                aukera = Integer.parseInt(testua);

                if (aukera < 0 || aukera > 5) {
                    erakutsiMezua("0 eta 5 arteko aukera bat sartu.");
                }

            } catch (NumberFormatException e) {

                aukera = -1;
                erakutsiMezua("Zenbaki oso bat sartu behar duzu.");
            }

        } while (aukera < 0 || aukera > 5);

        return aukera;
    }

    /**
     * Lan-direktorio berri baten bidea eskatzen du (CD).
     *
     * Bidea hutsik badago, uneko direktorioa erakusten da.
     */
    private void aldatuDirektorioa() {

        String bidea = irakurriLerroa(
                "└─ Direktorioaren bidea (hutsik = unekoa): ");

        try {
            File direktorioa = kudeatzailea.aldatuLanDirektorioa(bidea);

            erakutsiMezua(
                    "Lan-direktorioa: " + direktorioa.getPath());

        } catch (IOException | IllegalArgumentException e) {

            erakutsiMezua("Errorea: " + e.getMessage());
        }
    }

    /**
     * Lan-direktorioko fitxategiak eta direktorioak
     * modu tabulatuan erakusten ditu (DIR).
     *
     * Kudeatzaileak edukia irakurtzen du eta
     * menuak emaitzaren aurkezpena egiten du.
     */
    private void zerrendatuDirektorioa() {

        try {
            File[] elementuak = kudeatzailea.zerrendatu();

            SimpleDateFormat dataFormatua =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm");

            int fitxategiKopurua = 0;
            int direktorioKopurua = 0;
            long tamainaGuztira = 0;

            System.out.println();

            System.out.println(
                    "Lan-direktorioa: "
                            + kudeatzailea.getLanDirektorioa().getAbsolutePath());

            System.out.println();

            System.out.printf(
                    "%-17s %-8s %12s  %s%n",
                    "DATA", "MOTA", "TAMAINA", "IZENA");

            System.out.println(
                    "----------------------------------------------------------------");

            for (File elementua : elementuak) {

                long azkenAldaketa = elementua.lastModified();

                String data = azkenAldaketa == 0
                        ? "-"
                        : dataFormatua.format(new Date(azkenAldaketa));

                String mota;
                String tamaina = "";

                if (elementua.isDirectory()) {

                    mota = "<DIR>";
                    direktorioKopurua++;

                } else if (elementua.isFile()) {

                    mota = "<FILE>";
                    fitxategiKopurua++;

                    long luzera = elementua.length();

                    tamaina = String.valueOf(luzera);
                    tamainaGuztira += luzera;

                } else {

                    mota = "<BESTE>";
                }

                System.out.printf(
                        "%-17s %-8s %12s  %s%n",
                        data,
                        mota,
                        tamaina,
                        elementua.getName());
            }

            System.out.println();

            System.out.println(
                    fitxategiKopurua
                            + " fitxategi, "
                            + tamainaGuztira
                            + " byte.");

            System.out.println(
                    direktorioKopurua + " direktorio.");

        } catch (IOException e) {

            erakutsiMezua("Errorea: " + e.getMessage());
        }
    }

    /**
     * Direktorio berri baten izena eskatzen du
     * eta direktorioa sortzen saiatzen da (MKDIR).
     */
    private void sortuDirektorioa() {

        String izena = irakurriLerroa(
                "└─ Direktorio berriaren izena: ");

        try {
            File direktorioa = kudeatzailea.sortuDirektorioa(izena);

            erakutsiMezua(
                    "Direktorioa sortuta: " + direktorioa.getName());

        } catch (IOException | IllegalArgumentException e) {

            erakutsiMezua("Errorea: " + e.getMessage());
        }
    }

    /**
     * Datu-baseko erregistro bakoitzeko .aad fitxategia
     * sortzen saiatzen da.
     *
     * Fitxategi baten erroreak ez du gainerakoen
     * sorrera gelditzen.
     */
    private void sortuAadFitxategiak() {

        if (datuBasea == null || datuBasea.length == 0) {
            erakutsiMezua("Datu-basea hutsik dago.");
            return;
        }

        int sortuak = 0;
        int lehendikZeudenak = 0;
        int erroreak = 0;

        for (Erregistroa erregistroa : datuBasea) {

            try {
                boolean sortuta = kudeatzailea.sortuAadFitxategia(erregistroa);

                if (sortuta) {

                    System.out.println(
                            "Sortuta: " + erregistroa.getIzena().trim() + ".aad");

                    sortuak++;

                } else {

                    System.out.println(
                            "Lehendik existitzen da: "
                                    + erregistroa.getIzena().trim() + ".aad");

                    lehendikZeudenak++;
                }

            } catch (IOException | IllegalArgumentException e) {

                System.out.println(
                        "Errorea fitxategia sortzean: " + e.getMessage());

                erroreak++;
            }
        }

        erakutsiMezua(
                sortuak + " sortuta, "
                        + lehendikZeudenak + " lehendik, "
                        + erroreak + " errore.");
    }

    /**
     * .aad fitxategiak ezabatu aurretik
     * erabiltzaileari baieztapena eskatzen dio.
     *
     * Ezabaketa uneko lan-direktoriora mugatzen da.
     */
    private void ezabatuAadFitxategiak() {

        File[] fitxategiak;

        try {
            fitxategiak = kudeatzailea.aadFitxategiak();

        } catch (IOException e) {

            erakutsiMezua("Errorea: " + e.getMessage());
            return;
        }

        if (fitxategiak.length == 0) {

            erakutsiMezua(
                    "Ez dago .aad fitxategirik lan-direktorioan.");

            return;
        }

        String erantzuna = irakurriLerroa(
                "└─ " + fitxategiak.length
                        + " .aad fitxategi ezabatuko dira. Ziur? (b/e): ");

        if (!erantzuna.equalsIgnoreCase("b")) {

            erakutsiMezua(
                    "Ezabaketa bertan behera utzi da.");

            return;
        }

        int ezabatuak = 0;
        int erroreak = 0;

        for (File fitxategia : fitxategiak) {

            try {
                kudeatzailea.ezabatuAadFitxategia(fitxategia);

                System.out.println(
                        "Ezabatuta: " + fitxategia.getName());

                ezabatuak++;

            } catch (IOException | IllegalArgumentException e) {

                System.out.println(
                        "Errorea fitxategia ezabatzean: "
                                + e.getMessage());

                erroreak++;
            }
        }

        erakutsiMezua(
                ezabatuak + " .aad fitxategi ezabatuta, "
                        + erroreak + " errore.");
    }

    /**
     * Mezu bat modu bisualean erakusten du.
     *
     * Mezuaren luzerara egokitzen da, markoa ez apurtzeko.
     *
     * @param mezua erakutsi beharreko mezua
     */
    private void erakutsiMezua(String mezua) {

        int zabalera = Math.max(42, mezua.length());
        String marra = "─".repeat(zabalera + 2);

        System.out.println();
        System.out.println("┌" + marra + "┐");
        System.out.printf("│ %-" + zabalera + "s │%n", mezua);
        System.out.println("└" + marra + "┘");
    }

    /**
     * Programa amaitzean mezua erakusten du.
     */
    private void erakutsiAmaiera() {

        System.out.println();
        System.out.println(
                "╔════════════════════════════════════════════╗");

        System.out.printf(
                "║ %-42s ║%n",
                "Programa amaitu da.");

        System.out.println(
                "╚════════════════════════════════════════════╝");
    }

    /**
     * Testu luzeegiak mozten ditu menuan hobeto ikusteko.
     *
     * @param testua moztu beharreko testua
     * @param maxLuzera gehienezko luzera
     * @return egokitutako testua
     */
    private String moztuTestua(
            String testua,
            int maxLuzera) {

        if (testua == null) {
            return "";
        }

        if (testua.length() <= maxLuzera) {
            return testua;
        }

        return "..."
                + testua.substring(
                        testua.length() - (maxLuzera - 3));
    }
}
