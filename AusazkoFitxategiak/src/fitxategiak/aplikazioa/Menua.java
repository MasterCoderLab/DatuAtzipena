package fitxategiak.aplikazioa;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import fitxategiak.datuak.IbilgailuDatuak;
import fitxategiak.eredua.Ibilgailua;
import fitxategiak.kudeaketa.AusazkoFitxategiKudeatzailea;

/**
 * Erabiltzailearen eta ausazko fitxategiaren kudeatzailearen arteko lotura da.
 * Teklatuko sarrerak balidatzen ditu eta menuaren aukerak dagokien
 * eragiketetara bideratzen ditu, fitxategiaren logika hemen egin gabe.
 */
public class Menua {

    private final Scanner teklatua;
    private final AusazkoFitxategiKudeatzailea kudeatzailea;

    /**
     * Menua prestatzen du, Main klasetik jasotako baliabideak berrerabiliz.
     * Horrela, programa osoan Scanner bakarra erabiltzen da.
     *
     * @param teklatua erabiltzailearen sarrerak irakurtzeko Scanner partekatua
     * @param kudeatzailea fitxategiko eragiketak egiteko objektua
     */
    public Menua(Scanner teklatua, AusazkoFitxategiKudeatzailea kudeatzailea) {
        this.teklatua = teklatua;
        this.kudeatzailea = kudeatzailea;
    }

    /**
     * Lan-fitxategia prestatu eta erabiltzaileak irten arte menua errepikatzen du.
     * Aukera bakoitza bere metodora bideratzen da. Fitxategiko eragiketen
     * erroreak jakinarazten dira, programa bat-batean amaitu gabe.
     */
    public void exekutatu() {
        try {
            if (!prestatuFitxategia()) {
                return;
            }

            int aukera;
            do {
                erakutsiMenua();
                aukera = irakurriAukera();
                try {
                    switch (aukera) {
                        case 1 -> beteHasierakoDatuekin();
                        case 2 -> hustuFitxategia();
                        case 3 -> bilatuKodez();
                        case 4 -> bilatuHitzaz();
                        case 5 -> ezabatuErregistroa();
                        case 6 -> zerrendatuEzabatuak();
                        case 7 -> gehituErregistroa();
                        case 8 -> aldatuErregistroa();
                        case 0 -> erakutsiMezua("Programa amaitu da.");
                        default -> erakutsiMezua("Aukera baliogabea.");
                    }
                } catch (IOException | IllegalArgumentException e) {
                    erakutsiMezua("Errorea: " + e.getMessage());
                }
            } while (aukera != 0);
        } catch (NoSuchElementException e) {
            erakutsiMezua("Sarrera amaitu da. Programa ixten da.");
        }
    }

    /**
     * Argumentu bidez aukeratutako fitxategia erabiltzeko prest dagoela egiaztatzen du.
     * Lehendik badago, fitxategi arrunta dela eta goiburua zuzena dela
     * egiaztatzen du; bestela, sortu aurretik erabiltzailearen baimena eskatzen du.
     *
     * @return true fitxategiarekin lan egin badaiteke; false bestela
     */
    private boolean prestatuFitxategia() {
        File fitxategia = kudeatzailea.getFitxategia();
        try {
            if (fitxategia.exists()) {
                if (!fitxategia.isFile()) {
                    erakutsiMezua("Errorea: bidea ez da fitxategi bat.");
                    return false;
                }
                kudeatzailea.egiaztatuFitxategia();
                erakutsiMezua("Fitxategia aukeratuta: " + fitxategia.getPath());
            } else {
                if (!baieztatu("Fitxategia ez da existitzen. Sortu? (b/e): ")) {
                    erakutsiMezua("Programa amaitu da.");
                    return false;
                }
                kudeatzailea.sortuFitxategia();
                erakutsiMezua("Fitxategia sortu da: " + fitxategia.getPath());
            }
            return true;
        } catch (IOException e) {
            erakutsiMezua("Errorea: " + e.getMessage());
            return false;
        }
    }

