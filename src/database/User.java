package database;

import java.util.ArrayList;

public class User {
	String firstName;
	String lastName;
	double balance;
	String address; // keeping it stupidly simple..
	ArrayList<Reservation> reservations = new ArrayList<Reservation>(); // This will hold redundant copies of all reservations - definitely not best practice...
	
	public User(String firstName, String lastName, String address){
		balance = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
}
