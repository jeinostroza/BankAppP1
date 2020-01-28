package EmployeeApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewClients
 */
public class NewClients extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewClients() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		boolean status = false;
		String username;
		
		username = request.getParameter("approvedClient");
		
	
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		Random Num1 = new Random();
		Random Num2 = new Random();
		String ch_acc = "CH" + (Integer.toString(1 + Num1.nextInt(10000000))); 
		String sv_acc = "SV" + (Integer.toString(1 + Num2.nextInt(10000000)));
		
		
		try{  
		String applyyes = "Y";
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
		
		PreparedStatement statement=connection.prepareStatement(  
				"UPDATE client SET APPLY= ?, CH_ACC=?, SV_ACC=? WHERE username = '"+username+"'");   
		
		statement.setString(1, applyyes);
		statement.setString(2, ch_acc);
		statement.setString(3, sv_acc);
		
		
		ResultSet rs=statement.executeQuery();
		status=rs.next();
		
		connection.close();
		
		
		
		          
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
	}
		RequestDispatcher rd=request.getRequestDispatcher("approveacc.html");
		rd.include(request,response);
}
}
