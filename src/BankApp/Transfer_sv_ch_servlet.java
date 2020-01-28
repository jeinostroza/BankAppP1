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
 * Servlet implementation class Transfer_sv_ch_servlet
 */
public class Transfer_sv_ch_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer_sv_ch_servlet() {
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
		double amountd = Double.parseDouble(request.getParameter("amounttr"));
		double amountw = amountd*-1;
		String description = request.getParameter("descriptiontr");
		String accNum="";
		String accNum2="";
		HttpSession session=request.getSession(false);
		String username = (String)session.getAttribute("username");
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			Statement statement1=connection.createStatement();				
			String sql="SELECT CH_ACC, SV_ACC FROM client WHERE USERNAME= '"+username+"'";
			ResultSet rs = statement1.executeQuery(sql);
			while(rs.next()) {
				accNum = rs.getString("ch_acc");	
				accNum2 = rs.getString("sv_acc");				
			}	
			
			
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
			String sql="INSERT INTO transaction (description_trans, amount_trans, acc_number, username) values (?,?,?,?)";
			String sql2="INSERT INTO transaction (description_trans, amount_trans, acc_number, username) values (?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1,description);
			ps.setDouble(2,amountw);
			ps.setString(3,accNum);
			ps.setString(4,username);
			ps.execute();
			PreparedStatement ps2=connection.prepareStatement(sql2);
			ps2.setString(1,description);
			ps2.setDouble(2,amountd);
			ps2.setString(3,accNum2);
			ps2.setString(4,username);
			ps2.execute();
			
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
	}
		RequestDispatcher rd=request.getRequestDispatcher("confirmtransf.html");
		rd.include(request,response);

	}
	
}
