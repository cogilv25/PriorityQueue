package queuemanager;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Base Test class for all implementations of ****PriorityQueueTest classes that
 * use a dynamic limit container to store elements.
 * 
 * This is the Base class for all ***PriorityQueueTest classes that use a 
 * dynamic capacity container to store items. The common functions for these 
 * priority queue implementations are here to avoid excessive code duplication.
 * 
 * As these implementations dynamically increase their size as elements are
 * added we could test priority queue's up to the limits of available memory,
 * however, for the sake of time and my own sanity I will only test up to 15
 * items.
 * 
 * Derived classes must implement an @Before method that initializes q.
 * 
 * @author Calum Lindsay
 */
public abstract class DynamicCapacityPriorityQueueTestBase extends PriorityQueueTestBase {

    /**
     * Maximum number of items to add to the queue.
     */
    protected final int limit = names.length;
    
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

    
    /* These methods inherit their JavaDoc comments from PriorityQueueTestBase. */
    
    
    @Override
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
    
    
    @Override
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
    
    
    @Override
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
    
    
    @Override
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
    
}
