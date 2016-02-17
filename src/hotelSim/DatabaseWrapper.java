package hotelSim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseWrapper {
	Statement statement;
	Connection dbCon;
	
	DatabaseWrapper(){	
		try {
		dbCon = DriverManager.getConnection("jdbc:ucanaccess://src/database/hotel.accdb");
		//dbCon.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	ArrayList<ArrayList<String>> getQueryResults(String query){
			ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
			try {
			//dbCon = DriverManager.getConnection("jdbc:ucanaccess://src/database/hotel.accdb"); // dunno when connection closes automatically
			statement = dbCon.createStatement();
			ResultSet results = statement.executeQuery(query);
			
			for(int j = 0; results.next(); ++j) {
				output.add(new ArrayList<String>());
				for(int i = 1; i-1 < results.getMetaData().getColumnCount(); ++i) {
					output.get(j).add(results.getString(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Oh snap! The SQL statement blew something up!");
			e.printStackTrace();
		}
		return output;
	}
}
