package fitxategiak.kudeaketa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import fitxategiak.eredua.Ikaslea;

/**
 * 2AG3 testu-fitxategia zuzenean irakurri eta aldatzeko klasea.
 *
 * Erregistroak banan-banan prozesatzen dira BufferedReader bidez.
 * Ez da fitxategia ArrayList batean kargatzen.
 * Kontsolako sarrerak eta mezuak Menua klaseari dagozkio.
 */
public class TestuFitxategiKudeatzailea {

    private static final String GOIBURUA = "2AG3 1.0";

    private File fitxategia;

    /**
     * Bide batean fitxategirik badagoen egiaztatzen du.
     *
     * @param bidea fitxategiaren bidea
     * @return true bidea existitzen bada
     */
    public boolean badago(String bidea) {
        return bidea != null && new File(bidea).exists();
    }

    /**
     * Lanerako fitxategia hautatzen du.
     * Ez badago, baimena jasota goiburuarekin sortzen du.
     * Badagoenean, lehen lerroaren mota eta bertsioa egiaztatzen ditu.
     *
     * @param bidea fitxategiaren izena eta bidea
     * @param sortu fitxategia ez badago sortzeko baimena
     * @throws IOException ezin bada sortu, ireki edo goiburua okerra bada
     */
    public void hautatuFitxategia(String bidea, boolean sortu) throws IOException {
        if (bidea == null || bidea.isBlank()) {
            throw new IllegalArgumentException("Fitxategiaren bidea ezin da hutsik egon.");
        }

        File hautatua = new File(bidea.trim()).getCanonicalFile();

        if (hautatua.exists()) {
            if (!hautatua.isFile()) {
                throw new IOException("Hautatutako bidea ez da fitxategi bat.");
            }
            try (BufferedReader irakurlea = new BufferedReader(
                    new FileReader(hautatua, StandardCharsets.UTF_8))) {
                egiaztatuGoiburua(irakurlea);
            }
        } else {
            if (!sortu) {
                throw new IOException("Fitxategia ez da existitzen.");
            }
            File gurasoa = hautatua.getParentFile();
            if (gurasoa == null || !gurasoa.isDirectory() || !gurasoa.canWrite()) {
                throw new IOException("Helburuko direktorioa ez dago erabilgarri.");
            }
            if (!hautatua.createNewFile()) {
                throw new IOException("Ezin izan da fitxategia sortu.");
            }
            try (BufferedWriter idazlea = new BufferedWriter(
                    new FileWriter(hautatua, StandardCharsets.UTF_8))) {
                idazlea.write(GOIBURUA);
                idazlea.newLine();
            } catch (IOException e) {
                hautatua.delete();
                throw e;
            }
        }

        fitxategia = hautatua;
    }

    /**
     * Hautatutako fitxategiaren bidea itzultzen du.
     *
     * @return uneko fitxategia
     */
    public File getFitxategia() {
        return fitxategia;
    }

    /**
     * Hasierako datuak fitxategian idazten ditu, aurreko datuak ordeztuz.
     * Hasierako taula hemen bakarrik erabiltzen da.
     *
     * @param ikasleak hasierako taulako ikasleak
     * @throws IOException idazketa-hutsegitea gertatzen bada
     */
    public void hasierakoDatuakIdatzi(Ikaslea[] ikasleak) throws IOException {
        if (ikasleak == null) {
            throw new IllegalArgumentException("Hasierako datuak falta dira.");
        }
        for (Ikaslea ikaslea : ikasleak) {
            if (ikaslea == null) {
                throw new IllegalArgumentException("Hasierako erregistro baliogabea.");
            }
        }
        egiaztatuFitxategia();

        // FileWriter-ek aurretik zegoen edukia ordezten du.
        try (BufferedWriter idazlea = new BufferedWriter(
                new FileWriter(fitxategia, StandardCharsets.UTF_8))) {
            idazlea.write(GOIBURUA);
            idazlea.newLine();
            for (Ikaslea ikaslea : ikasleak) {
                idazlea.write(ikaslea.fitxategikoLerroa());
                idazlea.newLine();
            }
        }
    }

