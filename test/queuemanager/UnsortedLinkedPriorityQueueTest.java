/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

import org.junit.Before;

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
}
