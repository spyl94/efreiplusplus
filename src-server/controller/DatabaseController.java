package controller;

import java.sql.*;

public class DatabaseController 
{
	private String url = "jdbc:sqlite:efrei.db";
	private static Connection connect;
	
	private DatabaseController(){
		try {
			connect = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		if(connect == null){
			new DatabaseController();
		}
		return connect;
	}
	
  public static void main( String args[] ) throws ClassNotFoundException 
  {
	  	// load the sqlite-JDBC driver using the current class loader
	    Class.forName("org.sqlite.JDBC");
	    Connection connection = DatabaseController.getConnection();
	    
	    
	    
	    
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:efrei.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      //statement.executeUpdate("drop table student");
	      statement.executeUpdate("create table student (sid integer PRIMARY KEY AUTOINCREMENT, name string)");
	      statement.executeUpdate("create table teacher (tid integer PRIMARY KEY AUTOINCREMENT, name string)");
	      statement.executeUpdate("create table major (mid integer PRIMARY KEY AUTOINCREMENT, name string)");
	      statement.executeUpdate("create table course (cid integer PRIMARY KEY AUTOINCREMENT, name string)");
	      
	      
	      
	      statement.executeUpdate("insert into student (name) values('leo')");
	      //statement.executeUpdate("insert into person values(2, 'yui')");
	      ResultSet rs = statement.executeQuery("select * from student");
	      while(rs.next())
	      {
	        // read the result set
	        System.out.println("name = " + rs.getString("name"));
	        System.out.println("sid = " + rs.getInt("sid"));
	      }
	    }
	    catch(SQLException e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
  }
}
