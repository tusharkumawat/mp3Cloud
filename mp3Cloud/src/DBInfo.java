import java.sql.*;
public class DBInfo
{
  static
  {
	  try
	  {
	  //Load the correct driver
		Class.forName("com.mysql.jdbc.Driver");
	  }
	  catch(ClassNotFoundException e)
	  {
		  e.printStackTrace();
	  }
  }
  public static Connection getConn()
  {
	  Connection con=null;
	  try
	  {
		
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mp3Cloud", "root", "rat");
			
	  }
	  catch(SQLException e)
	  {
		  e.printStackTrace();
	  }
	  return con;
  }
}
