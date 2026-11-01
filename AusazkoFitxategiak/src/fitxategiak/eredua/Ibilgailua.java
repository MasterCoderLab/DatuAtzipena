package fitxategiak.eredua;

/**
 * Ibilgailu baten identifikatzailea, matrikula eta marka gordetzen ditu.
 *
 * Ausazko fitxategian testu-eremuek luzera finkoa dutenez,
 * matrikularen eta markaren gehieneko luzerak mugatuta daude.
 * Identifikatzaile negatiboak logikoki ezabatutako erregistroa adierazten du.
 */
public class Ibilgailua {

    /** Matrikulari fitxategian erreserbatutako karaktere kopurua. */
    public static final int MATRIKULA_LUZERA = 10;

    /** Markari fitxategian erreserbatutako karaktere kopurua. */
    public static final int MARKA_LUZERA = 20;

    private final int kodea;
    private final String matrikula;
    private final String marka;

    /**
     * Ibilgailua sortzen du, kodea eta testu-eremuen luzerak egiaztatuta.
     * Kode negatiboak onartzen dira ezabatutako erregistroak irakurtzeko;
     * zero, ordea, ez da identifikatzaile balioduna.
     *
     * @param kodea ibilgailuaren identifikatzailea, zerotik desberdina
     * @param matrikula ibilgailuaren matrikula, gehienez 10 karakterekoa
     * @param marka ibilgailuaren marka, gehienez 20 karakterekoa
     * @throws IllegalArgumentException kodea edo testu-eremuak baliogabeak badira
     */
    public Ibilgailua(int kodea, String matrikula, String marka) {
        if (kodea == 0) {
            throw new IllegalArgumentException("Kodea ezin da 0 izan.");
        }

        if (matrikula == null || matrikula.isBlank()
                || matrikula.length() > MATRIKULA_LUZERA) {
            throw new IllegalArgumentException("Matrikula baliogabea.");
        }

        if (marka == null || marka.isBlank()
                || marka.length() > MARKA_LUZERA) {
            throw new IllegalArgumentException("Marka baliogabea.");
        }

        this.kodea = kodea;
        this.matrikula = matrikula.trim();
        this.marka = marka.trim();
    }

    /**
     * Ibilgailuaren identifikatzailea itzultzen du.
     *
     * @return kode positiboa edo negatiboa, erregistroaren egoeraren arabera
     */
    public int getKodea() {
        return kodea;
    }

    /**
     * Ibilgailuaren matrikula itzultzen du.
     *
     * @return matrikula, hasierako eta amaierako zuriunerik gabe
     */
    public String getMatrikula() {
        return matrikula;
    }

    /**
     * Ibilgailuaren marka itzultzen du.
     *
     * @return marka, hasierako eta amaierako zuriunerik gabe
     */
    public String getMarka() {
        return marka;
    }

    /**
     * Erregistroa logikoki ezabatuta dagoen adierazten du.
     * Fitxategian ezabatutako erregistroek identifikatzaile negatiboa dute.
     *
     * @return true kodea negatiboa bada; false bestela
     */
    public boolean ezabatutaDago() {
        return kodea < 0;
    }

    /**
     * Ibilgailuaren datuak testu irakurgarri batean erakusten ditu.
     *
     * @return kodea, matrikula eta marka dituen testua
     */
    @Override
    public String toString() {
        return "Kodea: " + kodea
                + " | Matrikula: " + matrikula
                + " | Marka: " + marka;
    }
}
