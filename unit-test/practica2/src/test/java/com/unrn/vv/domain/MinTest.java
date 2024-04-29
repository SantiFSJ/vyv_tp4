package com.unrn.vv.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MinTest {
    private List<String> list;   // Test fixture
 
    @BeforeEach      // Set up - Called before every test method.
    public void setUp()
    {
       list = new ArrayList<String>();
    }
 
    @AfterEach     // Tear down - Called after every test method.
    public void tearDown()
    {
       list = null;
    }
 
    @Test
    public void testForNullList()
    {
       list = null;
       try {
          Min.min (list);
       } catch (NullPointerException e) {
          return;
       }
       fail("NullPointerException expected");
    }
 
    @Test
    public void testForNullElement()
    {
       list.add (null);
       list.add ("cat");
       assertThrows(NullPointerException.class, () -> Min.min (list));
    
    }
 
    @Test
    public void testForSoloNullElement()
    {
       list.add (null);
       assertThrows(NullPointerException.class, () -> Min.min (list));
    }
 
    @SuppressWarnings ("unchecked")
    public void testMutuallyIncomparable()
    {
       List list = new ArrayList<>();
       list.add ("cat");
       list.add ("dog");
       list.add (1);
       assertThrows(ClassCastException.class, () -> Min.min (list));
    }
 
    @Test
    public void testEmptyList()
    {
       Min.min (list);
       assertThrows(IllegalArgumentException.class, () -> Min.min (list));
    }
 
    @Test
    public void testSingleElement()
    {
       list.add ("cat");
       Object obj = Min.min (list);
       assertTrue(obj.equals ("cat"), "Lista de un Solo elemento");
    }
 
    @Test
    public void testDoubleElement()
    {
       list.add ("dog");
       list.add ("cat");
       Object obj = Min.min (list);
       assertTrue ( obj.equals ("cat"), "Lista con dos Elementos");
    }
 }
