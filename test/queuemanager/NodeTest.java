package queuemanager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the implementation of a node used by all linked lists in the
 * project.
 *
 * @author Calum Lindsay
 */
public class NodeTest {
    
    /**
     * The node instance used in the tests.
     */
    private Node<String> instance;
    
    /**
     * Test string required for tests.
     */
    private String TEST_STRING = "EVGA";
    
    /**
     * Test string required for tests.
     */
    private String TEST_STRING_2 = "ASUS";
    
    /**
     * This method is run before each test and creates a new node
     * effectively resetting any state from previously run tests.
     */
    @Before
    public void setUp()
    {
        instance = new Node<>(TEST_STRING);
    }
    
    /**
     * Tests that the getValue method operates correctly.
     */
    @Test
    public void testGetValue()
    {
        assertEquals(TEST_STRING, instance.getValue());
    }
    
    /**
     * Tests that the setNext and getNext methods operate correctly.
     */
    @Test
    public void testSetNextAndGetNext()
    {
        /* When a node has no other node assigned to its next variable it
         * should return null. */
        assertEquals(null, instance.getNext());
        
        /* When a node has a node assigned to its next variable it should return
         * that node. */
        Node<String> nextNode = new Node<>(TEST_STRING_2);
        instance.setNext(nextNode);
        assertEquals(nextNode,instance.getNext());
    }
}
