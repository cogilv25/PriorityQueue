/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Base Test class for all implementations of ****PriorityQueueTest classes.
 * 
 * Naming this class PriorityQueueTest causes JUnit4 to attempt to run the tests
 * which isn't what we want. This is the Base class for all ***PriorityQueueTest
 * classes it contains the common functions for all priority queue
 * implementations to avoid excessive code duplication.
 * 
 * @author Calum Lindsay
 */
public abstract class PriorityQueueTestBase {
    protected PriorityQueue pq = null;
    
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
     * Tests that head throws an exception when called on an empty queue.
     */
    @Test
    public void shouldThrowExceptionWhenHeadCalledOnEmptyQueue()
    {
        /**
         * Checking the highest priority item on an empty list should throw an
         * exception.
         */
        try 
        {
            pq.head();
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
            pq.remove();
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
        assertTrue(pq.isEmpty());
    }
    
    /**
     * Tests that toString returns the expected string when the queue is empty.
     */
    @Test
    public void shouldReturnExpectedStringFromToStringWhenQueueIsEmpty()
    {
        String expResult = "[]";
        assertEquals(expResult,pq.toString());
    }
    
    
    /**
     * The implementation of these functions differs depending on whether or not
     * the ADT has an expandable capacity.
     */
    @Test
    public abstract void shouldAddItemsWhenAddingToNonFullQueue();
    
    @Test
    public abstract void shouldReturnFalseWhenIsEmptyCalledOnNonEmptyQueue();
    
    @Test
    public abstract void shouldRemoveItemsWhenRemovingFromNonEmptyQueue();
    
    @Test
    public abstract void shouldReturnHighestPriorityItemFromHeadWhenQueueNotEmpty();
    
    @Test
    public abstract void shouldReturnExpectedStringFromToStringWhenQueueNotEmpty();
    
    @Test
    public void shouldAddAndRemove1000ItemsWithoutError()
    {
        //TODO: Implementations
    }
    
}