    /**
     * Fitxategiko erregistro guztiak kentzen ditu, goiburua mantenduz.
     *
     * @throws IOException idazketa-hutsegitea gertatzen bada
     */
    public void hustuFitxategia() throws IOException {
        egiaztatuFitxategia();
        try (BufferedWriter idazlea = new BufferedWriter(
                new FileWriter(fitxategia, StandardCharsets.UTF_8))) {
            idazlea.write(GOIBURUA);
            idazlea.newLine();
        }
    }

    /**
     * Fitxategiko erregistroak banan-banan zenbatzen ditu.
     * Goiburua ez da erregistro gisa kontatzen.
     *
     * @return erregistroen kopurua
     * @throws IOException irakurketa-errorea edo datu baliogabeak badaude
     */
    public int zenbatuErregistroak() throws IOException {
        int kopurua = 0;
        try (BufferedReader irakurlea = irekiIrakurlea()) {
            String lerroa;
            while ((lerroa = irakurlea.readLine()) != null) {
                Ikaslea.lerrotikIrakurri(lerroa);
                kopurua++;
            }
        }
        return kopurua;
    }

    /**
     * Kode jakin bat bilatzen du zuzenean fitxategian.
     *
     * @param kodea bilatu beharreko kodea
     * @return aurkitutako ikaslea, edo null existitzen ez bada
     * @throws IOException fitxategia ezin bada irakurri
     */
    public Ikaslea bilatuKodez(int kodea) throws IOException {
        try (BufferedReader irakurlea = irekiIrakurlea()) {
            String lerroa;
            while ((lerroa = irakurlea.readLine()) != null) {
                Ikaslea ikaslea = Ikaslea.lerrotikIrakurri(lerroa);
                if (ikaslea.getKodea() == kodea) {
                    return ikaslea;
                }
            }
        }
        return null;
    }

    /**
     * Izenaren edo herriaren barruan hitz bat duten erregistroak bilatzen ditu.
     * Fitxategia lerroz lerro irakurtzen da, datuen taula osatu gabe.
     *
     * @param hitza bilatu beharreko hitza
     * @return bat datozen erregistroen testua; hutsa ez badaude
     * @throws IOException irakurketa-errorea badago
     */
    public String bilatuHitzaz(String hitza) throws IOException {
        if (hitza == null || hitza.isBlank()) {
            throw new IllegalArgumentException("Bilatzeko hitza ezin da hutsik egon.");
        }

        StringBuilder aurkitutakoak = new StringBuilder();
        try (BufferedReader irakurlea = irekiIrakurlea()) {
            String lerroa;
            while ((lerroa = irakurlea.readLine()) != null) {
                Ikaslea ikaslea = Ikaslea.lerrotikIrakurri(lerroa);
                if (ikaslea.daukaHitza(hitza.trim())) {
                    aurkitutakoak.append(ikaslea).append(System.lineSeparator());
                }
            }
        }
        return aurkitutakoak.toString();
    }


/**
 * Erregistro berri bat fitxategiaren amaieran gehitzen du.
 * Kodea errepikatuta ez dagoela egiaztatzen du eta
 * azken lerroak lerro-jauzia duela ziurtatzen du.
 *
 * @param ikaslea gehitu beharreko ikaslea
 * @throws IOException fitxategian errorea gertatzen bada
 */
public void gehituErregistroa(Ikaslea ikaslea) throws IOException {

    if (ikaslea == null) {
        throw new IllegalArgumentException("Erregistro baliogabea.");
    }

    if (bilatuKodez(ikaslea.getKodea()) != null) {
        throw new IOException(
                "Kode hori lehendik existitzen da: " + ikaslea.getKodea());
    }

    // Fitxategiaren azken karakterea irakurtzen da.
    int azkenKarakterea = -1;

    try (BufferedReader irakurlea = new BufferedReader(
            new FileReader(fitxategia, StandardCharsets.UTF_8))) {

        int karakterea;

        while ((karakterea = irakurlea.read()) != -1) {
            azkenKarakterea = karakterea;
        }
    }

    // Fitxategiaren amaieran idazten da, edukia ezabatu gabe.
    try (BufferedWriter idazlea = new BufferedWriter(
            new FileWriter(fitxategia, StandardCharsets.UTF_8, true))) {

        // Azken lerroak jauzirik ez badu, gehitu aurretik sartzen da.
        if (azkenKarakterea != -1
                && azkenKarakterea != '\n'
                && azkenKarakterea != '\r') {
            idazlea.newLine();
        }

        idazlea.write(ikaslea.fitxategikoLerroa());
        idazlea.newLine();
    }
}


