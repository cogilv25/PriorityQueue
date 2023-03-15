package queuemanager;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Base Test class for all implementations of ****PriorityQueueTest classes.
 * 
 * Naming this class PriorityQueueTest causes JUnit4 to attempt to run the tests
 * which isn't what we want. This is the Base class for all ***PriorityQueueTest
 * classes it contains the common functions and common test data for all
 * priority queue implementations to avoid excessive code duplication.
 * 
 * Derived classes must implement an @Before method that initializes q.
 * 
 * @author Calum Lindsay
 */
public abstract class PriorityQueueTestBase
{
    
    /**
     * The PriorityQueue instance used in the tests.
     */
    protected PriorityQueue q = null;
    
    /**
     * Common test data for all priority queue tests.
     */
    protected String[] names = 
    {
        "Donald", "Kim",
        "Roger", "Jordan",
        "Billy", "Rachel",
        "Jamie", "Octavia",
        "Holly", "Elizabeth",
        "Abigail", "David",
        "Henrietta", "Jerry",
        "Shaun"
    };
    
    /**
     * Common test data for all priority queue tests.
     */
    protected int[] priorities = 
    {
        3, 17,
        12, 5,
        4, 30,
        19, 14,
        1, 42,
        99, 31,
        13, 87,
        6
    };
    
    /**
     * Common sorted test data for all priority queue tests.
     */
    protected int[] sortedPriorities =
    {
        99, 87,
        42, 31,
        30, 19,
        17, 14,
        13, 12,
        6, 5,
        4, 3,
        1
    };
    
    /**
     * Common sorted test data for all priority queue tests.
     */
    protected String[] namesSortedByPriority = 
    {
        "Abigail", "Jerry",
        "Elizabeth", "David",
        "Rachel", "Jamie",
        "Kim", "Octavia",
        "Henrietta", "Roger",
        "Shaun", "Jordan",
        "Billy", "Donald",
        "Holly"
    };
    
