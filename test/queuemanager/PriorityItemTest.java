package queuemanager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the implementation of the PriorityItem class
 * 
 * @author Calum Lindsay
 */
public class PriorityItemTest
{
    
    /**
     * The PriorityItem instance used in the tests.
     */
    private PriorityItem<String> instance;
    
    /**
     * Test string required for tests.
     */
    private static final String TEST_STRING = "Donald";
    
    /**
     * Test string required for tests.
     */
    private static final int TEST_PRIORITY = 1;
    
    
    /**
     * This method is run before each test and creates a new PriorityItem
     * object effectively resetting any state from previously run tests.
     */
    @Before
    public void setUp() {
        instance = new PriorityItem<>(TEST_STRING,TEST_PRIORITY);
    }

    
    /**
     * Tests that getItem method operates correctly
     */
    @Test
    public void testGetItem() {
        Object expResult = TEST_STRING;
        Object result = instance.getItem();
        assertEquals(expResult, result);
    }

    
    /**
     * Test that getPriority method operates correctly
     */
    @Test
    public void testGetPriority() {
        int expResult = TEST_PRIORITY;
        int result = instance.getPriority();
        assertEquals(expResult, result);
    }

    
    /**
     * Test that toString method operates correctly
     */
    @Test
    public void testToString() {
        String expResult = "(" + TEST_STRING + ", " + TEST_PRIORITY + ")";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
