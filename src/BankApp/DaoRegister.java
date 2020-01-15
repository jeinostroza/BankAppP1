package BankApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DaoRegister
 */
public class DaoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DaoRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		boolean status = false;
		String firstname, lastname, street, city, state, zip, email, username, password;
		
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		street = request.getParameter("street");
		city = request.getParameter("city");
		state = request.getParameter("state");
		zip = request.getParameter("zip");
		username = request.getParameter("username");
		password = request.getParameter("pass");
		email = request.getParameter("email");
	
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
		
		PreparedStatement statement=connection.prepareStatement(  
				"INSERT INTO client (first_name,last_name,street,city, zip_code, state, username,password, email) VALUES (?,?,?,?,?,?,?,?,?)");   
		 
		statement.setString(1, firstname);
		statement.setString(2, lastname);
		statement.setString(3, street);
		statement.setString(4, city);
		statement.setString(5, zip);
		statement.setString(6, state);
		statement.setString(7, username);
		statement.setString(8, password);
		statement.setString(9, email);
		
		ResultSet rs=statement.executeQuery();
		status=rs.next();
		
		connection.close();
		
		
		
		          
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} 
		
		RequestDispatcher rd=request.getRequestDispatcher("confirmation.html");
		rd.include(request,response);
	}

}
