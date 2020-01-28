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

import BankApp.Transactions;

/**
 * Servlet implementation class EmplByClientSvServlet
 */
public class EmplByClientSvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmplByClientSvServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		       
//		String username = request.getParameter("username");
		
		String username = "yerios";
		
		System.out.println("Username: "+ username);
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			PreparedStatement ps = connection.prepareStatement("SELECT ID_TRANS, DESCRIPTION_TRANS, AMOUNT_TRANS, ACC_NUMBER, USERNAME FROM TRANSACTION WHERE USERNAME= '"+username+"' AND ACC_NUMBER LIKE 'SV%'");
			
			ResultSet rs = ps.executeQuery();
			
			List<Transactions> transactionlist = new ArrayList<Transactions>();
			
			while(rs.next()) {
				
				Transactions trans = new Transactions();
				trans.setId_trans(rs.getInt("ID_TRANS"));
				trans.setDescription_trans(rs.getString("DESCRIPTION_TRANS"));
				trans.setAmount_trans(rs.getDouble("AMOUNT_TRANS"));
				trans.setAcc_number(rs.getString("ACC_NUMBER"));
				
				
				transactionlist.add(trans);
			}
			
			Gson gson = new Gson();
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(gson.toJson(transactionlist));
			
		}catch(Exception e){
			   e.printStackTrace();
		   }
		
	}

	

}