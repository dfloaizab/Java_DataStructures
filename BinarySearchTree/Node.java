public class Node<E> {

    //key: determina el ordenamiento del árbol
    int key;

    //información que va a guardar el nodo
    E data;

    //referencia a la rama izquierda
    Node left;

    Node right;

    public Node() {
        left = null;
        right = null;

        data = null;
    }

    public Node(int key, E data) {
        this.key = key;
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
