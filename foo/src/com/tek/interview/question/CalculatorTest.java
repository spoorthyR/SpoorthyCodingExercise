package com.tek.interview.question;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest extends calculator {

	private calculator caluculator;

	@Before
	public void setUp() throws Exception {
		caluculator = new calculator();
	}

	@After
	public void tearDown() throws Exception {
		caluculator = null;
	}

	@Test
	public void testCalculate() throws Exception {
		Map<String, Order> o = new HashMap<String, Order>();

		Order c = new Order();

		//double grandTotal = 0;  this variable is never used

		c.add(new OrderLine(new Item("book",  10), 1));
		c.add(new OrderLine(new Item("music CD",  10), 1));
		c.add(new OrderLine(new Item("chocolate bar",  10), 1));

		o.put("Order 1", c);
		
		double totalPrice = caluculator.calculate(o);
		assertEquals("Expected result not returned",33.00, totalPrice,.01);
		
	}

	@Test
	public void testgetSalesTaxForOrderLine()  throws Exception{
		double tax = caluculator.getSalesTaxForOrderLine(new OrderLine(new Item("book",  10), 1));
		assertEquals("Expected result not returned",1.00, tax,.00);
		
		tax = caluculator.getSalesTaxForOrderLine(new OrderLine(new Item("imported book",  10), 1));
		assertEquals("Expected result not returned",1.50, tax,.00);
		
		tax = caluculator.getSalesTaxForOrderLine(new OrderLine(new Item("imported book",  10), 3));
		assertEquals("Expected result not returned",4.50, tax,.00);
	}
}
