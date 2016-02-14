package database;

import java.time.Period;
import java.util.Date;

public class Reservation { // Reservations are assumed to be roughly 24 hour periods, established by hotel (i.e. 8AM is moveout, 10AM is movein)
	Date dateReservered;
	Period period;
	String firstName;
	String lastName;
	double cost; // USD
	
	Reservation(String first, String last, Period period){
		dateReservered = new Date();
		firstName  = first;
		lastName = last;
		this.period = period;
	}
}
