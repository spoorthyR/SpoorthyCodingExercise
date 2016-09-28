package com.tek.interview.question;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OrderTest {
	@Rule
	  public final ExpectedException exception = ExpectedException.none();
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOrder() {
		Order order = new Order();
		assertTrue("Order sise should be zero and should not throw Exception", order.size()==0);
		try {
		OrderLine line = new OrderLine(new Item("imported box of chocolate", 10), 1);
			order.add(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue("Order size should be 1", order.size()==1);
		
	}

	@Test
	public void testIllegalArgumentExceptionForNullOrderLine() throws Exception {
		Order order = new Order();
		exception.expect(IllegalArgumentException.class);
		order.add(null);
	}

}
