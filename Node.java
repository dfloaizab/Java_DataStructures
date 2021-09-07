public class Node<E> {

    /* fields */
    E value;  /* value or data of the node */
    Node next;


    /* methods */

    public Node() {
    }

    public Node(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
