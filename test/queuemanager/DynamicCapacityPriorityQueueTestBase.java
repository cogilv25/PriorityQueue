/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Base Test class for all implementations of ****PriorityQueueTest classes that
 * use a dynamic LIMIT container to store elements.
 * 
 * This is the Base class for all ***PriorityQueueTest classes that use a 
 * dynamic LIMIT container to store items. The common functions for these 
 * priority queue implementations are here to avoid excessive code duplication.
 * 
 * As these implementations dynamically increase their size as elements are
 * added we could test priority queue's up to the limits of available memory,
 * however, for the sake of time and my own sanity I will only test up to 15
 * items.
 * 
 * Derived classes must implement an @Before method that initializes pq.
 * 
 * @author Calum Lindsay
 */
public abstract class DynamicCapacityPriorityQueueTestBase extends PriorityQueueTestBase {

    protected static final int LIMIT = 15;
    /**
     * Tests that items can be added up to the LIMIT.
     * 
     * This is done by recursively adding items until we hit LIMIT and
     * ensuring that each item has actually been added to the queue.
     */
    @Test
    public void shouldAddItemsWhenAddingToQueue() {
      /**
         * Add items recursively until LIMIT is reached, checking each item is
         * added as we go.
         */
        try 
        {
            for(int i=0; i<LIMIT; ++i)
            {
                pq.add(names[i],priorities[i]);
                
                if(!pq.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to queue");
        }
    }

    /**
     * Tests that isEmpty will always return false if there are any items in the
     * queue up to LIMIT.
     * 
     * This is done by recursively adding items until we hit LIMIT and
     * ensuring that isEmpty returns false after each item is added. Next
     * items are recursively removed until the queue is empty and it is checked
     * that isEmpty returns false before each item is returned. 
     */
    @Override
    public void shouldReturnFalseWhenIsEmptyCalledOnNonEmptyQueue()
    {
        /**
         * Add items recursively until we hit LIMIT checking that isEmpty
         * always returns false.
         */
        try
        {
            for(int i=0;i<LIMIT;i++)
            {
                pq.add(i, LIMIT);
                assertFalse(pq.isEmpty());
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to queue");
        }
        
        /**
         * Remove items recursively until queue is empty checking that isEmpty
         * returns true as long as there are items in the queue.
         */
        try
        {
            for(int i=0;i<LIMIT;i++)
            {
                assertFalse(pq.isEmpty());
                pq.remove();
            }
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException recieved when removing from a queue that is not empty");
        }
    }
    
    /**
     * Tests that items can be removed from the queue while there is at least
     * one item in the queue.
     * 
     * This is done by first filling the list with items up to LIMIT then
     * recursively removing items until the queue is empty and checking after
     * each item is removed that the item was actually removed from the queue.
     */
    @Override
    public void shouldRemoveItemsWhenRemovingFromNonEmptyQueue()
    {
        /**
         * Add items recursively until we hit LIMIT, checking each item is
         * added as we go.
         */
        try 
        {
            for(int i=0; i<LIMIT; ++i)
            {
                pq.add(names[i],priorities[i]);
                
                if(!pq.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to queue");
        }
        
        /**
         * Remove items recursively until queue is empty, checking each item is
         * removed as we go.
         */
        try 
        {
            for(int i=0; i<LIMIT; ++i)
            {
                pq.remove();
                
                if(pq.toString().contains(namesSortedByPriority[i] + ", " + sortedPriorities[i]))
                    fail("Item ("+ namesSortedByPriority[i] + ", " + sortedPriorities[i] + ") was not removed from the queue");
            }
        } catch(QueueUnderflowException e) {
            fail("QueueOverflowException received when removing from a queue that is not empty");
        }
    }
    
    
    /**
     * Tests that head always returns the highest priority item while the queue
     * contains at least 1 item.
     * 
     * This is done by first filling the list up to LIMIT with items and
     * checking after each item is added that head returns the highest priority
     * item then recursively removing items until the queue is empty and
     * checking before each item is removed that head returns the highest 
     * priority item.
     */
    @Override
    public void shouldReturnHighestPriorityItemFromHeadWhenQueueNotEmpty()
    {
        /**
         * Add items recursively until we hit LIMIT checking that the highest
         * priority item is always returned from head.
         */
        int maxIndex = 0;
        try {
            for(int i=0; i<LIMIT; ++i)
            {
                pq.add(names[i],priorities[i]);
                maxIndex = (priorities[maxIndex]>priorities[i]) ? maxIndex : i;
                /**
                 * Check that the highest priority item is returned by head.
                 */
                try
                {
                    assertEquals(names[maxIndex],pq.head());
                } catch(QueueUnderflowException e)
                {
                    fail("QueueUnderflowException recieved when calling head on a non-empty queue");
                }
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to queue");
        }
        
        /**
         * Remove items recursively until queue is empty checking that the
         * highest priority item is always returned from head.
         */
        try {
            for(int i=0; i<LIMIT; ++i)
            {
                /**
                 * Check that the highest priority item is returned by head.
                 */
                try
                {
                    assertEquals(namesSortedByPriority[i],pq.head());
                } catch(QueueUnderflowException e)
                {
                    fail("QueueUnderflowException recieved when calling head on a queue that is not empty");
                }
                pq.remove();
            }
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException recieved when removing from a queue that is not empty");
        }
    }
    
    /**
     * Tests that toString returns the expected string when the queue contains
     * at least one item.
     * 
     * This is done by first filling the list up to LIMIT with items and
     * checking after each item is added that toString contains all the expected
     * items then recursively removing items until the queue is empty and
     * checking before each item is removed that toString contains all the
     * expected items.
     */
    @Override
    public void shouldReturnExpectedStringFromToStringWhenQueueNotEmpty() 
    {
        /**
         * Add items recursively until we hit LIMIT ensuring that toString 
         * returns a string containing all the correct items as we go.
         */
        try 
        {
            for(int i=0; i<LIMIT; ++i)
            {
                pq.add(names[i],priorities[i]);
                
                /**
                 * Loop through the items that should be in the queue and for
                 * each check that toString returns a String containing them.
                 */
                for(int j = 0; j < i; ++j)
                assertTrue(pq.toString().contains(names[j] + ", " + priorities[j]));
            }
        } 
        catch(QueueOverflowException e)
        {
            fail("QueueOverflowException received when adding to queue");
        }
        
        /**
         * Recursively remove items from the queue until empty ensuring that
         * toString returns a string containing all the correct items as we go.
         */
        try
        {
            for(int i = 0; i < LIMIT; ++i)
            {
                /**
                 * Loop through the items that should remain in the queue and
                 * for each check that toString returns a string containing them.
                 */
                for(int j = i; j < LIMIT; ++j)
                    assertTrue(pq.toString().contains(namesSortedByPriority[j] + ", " + sortedPriorities[j]));
                pq.remove();
            }
        }
        catch(QueueUnderflowException e)
        {
            fail("QueueUndeflowException received when removing from a queue that is not empty");
        }
    }
    
}
