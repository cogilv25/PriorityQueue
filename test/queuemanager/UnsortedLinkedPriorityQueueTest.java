/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *Test class for the implementation of the UnsortedLinkedPriorityQueue ADT.
 * 
 * @author Calum Lindsay
 */
public class UnsortedLinkedPriorityQueueTest extends DynamicCapacityPriorityQueueTestBase
{
    /**
     * This function is run before each test and creates a new priority queue
     * effectively resetting any state from previously run tests.
     */
    @Before
    public void setUp()
    {
        pq = new UnsortedLinkedPriorityQueue<String>();
    }
    
    /**
     * Tests a possible flaw where when the ULPQ ADT seeks the highest priority 
     * item it goes past the end of the linked list.
     */
    @Test
    public void shouldReturnHighestPriorityItemFromHeadWhenHighestPriorityItemAtEndOfLinkedList()
    {
        /**
        * Add items recursively until LIMIT is reached, in order from lowest
        * priority to highest priority.
        */
       try 
       {
           for(int i=LIMIT-1; i>=0; --i)
               pq.add(namesSortedByPriority[i],sortedPriorities[i]);
           
       } catch(QueueOverflowException e)
       {
           fail("QueueOverflowException recieved when adding to queue");
       }
       
       
       try {
           assertEquals(namesSortedByPriority[0],pq.head());
       }
       catch(QueueUnderflowException e)
       {
           fail("QueueUnderflowException received when calling head on a non-empty queue");
       }
    }
    
}
