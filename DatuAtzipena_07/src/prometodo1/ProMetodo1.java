/*
 * Metodo estatikoa edo klase-metodoa erabiltzea.
 * Ez da objektu bat sortu behar hori eskatzeko.
 */

package prometodo1;

/*
 * ProMetodo1 klaseak emandako asteko egun baten hurrengo eguna
 * kalkulatzen eta pantailan erakusten du metodo estatiko baten bidez.
 *
 * Pantailan agertuko den emaitza:
 * igandea-ren hurrengo eguna hau da: astelehena
 */
public class ProMetodo1 {

    public static void main(String[] args) {

        String astea[] = {
            "astelehena",
            "asteartea",
            "asteazkena",
            "osteguna",
            "ostirala",
            "larunbata",
            "igandea"
        };

        String gaur = "igandea";

        String hurrengoa = hurrengoEguna(astea, gaur);

        System.out.println(gaur + "-ren hurrengo eguna hau da: " + hurrengoa);
    }

    public static String hurrengoEguna(String[] astea, String eguna) {

        int posizioa = 0, hurrengoa;

        for (int i = 0; i < astea.length; i++) {

            if (astea[i].equals(eguna)) {
                posizioa = i;
            }
        }

        if (posizioa == astea.length - 1) {
            hurrengoa = 0;
        } else {
            hurrengoa = posizioa + 1;
        }

        return astea[hurrengoa];
    }
}