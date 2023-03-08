/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

import org.junit.Before;

/**
 *
 * @author Calum
 */
public class UnsortedArrayPriorityQueueTest extends FixedCapacityPriorityQueueTestBase 
{
    
    @Before
    public void setUp()
    {
        pq = new UnsortedArrayPriorityQueue<>(capacity);
    }
}
