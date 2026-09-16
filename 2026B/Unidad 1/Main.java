public class Main {
    public static void main(String[] args) {
        List<Producto> inventario = new List<>();

        System.out.println("¿Está vacía al crear? " + inventario.estaVacia());

        // Agregar elementos al final. complejidad O(1)
        inventario.append(new Producto("Arroz", "kg", 50));
        inventario.append(new Producto("Aceite", "L", 20));
        inventario.agregarAlInicio(new Producto("Sal", "kg", 15));

        System.out.println("\nInventario:");
        inventario.recorrer();
        System.out.println("Tamaño: " + inventario.tamanio());

        // Ubicar por posición. Complejidad O(n)
        System.out.println("\nElemento en posición 1: " + inventario.obtenerEnPosicion(1));
        System.out.println("Primero: " + inventario.primero());
        System.out.println("Último: " + inventario.ultimo());

        // Buscar por valor (equals de Producto compara por nombre). Complejidad O(n)
        boolean existeAceite = inventario.buscar(new Producto("Aceite", "", 0));
        boolean existePanela = inventario.buscar(new Producto("Panela", "", 0));
        System.out.println("\n¿Existe 'Aceite'? " + existeAceite);
        System.out.println("¿Existe 'Panela'? " + existePanela);
    }
}
