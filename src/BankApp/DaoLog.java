package BankApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DaoLog {
	
	public static boolean validate(String username,String password){  
		boolean status=false; 
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
		PreparedStatement statement=connection.prepareStatement(  
				"SELECT * FROM CLIENT WHERE USERNAME=? and PASSWORD=?");   
		statement.setString(1,username);  
		statement.setString(2,password);  
		      
		ResultSet rs=statement.executeQuery();  
		status=rs.next();  
		          
		}catch(Exception e){System.out.println(e);}  
		return status;  
		}  
	

}
