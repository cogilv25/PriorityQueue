package queuemanager;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Base Test class for all implementations of ****PriorityQueueTest classes that
 * use a fixed capacity container to store elements.
 * 
 * This is the Base class for all ***PriorityQueueTest classes that use a fixed 
 * capacity container to store items. The common methods for these priority 
 * queue implementations are here.
 * 
 * Derived classes must implement an @Before method that initializes q.
 * 
 * q must have a capacity >= limit.
 * 
 * @author Calum Lindsay
 */
public abstract class FixedCapacityPriorityQueueTestBase extends PriorityQueueTestBase {
    
    /**
     * Tests that the add method throws an exception when the queue is full.
     */
    @Test
    public void shouldThrowExceptionWhenAddingToFullQueue()
    {
        /* Add items recursively until queue is full, checking each
         * item is added as we go. */
        try 
        {
            for(int i=0; i<limit; ++i)
            {
                q.add(names[i],priorities[i]);
                
                if(!q.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
        
        /* Attempting to add another item should throw an exception. */
        try
        {
            q.add("Some Name",420);
            fail("No Exception received when adding an item to a full queue");
        }catch(QueueOverflowException e){}
    }
    
}
