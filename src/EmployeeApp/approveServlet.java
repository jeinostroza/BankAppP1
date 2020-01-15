package EmployeeApp;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;





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



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
//		String id_client = "", firstname = "", lastname = "", street = "", city = "", zip = "", state = "", username = "", m_incomes = "", m_expenses = "";

		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		try {
			response.setContentType("html/text");
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			PreparedStatement ps = connection.prepareStatement("SELECT ID_CLIENT, FIRST_NAME, LAST_NAME, STREET, CITY, ZIP_CODE, STATE, USERNAME, M_INCOMES, M_EXPENSES FROM Client WHERE M_INCOMES > 0 AND M_EXPENSES > 0");
			
			ResultSet rs = ps.executeQuery();
			JsonObject jsonResponse = new JsonObject();
			JsonArray data = new JsonArray();
			
			
			while(rs.next()) {
				JsonArray row = new JsonArray();
				row.add(new JsonPrimitive(rs.getInt("ID_CLIENT")));
				row.add(new JsonPrimitive(rs.getString("FIRST_NAME")));
				row.add(new JsonPrimitive(rs.getString("LAST_NAME")));
				row.add(new JsonPrimitive(rs.getString("STREET")));
				row.add(new JsonPrimitive(rs.getString("CITY")));		
				row.add(new JsonPrimitive(rs.getString("ZIP_CODE")));
				row.add(new JsonPrimitive(rs.getString("STATE")));
				row.add(new JsonPrimitive(rs.getString("USERNAME")));
				row.add(new JsonPrimitive(rs.getString("M_INCOMES")));
				row.add(new JsonPrimitive(rs.getString("M_EXPENSES")));	
				data.add(row);
				
			}
			
			jsonResponse.add("ResponseData", data);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			out.print(jsonResponse);
			out.flush();
			System.out.println(jsonResponse);
			
			
			try {
		         FileWriter file = new FileWriter("client.json");
		         file.write(jsonResponse.toString());
		         file.close();
		      } catch (IOException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		      System.out.println("JSON file created......");
			
/*			while(rs.next()) {
				String id_client = rs.getString("ID_CLIENT");
				firstname = rs.getString("first_name");
				lastname = rs.getString("last_name");
				street = rs.getString("street");
				city = rs.getString("city");
				zip = rs.getString("zip_code");
				state = rs.getString("state");
				username = rs.getString("username");
				m_incomes = Double.toString(rs.getDouble("m_incomes"));
				m_expenses = Double.toString(rs.getDouble("m_expenses"));
				
				client.put("id_client", id_client);
				client.put("firstname",firstname);
				client.put("lastname", lastname);
				client.put("street", street);				
				client.put("city", city);
				client.put("zip", zip);
				client.put("state", state);
				client.put("username", username);
				client.put("m_incomes",m_incomes);
				client.put("m_expenses", m_expenses);
				
				System.out.println(client);
				System.out.println(client.get("firstname"));
				
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				String jsonClient = gson.toJson(client);
				System.out.println(jsonClient);*/
				
				
				/*
				 * System.out.println(firstname); System.out.println(lastname);
				 * System.out.println(street); System.out.println(city);
				 * System.out.println(zip); System.out.println(state);
				 * System.out.println(username);
				 */
			
			
		
		
		}catch(Exception e){
		   e.printStackTrace();
	   }
		
	}

}
