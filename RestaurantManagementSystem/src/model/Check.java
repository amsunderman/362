package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Check {
	ArrayList<Integer> orders = new ArrayList<Integer>();
	
	public Check(String orderString) {
		Scanner scan = new Scanner(orderString);
		while (scan.hasNext()) {
			orders.add(scan.nextInt());
		}
		scan.close();
	}
	
	
	public String toString() {
		String returnString = "Orders: ";
		for (int i : orders) {
			returnString += i + " ";
		}
		return returnString;
	}
}
