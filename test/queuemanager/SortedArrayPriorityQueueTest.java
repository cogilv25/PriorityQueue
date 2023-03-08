/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package queuemanager;

import org.junit.Before;

/**
 *
 * @author Calum
 */
public class SortedArrayPriorityQueueTest extends FixedCapacityPriorityQueueTestBase {
    
    @Before
    public void setUp()
    {
        pq = new SortedArrayPriorityQueue<>(capacity);
    }
    
}
