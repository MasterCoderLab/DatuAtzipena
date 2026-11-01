package fitxategiak.kudeaketa;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;

import fitxategiak.eredua.Ibilgailua;

/**
 * Ibilgailuak luzera finkoko erregistroetan gordetzen eta irakurtzen ditu.
 * Goiburuak 40 byte ditu; erregistro bakoitzak, 64 byte. Kodearen arabera
 * posizioa kalkulatzen da, aurreko erregistroak irakurri gabe sartzeko.
 * Ezabatzea logikoa da: kodea negatibo bihurtzen da, datuak mantenduz.
 */
public class AusazkoFitxategiKudeatzailea {

    /** Fitxategiaren mota eta bertsioa identifikatzeko testua. */
    private static final String GOIBURUA = "IBILGAILUAK 1.0";

    /** Goiburuari gordetzeko erreserbatzen zaizkion 20 karaktereak. */
    private static final int GOIBURU_LUZERA = 20;

    /** Goiburua: 20 karaktere * 2 byte (writeChar) = 40 byte. */
    private static final int GOIBURU_BYTEAK = GOIBURU_LUZERA * 2;

    /** Erregistroa: 4 byte (int) + 10 * 2 (matrikula) + 20 * 2 (marka) = 64 byte. */
    private static final int ERREGISTRO_BYTEAK = 4
            + Ibilgailua.MATRIKULA_LUZERA * 2
            + Ibilgailua.MARKA_LUZERA * 2;

    private final File fitxategia;

    /**
     * Eragiketek erabiliko duten fitxategia ezartzen du.
     * Fitxategia ireki edo sortzea beste metodo batzuen ardura da.
     *
     * @param fitxategia komando-lerrotik jasotako lan-fitxategia
     */
    public AusazkoFitxategiKudeatzailea(File fitxategia) {
        this.fitxategia = fitxategia;
    }

    /**
     * Menuak fitxategiaren existentzia eta mota egiaztatzeko bidea ematen du.
     *
     * @return kudeatzailearen lan-fitxategia
     */
    public File getFitxategia() {
        return fitxategia;
    }

