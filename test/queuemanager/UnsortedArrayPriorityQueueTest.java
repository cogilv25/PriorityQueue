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
 *
 * @author Calum
 */
public class UnsortedArrayPriorityQueueTest {
    
    private UnsortedArrayPriorityQueue<String> stringQueue;
    
    @Before
    public void setUp()
    {
        stringQueue = new UnsortedArrayPriorityQueue<>(8);
    }
    
    @Test
    public void testAdd() throws QueueOverflowException
    {   
        //Adding an item to an empty queue
        try {
            stringQueue.add("Donald", 1);
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding an item to an empty queue");
        }
        //Filling the rest of the queue
        try {
            stringQueue.add("Roger", 12);
            stringQueue.add("Jordan", 5);
            stringQueue.add("Holly", 1);
            stringQueue.add("Rachel", 30);
            stringQueue.add("Jamie", 19);
            stringQueue.add("Octavia", 13);
            stringQueue.add("Billy", 4);
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding an item to a partially filled queue");
        }
        //Removing 2 items
        try {
            stringQueue.remove();
            stringQueue.remove();
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException recieved when removing an item from a partially filled queue");
        }
        //Add 2 items to make queue full again
        try {
            stringQueue.add("Ricky", 30);
            stringQueue.add("Jennifer", 19);
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding an item to an empty queue");
        }
        //Attempt to add another item, should throw exception
        try {
            stringQueue.add("Lola", 420);
            //Fail if no exception is thrown
            fail("No Exception recieved when addding an item to a full queue");
        } catch(QueueOverflowException e) {
        }
        //Check the queue has all items in their expected places
        String expResult = "[(Roger, 12), (Jordan, 5), (Holly, 1), (Octavia, 13), (Billy, 4), (Ricky, 30), (Jennifer, 19)]";
        String result = stringQueue.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testHead() throws QueueUnderflowException
    {
        //Checking the highest priority item on an empty list
        // should throw an exception
        try {
            stringQueue.head();
            //Fail if no exception is thrown
            fail("No Exception recieved when accessing the head of an empty queue");
        } catch(QueueUnderflowException e) {
        }
        //Add some items to the queue in an arbitrary order
        try {
            stringQueue.add("Roger", 12);
            stringQueue.add("Jordan", 5);
            stringQueue.add("Billy", 4);
            stringQueue.add("Rachel", 30);
            stringQueue.add("Jamie", 19);
            stringQueue.add("Octavia", 13);
            stringQueue.add("Holly", 1);
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding an item to a partially filled queue");
        }
        //Check that head returns the highest priority item
        String expResult = "Rachel";
        String result = stringQueue.head();
        assertEquals(expResult, result);
        //Remove the highest priority item
        try {
            stringQueue.remove();
        } catch(QueueUnderflowException e) {
            fail("QueueUnderflowException recieved when removing an item from a partially filled queue");
        }
        //Check that head returns the new highest priority item
        expResult = "Jamie";
        result = stringQueue.head();
        assertEquals(expResult, result);
    }

    @Test
    public void testRemove() throws QueueUnderflowException
    {
        //Attempting to remove the highest priority item
        // on an empty list should throw an exception
        try {
            stringQueue.remove();
            //Fail if no exception is thrown
            fail("No Exception recieved when removing from an empty queue");
        } catch(QueueUnderflowException e) {
        }
        
         //Fill the queue with some items
        try {
            stringQueue.add("Roger", 12);
            stringQueue.add("Jordan", 5);
            stringQueue.add("Billy", 4);
            stringQueue.add("Rachel", 30);
            stringQueue.add("Jamie", 19);
            stringQueue.add("Octavia", 13);
            stringQueue.add("Holly", 1);
            stringQueue.add("Danielle", 42);
        } catch(QueueOverflowException e) {
            fail("QueueOverflowException recieved when adding an item to a partially filled queue");
        }
        //Remove all items from the queue and for each one removed
        // check that the correct item has been removed
        String [] strings = {"Danielle", "Rachel", "Jamie", "Octavia",
            "Roger", "Jordan", "Billy"};
        try{
            for(int i =0;i<7;++i)
            {
                stringQueue.remove();
                assertNotEquals(strings[i],"");
            }
        }catch(QueueUnderflowException e)
        {
            fail("QueueUnderflowException recieved when removing an item from a partially filled queue");
        }
        
        //We will now retry removing the highest priority item
        // from the empty list which should again throw an exception
        try {
            stringQueue.remove();
            //Fail if no exception is thrown
            fail("No Exception recieved when removing from an empty queue");
        } catch(QueueUnderflowException e) {
        }
    }

    @Test
    public void testIsEmpty()
    {
        //isEmpty should return true when the
        // queue is empty.
        assertTrue(stringQueue.isEmpty());
        
        try{
            stringQueue.add("Donald", 3);
            stringQueue.add("Roger", 12);
            stringQueue.add("Jordan", 5);
            stringQueue.add("Holly", 1);
            stringQueue.add("Rachel", 30);
            stringQueue.add("Jamie", 19);
            stringQueue.add("Octavia", 13);
            stringQueue.add("Billy", 4);
        }
        catch(QueueOverflowException e)
        {
            fail("Unexpected QueueOverflowException thrown");
        }
        
        //Recursively remove one item at a time and
        // test that isEmpty returns false until
        // there is one item left in the queue
        for(int i = 0;i<7;++i)
        {
            try{
                stringQueue.remove();
            }catch(QueueUnderflowException e)
            {
                fail("Unexpected QueueUnderflowException thrown");
            }
            assertFalse(stringQueue.isEmpty());
        }
        
        //Finally remove the last item and check that the
        // isEmpty method returns true
        try{
            stringQueue.remove();
        }catch(QueueUnderflowException e)
        {
            fail("Unexpected QueueUnderflowException thrown");
        }
        
        assertTrue(stringQueue.isEmpty());
    }
    
    @Test
    public void testToString()
    {
        //Check that when the queue is empty we get
        // the expected string from toString
        String expResult = "[]";
        String result = stringQueue.toString();
        assertEquals(expResult, result);
        
        //Add the maximum number of items to the queue.
        try{
            stringQueue.add("Donald", 3);
            stringQueue.add("Roger", 12);
            stringQueue.add("Jordan", 5);
            stringQueue.add("Holly", 1);
            stringQueue.add("Rachel", 30);
            stringQueue.add("Jamie", 19);
            stringQueue.add("Octavia", 13);
            stringQueue.add("Billy", 4);
        }
        catch(QueueOverflowException e)
        {
            fail("Unexpected QueueOverflowException thrown");
        }
        
        //Check that we get the expected string from
        // toString with a full queue
        expResult = "[(Donald, 3), (Roger, 12), (Jordan, 5), (Holly, 1), "
                + "(Rachel, 30), (Jamie, 19), (Octavia, 13), (Billy, 4)]";
        result = stringQueue.toString();
        assertEquals(expResult, result);
        
        try{
            stringQueue.remove();
            stringQueue.remove();
            stringQueue.remove();
        }
        catch(QueueUnderflowException e)
        {
            fail("Unexpected QueueUnderflowException thrown");
        }
        
        //Check that we get the expected string from toString
        expResult = "[(Donald, 3), (Roger, 12), (Jordan, 5), (Holly, 1), (Billy, 4)]";
        result = stringQueue.toString();
        assertEquals(expResult, result);
        
        //Add two more items to the queue.
        try{
            stringQueue.add("David", 78);
            stringQueue.add("Henrietta", 3);
        }
        catch(QueueOverflowException e)
        {
            fail("Unexpected QueueOverflowException thrown");
        }
        
        //Check that we get the expected string from toString
        expResult = "[(Donald, 3), (Roger, 12), (Jordan, 5), (Holly, 1), (Billy, 4), (David, 78), (Henrietta, 3)]";
        result = stringQueue.toString();
        assertEquals(expResult, result);
        
        //Remove all items from the priority queue
        try{
            for(int i=0;i<7;i++)
            {
                stringQueue.remove();
            }
        }
        catch(QueueUnderflowException e)
        {
            fail("Unexpected QueueUnderflowException thrown");
        }
        
        //Check that we get the expected string from toString
        expResult = "[]";
        result = stringQueue.toString();
        assertEquals(expResult, result);
    }
}
