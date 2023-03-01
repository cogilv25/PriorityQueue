/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
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
public class SortedArrayPriorityQueueTest {
    
    private SortedArrayPriorityQueue<String> stringQueue;
    
    
    @Before
    public void setUp()
    {
        stringQueue = new SortedArrayPriorityQueue<>(8);
    }
    
    /**
     * Test of head method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testHead(){
        //Calling head() on an empty queue should throw a
        // QueueUnderflowException
        try{
            stringQueue.head();
            fail("Expected Exception was not thrown");
        }
        catch(QueueUnderflowException e)
        {}
           
        //Add some items to the queue. Any exceptions
        // here are unexpected
        try{
            stringQueue.add("Donald", 3);
            stringQueue.add("Roger", 12);
            stringQueue.add("Jordan", 5);
            stringQueue.add("Holly", 1);
        }
        catch(QueueOverflowException e)
        {
            fail("Unexpected QueueOverflowException thrown");
        }
        
        
        //Call head() on the queue to get the highest priority
        // item from the queue
        String result;
        try{
            result = stringQueue.head();
        }
        catch(QueueUnderflowException e)
        {
            fail("Unexpected QueueUnderflowException thrown");
            return;
        }
        
        //Test that the expected item was retrieved from
        // the queue
        String expResult = "Roger";
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testAdd(){
        //Add the maximum number of items to the queue.
        // Any exceptions here are unexpected
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
        
        //Attempt to add another item to the queue.
        // This should throw a QueueOverflowException
        try{
            stringQueue.add("Trevor", 505);
            fail("Expected Exception was not thrown");
        }
        catch(QueueOverflowException e)
        {}
        
        assertFalse(stringQueue.isEmpty());
    }
    
    /**
     * Test of remove method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testRemove(){
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
        
        //Remove the highest priority item from the queue.
        try{
            stringQueue.remove();
        }
        catch(QueueUnderflowException e)
        {
            fail("Unexpected QueueUnderflowException thrown");
        }
        
        //Check that stringQueue contains all expected
        // items
        String expResult = "[(Jamie, 19), (Octavia, 13), (Roger, 12), "
                + "(Jordan, 5), (Billy, 4), (Donald, 3), (Holly, 1)]";
        String result = stringQueue.toString();
        
        assertEquals(expResult,result);
    }

    /**
     * Test of isEmpty method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testIsEmpty() {
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

    /**
     * Test of toString method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testToString(){
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
        expResult = "[(Rachel, 30), (Jamie, 19), (Octavia, 13), (Roger, 12), "
                + "(Jordan, 5), (Billy, 4), (Donald, 3), (Holly, 1)]";
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
        expResult = "[(Roger, 12), (Jordan, 5), (Billy, 4), (Donald, 3), (Holly, 1)]";
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
        expResult = "[(David, 78), (Roger, 12), (Jordan, 5), (Billy, 4), (Donald, 3), (Henrietta, 3), (Holly, 1)]";
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