    /**
     * Fitxategi berria sortu eta mota eta bertsioa duen goiburua idazten du.
     * Lehendik existitzen den fitxategia ez da gainidazten.
     *
     * @throws IOException fitxategia existitzen bada edo sortu ezin bada
     */
    public void sortuFitxategia() throws IOException {
        if (fitxategia.exists()) {
            throw new IOException("Fitxategia lehendik existitzen da.");
        }
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "rw")) {
            idatziTestua(fitx, GOIBURUA, GOIBURU_LUZERA);
        }
    }

    /**
     * Existitzen den fitxategiaren egitura egiaztatzen du.
     * Goiburu zuzena, erregistroen 64 byteko tamaina eta kode bakoitzaren
     * kokapena aztertzen ditu. Ezabatutako kode negatiboak ere onartzen dira.
     *
     * @throws IOException formatua baliogabea bada edo irakurketa huts egiten badu
     */
    public void egiaztatuFitxategia() throws IOException {
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "r")) {
            if (fitx.length() < GOIBURU_BYTEAK
                    || (fitx.length() - GOIBURU_BYTEAK) % ERREGISTRO_BYTEAK != 0) {
                throw new IOException("Fitxategiaren tamaina baliogabea da.");
            }
            if (!GOIBURUA.equals(irakurriTestua(fitx, GOIBURU_LUZERA))) {
                throw new IOException("Fitxategiaren mota edo bertsioa ez da zuzena.");
            }
            long kopurua = (fitx.length() - GOIBURU_BYTEAK) / ERREGISTRO_BYTEAK;
            for (long kodea = 1; kodea <= kopurua; kodea++) {
                fitx.seek(GOIBURU_BYTEAK + (kodea - 1) * ERREGISTRO_BYTEAK);
                int gordetakoKodea = fitx.readInt();
                if (gordetakoKodea != kodea && gordetakoKodea != -kodea) {
                    throw new IOException("Erregistroaren kodea eta posizioa ez datoz bat.");
                }
            }
        }
    }

    /**
     * Aurreko edukia ordezkatu eta hasierako taulako erregistroak idazten ditu.
     * Kode bakoitzaren posizioan idazten da, goiburuaren ondoren.
     * Menuak aurrez berrespena eskatu behar du, aurreko datuak galduko baitira.
     *
     * @param ibilgailuak hasierako taulatik sortutako ibilgailuak
     * @throws IOException idazketak huts egiten badu
     */
    public void beteHasierakoDatuekin(Ibilgailua[] ibilgailuak) throws IOException {
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "rw")) {
            fitx.setLength(0);
            idatziTestua(fitx, GOIBURUA, GOIBURU_LUZERA);
            for (Ibilgailua ibilgailua : ibilgailuak) {
                fitx.seek(posizioa(ibilgailua.getKodea()));
                idatziErregistroa(fitx, ibilgailua);
            }
        }
    }

    /**
     * Erregistroen eremua mozten du, goiburuko lehen 40 byteak utziz.
     * Horrela, fitxategiak bere mota eta bertsioa mantentzen ditu.
     *
     * @throws IOException fitxategia aldatu ezin bada
     */
    public void hustuFitxategia() throws IOException {
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "rw")) {
            fitx.setLength(GOIBURU_BYTEAK);
        }
    }

    /**
     * Kodearen posiziora zuzenean joan eta erregistro aktiboa irakurtzen du.
     * Ez ditu aurreko erregistroak zeharkatzen; ezabatutakoa edo existitzen
     * ez dena ez da bilaketa arrunt batean erakusten.
     *
     * @param kodea bilatu beharreko identifikatzaile positiboa
     * @return ibilgailua aurkitzen bada; null bestela
     * @throws IOException irakurketa huts egiten badu
     */
    public Ibilgailua bilatuKodez(int kodea) throws IOException {
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "r")) {
            if (!aktiboaDago(fitx, kodea)) {
                return null;
            }
            fitx.seek(posizioa(kodea));
            return irakurriErregistroa(fitx);
        }
    }

    /**
     * Matrikulan edo markan testu-zatia duten erregistro aktiboak bilatzen ditu.
     * Testuaren bilaketak erregistroak banan-banan irakurtzea eskatzen du;
     * ez dira fitxategiko datu guztiak bilduma batean kargatzen.
     *
     * @param hitza bilatu beharreko testu-zatia
     * @return bat datozen erregistroen testua, edo kate hutsa
     * @throws IOException irakurketa huts egiten badu
     */
    public String bilatuHitzaz(String hitza) throws IOException {
        String bilaketa = hitza.trim().toLowerCase(Locale.ROOT);
        StringBuilder emaitza = new StringBuilder();
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "r")) {
            fitx.seek(GOIBURU_BYTEAK);
            while (fitx.getFilePointer() < fitx.length()) {
                Ibilgailua ibilgailua = irakurriErregistroa(fitx);
                if (!ibilgailua.ezabatutaDago()
                        && (ibilgailua.getMatrikula().toLowerCase(Locale.ROOT).contains(bilaketa)
                        || ibilgailua.getMarka().toLowerCase(Locale.ROOT).contains(bilaketa))) {
                    emaitza.append(ibilgailua).append(System.lineSeparator());
                }
            }
        }
        return emaitza.toString();
    }

    /**
     * Erregistro aktiboaren identifikatzailea negatibo bihurtzen du.
     * Beste eremuak eta fitxategiaren luzera ez dira aldatzen; horrela,
     * aurrerago posizio bera berrerabil daiteke.
     *
     * @param kodea ezabatu beharreko identifikatzaile positiboa
     * @return true erregistroa ezabatu bada; false aurkitu ez bada
     * @throws IOException irakurketa edo idazketa huts egiten badu
     */
    public boolean ezabatuErregistroa(int kodea) throws IOException {
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "rw")) {
            if (!aktiboaDago(fitx, kodea)) {
                return false;
            }
            fitx.seek(posizioa(kodea));
            fitx.writeInt(-kodea);
            return true;
        }
    }

    /**
     * Kode negatiboa duten erregistroak irakurri eta testu gisa biltzen ditu.
     * Datuak fitxategitik zuzenean irakurtzen dira, posizioz posizio.
     *
     * @return ezabatutako erregistroen testua, edo kate hutsa
     * @throws IOException irakurketa huts egiten badu
     */
    public String zerrendatuEzabatuak() throws IOException {
        StringBuilder emaitza = new StringBuilder();
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "r")) {
            fitx.seek(GOIBURU_BYTEAK);
            while (fitx.getFilePointer() < fitx.length()) {
                Ibilgailua ibilgailua = irakurriErregistroa(fitx);
                if (ibilgailua.ezabatutaDago()) {
                    emaitza.append(ibilgailua).append(System.lineSeparator());
                }
            }
        }
        return emaitza.toString();
    }

    /**
     * Erregistro berriarentzat kode positibo erabilgarri txikiena bilatzen du.
     * Lehenik ezabatutako posizioak berrerabiltzen ditu; halakorik ez badago,
     * fitxategiaren amaieran idazten du. Kodea programak berak esleitzen du.
     *
     * @param matrikula ibilgailu berriaren matrikula
     * @param marka ibilgailu berriaren marka
     * @return kode automatikoa duen ibilgailu berria
     * @throws IllegalArgumentException matrikula aktibo batek erabiltzen badu
     * @throws IOException irakurketa edo idazketa huts egiten badu
     */
    public Ibilgailua gehituErregistroa(String matrikula, String marka) throws IOException {
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "rw")) {
            long kopurua = (fitx.length() - GOIBURU_BYTEAK) / ERREGISTRO_BYTEAK;
            int kodea = 1;
            while (kodea <= kopurua) {
                fitx.seek(posizioa(kodea));
                if (fitx.readInt() < 0) {
                    break;
                }
                kodea++;
            }
            Ibilgailua berria = new Ibilgailua(kodea, matrikula, marka);
            if (matrikulaErabilita(fitx, berria.getMatrikula(), 0)) {
                throw new IllegalArgumentException("Matrikula dagoeneko erabilita dago.");
            }
            fitx.seek(posizioa(kodea));
            idatziErregistroa(fitx, berria);
            return berria;
        }
    }

    /**
     * Erregistro aktiboaren testu-eremuak bere posizioan ordezkatzen ditu.
     * Identifikatzailea ez da aldatzen, eta ezabatutako erregistroak
     * ez dira metodo honen bidez aldatzen.
     *
     * @param kodea aldatu beharreko identifikatzaile positiboa
     * @param matrikula matrikula berria
     * @param marka marka berria
     * @return true erregistroa aldatu bada; false aurkitu ez bada
     * @throws IllegalArgumentException matrikula beste ibilgailu aktibo batek badu
     * @throws IOException irakurketa edo idazketa huts egiten badu
     */
    public boolean aldatuErregistroa(int kodea, String matrikula, String marka)
            throws IOException {
        try (RandomAccessFile fitx = new RandomAccessFile(fitxategia, "rw")) {
            if (!aktiboaDago(fitx, kodea)) {
                return false;
            }
            Ibilgailua berria = new Ibilgailua(kodea, matrikula, marka);
            if (matrikulaErabilita(fitx, berria.getMatrikula(), kodea)) {
                throw new IllegalArgumentException("Matrikula dagoeneko erabilita dago.");
            }
            fitx.seek(posizioa(kodea));
            idatziErregistroa(fitx, berria);
            return true;
        }
    }

    /**
     * Kodeari dagokion erregistroa existitzen den eta aktibo dagoen egiaztatzen du.
     * Hiru eragiketek kontrol bera erabiltzen dute: bilatu, ezabatu eta aldatu.
     * Fitxategiko erakuslea kodea irakurri ondoren gelditzen da; deitzaileak
     * berriro kokatu behar du erregistro osoa irakurri edo aldatzeko.
     *
     * @param fitx irekitako ausazko sarbideko fitxategia
     * @param kodea egiaztatu beharreko kode positiboa
     * @return true posizioa existitzen bada eta kodea positiboa bada
     * @throws IOException fitxategia irakurtzean errorea gertatzen bada
     */
    private boolean aktiboaDago(RandomAccessFile fitx, int kodea) throws IOException {
        long posizioa = posizioa(kodea);
        if (posizioa + ERREGISTRO_BYTEAK > fitx.length()) {
            return false;
        }
        fitx.seek(posizioa);
        return fitx.readInt() == kodea;
    }

    /**
     * Matrikula beste erregistro aktibo batean dagoen egiaztatzen du.
     * Ezabatutako erregistroak baztertzen dira, haien matrikulak berrerabil
     * daitezkeelako. Aldatzean, erregistroaren kode propioa ere baztertzen da.
     * Bilaketak fitxategiko erakuslea mugitzen du; deitzaileak berriro
     * kokatu behar du fitxategia idatzi aurretik.
     *
     * @param fitx irekitako ausazko sarbideko fitxategia
     * @param matrikula egiaztatu beharreko matrikula, normalizatuta
     * @param baztertuKodea baztertu beharreko kodea; 0, inor ez baztertzeko
     * @return true matrikula beste erregistro aktibo batek badu
     * @throws IOException fitxategia irakurtzean errorea gertatzen bada
     */
    private boolean matrikulaErabilita(RandomAccessFile fitx, String matrikula,
            int baztertuKodea) throws IOException {
        fitx.seek(GOIBURU_BYTEAK);
        while (fitx.getFilePointer() < fitx.length()) {
            Ibilgailua ibilgailua = irakurriErregistroa(fitx);
            if (!ibilgailua.ezabatutaDago()
                    && ibilgailua.getKodea() != baztertuKodea
                    && ibilgailua.getMatrikula().equalsIgnoreCase(matrikula)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kodearen kokapen zuzena kalkulatzen du goiburua kontuan hartuta.
     * Formula: goiburua + (kodea - 1) * erregistroaren byte kopurua.
     *
     * @param kodea identifikatzaile positiboa
     * @return erregistroaren hasierako posizioa, bytetan
     * @throws IllegalArgumentException kodea positiboa ez bada
     */
    private long posizioa(int kodea) {
        if (kodea <= 0) {
            throw new IllegalArgumentException("Kodea positiboa izan behar da.");
        }
        return GOIBURU_BYTEAK + (long) (kodea - 1) * ERREGISTRO_BYTEAK;
    }

    /**
     * Erregistro osoa idazten du uneko posizioan, 64 byte erabilita.
     * Lehenik kodea, gero luzera finkoko matrikula eta marka idazten dira.
     *
     * @param fitx kokatuta dagoen ausazko fitxategia
     * @param ibilgailua idatzi beharreko datuak
     * @throws IOException idazketa huts egiten badu
     */
    private void idatziErregistroa(RandomAccessFile fitx, Ibilgailua ibilgailua)
            throws IOException {
        fitx.writeInt(ibilgailua.getKodea());
        idatziTestua(fitx, ibilgailua.getMatrikula(), Ibilgailua.MATRIKULA_LUZERA);
        idatziTestua(fitx, ibilgailua.getMarka(), Ibilgailua.MARKA_LUZERA);
    }

    /**
     * Uneko posiziotik 64 byteko erregistroa irakurtzen du.
     * Kode negatiboa mantentzen da ezabatze logikoa bereizteko.
     *
     * @param fitx kokatuta dagoen ausazko fitxategia
     * @return irakurritako ibilgailua
     * @throws IOException irakurketa huts egiten badu
     */
    private Ibilgailua irakurriErregistroa(RandomAccessFile fitx) throws IOException {
        int kodea = fitx.readInt();
        String matrikula = irakurriTestua(fitx, Ibilgailua.MATRIKULA_LUZERA);
        String marka = irakurriTestua(fitx, Ibilgailua.MARKA_LUZERA);
        return new Ibilgailua(kodea, matrikula, marka);
    }

    /**
     * Testua luzera finkoz idazten du: karaktere bakoitza bi bytetan
     * gordetzen du eta falta diren posizioak zuriunez betetzen ditu.
     * Horrela, hurrengo erregistroaren hasiera ez da lekuz aldatzen.
     *
     * @param fitx idazteko ausazko fitxategia
     * @param testua idatzi beharreko testua
     * @param luzera erreserbatutako karaktere kopurua
     * @throws IOException idazketa huts egiten badu
     */
    private void idatziTestua(RandomAccessFile fitx, String testua, int luzera)
            throws IOException {
        for (int i = 0; i < luzera; i++) {
            fitx.writeChar(i < testua.length() ? testua.charAt(i) : ' ');
        }
    }

    /**
     * Luzera finkoko testua berreskuratzen du, karakterez karaktere.
     * Biltegiratzeko erabilitako amaierako zuriuneak kentzen dira.
     *
     * @param fitx irakurtzeko ausazko fitxategia
     * @param luzera irakurri beharreko karaktere kopurua
     * @return irakurritako testua, zuriunerik gabe muturretan
     * @throws IOException irakurketa huts egiten badu
     */
    private String irakurriTestua(RandomAccessFile fitx, int luzera) throws IOException {
        char[] karaktereak = new char[luzera];
        for (int i = 0; i < luzera; i++) {
            karaktereak[i] = fitx.readChar();
        }
        return new String(karaktereak).trim();
    }
}
