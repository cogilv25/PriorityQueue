package queuemanager;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Base Test class for all implementations of ****PriorityQueueTest classes that
 * use a fixed capacity container to store elements.
 * 
 * This is the Base class for all ***PriorityQueueTest classes that use a fixed 
 * capacity container to store items. The common functions for these priority 
 * queue implementations are here to avoid excessive code duplication.
 * 
 * Derived classes must implement an @Before method that initializes q.
 * 
 * @author Calum Lindsay
 */
public abstract class FixedCapacityPriorityQueueTestBase extends PriorityQueueTestBase {
    /**
     * The capacity of the Priority Queue to be tested.
     * Should be the same length as the test data if the test data is to be used.
     */
    protected final int capacity = names.length;
    
    /**
     * Tests that items can be added to a queue as long as the queue is not full.
     */
    @Test
    public void shouldAddItemsWhenAddingToNonFullQueue()
    {
        /* Add items recursively until queue is full, checking each item is
         * added as we go. */
        try 
        {
            for(int i=0; i<capacity; ++i)
            {
                q.add(names[i],priorities[i]);
                
                if(!q.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
    }
    
    /**
     * Tests that isEmpty will always return false if there are any items in the
     * queue.
     */
    @Override
    public void shouldReturnFalseWhenIsEmptyCalledOnNonEmptyQueue()
    {
        /* Add items recursively until queue is full checking that isEmpty
         * always returns false. */
        try
        {
            for(int i=0;i<capacity;i++)
            {
                q.add(i, capacity);
                assertFalse(q.isEmpty());
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
        
        /* Remove items recursively until queue is empty checking that isEmpty
         * returns true as long as there are items in the queue. */
        try
        {
            for(int i=0;i<capacity;i++)
            {
                assertFalse(q.isEmpty());
                q.remove();
            }
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException recieved when removing from a queue that is not empty");
        }
    }
    
    /**
     * Tests that items can be removed from the queue while there is at least
     * one item in the queue.
     */
    @Override
    public void shouldRemoveItemsWhenRemovingFromNonEmptyQueue()
    {
        /* Add items recursively until queue is full, checking each item is
         * added as we go. */
        try 
        {
            for(int i=0; i<capacity; ++i)
            {
                q.add(names[i],priorities[i]);
                
                if(!q.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
        
        /* Remove items recursively until queue is empty, checking each item is
         * removed as we go. */
        try 
        {
            for(int i=0; i<capacity; ++i)
            {
                q.remove();
                
                if(q.toString().contains(namesSortedByPriority[i] + ", " + sortedPriorities[i]))
                    fail("Item ("+ namesSortedByPriority[i] + ", " + sortedPriorities[i] + ") was not removed from the queue");
            }
        } catch(QueueUnderflowException e) {
            fail("QueueOverflowException received when removing from a queue that is not empty");
        }
    }
    
    
    /**
     * Tests that head returns the highest priority item while the queue
     * contains at least 1 item.
     */
    @Override
    public void shouldReturnHighestPriorityItemFromHeadWhenQueueNotEmpty()
    {
        /* Add items recursively until queue is full checking that the highest
         * priority item is always returned from head. */
        int maxIndex = 0;
        try {
            for(int i=0; i<capacity; ++i)
            {
                q.add(names[i],priorities[i]);
                maxIndex = (priorities[maxIndex]>priorities[i]) ? maxIndex : i;
                /* Check that the highest priority item is returned by head. */
                try
                {
                    assertEquals(names[maxIndex],q.head());
                } catch(QueueUnderflowException e)
                {
                    fail("QueueUnderflowException recieved when calling head on a non-empty queue");
                }
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
        
        /* Remove items recursively until queue is empty checking that the
         * highest priority item is always returned from head. */
        try {
            for(int i=0; i<capacity; ++i)
            {
                /* Check that the highest priority item is returned by head. */
                try
                {
                    assertEquals(namesSortedByPriority[i],q.head());
                } catch(QueueUnderflowException e)
                {
                    fail("QueueUnderflowException recieved when calling head on a queue that is not empty");
                }
                q.remove();
            }
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException recieved when removing from a queue that is not empty");
        }
    }
    
    /**
     * Tests that toString returns the expected string when the queue contains
     * at least one item.
     */
    @Override
    public void shouldReturnExpectedStringFromToStringWhenQueueNotEmpty() 
    {
        /* Add items recursively until queue is full ensuring that toString 
         * returns a string containing all the correct items as we go. */
        try 
        {
            for(int i=0; i<capacity; ++i)
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
            fail("QueueOverflowException received when adding to a queue that is not full");
        }
        
        /* Recursively remove items until empty ensuring that toString returns a
         * string containing all the correct items as we go. */
        try
        {
            for(int i = 0; i < capacity; ++i)
            {
                /* Loop through the items that should remain in the queue and
                 * check that toString returns a string containing them. */
                for(int j = i; j < capacity; ++j)
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
     * Tests that the add method throws an exception when the queue is full.
     */
    @Test
    public void shouldThrowExceptionWhenAddingToFullQueue()
    {
        /* Add items recursively until queue is full, checking each
         * item is added as we go. */
        try 
        {
            for(int i=0; i<capacity; ++i)
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
