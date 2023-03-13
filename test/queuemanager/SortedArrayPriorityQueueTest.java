package queuemanager;

import org.junit.Test;
import org.junit.Before;

/**
 * Test class for the implementation of the SortedArrayPriorityQueue ADT.
 * 
 * @author Calum Lindsay
 */
public class SortedArrayPriorityQueueTest extends FixedCapacityPriorityQueueTestBase {
    
    /**
     * This method is run before each test and creates a new priority queue
     * effectively resetting any state from previously run tests.
     */
    @Before
    public void setUp()
    {
        q = new SortedArrayPriorityQueue<String>(capacity);
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
