/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package queuemanager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Calum
 */
public class PriorityItemTest {
    
    private PriorityItem<String> instance;
    private static final String MOCK_STRING = "Donald";
    private static final int MOCK_PRIORITY = 1;
    
    @Before
    public void setUp() {
        instance = new PriorityItem<>(MOCK_STRING,MOCK_PRIORITY);
    }

    /**
     * Test of getItem method, of class PriorityItem.
     */
    @Test
    public void testGetItem() {
        Object expResult = MOCK_STRING;
        Object result = instance.getItem();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriority method, of class PriorityItem.
     */
    @Test
    public void testGetPriority() {
        int expResult = MOCK_PRIORITY;
        int result = instance.getPriority();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class PriorityItem.
     */
    @Test
    public void testToString() {
        String expResult = "(" + MOCK_STRING + ", " + MOCK_PRIORITY + ")";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
