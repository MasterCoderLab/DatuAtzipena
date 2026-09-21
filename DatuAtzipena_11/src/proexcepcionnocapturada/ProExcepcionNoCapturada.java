/*
 * Errore posiblearekin zer gertatzen den axola ez badu,
 * salbuespena kanpora bidaliko da.
 */

package proexcepcionnocapturada;

/*
 * ProExcepcionNoCapturada klaseak zeroz zatitzean sortzen den
 * ArithmeticException salbuespena ez du bertan kudeatzen.
 * Salbuespena kanpora bidaltzen du throws erabiliz.
 */
public class ProExcepcionNoCapturada {

    public static void main(String[] args) throws ArithmeticException {

        int a, b;

        a = 0;
        b = 10 / a;

        System.out.println(b);
        System.out.println("Esto ya no se imprime");
        System.out.println("continua el programa...");
    }

    /*
     * Exekuzioa geldituko da, salbuespenaren informazioa erakutsiko da,
     * baina ez da ondorengo jarraibideetako bat ere exekutatuko.
     */
}