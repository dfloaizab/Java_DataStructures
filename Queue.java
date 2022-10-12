public class Queue {

    QueueNode head;
    QueueNode tail;
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
    public void Enqueue(QueueNode newNode)
    {
        //list is empty:
        if( isEmpty() )
        {
            head = newNode;
            tail = newNode;
        }
        else //list is not empty:
        {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * o dispatch
     * (similar to delete the first node of the list)
     * @return
     */
    public QueueNode Dequeue()
    {
        QueueNode toReturn = head;
        if(!isEmpty())
        {
            head = head.getNext();
        }
        return toReturn;
    }
    
    public void printQueue()
    {
        //current node start at head of the list
        QueueNode currentNode = head;
        System.out.print("\n");
        //traverse list
        while(  currentNode != null  )
        {
            //write content of the node
            System.out.print(currentNode.getData()+"->");

            //update current node position
            currentNode = currentNode.getNext();
        }
    }
    
}
