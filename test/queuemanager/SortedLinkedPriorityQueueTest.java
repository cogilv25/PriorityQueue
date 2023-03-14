package queuemanager;

import org.junit.Before;

/**
 * Test class for the implementation of the SortedLinkedPriorityQueue ADT.
 * 
 * @author Calum Lindsay
 */
public class SortedLinkedPriorityQueueTest extends DynamicCapacityPriorityQueueTestBase
{
    
    /**
     * This method is run before each test and creates a new priority queue
     * effectively resetting any state from previously run tests.
     */
    @Before
    public void setUp()
    {
        q = new SortedLinkedPriorityQueue<String>();
    }
    
}
