package database;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Reservation { // Reservations are assumed to be roughly 24 hour periods, established by hotel (i.e. 8AM is moveout, 10AM is movein)
	Date dateReservered;
	LocalDate startDate;
	LocalDate endDate;
	Period period;
	String firstName;
	String lastName;
	double cost; // USD
	
	Reservation(String first, String last, LocalDate start, LocalDate end){
		dateReservered = new Date();
		firstName  = first;
		lastName = last;
		startDate = start;
		endDate = end;
		this.period = Period.between(start, end);
	}
	
	public String toString(){
		return ("checkin: " + startDate + " checkout: " + endDate);
		
	}
}
