package BankApp;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		PrintWriter out=response.getWriter();
		out.print("<h2>First name: "+request.getParameter("firstname")+"</h2>");
		out.print("<h2>Last name : "+request.getParameter("lastname")+"</h2>");
		out.print("<h2>Street    : "+request.getParameter("street")+"</h2>");
		out.print("<h2>City      : "+request.getParameter("city")+"</h2>");
		out.print("<h2>State     : "+request.getParameter("state")+"</h2>");
		out.print("<h2>Zip code  : "+request.getParameter("zip")+"</h2>");
		out.print("<h2>Email     : "+request.getParameter("email")+"</h2>");
		out.print("<h2>Username  : "+request.getParameter("username")+"</h2>");
		
	}

}
