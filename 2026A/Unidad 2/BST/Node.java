class Node<T> {
    int key;           // Clave para ordenar (por ejemplo, cédula)
    T data;            // Dato genérico almacenado
    Node<T> left;      // Referencia al hijo izquierdo
    Node<T> right;     // Referencia al hijo derecho

    // Constructor del nodo
    public Node(int key, T data) {
        this.key = key;
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
