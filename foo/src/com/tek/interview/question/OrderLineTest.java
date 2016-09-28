package com.tek.interview.question;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OrderLineTest {
	@Rule
	  public final ExpectedException exception = ExpectedException.none();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetItem() throws Exception {
		Item item = new Item("imported box of chocolate", 10.00);
		OrderLine line = new OrderLine(item, 1);
		assertEquals("Item returned is same item that is set as parameter in Constructor", line.getItem(), item);
	}

	@Test
	public void testGetQuantity() throws Exception {
		
		int quantitity = 10;
		OrderLine line = new OrderLine(new Item("imported box of chocolate", (float)10.00), quantitity);
		assertEquals("Item returned is same item that is set as parameter in Constructor", line.getQuantity(), quantitity);
	}
	
	@Test
	public void testIllegalArgumentExceptionForNullItem() throws Exception {
		exception.expect(IllegalArgumentException.class);
		OrderLine orderLine = new OrderLine(null,1);
	}

}
