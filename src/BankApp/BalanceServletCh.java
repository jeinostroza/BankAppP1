package BankApp;

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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class BalanceServletCh
 */
public class BalanceServletCh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceServletCh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		
		HttpSession session=request.getSession(false);
		String username = (String)session.getAttribute("username");
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			PreparedStatement ps = connection.prepareStatement("SELECT SUM(AMOUNT_TRANS) FROM TRANSACTION WHERE ACC_NUMBER LIKE 'CH%'");
			
			ResultSet rs = ps.executeQuery();
			
			double balanceCh = 0;
			
			while(rs.next()) {
				balanceCh=rs.getDouble("SUM");
			}
			
			Gson gson = new Gson();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(gson.toJson(balanceCh));
			
			
		}catch(Exception e){
			   e.printStackTrace();
		   }
		
	}

	
}
