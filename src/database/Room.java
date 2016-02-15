package database;

import java.time.Period;
import java.util.ArrayList;

public class Room {
	static int ID = -1;
	int key;
	public String name; // 1
	String type; // 3
	String description; // 4
	double defaultPrice; // 5
	int numBeds; // 6
	public int number; // 2
	boolean nonSmoke; // 7

	ArrayList<Reservation> reservations = new ArrayList<Reservation>();

	public Room(String name, int number) {
		this.name = name;
		this.number = number;
		key = ++ID;
	}

	boolean addReservation(Reservation resIn) {
		if (reservationValid(resIn)) {
			reservations.add(resIn);
			System.out.println("Added: " + resIn);
			return true;
		} else
			return false;
	}

	boolean reservationValid(Reservation resIn) {
		for (Reservation res : reservations) {
			// Going to go back to this and define what exactly is legal, and how check-ins and check-outs work..
			// (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1) dateRanges
			// overlap if this is true
			//System.out.println("Comparing " + resIn + " and " + res);
			if (	(res.startDate.isBefore(resIn.endDate) 
					/*|| res.startDate.equals(resIn.endDate)*/) 
					&& (resIn.startDate.isBefore(res.endDate) 
					/*|| resIn.startDate.equals(res.endDate)*/ 
					|| Period.between(resIn.startDate, resIn.endDate).isZero() 
					|| resIn.startDate.equals(res.endDate) 
					|| Period.between(resIn.startDate, resIn.endDate).isNegative())){
				
				System.out.println("Invalid Range: " + resIn);
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return name;
	}
}
