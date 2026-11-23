
import java.util.*;

public class Grafo_LA<E> {

    // Clase interna para representar una arista
    public static class Arista<E> {
        private Nodo<E> destino;
        private double peso;

        public Arista(Nodo<E> destino, double peso) {
            this.destino = destino;
            this.peso = peso;
        }

        public Nodo<E> getDestino() {
            return destino;
        }

        public double getPeso() {
            return peso;
        }

        @Override
        public String toString() {
            return destino.getDato() + "(" + peso + ")";
        }
    }

    // Lista de adyacencia
    private Map<Nodo<E>, List<Arista<E>>> adyacencias;

    private boolean dirigido;

    public Grafo_LA(boolean dirigido) {
        this.dirigido = dirigido;
        adyacencias = new LinkedHashMap<>();
    }

    // Añadir nodo
    public void agregarNodo(Nodo<E> nodo) {

        if (!adyacencias.containsKey(nodo)) {
            adyacencias.put(nodo, new ArrayList<>());
        }
    }

    // Crear nodo directamente a partir de un dato
    public Nodo<E> agregarNodo(int id, E dato) {

        Nodo<E> nodo = new Nodo<>(id, dato);
        agregarNodo(nodo);

        return nodo;
    }

    // Crear arista
    public void agregarArista(
            Nodo<E> origen,
            Nodo<E> destino,
            double peso) {

        if (!adyacencias.containsKey(origen) ||
            !adyacencias.containsKey(destino)) {

            throw new IllegalArgumentException(
                "Los nodos deben existir previamente");
        }

        adyacencias.get(origen).add(
            new Arista<>(destino, peso));

        // Si no es dirigido, agregar conexión inversa
        if (!dirigido) {
            adyacencias.get(destino).add(
                new Arista<>(origen, peso));
        }
    }

    // Sobrecarga para aristas sin peso
    public void agregarArista(
            Nodo<E> origen,
            Nodo<E> destino) {

        agregarArista(origen, destino, 1.0);
    }

    // Consultar vecinos
    public List<Arista<E>> obtenerVecinos(Nodo<E> nodo) {

        if (!adyacencias.containsKey(nodo)) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(
            adyacencias.get(nodo));
    }

    // Consultar nodos
    public Set<Nodo<E>> obtenerNodos() {
        return Collections.unmodifiableSet(
            adyacencias.keySet());
    }

    // Consultar cantidad de nodos
    public int cantidadNodos() {
        return adyacencias.size();
    }

    // Mostrar grafo
    public void mostrarGrafo() {

        System.out.println("Grafo mediante listas:");

        for (Nodo<E> nodo : adyacencias.keySet()) {

            System.out.print(nodo.getDato() + " -> ");

            for (Arista<E> arista :
                    adyacencias.get(nodo)) {

                System.out.print(arista + " ");
            }

            System.out.println();
        }
    }
}
