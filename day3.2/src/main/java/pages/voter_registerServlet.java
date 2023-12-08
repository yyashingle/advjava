package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.sql.Date.valueOf;
import dao.UserDaoImpl;
import pojos.User;

@WebServlet("/voter_register")
public class voter_registerServlet extends HttpServlet {

	private UserDaoImpl userDao;

	public void init() throws ServletException {
		try {
			userDao = new UserDaoImpl();
		}catch(Exception e) {
			throw new ServletException("in init"+getClass(),e);
		}
	}


	public void destroy() {
		try {
			// clean up dao
			userDao.cleanUp();
		} catch (Exception e) {
			System.out.println("err in destroy of " + getClass() + " " + e);
			// how to inform the WC ?
			// throw new RuntimeException("err in destroy of "+getClass(), e);
		}
		
	}
//
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			String firstName = request.getParameter("fn");
			String LastName = request.getParameter("ln");
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			Date date = valueOf(request.getParameter("dob"));
		    String user=userDao.registerNewVoter(new User(firstName,LastName,email,password,date));
		    pw.print(user);
		    
		    if(user.equals("New voter details inserted....")) {
		    	response.sendRedirect("/day3.2");
		    }else {
		    	
		    }
		    
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
	}

}
