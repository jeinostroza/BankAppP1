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
 * Servlet implementation class TransferServlet
 */
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
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

		//++++++++++++++++++++++++++++++WITHDRAW ACCOUNT+++++++++++++++++++++++++++++++++++++++++++		
		response.setContentType("text/html");
		String acctypew = request.getParameter("acctrwit");
		String acctypew1 = acctypew;
		System.out.println(acctypew1);
		boolean test = (acctypew1 == "ch_acc");
		System.out.println(test);
		double awtr = Double.parseDouble(request.getParameter("amounttr"));
		System.out.println(request.getParameter("amounttr"));
		awtr = (awtr*-1);
		String acctyped = request.getParameter("acctrdep");
		Double adtr = Double.parseDouble(request.getParameter("amounttr"));
		String accNum="";
		String accNum2="";
		
		HttpSession session=request.getSession(false);
		String username = (String)session.getAttribute("username");
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		if(acctypew == "ch_acc") {
			
			try{  			
				
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
				Statement statement1=connection.createStatement();				
				String sql="SELECT CH_ACC FROM client WHERE USERNAME= '"+username+"'";
				ResultSet rs = statement1.executeQuery(sql);
				while(rs.next()) {
					accNum = rs.getString(acctypew);	
					
				}
				System.out.println("paso withdraw"+ accNum);
				Statement statement2=connection.createStatement();
				String sql2="SELECT SV_ACC FROM client WHERE USERNAME= '"+username+"'";			
				ResultSet rs2 = statement2.executeQuery(sql2);				
				rs.close();
				while(rs2.next()) {
					accNum2 = rs2.getString(acctyped);
				}
				System.out.println("Paso Deposit" + accNum2);
				          
				}catch(SQLException | ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				} 
				try {
					
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
					String sql="INSERT INTO transaction (amount_trans, acc_num) values (?,?)";
					PreparedStatement ps=connection.prepareStatement(sql);
					
					ps.setDouble(1,awtr);
					ps.setString(2,accNum);
					ps.execute();
					
					String sql2="INSERT INTO transaction (amount_trans, acc_num) values (?,?)";
					PreparedStatement ps2=connection.prepareStatement(sql2);
					ps2.setDouble(1,adtr);
					ps2.setString(2,accNum2);
					ps.execute();
					
				}catch(SQLException | ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				} 
		
		}else {
			try{  
	
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
		Statement statement1=connection.createStatement();	
		String sql="SELECT CH_ACC FROM client WHERE USERNAME= '"+username+"'";
		ResultSet rs = statement1.executeQuery(sql);
		
		while(rs.next()) {
			accNum = rs.getString(acctypew);	
			
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
			String sql="INSERT INTO transaction (amount_trans, acc_num) values (?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ps.setDouble(1,awtr);
			ps.setString(2,accNum);
			
			
			ps.execute();
			
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} 
		}	
			
		RequestDispatcher rd=request.getRequestDispatcher("confirmtransf.html");
		rd.include(request,response);
		
	}
		
	
		//++++++++++++++++++++++++++++++END WITHDRAW ACCOUNT+++++++++++++++++++++++++++++++++++++++

	
	
	}
	
	
	


