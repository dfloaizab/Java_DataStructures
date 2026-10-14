public class Node<E> {

    E data;
    int key;
    Node left;
    Node right;

    public Node(E data, int key, Node left, Node right) {
        this.data = data;
        this.key = key;
        this.left = left;
        this.right = right;
    }


    
}
