package fitxategiak.aplikazioa;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import fitxategiak.eredua.Ikaslea;
import fitxategiak.kudeaketa.TestuFitxategiKudeatzailea;

/**
 * Programaren kontsolako menua kudeatzen duen klasea.
 * Erabiltzailearen sarrerak baliozkotzen ditu eta emaitzak erakusten ditu.
 * Fitxategi-eragiketak kudeatzaileari delegatzen dizkio.
 */
public class Menua {

    private final Scanner teklatua;
    private final TestuFitxategiKudeatzailea kudeatzailea;
    private final Ikaslea[] hasierakoDatuak;

    /**
     * Menuaren objektua sortzen du.
     *
     * @param teklatua programako Scanner bakarra
     * @param kudeatzailea fitxategiak kudeatzeko objektua
     * @param hasierakoDatuak hasieratzean erabiliko diren ikasleak
     */
    public Menua(Scanner teklatua, TestuFitxategiKudeatzailea kudeatzailea,
            Ikaslea[] hasierakoDatuak) {
        this.teklatua = teklatua;
        this.kudeatzailea = kudeatzailea;
        this.hasierakoDatuak = hasierakoDatuak;
    }

    /**
     * Programa abiarazten du, fitxategia aukeratu eta menua erakutsiz.
     */
    public void exekutatu() {
        try {
            if (!aukeratuFitxategia()) {
                erakutsiMezua("Programa amaitu da.");
                return;
            }

            int aukera;
            do {
                erakutsiMenua();
                aukera = irakurriAukera();

                switch (aukera) {
                    case 1 -> hasieratuFitxategia();
                    case 2 -> hustuFitxategia();
                    case 3 -> zenbatuErregistroak();
                    case 4 -> bilatuKodez();
                    case 5 -> bilatuHitzaz();
                    case 6 -> gehituErregistroa();
                    case 7 -> ezabatuErregistroa();
                    case 8 -> kopiatuFitxategia();
                    case 9 -> aldatuErregistroa();
                    case 0 -> erakutsiMezua("Programa amaitu da.");
                }
            } while (aukera != 0);

        } catch (NoSuchElementException e) {
            // Sarrera itxita badago, modu kontrolatuan amaitzen da.
            erakutsiMezua("Sarrera amaitu da. Programa ixten da.");
        }
    }

    /**
     * Lanerako fitxategiaren bidea eskatzen du eta behar denean sortzen du.
     *
     * @return true fitxategia egoki hautatu bada
     */
    private boolean aukeratuFitxategia() {
        System.out.println("=== 2AG3 - TESTU FITXATEGIAK ===");
        System.out.println("Fitxategiaren izena eta bidea eskatu behar dira hasieran.");

        boolean aukeratua = false;
        boolean irten = false;

        while (!aukeratua && !irten) {
            String bidea = irakurriLerroa(
                    "Fitxategiaren bidea (0 = irten): ");

            if (bidea.equals("0")) {
                irten = true;
            } else if (bidea.isBlank()) {
                erakutsiMezua("Bidea ezin da hutsik egon.");
            } else {
                boolean sortu = false;

                if (!kudeatzailea.badago(bidea)) {
                    sortu = baieztatu("Fitxategia ez da existitzen. Sortu? (b/e): ");
                }

                if (kudeatzailea.badago(bidea) || sortu) {
                    try {
                        kudeatzailea.hautatuFitxategia(bidea, sortu);
                        erakutsiMezua("Fitxategia hautatuta: "
                                + kudeatzailea.getFitxategia().getPath());
                        aukeratua = true;
                    } catch (IOException | IllegalArgumentException e) {
                        erakutsiErrorea(e);
                    }
                }
            }
        }
        return aukeratua;
    }

    /**
     * Menu nagusia eta uneko fitxategiaren izena erakusten ditu.
     */
    private void erakutsiMenua() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║          2AG3 - TESTU FITXATEGIAK                  ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ [1] Hasierako datuekin bete                        ║");
        System.out.println("║ [2] Fitxategia hustu                               ║");
        System.out.println("║ [3] Erregistroak zenbatu                           ║");
        System.out.println("║ [4] Kodearen arabera bilatu                        ║");
        System.out.println("║ [5] Hitzaren arabera bilatu                        ║");
        System.out.println("║ [6] Erregistro berria gehitu                       ║");
        System.out.println("║ [7] Erregistroa ezabatu                            ║");
        System.out.println("║ [8] Fitxategiaren kopia sortu                      ║");
        System.out.println("║ [9] Erregistroa aldatu                             ║");
        System.out.println("║ [0] Irten                                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println("Lan-fitxategia: " + kudeatzailea.getFitxategia().getPath());
    }

