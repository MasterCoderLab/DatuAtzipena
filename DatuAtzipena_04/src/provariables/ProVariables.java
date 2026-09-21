package provariables;

public class ProVariables {

    /*
     * Aldagai bat definitzen den kode-blokeak zehazten du haren eremua.
     */

    public static void main(String[] args) {

        int a;
        a = 9;

        {
            int b = a + 1;
            System.out.println("b: " + b);
        }

        // System.out.println("b: " + b);

        System.out.println("a: " + a);

        String c, d;
        c = "Kaixo";
        d = " Luis";

        String e = c + d;

        System.out.println(e);
    }
}