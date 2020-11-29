package cheatcardgame;

import java.util.*;
import java.io.*;
import java.sql.*;


public class Database {

	private Connection conn;
	//Add any other data fields you like – at least a Connection object is mandatory

	public Database() throws IOException {
		//Add your code here
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("cheatcardgame/db.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");
		try {
			conn = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// use query statement and parse data
	public ArrayList<String> query(String query) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				result.add(rs.getString(1) + "," + rs.getString(2));
				if (rs.getString(1) == null)
					return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// executes a dml statement and inserts sql code into database
	public void executeDML(String dml) throws SQLException {
		Statement stmt=conn.createStatement();
		//Execute a DML statement
		stmt.execute(dml);
		conn.close();
	}

	// checks login, if username and password match database return true
	public boolean checkLogin(String username, String password) {
		ArrayList<String> result = query("SELECT username, aes_decrypt(password, 'key') FROM users");
		for (int i = 0; i < result.size()-1; i++) {
			if (result.get(i).split(",")[0].equals(username)) {
				if (result.get(i).split(",")[1].equals(password)) {
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}

	// checks if username is in use and if not creates new account
	public boolean createNewAccount(String username, String password) {
		ArrayList<String> result = query("SELECT username, aes_decrypt(password, 'key') FROM users");
		for (int i = 0; i < result.size()-1; i++) {
			if (result.get(i).split(",")[0].equals(username)) 
				return false;
		}
		String dml = "INSERT INTO users VALUES('" + username + "', aes_encrypt('" + password + "','key'))";
		try {
			executeDML(dml);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}

