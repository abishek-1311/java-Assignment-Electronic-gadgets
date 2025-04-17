package util;
import java.sql.*;

public class DBConnection {
	private  static Connection  con ;
	
	 
	public static Connection getConnection() throws SQLException
	{
		con=DriverManager.getConnection(PropertyUtil.getPropertyString());
		System.out.println("connection Done");
		return con;
	}
}
