package queue_example;

/**
 *
 * @author estudiante
 */
public class Queue<T> {
    
    QueueNode head;
    QueueNode tail;
    int limit, size; //max # of elements

    /**
     * create a queue without limit
     */
    public Queue() {        
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * create a queue with limit of elements
     * @param limit 
     */
    public Queue(int limit) {        
        head = null;
        tail = null;
        size = 0;
        this.limit = limit;
    }
    
    private boolean isEmpty()
    {
        return (head == null && tail == null);

    }
    
    public void enqueue(   T data  ) throws Exception
    {
       
    }
    
    /**
     * add an element to the queue
     * @param newNode 
     */
    public void enqueue( QueueNode newNode) throws Exception
    {
         
    }
    
    /**
     * dispatch element from the queue
     * @return 
     */
    public QueueNode  dequeue()
    {
      
    }
    
    public void printQueue()
    {
        QueueNode current = head;
        //traverse the queue from head to tail:
        while(current != null)
        {
            System.out.print(current.getData()+"<-");
            current = current.getNext();
        }
    }
    
}
