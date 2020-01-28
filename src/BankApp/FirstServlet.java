package BankApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FirstServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("username");
		String p=request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setAttribute("username", n);
		
		if(DaoLog.validate(n, p)){
			RequestDispatcher rd=request.getRequestDispatcher("user.html");
			rd.forward(request,response);
			Cookie ck = new Cookie("username", n);
			response.addCookie(ck);

		}
		else{
			
			RequestDispatcher rd=request.getRequestDispatcher("reject.html");
			rd.include(request,response);
		}
		
		out.close();
	}

}
