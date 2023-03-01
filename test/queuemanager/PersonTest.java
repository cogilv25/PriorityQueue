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
public class PersonTest {
    
    private Person instance;
    private static final String MOCK_PERSON_NAME = "Donald";
    
    @Before
    public void setUp() {
        instance = new Person(MOCK_PERSON_NAME);
    }

    /**
     * Test of getName method, of class Person.
     */
    @Test
    public void testGetName() {
        String expResult = MOCK_PERSON_NAME;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        String expResult = MOCK_PERSON_NAME;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
