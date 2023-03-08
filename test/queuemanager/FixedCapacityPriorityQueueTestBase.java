/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Calum
 */
public abstract class FixedCapacityPriorityQueueTestBase extends PriorityQueueTestBase {
    protected final int capacity = names.length;
    
    @Override
    public void shouldAddItemsWhenAddingToNonFullQueue()
    {
        //Add items recursively until queue is full, checking each
        // item is added as we go
        try 
        {
            for(int i=0; i<capacity; ++i)
            {
                pq.add(names[i],priorities[i]);
                
                if(!pq.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
    }
    
    
    @Override
    public void shouldReturnFalseWhenIsEmptyCalledOnNonEmptyQueue()
    {
        //Add items recursively until queue is full checking that isEmpty always returns false
        try
        {
            for(int i=0;i<capacity;i++)
            {
                pq.add(i, capacity);
                assertFalse(pq.isEmpty());
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
        
        try
        {
            for(int i=0;i<capacity;i++)
            {
                assertFalse(pq.isEmpty());
                pq.remove();
            }
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException recieved when removing from a queue that is not empty");
        }
    }
    
    @Override
    public void shouldRemoveItemsWhenRemovingFromNonEmptyQueue()
    {
        //Add items recursively until queue is full, checking each
        // item is added as we go
        try 
        {
            for(int i=0; i<capacity; ++i)
            {
                pq.add(names[i],priorities[i]);
                
                if(!pq.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
        
        //Remove items recursively until queue is empty, checking each
        // item is removed as we go
        try 
        {
            for(int i=0; i<capacity; ++i)
            {
                pq.remove();
                
                if(pq.toString().contains(namesSortedByPriority[i] + ", " + sortedPriorities[i]))
                    fail("Item ("+ namesSortedByPriority[i] + ", " + sortedPriorities[i] + ") was not removed from the queue");
            }
        } catch(QueueUnderflowException e) {
            fail("QueueOverflowException received when removing from a queue that is not empty");
        }
    }
    
    @Override
    public void shouldReturnHighestPriorityItemFromHeadWhenQueueNotEmpty()
    {
        //Add items recursively until queue is full checking that the highest priority item is always returned from head
        int maxIndex = 0;
        try {
            for(int i=0; i<capacity; ++i)
            {
                pq.add(names[i],priorities[i]);
                maxIndex = (priorities[maxIndex]>priorities[i]) ? maxIndex : i;
                //Check that the highest priority item is returned by head
                try
                {
                    assertEquals(names[maxIndex],pq.head());
                } catch(QueueUnderflowException e)
                {
                    fail("QueueUnderflowException recieved when calling head on a non-empty queue");
                }
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
        
        //Remove items recursively until queue is empty checking that the highest priority item is always returned from head
        try {
            for(int i=0; i<capacity; ++i)
            {
                //Check that the highest priority item is returned by head
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
    
    @Override
    public void shouldReturnExpectedStringFromToStringWhenQueueNotEmpty() 
    {
        //Add items recursively until queue is full ensuring that
        // toString returns a string containing all the correct items as we go.
        try 
        {
            for(int i=0; i<capacity; ++i)
            {
                pq.add(names[i],priorities[i]);
                
                //Loop through the items that should be in the queue and for each 
                // check that toString returns a String containing them.
                for(int j = 0; j < i; ++j)
                assertTrue(pq.toString().contains(names[j] + ", " + priorities[j]));
            }
        } 
        catch(QueueOverflowException e)
        {
            fail("QueueOverflowException received when adding to a queue that is not full");
        }
        
        //Recursively remove items from the queue until empty ensuring that
        // toString returns a string containing all the correct items as we go.
        try
        {
            for(int i = 0; i < capacity; ++i)
            {
                //Loop through the items that should remain in the queue and for each 
                // check that toString returns a String containing them.
                for(int j = i; j < capacity; ++j)
                    assertTrue(pq.toString().contains(namesSortedByPriority[j] + ", " + sortedPriorities[j]));
                pq.remove();
            }
        }
        catch(QueueUnderflowException e)
        {
            fail("QueueUndeflowException received when removing from a queue that is not empty");
        }
    }
    
    /** 
     * This test is unique to fixed capacity priority queues.
     */
    
    @Test
    public void shouldThrowExceptionWhenAddingToFullQueue()
    {
        //Add items recursively until queue is full, checking each
        // item is added as we go
        try 
        {
            for(int i=0; i<capacity; ++i)
            {
                pq.add(names[i],priorities[i]);
                
                if(!pq.toString().contains(names[i] + ", " + priorities[i]))
                    fail("Item number "+ (i+1) +" was not added to the queue");
            }
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding to a queue that is not full");
        }
        
        //Attempting to add another item should throw an exception
        try
        {
            pq.add("Some Name",420);
            fail("No Exception received when adding an item to a full queue");
        }catch(QueueOverflowException e){}
    }
    
    
    
    
}
