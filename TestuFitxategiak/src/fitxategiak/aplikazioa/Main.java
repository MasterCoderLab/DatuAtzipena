package fitxategiak.aplikazioa;

import java.util.Scanner;

import fitxategiak.datuak.IkasleDatuak;
import fitxategiak.kudeaketa.TestuFitxategiKudeatzailea;

/**
 * Testu-fitxategien programaren abiapuntua.
 * Objektuak sortu eta menua abiarazten ditu.
 */
public class Main {

    /**
     * Programa abiarazten du Scanner bakarra erabiliz.
     *
     * @param args komando-lerroko argumentuak (ez dira erabiltzen)
     */
    public static void main(String[] args) {
        try (Scanner teklatua = new Scanner(System.in)) {
            TestuFitxategiKudeatzailea kudeatzailea =
                    new TestuFitxategiKudeatzailea();
            Menua menua = new Menua(teklatua, kudeatzailea,
                    IkasleDatuak.lortuHasierakoDatuak());
            menua.exekutatu();
        }
    }
}
