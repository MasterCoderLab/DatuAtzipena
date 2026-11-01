
package fitxategiak.datuak;

import fitxategiak.eredua.Ibilgailua;

/**
 * Ariketako hasierako ibilgailuen datuak eskaintzen ditu.
 *
 * Datu hauek menuko hasieratze-aukeran soilik erabiltzen dira.
 * Gainerako eragiketak ausazko fitxategiaren gainean egiten dira
 * zuzenean, datuak memorian kargatu gabe.
 */
public class IbilgailuDatuak {

    /**
     * PDFko adibidean agertzen diren hiru ibilgailuak sortzen ditu.
     * Dei bakoitzean array berri bat itzultzen du, fitxategia
     * hasierako datuekin betetzeko.
     *
     * @return hasierako hiru ibilgailuak dituen arraya
     */
    public static Ibilgailua[] lortu() {
        return new Ibilgailua[] {
            new Ibilgailua(1, "1234ABC", "Seat"),
            new Ibilgailua(2, "5678XYZ", "Ford"),
            new Ibilgailua(3, "2468DEF", "Opel")
        };
    }
}
