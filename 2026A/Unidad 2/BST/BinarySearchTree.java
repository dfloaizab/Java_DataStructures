/ Clase BinarySearchTree que implementa el BST
class BinarySearchTree<T> {

    Node<T> root; // Nodo raíz del árbol

    // Constructor vacío
    public BinarySearchTree() {
        this.root = null;
    }

    // Método público para insertar un nodo
    public void addNode(int key, T data) {
        root = insertRec(root, key, data);
    }

    // Método recursivo para insertar respetando la propiedad del BST
    private Node<T> insertRec(Node<T> current, int key, T data) {
        if (current == null) {
            return new Node<>(key, data);
        }

        if (key < current.key) {
            current.left = insertRec(current.left, key, data);
        } else if (key > current.key) {
            current.right = insertRec(current.right, key, data);
        }
        // Si la clave ya existe, no se inserta (podrías actualizar si quisieras)

        return current;
    }

    // Método público para buscar un nodo por su clave
    public Node<T> searchNode(int key) {
        return searchRec(root, key);
    }

    // Búsqueda recursiva
    private Node<T> searchRec(Node<T> current, int key) {
        if (current == null || current.key == key) {
            return current;
        }

        if (key < current.key) {
            return searchRec(current.left, key);
        } else {
            return searchRec(current.right, key);
        }
    }

    // Recorrido inOrden: izquierda, raíz, derecha
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.key + " -> " + node.data);
            inOrderRec(node.right);
        }
    }

    // Recorrido preOrden: raíz, izquierda, derecha
    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node<T> node) {
        if (node != null) {
            System.out.println(node.key + " -> " + node.data);
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    // Recorrido postOrden: izquierda, derecha, raíz
    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node<T> node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.println(node.key + " -> " + node.data);
        }
    }
}
