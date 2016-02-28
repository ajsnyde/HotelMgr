package hotelSim;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
	
	boolean newUser(String firstName, String lastName, String username, String password, String phoneNum,
			String email, double balance) {
		String insertTableSQL = "INSERT INTO Customers"
				+ "(FirstName, LastName, balance, username, password, phoneNum, Email) VALUES"
				+ "(?,?,?,?,?,?,?)";
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = dbCon.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setDouble(3, balance);
			preparedStatement.setString(4, username);
			preparedStatement.setString(5, String.valueOf(password));
			preparedStatement.setString(6, phoneNum);
			preparedStatement.setString(7, email);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	boolean newReservation(String username, String roomType, int roomNum, LocalDate startDate, LocalDate endDate, long numDays, double pricePerNight, String notes) {
		
		String insertTableSQL = "INSERT INTO Reservations"
				+ "(StartDate, EndDate, Cost, Notes, CustomerID, RoomID, Days, Paid) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		int userID = getUserID(username);
		if(userID == -1)	// user not found
			return false;
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = dbCon.prepareStatement(insertTableSQL);
			preparedStatement.setDate(1, Date.valueOf(startDate));
			preparedStatement.setDate(2, Date.valueOf(endDate));
			preparedStatement.setDouble(3, (numDays*pricePerNight));
			preparedStatement.setString(4, notes);
			preparedStatement.setInt(5, getUserID(username));
			preparedStatement.setInt(6, roomNum);
			preparedStatement.setLong(7, numDays);
			preparedStatement.setBoolean(8, false);
			preparedStatement.executeUpdate();
			
			modifyUserBalance(userID, -numDays*pricePerNight);
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	boolean modifyUserBalance(int customerID, double change) {
		String changeBalance = "UPDATE Customers SET Balance = Balance + ? WHERE CustomerID = ?";
				
		PreparedStatement preparedStatement;
		try {
			preparedStatement = dbCon.prepareStatement(changeBalance);
			preparedStatement.setDouble(1, change);
			preparedStatement.setInt(2, customerID);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	int getUserID(String username) {
		String selectUserSQL = "SELECT CustomerID FROM Customers WHERE Username = ?";
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = dbCon.prepareStatement(selectUserSQL);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	void executeQuery(String query){
		try {
		//dbCon = DriverManager.getConnection("jdbc:ucanaccess://src/database/hotel.accdb"); // dunno when connection closes automatically
		statement = dbCon.createStatement();
		ResultSet results = statement.executeQuery(query);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Oh snap! The SQL statement blew something up!");
		e.printStackTrace();
	}
	}
}
