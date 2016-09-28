package com.tek.interview.question;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* ****************************************************************************************
 
Please remove all bugs from the code below to get the following output:

<pre>

*******Order 1*******
1 book: 13.74
1 music CD: 16.49
1 chocolate bar: 0.94
Sales Tax: 2.84
Total: 28.33
*******Order 2*******
1 imported box of chocolate: 11.5
1 imported bottle of perfume: 54.62
Sales Tax: 8.62
Total: 57.5
*******Order 3*******
1 Imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 10.73
1 box of imported chocolates: 12.94
Sales Tax: 8.77
Total: 67.98
Sum of orders: 153.81
 
</pre>
 
******************************************************************************************** */

/*
 * represents an item, contains a price and a description.
 *
 */
class Item {

	private String description;
	private double price; //Changed to double for consistency

	public Item(String description, double price) {
		//super(); no need to call super
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
}

/*
 * represents an order line which contains the @link Item and the quantity.
 *
 */
class OrderLine {

	private int quantity;
	private Item item;

	/*
	 * @param item Item of the order
	 * 
	 * @param quantity Quantity of the item
	 */
	public OrderLine(Item item, int quantity) throws Exception {
		if (item == null) {
			System.err.println("ERROR - Item is NULL");
			throw new IllegalArgumentException("Item is NULL");
		}
		assert quantity > 0;
		//reference class members item/quantity using this.item/this.quality
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}
}

class Order {
	//Initialise orderLines
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public void add(OrderLine o) throws Exception {
		if (o == null) {
			System.err.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}
		orderLines.add(o);
	}

	public int size() {
		return orderLines.size();
	}

	public OrderLine get(int i) {
		return orderLines.get(i);
	}

	public void clear() {
		this.orderLines.clear();
	}
}

class calculator {

	/**
	 * rounding method returns precision upto two decimals for any given double
	 * ex: 13.33333, retuns 13.33
	 * @param value
	 * @return
	 */
	private static double rounding(double value) {
		return ( Math.floor (value * 100) / 100);  //Converting double to int looses precision instead use Math.floor to get right value correct upto 2 decimals
	}

	/**
	 * receives a collection of orders. For each order, iterates on the order lines and calculate the total price which
	 * is the item's price * quantity * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without taxes for this order
	 * @return 
	 */
	public double calculate(Map<String, Order> o) {

		double grandtotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : o.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");
		//	grandtotal = 0;

			Order r = entry.getValue();

			double total = getTotalPricePerOrderIncludingTax(r);

			grandtotal += total;
		}

		System.out.println("Sum of orders: " +  rounding(grandtotal));
		return grandtotal;
	}

	protected double getTotalPricePerOrderIncludingTax(Order r) {
		double totalTax = 0;
		double total = 0;

		// Iterate through the items in the order
		// it should be i<r.size() instead or  i <= r.size();
		for (int i = 0; i < r.size(); i++) {

			// Calculate the taxes
			double tax = 0;

			OrderLine orderLine = r.get(i);
			tax = getSalesTaxForOrderLine(orderLine);

			// Calculate the total price
			double totalprice = rounding(orderLine.getItem().getPrice()*orderLine.getQuantity() + tax);

			// Print out the item's total price
			System.out.println(orderLine.getItem().getDescription() + ": " + totalprice);

			// Keep a running total
			totalTax += tax;
			total += orderLine.getItem().getPrice();
		}

		// Print out the total taxes
		System.out.println("Sales Tax: " + rounding(totalTax));

		// Print out the total amount
		System.out.println("Total: " + rounding(total));
		total = total + totalTax;
		return total;
	}

	/**
	 * This method returns sales tax per order line, depending on weather item is imported or not
	 * If imported sales tax is 15% else it is 10%
	 * @param orderLine
	 * @return
	 */
	protected double getSalesTaxForOrderLine(OrderLine orderLine) {
		double tax;
		if (orderLine.getItem().getDescription().contains("imported")) {
			tax = (orderLine.getItem().getPrice()*orderLine.getQuantity() * 0.15); // Extra 5% tax on
			// imported items
		} else {
			tax = (getTotalPriceForOrderLineBeforeTax(orderLine) * 0.10);
		}
		return (tax);
	}
	
	/**
	 * return total price per OrderLine i.e. multiply item price by quantity
	 * item.price() * quantitiy
	 * @param orderLine
	 * @return
	 */
	protected double getTotalPriceForOrderLineBeforeTax(OrderLine orderLine){
		return orderLine.getItem().getPrice()* orderLine.getQuantity();
	}
}

public class Foo {

	public static void main(String[] args) throws Exception {

		Map<String, Order> o = new HashMap<String, Order>();

		Order c = new Order();

		//double grandTotal = 0;  this variable is never used

		c.add(new OrderLine(new Item("book", (float) 12.49), 1));
		c.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		c.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		o.put("Order 1", c);

		// Reuse cart for an other order  "Should not reuse  same object because it in Map it is stored as reference'
		//if you clear and add new value all map entries will have same Order
		//c.clear();
		c = new Order();

		c.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		c.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));

		o.put("Order 2", c);

		// Reuse cart for an other order  "Should not reuse  same object because it in Map it is stored as reference'
		//if you clear and add new value all map entries will have same Order
		//c.clear();
		c = new Order();

		c.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		c.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		c.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		c.add(new OrderLine(new Item("box of importd chocolates", (float) 11.25), 1));

		o.put("Order 3", c);

		new calculator().calculate(o);

	}
}