    /**
     * Erabilgarri dauden zortzi eragiketak eta irteteko aukera erakusten ditu.
     * Aukeren aurkezpena hemen mantentzen da, kudeatzailetik bereizita.
     */
    private void erakutsiMenua() {
        System.out.println("\n========== AUSAZKO FITXATEGIAK ==========");
        System.out.println("1. Hasierako datuekin bete");
        System.out.println("2. Fitxategia hustu");
        System.out.println("3. Kodearen arabera bilatu");
        System.out.println("4. Hitzaren arabera bilatu");
        System.out.println("5. Erregistroa ezabatu");
        System.out.println("6. Ezabatutakoak zerrendatu");
        System.out.println("7. Erregistroa gehitu");
        System.out.println("8. Erregistroa aldatu");
        System.out.println("0. Irten");
        System.out.println("=========================================");
    }

    /**
     * Menuaren aukera irakurtzen du, 0 eta 8 arteko zenbaki osoa lortu arte.
     * Zenbakia ez den sarrera edo aukeraz kanpoko balioa jasotzean,
     * erabiltzaileari berriro galdetzen dio.
     *
     * @return menuan aukeratutako zenbakia
     */
    private int irakurriAukera() {
        int aukera = -1;
        while (aukera < 0 || aukera > 8) {
            String sarrera = irakurriLerroa("Aukera: ");
            try {
                aukera = Integer.parseInt(sarrera);
            } catch (NumberFormatException e) {
                aukera = -1;
            }
            if (aukera < 0 || aukera > 8) {
                erakutsiMezua("Aukeratu 0 eta 8 arteko zenbaki bat.");
            }
        }
        return aukera;
    }

    /**
     * Erregistro bat bilatzeko, ezabatzeko edo aldatzeko kodea irakurtzen du.
     * Identifikatzaile aktiboek zenbaki oso positiboa behar dute;
     * balio okerrak berriro eskatzen dira.
     *
     * @param galdera erabiltzaileari erakutsiko zaion mezua
     * @return erabiltzaileak sartutako kode positiboa
     */
    private int irakurriKodea(String galdera) {
        int kodea = 0;
        while (kodea <= 0) {
            String sarrera = irakurriLerroa(galdera);
            try {
                kodea = Integer.parseInt(sarrera);
            } catch (NumberFormatException e) {
                kodea = 0;
            }
            if (kodea <= 0) {
                erakutsiMezua("Kodea zenbaki oso positiboa izan behar da.");
            }
        }
        return kodea;
    }

    /**
     * Testu-eremu baten balioa irakurtzen du, hutsik ez dagoela eta
     * fitxategiko eremuaren gehieneko luzera errespetatzen duela egiaztatuz.
     *
     * @param galdera erabiltzaileari erakutsiko zaion mezua
     * @param gehienez onartutako gehieneko karaktere kopurua
     * @return balidatutako testua
     */
    private String irakurriTestua(String galdera, int gehienez) {
        String testua;
        do {
            testua = irakurriLerroa(galdera);
            if (testua.isEmpty() || testua.length() > gehienez) {
                erakutsiMezua("Testuak 1 eta " + gehienez + " karaktere artean izan behar ditu.");
            }
        } while (testua.isEmpty() || testua.length() > gehienez);
        return testua;
    }

    /**
     * Eragiketa bat egin aurretik baieztapena eskatzen du.
     * 'b' edo 'e' soilik onartzen ditu, maiuskulak eta minuskulak
     * bereizi gabe, ustekabeko erantzunek ez dezaten eragiketa baieztatu.
     *
     * @param galdera baieztapenaren mezua
     * @return true erabiltzaileak baietz esan badu; false ezezkoa bada
     */
    private boolean baieztatu(String galdera) {
        String erantzuna;
        do {
            erantzuna = irakurriLerroa(galdera);
            if (!erantzuna.equalsIgnoreCase("b") && !erantzuna.equalsIgnoreCase("e")) {
                erakutsiMezua("Idatzi 'b' (bai) edo 'e' (ez).");
            }
        } while (!erantzuna.equalsIgnoreCase("b") && !erantzuna.equalsIgnoreCase("e"));
        return erantzuna.equalsIgnoreCase("b");
    }

