/*
 * Metodo EZ estatikoa erabiltzea.
 * Objektu bat sortu behar da hori eskatzeko.
 */

package prometodo2;

/*
 * ProMetodo2 klaseak asteko egun baten hurrengo eguna kalkulatzen du.
 * Metodo ez-estatikoa erabiltzen denez, objektu bat sortu behar da
 * metodoari deitzeko.
 */
public class ProMetodo2 {

    public static void main(String[] args) {

        String semana[] = {
            "lunes",
            "martes",
            "miercoles",
            "jueves",
            "viernes",
            "sabado",
            "domingo"
        };

        String hoy = "domingo";

        ProMetodo2 pm2 = new ProMetodo2();

        String siguiente = pm2.diaSiguiente(semana, hoy);

        System.out.println("El día siguiente a " + hoy + " es: " + siguiente);
    }

    public String diaSiguiente(String[] semana, String dia) {

        int posicion = 0, siguiente;

        for (int i = 0; i < semana.length; i++) {

            if (semana[i].equals(dia)) {
                posicion = i;
            }
        }

        if (posicion == semana.length - 1) {
            siguiente = 0;
        } else {
            siguiente = posicion + 1;
        }

        return semana[siguiente];
    }
}