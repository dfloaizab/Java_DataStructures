/**
 * A Singly Linked List implementation in java
 * Diego Loaiza 2021
 */
public class SinglyLinkedList {

    //attributes
    private ListNode head;
    private ListNode tail;

    // methods

    public SinglyLinkedList() {
        head = null;
        tail = null;
    }

    public SinglyLinkedList(ListNode head, ListNode tail) {
        this.head = head;
        this.tail = tail;
    }

    // operations on the list

    /**
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty()
    {
        return(head == null || tail == null);
    }

    /**
     * insert a node at the end of the list
     * @param nodeToInsert
     */
    void appendNode(ListNode nodeToInsert)
    {
        if(isEmpty()) {
            head = nodeToInsert;
        } else tail.setNext(nodeToInsert);
        tail = nodeToInsert;
    }

    /**
     * Insert a node in the list given its position
     * @param nodeToInsert
     * @param pos
     */
    void insertNode(  ListNode nodeToInsert, int pos  )
    {

    }

    /**
     * delete a node from the list given its position
     * @param pos
     */
    void deleteNode(int pos)
    {

    }


}
