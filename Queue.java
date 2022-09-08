public class Queue {

    Node head;
    Node tail;
    int size; //MUY UTIL cuando se implementa un buffer circular

    public Queue() {
        head = null;
        tail = null;
    }
    
    public boolean isEmpty()
    {
        return(head == null || tail ==  null);
    }

    /**
     * add a node to the queue
     * (similar to append method on the list)
     * @param newNode
     */
    public void Enqueue(Node newNode)
    {

    }

    /**
     * o dispatch
     * (similar to delete the first node of the list)
     * @return
     */
    public Node Dequeue()
    {
        Node toReturn = head;
        if(!isEmpty())
        {
            head = head.getNext();
        }
        return toReturn;
    }

}
