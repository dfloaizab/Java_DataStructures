public class ListNode<T> {

     /* atributos de un nodo */
     T data;
     ListNode next;


     /* métodos de un nodo */
     public ListNode(T data) {
          this.data = data;
     }

     /* métodos get/set (consulta, modificación) */

     public T getData() {
          return data;
     }

     public void setData(T data) {
          this.data = data;
     }

     public ListNode getNext() {
          return next;
     }

     public void setNext(ListNode next) {
          this.next = next;
     }
}
