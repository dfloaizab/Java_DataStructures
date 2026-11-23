
import java.util.*;

public class Grafo_MA<E> {

    private List<Nodo<E>> nodos;
    private double[][] matriz;

    private boolean dirigido;

    public Grafo_MA(boolean dirigido) {

        this.dirigido = dirigido;

        nodos = new ArrayList<>();

        matriz = new double[0][0];
    }

    // Agregar nodo
    public void agregarNodo(Nodo<E> nodo) {

        if (nodos.contains(nodo)) {
            return;
        }

        int n = nodos.size();

        nodos.add(nodo);

        // Crear matriz ampliada
        double[][] nuevaMatriz =
            new double[n + 1][n + 1];

        // Inicializar ausencia de aristas
        for (int i = 0; i <= n; i++) {
            Arrays.fill(
                nuevaMatriz[i],
                Double.POSITIVE_INFINITY);
        }

        // Copiar matriz anterior
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                nuevaMatriz[i][j] = matriz[i][j];
            }
        }

        matriz = nuevaMatriz;
    }

    // Crear nodo directamente
    public Nodo<E> agregarNodo(int id, E dato) {

        Nodo<E> nodo = new Nodo<>(id, dato);

        agregarNodo(nodo);

        return nodo;
    }

    // Obtener posición de un nodo
    private int obtenerIndice(Nodo<E> nodo) {

        return nodos.indexOf(nodo);
    }

    // Crear arista
    public void agregarArista(
            Nodo<E> origen,
            Nodo<E> destino,
            double peso) {

        int i = obtenerIndice(origen);
        int j = obtenerIndice(destino);

        if (i == -1 || j == -1) {
            throw new IllegalArgumentException(
                "Los nodos deben existir previamente");
        }

        matriz[i][j] = peso;

        if (!dirigido) {
            matriz[j][i] = peso;
        }
    }

    // Arista sin peso
    public void agregarArista(
            Nodo<E> origen,
            Nodo<E> destino) {

        agregarArista(origen, destino, 1.0);
    }

    // Consultar peso de una arista
    public double obtenerPeso(
            Nodo<E> origen,
            Nodo<E> destino) {

        int i = obtenerIndice(origen);
        int j = obtenerIndice(destino);

        if (i == -1 || j == -1) {
            return Double.POSITIVE_INFINITY;
        }

        return matriz[i][j];
    }

    // Consultar nodos
    public List<Nodo<E>> obtenerNodos() {
        return Collections.unmodifiableList(nodos);
    }

    // Consultar cantidad de nodos
    public int cantidadNodos() {
        return nodos.size();
    }

    // Consultar si existe una arista
    public boolean existeArista(
            Nodo<E> origen,
            Nodo<E> destino) {

        return obtenerPeso(origen, destino)
                != Double.POSITIVE_INFINITY;
    }

    // Mostrar matriz
    public void mostrarGrafo() {

        System.out.println("Grafo mediante matriz:");

        System.out.print("      ");

        for (Nodo<E> nodo : nodos) {
            System.out.printf("%10s", nodo.getDato());
        }

        System.out.println();

        for (int i = 0; i < nodos.size(); i++) {

            System.out.printf(
                "%6s", nodos.get(i).getDato());

            for (int j = 0; j < nodos.size(); j++) {

                if (matriz[i][j] ==
                        Double.POSITIVE_INFINITY) {

                    System.out.printf("%10s", "INF");

                } else {

                    System.out.printf(
                        "%10.1f", matriz[i][j]);
                }
            }

            System.out.println();
        }
    }
}
