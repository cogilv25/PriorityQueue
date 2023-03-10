/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package queuemanager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the implementation of the Person class.
 * 
 * @author Calum Lindsay
 */
public class PersonTest {
    
    /**
     * The person instance used in the tests.
     */
    private Person instance;
    
    /**
     * Test string required for tests.
     */
    private static final String TEST_PERSON_NAME = "Donald";
    
    /**
     * This method is run before each test and creates a new person object
     * effectively resetting any state from previously run tests.
     */
    @Before
    public void setUp()
    {
        instance = new Person(TEST_PERSON_NAME);
    }

    /**
     * Tests that getName method operates correctly
     */
    @Test
    public void testGetName()
    {
        String expResult = TEST_PERSON_NAME;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test that toString method operates correctly
     */
    @Test
    public void testToString()
    {
        String expResult = TEST_PERSON_NAME;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
