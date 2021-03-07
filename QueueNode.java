package queue_example;

public class QueueNode<T> {

     /* atributos de un nodo */
     T data;
     QueueNode next;


     /* métodos de un nodo */
     public QueueNode(T data) {
          this.data = data;
     }

     /* métodos get/set (consulta, modificación) */

     public T getData() {
          return data;
     }

     public void setData(T data) {
          this.data = data;
     }

     public QueueNode getNext() {
          return next;
     }

     public void setNext(QueueNode next) {
          this.next = next;
     }
}