    /**
     * Teklatuko lerro osoa irakurtzen du eta ertzetako zuriuneak kentzen ditu.
     * Irakurketa toki bakarrean egiteak sarreraren tratamendua bateratzen du.
     *
     * @param galdera sarrera eskatzeko mezua
     * @return irakurritako lerroa, ertzetako zuriunerik gabe
     */
    private String irakurriLerroa(String galdera) {
        System.out.print(galdera);
        return teklatua.nextLine().trim();
    }

    /**
     * Erabiltzailearen baimena jaso ondoren hasierako hiru ibilgailuak idazten ditu.
     * Aurreko erregistroak ordezkatzen direnez, baieztapena ezinbestekoa da.
     * Hasierako taula aukera honetan bakarrik erabiltzen da.
     *
     * @throws IOException fitxategian idazterakoan errorea gertatzen bada
     */
    private void beteHasierakoDatuekin() throws IOException {
        if (!baieztatu("Aurreko datuak galduko dira. Jarraitu? (b/e): ")) {
            erakutsiMezua("Eragiketa bertan behera utzi da.");
            return;
        }
        kudeatzailea.beteHasierakoDatuekin(IbilgailuDatuak.lortu());
        erakutsiMezua("Hasierako datuak idatzi dira.");
    }

    /**
     * Baieztapena jaso ondoren fitxategiko erregistroak kentzen ditu.
     * Kudeatzaileak datu motaren eta bertsioaren goiburua mantentzen du.
     *
     * @throws IOException fitxategia hustu ezin bada
     */
    private void hustuFitxategia() throws IOException {
        if (!baieztatu("Erregistro guztiak kendu? (b/e): ")) {
            erakutsiMezua("Eragiketa bertan behera utzi da.");
            return;
        }
        kudeatzailea.hustuFitxategia();
        erakutsiMezua("Fitxategia hustu da; goiburua mantendu da.");
    }

    /**
     * Kode positibo baten bidez erregistro aktibo bat bilatzeko eskatzen du.
     * Bilaketa kudeatzaileak egiten du fitxategiaren gainean;
     * hemen emaitza edo ez dagoelako mezua baino ez da erakusten.
     *
     * @throws IOException fitxategia irakurtzerakoan errorea gertatzen bada
     */
    private void bilatuKodez() throws IOException {
        int kodea = irakurriKodea("Bilatzeko kodea: ");
        Ibilgailua ibilgailua = kudeatzailea.bilatuKodez(kodea);
        if (ibilgailua == null) {
            erakutsiMezua("Kode hori ez da aurkitu.");
        } else {
            erakutsiMezua(ibilgailua.toString());
        }
    }

    /**
     * Erabiltzaileak emandako hitza duten erregistro aktiboak erakusten ditu.
     * Kudeatzaileak matrikulan edo markan egiten du bilaketa;
     * bat-etortzerik ez badago, horren berri ematen da.
     *
     * @throws IOException fitxategia irakurtzerakoan errorea gertatzen bada
     */
    private void bilatuHitzaz() throws IOException {
        String hitza = irakurriTestua("Bilatzeko hitza: ", 100);
        String emaitza = kudeatzailea.bilatuHitzaz(hitza);
        if (emaitza.isEmpty()) {
            erakutsiMezua("Ez da bat datorren erregistrorik aurkitu.");
        } else {
            System.out.println(emaitza);
        }
    }

