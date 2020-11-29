package cheatcardgame;

import java.util.*;
import java.io.*;
import java.sql.*;

public class Databases
{
	private Connection conn;
	//Add any other data fields you like – at least a Connection object is mandatory
	public void setConnection(String fn) throws IOException 
	{
		//Add your code here
		//Add your code here
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(fn);
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

	public Connection getConnection()
	{
		return conn;
	}

	public ArrayList<String> query(String query) 
	{
		//Add your code here
		ArrayList<String> result = new ArrayList<String>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			int i = 0;
			if (query.contains("user")) {
				while(rs.next()) 
				{
					result.add(rs.getString(1) + "," + rs.getString(2));
					if (rs.getString(1) == null)
						return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void executeDML(String dml) throws SQLException
	{
		//Add your code here
		Statement stmt=conn.createStatement();
		//Execute a DML statement
		stmt.execute(dml);
		conn.close();
	}

}
