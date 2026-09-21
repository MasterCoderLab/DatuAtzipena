/*
 * Errore aritmetikoaren kudeaketa.
 * 0ren arteko zatiketa, programa ustekabean ez amaitzeko.
 */

package proexcepcioncapturada;

/*
 * ProExcepcionCapturada klaseak zeroz zatitzean sortzen den
 * ArithmeticException salbuespena atzeman eta kudeatzen du,
 * programak exekutatzen jarrai dezan.
 */
public class ProExcepcionCapturada {

    public static void main(String[] args) {

        int a, b;

        try {

            a = 0;
            b = 10 / a;

            System.out.println("Esto ya no se imprime");

        } catch (ArithmeticException objetoExcep) {

            System.out.println("División por cero " + objetoExcep);
        }

        System.out.println("continua el programa...");
    }

    /*
     * try {
     *     bloque de código peligroso
     * }
     *
     * catch (TipoExcepción1 exOb) {
     *     gestión de excepciones para TipoExcepción1
     * }
     *
     * catch (TipoExcepción2 exOb) {
     *     gestión de excepciones para TipoExcepción2
     * }
     *
     * finally {
     *     bloque de código que siempre se ejecuta,
     *     sucedan o no las excepciones. Es opcional.
     * }
     */
}