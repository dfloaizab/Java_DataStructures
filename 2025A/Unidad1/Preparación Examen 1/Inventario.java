class Inventario {
    private ListaEnlazada productos = new ListaEnlazada();
    private Stack<Producto> devoluciones = new Stack<>();
    private Queue<Producto> reposiciones = new LinkedList<>();

    public void agregarProducto(Producto p) {
        productos.agregarProducto(p);
    }

    public void agregarDevolucion(Producto p) {
        devoluciones.push(p);
    }

    public void agregarReposicion(Producto p) {
        reposiciones.offer(p);
    }

    public void procesarReposicion() {
        if (!reposiciones.isEmpty()) {
            System.out.println("Procesando reposición: " + reposiciones.poll());
        } else {
            System.out.println("No hay reposiciones pendientes.");
        }
    }

    public void mostrarInventario() {
        productos.mostrarInventario();
    }
}

// Clase principal con ejemplo de uso
public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // Agregar productos
        inventario.agregarProducto(new Producto("A101", "Laptop Lenovo", 5, 2500.0));
        inventario.agregarProducto(new Producto("B202", "Mouse Logitech", 15, 30.0));
        inventario.agregarProducto(new Producto("C303", "Teclado Mecánico", 8, 80.0));

        // Mostrar inventario
        System.out.println("Inventario Actual:");
        inventario.mostrarInventario();

        // Agregar devoluciones y reposiciones
        inventario.agregarDevolucion(new Producto("B202", "Mouse Logitech", 1, 30.0));
        inventario.agregarReposicion(new Producto("C303", "Teclado Mecánico", 5, 80.0));

        // Procesar reposición
        inventario.procesarReposicion();
    }
}
