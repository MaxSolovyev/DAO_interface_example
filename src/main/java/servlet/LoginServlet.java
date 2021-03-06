package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserService service = UserServiceImpl.getInstance();

	private boolean isInvalid;

	public LoginServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if (isInvalid) {
			request.setAttribute("isNotLogin", true);
			isInvalid = false;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if (login.isEmpty() || password.isEmpty()) {
			isInvalid = true;
			response.sendRedirect("login");
			return;
		}

		User user = service.getUserByName(login);

		if (user == null) {
			isInvalid = true;
			response.sendRedirect("login");
			return;
		}

		if (user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.setContentType("text/html");
			if (user.getRole().equals("admin")) {
				response.sendRedirect("/admin");
				return;
			}
			response.sendRedirect("/user");
		} else {
			isInvalid = true;
			response.sendRedirect("login");
		}
	}
}
