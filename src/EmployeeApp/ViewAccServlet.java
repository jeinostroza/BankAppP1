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

/**
 * Servlet implementation class ViewAccServlet
 */
public class ViewAccServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAccServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		try {
			response.setContentType("html/text");
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			PreparedStatement ps = connection.prepareStatement("SELECT ID_CLIENT, FIRST_NAME, LAST_NAME, STREET, CITY, ZIP_CODE, STATE, USERNAME, EMAIL, M_INCOMES, M_EXPENSES, CH_ACC, SV_ACC, APPLY FROM Client");
			
			ResultSet rs = ps.executeQuery();
//			JsonObject jsonResponse = new JsonObject();
//			JsonArray data = new JsonArray();
			
			List<Clients> clientlist = new ArrayList<Clients>();
			
//			Map<String, String>clientMap = new HashMap<String, String>();
			
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
				client.setEmail(rs.getString("EMAIL"));
				client.setM_incomes(rs.getDouble("M_INCOMES"));
				client.setM_expenses(rs.getDouble("M_EXPENSES"));
				client.setCh_acc(rs.getString("CH_ACC"));
				client.setSv_acc(rs.getString("SV_ACC"));
				client.setApply(rs.getString("APPLY"));
				
		
			clientlist.add(client);

		}	
			
		Gson gson = new Gson();
	
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(gson.toJson(clientlist));
		
		}catch(Exception e){
		   e.printStackTrace();
	   }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
