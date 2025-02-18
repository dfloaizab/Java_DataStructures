public class OrdenamientosEnteros
{


    public static void main(String[] args)
    {
        //declarar arreglo desordenado:
        int[] desordenado = {15, 8, 7, 9, 5, 1, 0, 4, 10, 3};

        //declarar arreglo que va a guardar los elementos ordenados:
        int[] ordenado = burbuja(desordenado);

        //Ordenar:
        for(int i=0; i < ordenado.length; i++)
            System.out.printf("%d,",ordenado[i]);

    }

    /**
     * Ordenamiento burbuja de números enteros
     * @param arr, que corresponde a un arreglo de enteros donde los elementos pueden estar desordenados
     * @return el arreglo ordenado en orden ascendente
     */
    public static int[] burbuja(int[] arr) {
        int n = arr.length;
        int[] ordenado = arr.clone(); // Copia del arreglo original

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (ordenado[j] > ordenado[j + 1]) {
                    // Intercambio de valores
                    int temp = ordenado[j];
                    ordenado[j] = ordenado[j + 1];
                    ordenado[j + 1] = temp;
                }
            }
        }
        return ordenado;
    }

    public static int[] insertSort(int[] arr)
    {

    }

    //Completar implementación de acuerdo al algoritmo
    public static int[] mergeSort(int[] arr)
    {

    }


}
