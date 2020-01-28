package BankApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class OpenServlet
 */
public class OpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenServlet() {
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
		PrintWriter out = response.getWriter();
		boolean status = false;
		double m_incomes, m_expenses;
		
		
		
		m_incomes = Double.parseDouble(request.getParameter("monthlyIncomes"));
		m_expenses = Double.parseDouble(request.getParameter("monthlyExpenses"));
		String applys = "N";	
		String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
		String c_user="java";
		String c_pass= "java";
		
		try{ 
		HttpSession session=request.getSession(false);
		String username = (String)session.getAttribute("username");
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection connection = DriverManager.getConnection(myDB, c_user, c_pass);
		
		PreparedStatement statement=connection.prepareStatement(  
				"UPDATE Client SET M_INCOMES='"+m_incomes+"', M_EXPENSES='"+m_expenses+"', APPLY='"+applys+"' WHERE USERNAME ='"+username+"'");   
		 
		
		
		ResultSet rs=statement.executeQuery();
		status=rs.next();
		
		connection.close();
		
		
		
		          
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} 
		
		RequestDispatcher rd=request.getRequestDispatcher("openconfir.html");
		rd.include(request,response);
	
	}

}
