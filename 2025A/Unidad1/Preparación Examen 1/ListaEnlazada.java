// Lista Enlazada para almacenar productos
class ListaEnlazada {
    private class Nodo {
        Producto producto;
        Nodo siguiente;
        public Nodo(Producto producto) {
            this.producto = producto;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;

    // Agregar producto a la lista
    public void agregarProducto(Producto producto) {
        Nodo nuevo = new Nodo(producto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Mostrar inventario
    public void mostrarInventario() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.producto);
            actual = actual.siguiente;
        }
    }
}
