package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

// Normally, I'd set up a MySQL database, but for ease of use and portability, this will do...

public class Database {
	ArrayList<Room> rooms = new ArrayList<Room>();
	
	public Database(){
		loadRooms("src/data/rooms.csv");
		/////////////////////TESTS////////////////////////
	
		rooms.get(0).addReservation(new Reservation("New", "User", LocalDate.of(1,1,1), LocalDate.of(1,1,2)));
		//rooms.get(0).reservations.add(new Reservation("New", "User", LocalDate.of(2016, 5, 15), LocalDate.of(2016, 5, 16)));
		rooms.get(0).addReservation(new Reservation("New", "User", LocalDate.of(1,1,2), LocalDate.of(1,1,3)));
		for(Reservation res : rooms.get(0).reservations)
			System.out.println(res);
	}
	
	public ArrayList<String> getRoomNames(){
		ArrayList<String> tempRooms = new ArrayList<String>();
		for(Room room: rooms)
			tempRooms.add(room.name);
		return tempRooms;
	}
	
	public ArrayList<Room> getRooms(){
		ArrayList<Room> tempRooms = new ArrayList<Room>();
		for(Room room: rooms)
			tempRooms.add(room);
		return tempRooms;
	}

	boolean checkReservations(Room room, LocalDate dateStart, LocalDate dateEnd){
		if(room.reservationValid(new Reservation("New", "User", dateStart, dateEnd)))
			return true;
		return false;
	}
	
	void loadRooms(String fileName){ // mostly just copied - super easy
		Room room;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "	";

		try {
			br = new BufferedReader(new FileReader(fileName));
			br.readLine(); // allow for a header - could've used a fancy API, but I don't really need too much...
			while ((line = br.readLine()) != null) {
				String[] in = line.split(cvsSplitBy);
				
				room = new Room(in[0], Integer.parseInt(in[1]));
				room.type = in[2];
				room.description = in[3];
				room.defaultPrice = Integer.parseInt(in[4]);
				room.numBeds = Integer.parseInt(in[5]);
				room.nonSmoke = Boolean.parseBoolean(in[6]);
				rooms.add(room);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done");
	  }
	}
