package BankApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Dep_ch_servlet
 */
public class Dep_ch_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dep_ch_servlet() {
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
		String ch_dep_desc = request.getParameter("ch_dep_desc");
		String ch_dep_amount = request.getParameter("ch_dep_amount");
		double dep_amount = Double.valueOf(ch_dep_amount);
		String accNum="";
		HttpSession session=request.getSession(false);
		String username = (String)session.getAttribute("username");
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			Statement statement1=connection.createStatement();	
			String sql="SELECT CH_ACC FROM client WHERE USERNAME= '"+username+"'";
			ResultSet rs = statement1.executeQuery(sql);
			
			while(rs.next()) {
				accNum = rs.getString("ch_acc");	
				
			}
			rs.close();
			statement1.close();
			connection.close();
			
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			String sql="INSERT INTO transaction (DESCRIPTION_TRANS, AMOUNT_TRANS, ACC_NUMBER, USERNAME) values (?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ps.setString(1,ch_dep_desc);
			ps.setDouble(2,dep_amount);
			ps.setString(3,accNum);			
			ps.setString(4,username);
			
			ps.execute();
			
			
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("confirm_ch_dep.html");
		rd.include(request,response);
	}

}
