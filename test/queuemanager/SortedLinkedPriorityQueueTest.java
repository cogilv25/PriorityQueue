package queuemanager;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the implementation of the SortedLinkedPriorityQueue ADT.
 * 
 * @author Calum Lindsay
 */
public class SortedLinkedPriorityQueueTest extends DynamicCapacityPriorityQueueTestBase {
    /**
     * This method is run before each test and creates a new priority queue
     * effectively resetting any state from previously run tests.
     */
    @Before
    public void setUp()
    {
        q = new SortedLinkedPriorityQueue<String>();
    }
    
    /**
     * Tests that the internal array is sorted as items are added to the queue.
     */
    @Test
    public void shouldSortInternalArrayWhenItemsAreAddedToQueue()
    {
        //TODO: Implementation
    }
    
    /**
     * Tests a possible flaw where when seeking the highest priority 
     * item it goes past the end of the linked list.
     */
    @Test
    public void shouldReturnHighestPriorityItemFromHeadWhenHighestPriorityItemAtEndOfLinkedList()
    {
        /* Add items recursively until limit is reached, in order from lowest
        * priority to highest priority. */
       try 
       {
           for(int i=limit-1; i>=0; --i)
               q.add(namesSortedByPriority[i],sortedPriorities[i]);
           
       }
       catch(QueueOverflowException e)
       {
           fail("QueueOverflowException recieved when adding to queue");
       }
       
       /* Ensure that the highest priority item is returned from head without
        * any errors. */
       try {
           assertEquals(namesSortedByPriority[0],q.head());
       }
       catch(QueueUnderflowException e)
       {
           fail("QueueUnderflowException received when calling head on a non-empty queue");
       }
    }
}
