package fitxategiak.datuak;

import fitxategiak.eredua.Ikaslea;

/**
 * 2AG3 taldeko adibideko ikasle fikziozkoak.
 * Datu hauek fitxategia hasieratzeko aukeran soilik erabiltzen dira.
 */
public class IkasleDatuak {

    /**
     * Hasierako bost erregistroak sortzen ditu.
     *
     * @return ikasle fikziozkoen taula
     */
    public static Ikaslea[] lortuHasierakoDatuak() {
        return new Ikaslea[] {
                new Ikaslea(1, "Ane Etxeberria", "Tolosa", true),
                new Ikaslea(2, "Mikel Aranburu", "Beasain", true),
                new Ikaslea(3, "Leire Zubizarreta", "Ordizia", false),
                new Ikaslea(4, "Jon Lasa", "Donostia", true),
                new Ikaslea(5, "Nerea Garmendia", "Lazkao", false)
        };
    }
}
