package queue_example;

/**
 *
 * @author Diego Loaiza, 2021
 */
public class Queue_Example {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Queue myQueue = new Queue(5);
        try
        {
			 myQueue.enqueue(7);
			 myQueue.enqueue(4);
			 myQueue.enqueue(8);
			 myQueue.enqueue(12);
			 myQueue.enqueue(3);
			 myQueue.enqueue(99);
			 myQueue.enqueue(120);
			 myQueue.enqueue(133);
         
        }
        catch(Exception e)
        {
			System.out.print(e.getMessage()+"\n");
        }
        myQueue.printQueue();
        
    }
    
}
