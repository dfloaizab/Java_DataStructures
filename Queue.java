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
        //validate if queue is empty
        if(isEmpty())
        {
            QueueNode newNode = new QueueNode(data);
            head = newNode;
            tail = newNode;
            size++;
        }
        else
        {
            //append node: add to tail
            //VALIDATE LIMIT OF THE QUEUE
            if(size < limit)
            {
                QueueNode newNode = new  QueueNode(data);
                tail.setNext(newNode);
                tail = newNode;
                size++;
            }
            else
            {
             throw new Exception("out of limits");
            }
        }
    }
    
    /**
     * add an element to the queue
     * @param newNode 
     */
    public void enqueue( QueueNode newNode) throws Exception
    {
         //validate if queue is empty
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
            size++;
        }
        else
        {
            //append node: add to tail   
            //VALIDATE LIMIT OF THE QUEUE
            if(size < limit)
            {
                tail.setNext(newNode);
                tail = newNode;
                size++;
            }
            else
            {
                throw new Exception("out of limits");
            }
        }
    }
    
    /**
     * dispatch element from the queue
     * @return 
     */
    public QueueNode  dequeue()
    {
        QueueNode theNode = null;
        
        //get the node from the head:
        theNode = head;
        
        //1. delete node from the head
        head = head.getNext();
        
        //2. decrease the size of the queue     
        size--;
        
        return theNode;
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
