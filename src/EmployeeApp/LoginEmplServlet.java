package EmployeeApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginEmplServlet
 */
public class LoginEmplServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginEmplServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw= response.getWriter();
		response.setContentType("text/html");

		String user=request.getParameter("username");
		String pass=request.getParameter("password");

		if(user.equals("employee")&&pass.equals("123456")) {
				RequestDispatcher rd=request.getRequestDispatcher("employee.html");
				rd.forward(request,response);
				HttpSession session = request.getSession();
				session.setAttribute("user_empl", user);
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("rejectemp.html");
			rd.forward(request,response);
			HttpSession session = request.getSession();
			session.setAttribute("rejectemp.html", user);
			pw.close();
		}
		

	}
}