    /**
     * Aukeratutako erregistroa ezabatzen du, fitxategi laguntzaile baten bidez.
     *
     * @param kodea ezabatu beharreko erregistroaren kodea
     * @return true kodea aurkitu eta ezabatu bada
     * @throws IOException fitxategiak ezin badira behar bezala tratatu
     */
    public boolean ezabatuErregistroa(int kodea) throws IOException {
        return berridatziErregistroa(kodea, null);
    }

    /**
     * Kode baten erregistroa datu berriekin ordezten du.
     * Kodea bera mantentzen da, bikoiztu gabe.
     *
     * @param berria ikaslearen datu berriak, kode bera dutela
     * @return true ikaslea aurkitu eta aldatu bada
     * @throws IOException fitxategiak ezin badira behar bezala tratatu
     */
    public boolean aldatuErregistroa(Ikaslea berria) throws IOException {
        if (berria == null) {
            throw new IllegalArgumentException("Erregistro baliogabea.");
        }
        return berridatziErregistroa(berria.getKodea(), berria);
    }

    /**
     * Jatorrizko fitxategiaren kopia egiten du beste bide batean.
     * Ez du aurrez dagoen fitxategirik gainidazten.
     *
     * @param helburukoBidea kopiaren izena eta bidea
     * @return sortutako kopia
     * @throws IOException kopia ezin bada sortu
     */
    public File kopiatuFitxategia(String helburukoBidea) throws IOException {
        if (helburukoBidea == null || helburukoBidea.isBlank()) {
            throw new IllegalArgumentException("Helburuko bidea ezin da hutsik egon.");
        }
        egiaztatuFitxategia();

        File helburua = new File(helburukoBidea.trim()).getCanonicalFile();
        if (helburua.equals(fitxategia.getCanonicalFile())) {
            throw new IOException("Ezin da fitxategia bere buruaren gainean kopiatu.");
        }
        if (helburua.exists()) {
            throw new IOException("Helburuko fitxategia lehendik existitzen da.");
        }
        File gurasoa = helburua.getParentFile();
        if (gurasoa == null || !gurasoa.isDirectory() || !gurasoa.canWrite()) {
            throw new IOException("Helburuko direktorioa ez dago erabilgarri.");
        }
        if (!helburua.createNewFile()) {
            throw new IOException("Ezin izan da kopia sortu.");
        }

        // Kopia karakterez karaktere egiten da, jatorrizko lerro-jauziak mantenduz.
        try (FileReader irakurlea = new FileReader(fitxategia, StandardCharsets.UTF_8);
             FileWriter idazlea = new FileWriter(helburua, StandardCharsets.UTF_8)) {
            char[] bufferra = new char[1024];
            int irakurritakoak;
            while ((irakurritakoak = irakurlea.read(bufferra)) != -1) {
                idazlea.write(bufferra, 0, irakurritakoak);
            }
        } catch (IOException e) {
            helburua.delete();
            throw e;
        }
        return helburua;
    }

