// Clase principal con método main para probar el BST
public class Main {
    public static void main(String[] args) {

        // Creamos el árbol de personas
        BinarySearchTree<Persona> bst = new BinarySearchTree<>();

        // Insertamos personas usando la cédula como key
        bst.addNode(1003, new Persona("Carlos", 30));
        bst.addNode(1001, new Persona("Ana", 25));
        bst.addNode(1005, new Persona("Luis", 40));
        bst.addNode(1002, new Persona("María", 28));
        bst.addNode(1004, new Persona("Pedro", 35));

        // Probamos búsqueda
        Node<Persona> resultado = bst.searchNode(1002);
        if (resultado != null) {
            System.out.println("Encontrado: " + resultado.data);
        } else {
            System.out.println("No encontrado");
        }

        // Probamos recorridos
        System.out.println("\nRecorrido InOrden (ordenado por cédula):");
        bst.inOrder();

        System.out.println("\nRecorrido PreOrden:");
        bst.preOrder();

        System.out.println("\nRecorrido PostOrden:");
        bst.postOrder();
    }
}
