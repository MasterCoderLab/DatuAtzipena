package fitxategiak.eredua;

import java.io.IOException;
import java.util.Locale;

/**
 * 2AG3 taldeko ikasle baten datuak gordetzen dituen klasea.
 * Fitxategiko lerro bakoitzak lau eremu ditu, ";" bidez bereizita.
 */
public class Ikaslea {

    private final int kodea;
    private final String izena;
    private final String herria;
    private final boolean aktiboa;

    /**
     * Ikasle bat sortzen du, jasotako eremuak baliozkotuz.
     *
     * @param kodea ikaslearen identifikatzailea
     * @param izena ikaslearen izena
     * @param herria ikaslearen herria
     * @param aktiboa ikaslea aktibo dagoen ala ez
     */
    public Ikaslea(int kodea, String izena, String herria, boolean aktiboa) {
        if (kodea <= 0) {
            throw new IllegalArgumentException("Kodeak zero baino handiagoa izan behar du.");
        }
        this.kodea = kodea;
        this.izena = egiaztatuTestua(izena, "Izena");
        this.herria = egiaztatuTestua(herria, "Herria");
        this.aktiboa = aktiboa;
    }

    /**
     * Testu-eremu bat baliozkotzen du, fitxategiko formatua ez hondatzeko.
     *
     * @param testua baliozkotu beharreko testua
     * @param eremua eremuaren izena errore-mezuetarako
     * @return testua, kanpoko zuriunerik gabe
     */
    private static String egiaztatuTestua(String testua, String eremua) {
        if (testua == null || testua.isBlank()
                || testua.contains(";") || testua.contains("\n")
                || testua.contains("\r")) {
            throw new IllegalArgumentException(
                    eremua + " ezin da hutsik egon edo ';' karakterea eduki.");
        }
        return testua.trim();
    }

    /**
     * Testu-fitxategiko lerro bat ikasle bihurtzen du.
     *
     * @param lerroa fitxategitik irakurritako lerroa
     * @return lerro horren Ikaslea objektua
     * @throws IOException eremu kopurua edo edukia okerra bada
     */
    public static Ikaslea lerrotikIrakurri(String lerroa) throws IOException {
        String[] eremuak = lerroa.split(";", -1);

        if (eremuak.length != 4) {
            throw new IOException("Lerroaren formatua ez da zuzena: " + lerroa);
        }

        String booleanTestua = eremuak[3].trim().toLowerCase(Locale.ROOT);
        if (!booleanTestua.equals("true") && !booleanTestua.equals("false")) {
            throw new IOException("Balio boolearra baliogabea: " + lerroa);
        }

        try {
            return new Ikaslea(
                    Integer.parseInt(eremuak[0].trim()),
                    eremuak[1], eremuak[2],
                    Boolean.parseBoolean(booleanTestua));
        } catch (IllegalArgumentException e) {
            // Zenbakien eta gainerako eremuen erroreak modu bateratuan adierazten dira.
            throw new IOException("Erregistro baliogabea: " + lerroa, e);
        }
    }

    /** @return ikaslearen kodea */
    public int getKodea() {
        return kodea;
    }

    /** @return ikaslearen izena */
    public String getIzena() {
        return izena;
    }

    /** @return ikaslearen herria */
    public String getHerria() {
        return herria;
    }

    /** @return ikaslea aktibo dagoen ala ez */
    public boolean isAktiboa() {
        return aktiboa;
    }

    /**
     * Fitxategian idazteko erregistroaren testua prestatzen du.
     *
     * @return eremuak ';' bidez bereizita
     */
    public String fitxategikoLerroa() {
        return kodea + ";" + izena + ";" + herria + ";" + aktiboa;
    }

    /**
     * Ikasleak testu-eremu batean hitz bat duen egiaztatzen du.
     *
     * @param hitza bilatu beharreko testua
     * @return true izenak edo herriak hitza badu
     */
    public boolean daukaHitza(String hitza) {
        String bilaketa = hitza.toLowerCase(Locale.ROOT);
        return izena.toLowerCase(Locale.ROOT).contains(bilaketa)
                || herria.toLowerCase(Locale.ROOT).contains(bilaketa);
    }

    /** @return ikaslearen datuak pantailan erakusteko testua */
    @Override
    public String toString() {
        return kodea + " | " + izena + " | " + herria
                + " | " + (aktiboa ? "Aktiboa" : "Ez aktiboa");
    }
}