    /**
     * Erregistro aktiboa erakutsi eta ezabatzeko berrespena eskatzen du.
     * Kudeatzaileak ezabatze logikoa egiten du: identifikatzailea
     * negatibo bihurtzen da, gainerako eremuak aldatu gabe.
     *
     * @throws IOException fitxategia irakurri edo aldatzerakoan errorea badago
     */
    private void ezabatuErregistroa() throws IOException {
        int kodea = irakurriKodea("Ezabatzeko kodea: ");
        Ibilgailua ibilgailua = kudeatzailea.bilatuKodez(kodea);
        if (ibilgailua == null) {
            erakutsiMezua("Kode hori ez da aurkitu.");
            return;
        }
        System.out.println("Ezabatu beharrekoa: " + ibilgailua);
        if (!baieztatu("Ziur ezabatu nahi duzula? (b/e): ")) {
            erakutsiMezua("Eragiketa bertan behera utzi da.");
            return;
        }
        if (kudeatzailea.ezabatuErregistroa(kodea)) {
            erakutsiMezua("Erregistroa logikoki ezabatu da.");
        } else {
            erakutsiMezua("Kode hori ez da aurkitu.");
        }
    }

    /**
     * Identifikatzaile negatiboa duten erregistroak zerrendatzeko eskatzen du.
     * Ezabatutako erregistroen datuak gordeta daudela ikusteko balio du.
     *
     * @throws IOException fitxategia irakurtzerakoan errorea gertatzen bada
     */
    private void zerrendatuEzabatuak() throws IOException {
        String emaitza = kudeatzailea.zerrendatuEzabatuak();
        if (emaitza.isEmpty()) {
            erakutsiMezua("Ez dago ezabatutako erregistrorik.");
        } else {
            System.out.println(emaitza);
        }
    }

    /**
     * Matrikula eta marka balidatu ondoren erregistro berri bat gehitzen du.
     * Identifikatzailea ez zaio erabiltzaileari eskatzen: kudeatzaileak
     * erabilgarri dagoen txikiena esleitzen du, ezabatuak berrerabilita.
     *
     * @throws IOException fitxategian idazterakoan errorea gertatzen bada
     */
    private void gehituErregistroa() throws IOException {
        String matrikula = irakurriTestua("Matrikula: ", Ibilgailua.MATRIKULA_LUZERA);
        String marka = irakurriTestua("Marka: ", Ibilgailua.MARKA_LUZERA);
        Ibilgailua ibilgailua = kudeatzailea.gehituErregistroa(matrikula, marka);
        erakutsiMezua("Erregistroa gehitu da: " + ibilgailua);
    }

    /**
     * Kodearen arabera erregistro aktiboa aukeratu eta eremu berriak eskatzen ditu.
     * Erregistroaren identifikatzailea eta fitxategiko posizioa mantentzen dira;
     * matrikula eta marka soilik aldatzen dira.
     *
     * @throws IOException fitxategia irakurri edo aldatzerakoan errorea badago
     */
    private void aldatuErregistroa() throws IOException {
        int kodea = irakurriKodea("Aldatzeko kodea: ");
        Ibilgailua ibilgailua = kudeatzailea.bilatuKodez(kodea);
        if (ibilgailua == null) {
            erakutsiMezua("Kode hori ez da aurkitu.");
            return;
        }
        System.out.println("Uneko datuak: " + ibilgailua);
        String matrikula = irakurriTestua("Matrikula berria: ", Ibilgailua.MATRIKULA_LUZERA);
        String marka = irakurriTestua("Marka berria: ", Ibilgailua.MARKA_LUZERA);
        if (kudeatzailea.aldatuErregistroa(kodea, matrikula, marka)) {
            erakutsiMezua("Erregistroa aldatu da.");
        } else {
            erakutsiMezua("Kode hori ez da aurkitu.");
        }
    }

    /**
     * Programaren informazio- eta errore-mezuak modu bateratuan erakusten ditu.
     *
     * @param mezua pantailan azalduko den mezua
     */
    private void erakutsiMezua(String mezua) {
        System.out.println("\n[ " + mezua + " ]");
    }
}