    /**
     * Ezabaketa eta aldaketa egiteko fitxategi laguntzailea erabiltzen du.
     * Jatorrizkoa ordeztu aurretik kopia osoa idatzi eta ixten du.
     *
     * @param kodea aurkitu beharreko kodea
     * @param berria ordezko erregistroa, edo null ezabatzeko
     * @return true erregistroa aurkitu eta eragiketa egin bada
     * @throws IOException kopiatzean edo ordeztean errorea badago
     */
    private boolean berridatziErregistroa(int kodea, Ikaslea berria)
            throws IOException {

        egiaztatuFitxategia();
        File laguntzailea = File.createTempFile(
                "2ag3_laguntza_", ".tmp", fitxategia.getParentFile());
        boolean aurkitua = false;

        try (BufferedReader irakurlea = irekiIrakurlea();
             BufferedWriter idazlea = new BufferedWriter(
                     new FileWriter(laguntzailea, StandardCharsets.UTF_8))) {

            idazlea.write(GOIBURUA);
            idazlea.newLine();

            String lerroa;
            while ((lerroa = irakurlea.readLine()) != null) {
                Ikaslea ikaslea = Ikaslea.lerrotikIrakurri(lerroa);
                if (ikaslea.getKodea() == kodea) {
                    aurkitua = true;
                    if (berria != null) {
                        idazlea.write(berria.fitxategikoLerroa());
                        idazlea.newLine();
                    }
                } else {
                    idazlea.write(lerroa);
                    idazlea.newLine();
                }
            }

        } catch (IOException | RuntimeException e) {
            laguntzailea.delete();
            throw e;
        }

        if (!aurkitua) {
            laguntzailea.delete();
            return false;
        }

        // Irakasleak eskatutako prozedura: jatorrizkoa ezabatu eta
        // fitxategi laguntzailea jatorrizko izenarekin berrizendatu.
        if (!fitxategia.delete()) {
            laguntzailea.delete();
            throw new IOException("Ezin izan da jatorrizko fitxategia ezabatu.");
        }
        if (!laguntzailea.renameTo(fitxategia)) {
            // Laguntzailea gordetzen da datuak berreskuratu ahal izateko.
            throw new IOException("Ezin izan da fitxategia berrizendatu. "
                    + "Datuak hemen gorde dira: " + laguntzailea.getAbsolutePath());
        }
        return true;
    }

    /**
     * Fitxategia badagoela eta lehen lerroa zuzena dela egiaztatzen du.
     *
     * @throws IOException fitxategia ez badago edo goiburua okerra bada
     */
    private void egiaztatuFitxategia() throws IOException {
        if (fitxategia == null || !fitxategia.isFile()) {
            throw new IOException("Ez dago lanerako fitxategirik erabilgarri.");
        }
        try (BufferedReader irakurlea = new BufferedReader(
                new FileReader(fitxategia, StandardCharsets.UTF_8))) {
            egiaztatuGoiburua(irakurlea);
        }
    }

    /**
     * BufferedReader bat irekitzen du, lehen lerroa egiaztatuta.
     * Deitzen duen metodoak try-with-resources bidez itxiko du.
     *
     * @return goiburua irakurrita duen BufferedReader objektua
     * @throws IOException fitxategia edo goiburua baliogabea bada
     */
    private BufferedReader irekiIrakurlea() throws IOException {
        if (fitxategia == null || !fitxategia.isFile()) {
            throw new IOException("Ez dago lanerako fitxategirik erabilgarri.");
        }
        BufferedReader irakurlea = new BufferedReader(
                new FileReader(fitxategia, StandardCharsets.UTF_8));
        try {
            egiaztatuGoiburua(irakurlea);
            return irakurlea;
        } catch (IOException e) {
            irakurlea.close();
            throw e;
        }
    }

    /**
     * Irakurritako lehen lerroaren mota eta bertsioa egiaztatzen ditu.
     *
     * @param irakurlea fitxategiko irakurlea
     * @throws IOException goiburua ez bada "2AG3 1.0"
     */
    private void egiaztatuGoiburua(BufferedReader irakurlea) throws IOException {
        if (!GOIBURUA.equals(irakurlea.readLine())) {
            throw new IOException("Fitxategiaren goiburua okerra da. "
                    + "Espero zena: " + GOIBURUA);
        }
    }
}
