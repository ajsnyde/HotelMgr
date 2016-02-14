package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Normally, I'd set up a MySQL database, but for ease of use and portability, this will do...

public class Database {
	ArrayList<Room> rooms = new ArrayList<Room>();
	
	public Database(){
		loadRooms("src/data/rooms.csv");
	}
	
	ArrayList<String> getRoomNames(){
		ArrayList<String> tempRooms = new ArrayList<String>();
		for(Room room: rooms)
			tempRooms.add(room.name);
		return tempRooms;
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
