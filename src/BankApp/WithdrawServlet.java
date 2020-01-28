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
 * Servlet implementation class WithdrawServlet
 */
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServlet() {
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
//		PrintWriter out = response.getWriter();
		String acctypew = request.getParameter("account");

		String witDescrip = request.getParameter("withdrawDesc");		
		String amountw = request.getParameter("withdrawamount");
		double amountwit = Double.parseDouble(amountw)*-1;
		String accNum="";
	
		HttpSession session=request.getSession(false);
		String username = (String)session.getAttribute("username");
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		if(acctypew == "ch_acc") {
			//+++++++++++++++++++++++++++++++++++++++++++++
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
					String sql="INSERT INTO transaction (amount_trans, acc_num, Description) values (?,?,?)";
					PreparedStatement ps=connection.prepareStatement(sql);
					
					ps.setDouble(1,amountwit);
					ps.setString(2,accNum);
					ps.setString(3,witDescrip);
					
					ps.execute();
					
				}catch(SQLException | ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				} 
				
			
					//+++++++++++++++++++++++++++++++++++++++++++++
			
		
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
			String sql="INSERT INTO transaction (amount_trans, acc_num, Description) values (?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ps.setDouble(1,amountwit);
			ps.setString(2,accNum);
			ps.setString(3,witDescrip);
			
			ps.execute();
			
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} 
		}
		
		
			
		RequestDispatcher rd=request.getRequestDispatcher("confirdeposit.html");
		rd.include(request,response);
		
	}
	}


