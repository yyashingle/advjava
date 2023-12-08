package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.User;

/**
 * Servlet implementation class CandidateListPage
 */
@WebServlet("/candidate_list")
public class CandidateListPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5> In candidate list page </h5>");
			// retrieve clnt details from the HttpSession
			// 1. Get HS from WC
			HttpSession hs = request.getSession();
			System.out.println("session is new " + hs.isNew());// f
			System.out.println("session id " + hs.getId());// SAME

			// 2. Fetch user details from HttpSession
			User userDetail = (User) hs.getAttribute("user_info");
			if (userDetail != null)
				// send voter details to the clnt
				pw.print("<h5>" + userDetail + "</h5>");

			else // => no cookies
				pw.print("<h5> No Cookies , Session Tracking failed!!!!!</h5>");

		}
	}

}
