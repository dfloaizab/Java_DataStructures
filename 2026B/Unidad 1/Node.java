/**
 * Nodo de la lista simplemente enlazada.
 * Guarda el dato y la referencia al siguiente nodo.
 */
public class Node<E> {
    E data;
    Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
}
