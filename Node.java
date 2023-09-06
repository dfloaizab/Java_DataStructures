public class Node<T> {

     /* atributos de un nodo */
     T data;
     Node next;


     /* métodos de un nodo */
     public Node(T data) {
          this.data = data;
     }

     /* métodos get/set (consulta, modificación) */

     public T getData() {
          return data;
     }

     public void setData(T data) {
          this.data = data;
     }

     public Node getNext() {
          return next;
     }

     public void setNext(Node next) {
          this.next = next;
     }
}