    /**
     * 0 eta 9 arteko menu-aukera bat irakurtzen du.
     *
     * @return baliozko aukera
     */
    private int irakurriAukera() {
        int aukera = -1;
        do {
            String sarrera = irakurriLerroa("Aukera (0-9): ");
            try {
                aukera = Integer.parseInt(sarrera);
                if (aukera < 0 || aukera > 9) {
                    erakutsiMezua("0 eta 9 arteko zenbakia sartu.");
                }
            } catch (NumberFormatException e) {
                erakutsiMezua("Zenbaki oso bat sartu behar duzu.");
            }
        } while (aukera < 0 || aukera > 9);
        return aukera;
    }

    /**
     * Galdera erakusten du eta lerro bateko sarrera irakurtzen du.
     *
     * @param galdera erakutsi beharreko galdera
     * @return erantzuna, kanpoko zuriunerik gabe
     */
    private String irakurriLerroa(String galdera) {
        System.out.print(galdera);
        return teklatua.nextLine().trim();
    }

    /**
     * Erabiltzaileari baieztapena eskatzen dio.
     * "b" edo "e" erantzunak bakarrik onartzen ditu.
     * Erantzuna baliogabea bada, berriro eskatzen du.
     *
     * @param galdera erabiltzaileari erakutsiko zaion galdera
     * @return true baiezkoa bada; false ezezkoa bada
     */
    private boolean baieztatu(String galdera) {
        String erantzuna;

        do {
            erantzuna = irakurriLerroa(galdera);

            if (!erantzuna.equalsIgnoreCase("b")
                    && !erantzuna.equalsIgnoreCase("e")) {
                erakutsiMezua("Errorea: 'b' edo 'e' idatzi behar duzu.");
            }

        } while (!erantzuna.equalsIgnoreCase("b")
                && !erantzuna.equalsIgnoreCase("e"));

        return erantzuna.equalsIgnoreCase("b");
    }

    /**
     * Zenbaki oso positibo bat irakurtzen du, erroreak kontrolatuz.
     *
     * @param galdera erabiltzaileari egingo zaion galdera
     * @return kode positiboa
     */
    private int irakurriKodea(String galdera) {
        int kodea = -1;
        do {
            try {
                kodea = Integer.parseInt(irakurriLerroa(galdera));
                if (kodea <= 0) {
                    erakutsiMezua("Kodeak zero baino handiagoa izan behar du.");
                }
            } catch (NumberFormatException e) {
                erakutsiMezua("Zenbaki oso bat sartu behar duzu.");
            }
        } while (kodea <= 0);
        return kodea;
    }

    /**
     * Testu-eremu bat eskatzen du, ';' karaktererik onartu gabe.
     *
     * @param galdera eremuaren galdera
     * @return testu baliozkoa
     */
    private String irakurriTestua(String galdera) {
        String testua;
        do {
            testua = irakurriLerroa(galdera);
            if (testua.isBlank() || testua.contains(";")) {
                erakutsiMezua("Eremua ezin da hutsik egon edo ';' eduki.");
            }
        } while (testua.isBlank() || testua.contains(";"));
        return testua;
    }

    /**
     * Ikaslearen egoera aktiboa eskatzen du.
     *
     * @return true erabiltzaileak b sartu badu
     */
    private boolean irakurriAktiboa() {
        String erantzuna;
        do {
            erantzuna = irakurriLerroa("Aktiboa? (b/e): ");
            if (!erantzuna.equalsIgnoreCase("b")
                    && !erantzuna.equalsIgnoreCase("e")) {
                erakutsiMezua("b (bai) edo e (ez) idatzi.");
            }
        } while (!erantzuna.equalsIgnoreCase("b")
                && !erantzuna.equalsIgnoreCase("e"));
        return erantzuna.equalsIgnoreCase("b");
    }

    /**
     * Kode jakin baterako ikasle baten gainerako datuak eskatzen ditu.
     *
     * @param kodea ikaslearen kodea
     * @return erabiltzaileak osatutako ikaslea
     */
    private Ikaslea irakurriIkaslea(int kodea) {
        String izena = irakurriTestua("Izena: ");
        String herria = irakurriTestua("Herria: ");
        boolean aktiboa = irakurriAktiboa();
        return new Ikaslea(kodea, izena, herria, aktiboa);
    }

