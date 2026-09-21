/*
 * Oinarrizko motak baliotzat (kopiak) eta objektuak erreferentziatzat hartzen dira.
 */

package proparametros;

/*
 * ProParametros klaseak metodo bati oinarrizko aldagai bat eta array bat
 * parametro gisa pasatzean gertatzen den aldea erakusten du.
 *
 * Programaren emaitza:
 * antes: a= 0, v[0]=0
 * despues: a= 0, v[0]=7
 */
public class ProParametros {

    public static void main(String[] args) {

        int a = 0;
        int v[] = {0};

        System.out.println("antes: a= " + a + ", v[0]= " + v[0]);

        valorEntero(a, 7);
        valorVector(v, 7);

        System.out.println("despues: a= " + a + ", v[0]= " + v[0]);
    }

    public static void valorEntero(int a, int valor) {

        // Variable a pasa por valor
        a = valor;
    }

    public static void valorVector(int[] v, int valor) {

        // Se modifica el contenido del array
        v[0] = valor;
    }
}