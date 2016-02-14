package database;

import java.util.ArrayList;

public class Room {
	static int ID = -1;
	int key;
	String name;			//1
	String type;			//3
	String description;		//4
	double defaultPrice;	//5
	int numBeds;			//6
	int number;				//2
	boolean nonSmoke;		//7
	
	ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	
	Room(String name, int number){
		this.name = name;
		this.number = number;
		key = ++ID;
	}
}
