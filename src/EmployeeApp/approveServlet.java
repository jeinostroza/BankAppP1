package EmployeeApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import EmployeeApp.Clients;

/**
 * Servlet implementation class approveServlet
 */
public class approveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public approveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();

		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		try {
			response.setContentType("text/html");
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			PreparedStatement ps = connection.prepareStatement("SELECT ID_CLIENT, FIRST_NAME, LAST_NAME, STREET, CITY, ZIP_CODE, STATE, USERNAME, M_INCOMES, M_EXPENSES FROM Client WHERE M_INCOMES > 0 AND M_EXPENSES > 0 AND APPLY = 'N'");
			
			ResultSet rs = ps.executeQuery();
			
			List<Clients> clientlist = new ArrayList<Clients>();
			

			
			while(rs.next()){
				
				Clients client = new Clients();
				client.setId_client(rs.getInt("ID_CLIENT")); 
				client.setFirstname(rs.getString("FIRST_NAME"));
				client.setLastname(rs.getString("LAST_NAME"));
				client.setStreet(rs.getString("STREET"));
				client.setCity(rs.getString("CITY"));
				client.setZip(rs.getString("ZIP_CODE"));
				client.setState(rs.getString("STATE"));
				client.setUsername(rs.getString("USERNAME"));
				client.setM_incomes(rs.getDouble("M_INCOMES"));
				client.setM_expenses(rs.getDouble("M_EXPENSES"));

				
				
		
			clientlist.add(client);
;
		}	
			
		Gson gson = new Gson();
	
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(gson.toJson(clientlist));
		
		}catch(Exception e){
		   e.printStackTrace();
	   }
		
	}
}
	
	
		