    /**
     * Fitxategian hasierako taulako erregistroak idazten ditu.
     */
    private void hasieratuFitxategia() {
        if (!baieztatu("Uneko erregistroak galduko dira. Jarraitu? (b/e): ")) {
            erakutsiMezua("Eragiketa bertan behera utzi da.");
            return;
        }
        try {
            kudeatzailea.hasierakoDatuakIdatzi(hasierakoDatuak);
            erakutsiMezua("Hasierako datuak idatzi dira.");
        } catch (IOException | IllegalArgumentException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Fitxategiko erregistroak ezabatu aurretik baieztapena eskatzen du.
     */
    private void hustuFitxategia() {
        if (!baieztatu("Erregistro guztiak ezabatu? (b/e): ")) {
            erakutsiMezua("Ezabaketa bertan behera utzi da.");
            return;
        }
        try {
            kudeatzailea.hustuFitxategia();
            erakutsiMezua("Fitxategia hustu da (goiburua mantenduta).");
        } catch (IOException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Fitxategiko erregistroak zenbatu eta kopurua erakusten du.
     */
    private void zenbatuErregistroak() {
        try {
            erakutsiMezua("Erregistro kopurua: " + kudeatzailea.zenbatuErregistroak());
        } catch (IOException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Ikasle bat kodearen arabera bilatzen du.
     */
    private void bilatuKodez() {
        int kodea = irakurriKodea("Bilatzeko kodea: ");
        try {
            Ikaslea ikaslea = kudeatzailea.bilatuKodez(kodea);
            erakutsiMezua(ikaslea == null ? "Kode hori ez da aurkitu." : ikaslea.toString());
        } catch (IOException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Ikasleen izenetan edo herrietan hitz bat bilatzen du.
     */
    private void bilatuHitzaz() {
        String hitza = irakurriTestua("Bilatzeko hitza: ");
        try {
            String emaitza = kudeatzailea.bilatuHitzaz(hitza);
            if (emaitza.isEmpty()) {
                erakutsiMezua("Ez da bat datorren erregistrorik aurkitu.");
            } else {
                System.out.println("Aurkitutako erregistroak:");
                System.out.print(emaitza);
            }
        } catch (IOException | IllegalArgumentException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Erregistro berri bat teklatutik hartu eta amaieran gehitzen du.
     */
    private void gehituErregistroa() {
        int kodea = irakurriKodea("Kode berria: ");
        try {
            if (kudeatzailea.bilatuKodez(kodea) != null) {
                erakutsiMezua("Kode hori lehendik existitzen da.");
                return;
            }
            Ikaslea ikaslea = irakurriIkaslea(kodea);
            kudeatzailea.gehituErregistroa(ikaslea);
            erakutsiMezua("Erregistroa gehitu da: " + ikaslea);
        } catch (IOException | IllegalArgumentException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Erregistro bat ezabatzeko kodea eta baieztapena eskatzen ditu.
     */
    private void ezabatuErregistroa() {
        int kodea = irakurriKodea("Ezabatzeko kodea: ");
        try {
            Ikaslea ikaslea = kudeatzailea.bilatuKodez(kodea);
            if (ikaslea == null) {
                erakutsiMezua("Kode hori ez da aurkitu.");
                return;
            }
            System.out.println("Ezabatu beharrekoa: " + ikaslea);
            if (!baieztatu("Ziur ezabatu nahi duzula? (b/e): ")) {
                erakutsiMezua("Ezabaketa bertan behera utzi da.");
                return;
            }
            if (kudeatzailea.ezabatuErregistroa(kodea)) {
                erakutsiMezua("Erregistroa ezabatu da.");
            } else {
                erakutsiMezua("Kode hori ez da aurkitu.");
            }
        } catch (IOException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Fitxategiaren kopia sortzeko bidea eskatzen du.
     */
    private void kopiatuFitxategia() {
        String bidea = irakurriTestua("Kopiaren izena eta bidea: ");
        try {
            erakutsiMezua("Kopia sortuta: "
                    + kudeatzailea.kopiatuFitxategia(bidea).getPath());
        } catch (IOException | IllegalArgumentException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Kode baten ikaslea aurkitu eta haren eremuak aldatzen ditu.
     */
    private void aldatuErregistroa() {
        int kodea = irakurriKodea("Aldatzeko kodea: ");
        try {
            Ikaslea zaharra = kudeatzailea.bilatuKodez(kodea);
            if (zaharra == null) {
                erakutsiMezua("Kode hori ez da aurkitu.");
                return;
            }
            System.out.println("Uneko datuak: " + zaharra);
            System.out.println("Sartu datu berriak (kodea ez da aldatzen):");
            Ikaslea berria = irakurriIkaslea(kodea);
            if (kudeatzailea.aldatuErregistroa(berria)) {
                erakutsiMezua("Erregistroa aldatu da.");
            } else {
                erakutsiMezua("Kode hori ez da aurkitu.");
            }
        } catch (IOException | IllegalArgumentException e) {
            erakutsiErrorea(e);
        }
    }

    /**
     * Kontsolan informazio-mezua erakusten du.
     *
     * @param mezua erakutsi beharreko testua
     */
    private void erakutsiMezua(String mezua) {
        System.out.println("\n[ " + mezua + " ]");
    }

    /**
     * Salbuespen baten mezua erabiltzaileari erakusten dio.
     *
     * @param e gertatutako salbuespena
     */
    private void erakutsiErrorea(Exception e) {
        System.out.println("\nErrorea: " + e.getMessage());
    }
}