    /**
     * Maximum number of items to add to the queue.
     */
    protected final int limit = names.length;
    
    
    /**
     * Tests that head throws an exception when called on an empty queue.
     */
    @Test
    public void shouldThrowExceptionWhenHeadCalledOnEmptyQueue()
    {
        try 
        {
            q.head();
            /**
             * Fail if no exception is thrown.
             */
            fail("No Exception recieved when accessing the head of an empty queue");
        } catch(QueueUnderflowException e) {}
    }
    
    
    /**
     * Tests that remove throws an exception when called on an empty queue.
     */
    @Test
    public void shouldThrowExceptionWhenRemovingFromEmptyQueue()
    {
        /**
         * Removing an item from an empty list should throw an exception.
         */
        try
        {
            q.remove();
            //Fail if no exception is thrown
            fail("No Exception received when removing from an empty queue");
        } catch(QueueUnderflowException e){}
    }
    
    
    /**
     * Tests that isEmpty returns true when the queue is empty.
     */
    @Test
    public void shouldReturnTrueWhenIsEmptyCalledOnEmptyQueue()
    {
        assertTrue(q.isEmpty());
    }
    
    
    /**
     * Tests that toString returns the expected string when the queue is empty.
     */
    @Test
    public void shouldReturnExpectedStringFromToStringWhenQueueIsEmpty()
    {
        String expResult = "[]";
        assertEquals(expResult,q.toString());
    }
    
    
    /**
     * Tests that isEmpty returns false if the queue contains at least one item.
     */
    @Test
    public void shouldReturnFalseWhenIsEmptyCalledOnNonEmptyQueue()
    {
        /* Add items recursively until we hit limit checking that isEmpty
         * always returns false. */
        try
        {
            for(int i=0;i<limit;i++)
            {
                q.add(i, limit);
                assertFalse(q.isEmpty());
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException received when adding to queue");
        }
        
        /* Remove items recursively until queue is empty checking that isEmpty
         * returns true as long as there are items in the queue. */
        try
        {
            for(int i=0;i<limit;i++)
            {
                assertFalse(q.isEmpty());
                q.remove();
            }
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException received when removing from a queue that is not empty");
        }
    }
    
    /**
     * Tests that items can be removed from the queue while there is at least
     * one item in the queue.
     */
    @Test
    public void shouldRemoveItemsWhenRemovingFromNonEmptyQueue()
    {
        /* Add items recursively until we hit limit, checking each item is
         * added as we go. */
        try 
        {
            for(int i=0; i<limit; ++i)
            {
                q.add(names[i],priorities[i]);
                
                if(!q.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException received when adding to queue");
        }
        
        /* Remove items recursively until queue is empty, checking each item is
         * removed as we go. */
        try 
        {
            for(int i=0; i<limit; ++i)
            {
                q.remove();
                
                if(q.toString().contains(namesSortedByPriority[i] + ", " + sortedPriorities[i]))
                    fail("Item ("+ namesSortedByPriority[i] + ", " + sortedPriorities[i] + ") was not removed from the queue");
            }
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException received when removing from a queue that is not empty");
        }
    }
    
    
    /**
     * Tests that head always returns the highest priority item while the queue
     * contains at least 1 item.
     */
    @Test
    public void shouldReturnHighestPriorityItemFromHeadWhenQueueNotEmpty()
    {
        /* Add items recursively until we hit limit checking that the highest
         * priority item is always returned from head. */
        int maxIndex = 0;
        try {
            for(int i=0; i<limit; ++i)
            {
                q.add(names[i],priorities[i]);
                maxIndex = (priorities[maxIndex]>priorities[i]) ? maxIndex : i;
                
                /* Check that the highest priority item is returned by head. */
                try
                {
                    assertEquals(names[maxIndex],q.head());
                } catch(QueueUnderflowException e)
                {
                    fail("QueueUnderflowException received when calling head on a non-empty queue");
                }
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException received when adding to queue");
        }
        
        /* Remove items recursively until queue is empty checking that the
         * highest priority item is always returned from head. */
        try {
            for(int i=0; i<limit; ++i)
            {
                /* Check that the highest priority item is returned by head. */
                try
                {
                    assertEquals(namesSortedByPriority[i],q.head());
                } catch(QueueUnderflowException e)
                {
                    fail("QueueUnderflowException received when calling head on a queue that is not empty");
                }
                
                q.remove();
            }
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException received when removing from a queue that is not empty");
        }
    }
    
    
    /**
     * Tests that toString returns the expected string when the queue contains
     * at least one item.
     */
    @Test
    public void shouldReturnExpectedStringFromToStringWhenQueueNotEmpty() 
    {
        /* Add items recursively until we hit limit ensuring that toString 
         * returns a string containing all the correct items as we go. */
        try 
        {
            for(int i=0; i<limit; ++i)
            {
                q.add(names[i],priorities[i]);
                
                /* Loop through the items that should be in the queue and for
                 * each check that toString returns a String containing them. */
                for(int j = 0; j < i; ++j)
                assertTrue(q.toString().contains(names[j] + ", " + priorities[j]));
            }
        } 
        catch(QueueOverflowException e)
        {
            fail("QueueOverflowException received when adding to queue");
        }
        
        /* Recursively remove items until empty ensuring that toString returns a
         * string containing all the correct items as we go. */
        try
        {
            for(int i = 0; i < limit; ++i)
            {
                /* Loop through the items that should remain in the queue and
                 * check that toString returns a string containing them. */
                for(int j = i; j < limit; ++j)
                    assertTrue(q.toString().contains(namesSortedByPriority[j] + ", " + sortedPriorities[j]));
                q.remove();
            }
        }
        catch(QueueUnderflowException e)
        {
            fail("QueueUndeflowException received when removing from a queue that is not empty");
        }
    }
    
    
    /**
     * Tests that items can be added up to the limit.
     */
    @Test
    public void shouldAddItemsWhenAddingToQueue() {
        /* Add items recursively until limit is reached, checking each item is
        * added as we go. */
       try 
       {
           for(int i=0; i<limit; ++i)
           {
               q.add(names[i],priorities[i]);

               if(!q.toString().contains(names[i] + ", " + priorities[i]))
                   fail("Item number "+ (i+1) +" was not added to the queue");
           }
       } catch(QueueOverflowException e) {
           fail("QueueOverflowException received when adding to queue");
       }
    }
    
}
