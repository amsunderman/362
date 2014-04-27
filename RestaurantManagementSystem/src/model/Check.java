package model;

import interfaces.CheckInterface;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Model object representing the monetary value of a collection of customer orders
 * @author Team 3
 *
 */
public class Check implements CheckInterface {
	
	/**
	 * list of orders that this instance of check represents
	 */
	ArrayList<Integer> orders = new ArrayList<Integer>();
	
	/**
	 * constructor for Check. takes in a string of order numbers (space delimited
	 * @param orderString space delimited string of order numbers
	 */
	public Check(String orderString) {
		
		// initialize scanner to parse space delimited input
		Scanner scan = new Scanner(orderString);
		
		// add order numbers to list of orders for current check
		while (scan.hasNext()) {
			orders.add(scan.nextInt());
		}
		
		// close scanner
		scan.close();
	}
	
	/**
	 * converts check into a formatted string containing all orders
	 */
	@Override
	public String toString() {
		
		// initialize formatted return string
		String returnString = "Orders: ";
		
		// loop through orders and concatenate them onto return string
		for (int i : orders) {
			returnString += i + " ";
		}
		
		// return formatted order string
		return returnString;
	}
}
