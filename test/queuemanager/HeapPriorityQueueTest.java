package queuemanager;

import org.junit.Test;
import org.junit.Before;

/**
 * Test class for the implementation of the HeapPriorityQueue ADT.
 * 
 * @author Calum Lindsay
 */
public class HeapPriorityQueueTest extends FixedCapacityPriorityQueueTestBase {
    
    
    @Before
    public void setUp()
    {
        q = new HeapPriorityQueue();
    }
    
    /**
     * Tests that the internal array is sorted as items are added to the queue.
     */
    @Test
    public void shouldSortInternalArrayWhenItemsAreAddedToQueue()
    {
        //TODO: Implementation
    }
}
