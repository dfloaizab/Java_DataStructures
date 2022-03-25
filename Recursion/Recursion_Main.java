/**
 * Some codings on recursion Vs. iteration
 * Diego Loaiza, 2022
 * Estructuras de Datos
 * Integrante 1:
 * Integrante 2:
 */
public class Recursion_Main {


    static long fibo[];

    public static void main(String[] args) {

        //System.out.println("Suma iterativa hasta 100:" + sumaIterativa(500));
        //System.out.println("Suma recursiva hasta 100:" + sumaRecursiva(1000));

        int x = 20;
        //System.out.println("Factorial iterativo de "+x+":" + factorialIterativo(x));
        //System.out.println(("Factorial recursivo de "+x+":" + factorialRecursivo(x)));

        int f = 48;
        fibo = new long[100];
        System.out.println("Término " + f + " de la secuencia de Fibonacci (iterativo):" + fiboIterativo(f));
        System.out.println("Término " + f + " de la secuencia de Fibonacci (recursivo):" + fiboRecursivo(f));

    }

    /**
     * An iterative implementation of the Fibonacci Sequence
     * using an array of precalculated values
     * @param n
     * @return nth term of the sequence
     */
    static long fiboIterativo(int n)
    {
        /* usar arreglo de valores previamente calculados de la secuencia: */
        fibo[0] = 1;
        fibo[1] = 1;
        int fiboN = 1;
        /* caso base, los dos primeros términos de la secuencia: */
        if(n == 0 || n == 1) /*  n <= 1   */
        {
            return 1;
        }
        else
        {
            //calcular términos de la secuencia hasta n:
            for(int i = 2; i <= n; i++)
            {
                 fibo[i] = fibo[i-1] + fibo[i-2];
            }
        }
        // retornar término n de la secuencia:
        return fibo[n];
    }

    /**
     * A recursive implementation of the Fibonacci Sequence
     * @param n
     * @return nth term of the sequence
     */
    static long fiboRecursivo(int n)
    {
        //base case:
        if(n <= 1)
            return 1;
        else
            //recursive calls:
            return fiboRecursivo(n-1)+ fiboRecursivo(n-2);
    }

    /* SUMATORIA ITERATIVA Y RECURSIVA: */
    static int sumaIterativa(int n)
    {
        int res=0;
        for(int i = 1; i <= n; i++)
        {
            res = res+i;
        }
        return res;
    }
    static int sumaRecursiva(int n)
    {
        /* caso base: */
        if(n == 0)
            return 0;
        else
            /* llamado recursivo sobre una parte más pequeña del problema */
            return sumaRecursiva(n-1) + n;
    }
    /* FACTORIAL ITERATIVO Y RECURSIVO: */
    static long factorialIterativo(int n)
    {
        long res=1;
        for(int i=1; i<=n; i++)
        {
            res = res * i;
        }
        return res;
    }

    static long factorialRecursivo(int n)
    {
        if(n == 0)
            return 1;
        else
            return n* factorialRecursivo(n-1);
    }
    
    /**
     * PUNTO 1: conteo de caracteres, versión recursiva
     * @param cadena de entrada
     * @return número de caracteres de la cadena
     */
    static int conteoCaracteresRecursivo(String cadena)
    {
        //TODO: Implementar
    }

    /**
     * PUNTO 2: inversión de una cadena, versión recursiva
     * @param cadenaOriginal
     * @return cadena con sus caracteres invertidos
     */
    static String invertirCadenaRecursivo(String cadenaOriginal)
    {
        //TODO: Implementar
    }


    /**
     * PUNTO3: inversión de una cadena, versión iterativa
     * @param cadenaOrigina
     * @return
     */
    static String invertirCadenaIterativo(String cadenaOriginal)
    {
        //TODO: Implementar
    }

    /**
     * PUNTO 4: conteo de cuántas veces aparece un caracter c en una cadena s, versión recursiva
     * @param C caracter
     * @param S cadena
     * @return número de veces que aparece el caracter C en S
     */
    static int conteoCaracterRecursivo(char C, String S)
    {
        //TODO: Implementar
    }

    /**
     * PUNTO 5: conteo de cuántas veces aparece un caracter c en una cadena s, versión iterativa
     * @param C caracter
     * @param S cadena
     * @return número de veces que aparece el caracter C en S
     */
    static int conteoCaracterIterativo(char C, String S)
    {
        //TODO: Implementar
    }



}

